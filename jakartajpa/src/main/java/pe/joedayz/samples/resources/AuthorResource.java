package pe.joedayz.samples.resources;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import pe.joedayz.samples.business.AuthorBusiness;
import pe.joedayz.samples.entities.Author;

@Path("authors")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthorResource {

  @Inject
  private AuthorBusiness authorBusiness;

  @GET
  public Response find(@QueryParam("name") String name){
    if(name != null && !name.isEmpty()){
      return Response
          .ok(authorBusiness.findByName(name))
          .build();
    }
    return Response.ok(authorBusiness.findAll()).build();
  }

  @POST
  public Response save(Author author){
    author.setId(null);
    authorBusiness.save(author);
    return Response
        .created(URI.create(String.format("authors/%d",author.getId())))
        .build();
  }
}
