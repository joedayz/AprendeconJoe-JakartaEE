package pe.joedayz.samples.restclient;

import jakarta.inject.Inject;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/emails")
public class EmailResource {

  @Inject
  private UserService userService;

  @Inject
  private EmailService emailService;

  @POST
  @Path("/{idUser}")
  public Response send(@PathParam("idUser") String idUser,
        @DefaultValue("Hello") @FormParam("subject") String subject,
      @DefaultValue("Hello! Welcome!") @FormParam("message") String message){
    UserDto user = userService.findById(idUser);
    if(user==null){
      return Response.status(Status.NOT_FOUND).build();
    }
    emailService.send("system@test.test", user.getEmail(), subject, message);
    return Response.ok().build();
  }

  @POST
  public Response sendAndSaveUser(@FormParam("name") String name, @FormParam("email") String email,
      @DefaultValue("Hello") @FormParam("subject") String subject,
      @DefaultValue("Hello! Welcome!") @FormParam("message") String message){

      UserDto user = UserDto.of(name, email);

      userService.saveAsync(user, subject, message);

      // Call sync
//      if(userService.save(user)){
//        emailService.send("system@test.test", user.getEmail(), subject, message);
//        return Response.ok().build();
//      }
      //return Response.serverError().build();

      return Response.ok().build();
  }
}
