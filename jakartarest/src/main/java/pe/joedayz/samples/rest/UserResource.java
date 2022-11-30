package pe.joedayz.samples.rest;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.net.URI;
import java.util.Collections;
import java.util.Optional;

@Path("/users")
public class UserResource {


  @Inject
  private UserDatasource userDatasource;

  // http://{host}:{port}/{context-root}/api/{resource-path}
  //ex: // http://localhost:8080/jakartarest/api/users
  @GET  // POST, PUT, DELETE, HEAD, OPTIONS, PATCH
  // @QueryParameter, @PathParameter, @Consumes, @Produces
  @Produces(APPLICATION_JSON)
  public Response list(){
    //return Response.ok(Collections.EMPTY_LIST).build();
    return Response.ok(userDatasource.listAll()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
  public Response save(@FormParam("name") String name, @FormParam("email")String email){
    User user = userDatasource.persist(User.of(name, email));
    return Response.created(URI.create(String.format("/users/%s", user.getId()))).build();
  }

  @GET
  @Path("/{id}")   //http://localhost:8080/jakartarest/api/users/1
  @Produces(APPLICATION_JSON)
  public Response findById(@PathParam("id") String id){
      Optional<User> user = userDatasource.findById(id);
      if(!user.isPresent()){
        return Response.status(Status.NOT_FOUND).build();
      }
      return Response.ok(user.get()).build();
  }

  @GET
  @Produces(APPLICATION_JSON)
  //@DefaultValue("jamdiazdiaz@gmail.com")
  //@HeaderParam("email")
  //@CookieParam("email")
  //Matrix pattern:     /users;email=jamdiazdiaz@gmail.com
  //@MatrixParam
  public Response list( @QueryParam("email") String email){
    if(email!=null){
      return Response.ok(userDatasource.listByEmail(email)).build();
    }
    return Response.ok(userDatasource.listAll()).build();
  }
}
