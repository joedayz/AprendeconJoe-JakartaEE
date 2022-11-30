package pe.joedayz.samples.rest;

import jakarta.annotation.PostConstruct;

import jakarta.inject.Singleton;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.sse.Sse;
import jakarta.ws.rs.sse.SseBroadcaster;
import jakarta.ws.rs.sse.SseEventSink;


@Path("/chat")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Singleton
public class ChatResource {

  @Context
  private Sse sse;
  private SseBroadcaster sseBroadcaster;

  @PostConstruct
  public void init(){
    sseBroadcaster = sse.newBroadcaster();
  }

  @GET
  @Produces(MediaType.SERVER_SENT_EVENTS)
  public void startConnection(@Context SseEventSink eventSink){
    eventSink.send(sse.newEvent("welcome to chat!"));
    sseBroadcaster.register(eventSink);
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response sendMessage(@FormParam("message") String message){
    sseBroadcaster.broadcast(sse.newEvent(message));
    return Response.ok().build();
  }

}