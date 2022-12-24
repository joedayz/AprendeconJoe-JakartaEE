package pe.joedayz.samples.jakartaejb.endpoint;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.core.Response;
import pe.joedayz.samples.jakartaejb.singleton.AccessSingleton;

@Path("access")
public class AccessCountEndpoint {

  @Inject
  private AccessSingleton accessSingleton;


  @GET
  public Response count(){
    accessSingleton.increment();
    return Response.ok(accessSingleton.getCount()).build();
  }
}
