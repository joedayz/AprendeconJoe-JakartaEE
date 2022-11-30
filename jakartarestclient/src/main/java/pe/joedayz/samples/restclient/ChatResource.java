package pe.joedayz.samples.restclient;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
public class ChatResource {

  @Inject
  private ChatService chatService;


  @GET
  @Produces(MediaType.TEXT_PLAIN)
  public Response startConnection(){
    chatService.startConnection();
    return Response.ok().build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response sendMessage(@FormParam("message") String message){
    chatService.sendMessage(message);
    return Response.ok().build();
  }

  @GET
  @Path("/messages")
  public Response listMessages(){
    return Response.ok(chatService.getMessages()).build();
  }
}
