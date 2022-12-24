package pe.joedayz.samples.jakartaejb.endpoint;

import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import java.net.URI;
import java.util.Optional;
import pe.joedayz.samples.jakartaejb.entity.Product;
import pe.joedayz.samples.jakartaejb.stateless.ProductBusiness;

@Path("/products")
public class ProductEndpoint {

  @Inject
  private ProductBusiness productBusiness;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll(){
    return Response.ok(productBusiness.findAll()).build();
  }

  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  public Response save(Product product){
    product.setId(null);
    productBusiness.save(product);
    return Response
        .created(URI.create(String.format("/products/%d",product.getId())))
        .build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") Long id){
    Optional<Product> product = productBusiness.findById(id);
    if(!product.isPresent()){
      return Response
          .status(Response.Status.NOT_FOUND)
          .build();
    }
    return Response.ok(product.get()).build();
  }
}
