package pe.joedayz.samples.restclient;

import jakarta.inject.Inject;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.InvocationCallback;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Future;
import java.util.logging.Logger;

public class UserService {

  private static final Logger logger = Logger.getLogger(UserService.class.getName());
  private static final String TARGET_SERVICE = "http://127.0.0.1:8080/jakartarest/api/users";

  @Inject
  EmailService emailService;

  public UserDto findById(String id){
    Client client = ClientBuilder.newClient();
    UserDto user = client.target(TARGET_SERVICE+"/"+id).request(MediaType.APPLICATION_JSON)
        .get(UserDto.class);
    // Builder.post()
    // Builder.head()
    // Builder.put()
    // Builder.delete()
    // Builder.options()
    // Builder.trace()
    // Builder.patch()
    return user;
  }

  public boolean save(UserDto user) {
    Form form = new Form();
    form.param("name", user.getName());
    form.param("email", user.getEmail());

    Client client = ClientBuilder.newClient();
    Response response = client.target(TARGET_SERVICE+"/").request().post(Entity.form(form));
    return response.getStatus() == Status.CREATED.getStatusCode();
  }

  public void saveAsyncCompletionStage(UserDto userDto, String subject, String message){
    Form form = new Form();
    form.param("name", userDto.getName());
    form.param("email", userDto.getEmail());

    Client client = ClientBuilder.newClient();
    CompletionStage<Response> completionStage = client.target(TARGET_SERVICE+"/").request()
        .rx().post(Entity.form(form));

    completionStage.thenAccept(response -> {
      if(response.getStatus()== Status.CREATED.getStatusCode()){
        emailService.send("system@test.test", userDto.getEmail(), subject, message);
        logger.info("User saved!");
      }else{
        logger.warning("The user had some issues to save!");
      }
    });
  }

  public Future saveAsync(UserDto userDto, String subject, String message){
    Form form = new Form();
    form.param("name", userDto.getName());
    form.param("email", userDto.getEmail());

    Client client = ClientBuilder.newClient();
    return client.target(TARGET_SERVICE+"/").request().async().post(Entity.form(form),
        new InvocationCallback<Response>() {
          @Override
          public void completed(Response response) {
            if(response.getStatus()== Status.CREATED.getStatusCode()){
              emailService.send("system@test.test", userDto.getEmail(), subject, message);
              logger.info("User saved!");
            }else{
              logger.warning("The user had some issues to save!");
            }
          }

          @Override
          public void failed(Throwable throwable) {
            logger.warning("The user had some issues to save!");
          }
        });
  }
}
