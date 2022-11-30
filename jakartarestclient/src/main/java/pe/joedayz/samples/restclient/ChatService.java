package pe.joedayz.samples.restclient;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.SessionScoped;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Form;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import jakarta.ws.rs.sse.SseEventSource;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@SessionScoped
public class ChatService implements Serializable {

  private static final String TARGET_SERVICE = "http://127.0.0.1:8080/jakartarest/api/chat";
  private SseEventSource sseEventSource;
  private List<String> messages;

  @PostConstruct
  public void init(){
    messages = new ArrayList<>();
  }

  public void startConnection(){
    Client client = ClientBuilder.newClient();
    WebTarget webTarget = client.target(TARGET_SERVICE);
    sseEventSource = SseEventSource.target(webTarget).build();
    sseEventSource.register(inboundSseEvent -> messages.add(inboundSseEvent.readData()));
    sseEventSource.open();
  }
  public boolean sendMessage(String message){
    Form form = new Form();
    form.param("message", message);
    Client client = ClientBuilder.newClient();
    Response response = client.target(TARGET_SERVICE).request().post(Entity.form(form));
    return response.getStatus() == Response.Status.OK.getStatusCode();
  }

  public void closeConnection(){
    sseEventSource.close();
  }

  public List<String> getMessages(){
    return messages;
  }
}
