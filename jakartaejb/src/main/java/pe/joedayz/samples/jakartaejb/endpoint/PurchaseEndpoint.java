package pe.joedayz.samples.jakartaejb.endpoint;

import static jakarta.ws.rs.core.MediaType.APPLICATION_FORM_URLENCODED;

import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;
import java.io.Serializable;
import java.net.URI;
import java.util.Optional;
import java.util.logging.Logger;
import pe.joedayz.samples.jakartaejb.entity.Product;
import pe.joedayz.samples.jakartaejb.entity.Purchase;
import pe.joedayz.samples.jakartaejb.stateful.ShoppingCartBusiness;
import pe.joedayz.samples.jakartaejb.stateless.ProductBusiness;
import pe.joedayz.samples.jakartaejb.stateless.PurchaseBusiness;

@Path("/purchases")
@SessionScoped
public class PurchaseEndpoint implements Serializable {

  private static Logger logger = Logger.getLogger(PurchaseEndpoint.class.getName());

  @Inject
  private PurchaseBusiness purchaseBusiness;

  @Inject
  private ShoppingCartBusiness shoppingCartBusiness;

  @Inject
  private ProductBusiness productBusiness;


  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAll(){
    logger.info("Obteniendo los productos comprados");
    return Response
        .ok(purchaseBusiness.findAll())
        .build();
  }

  @PUT
  @Path("/shoppingcart")
  @Consumes(APPLICATION_FORM_URLENCODED)
  public Response addProduct(@FormParam("idProduct") Long idProduct){
    Optional<Product> product = productBusiness.findById(idProduct);
    if(!product.isPresent()){
      return Response.status(Status.BAD_REQUEST).build();
    }
    logger.info("Agregando producto al carrito " + product.get());
    shoppingCartBusiness.add(product.get());
    return Response.ok().build();
  }

  @GET
  @Path("/shoppingcart")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findAllCarts(){
    logger.info("Contenido del carrito " + shoppingCartBusiness.getProducts());
    return Response
        .ok(shoppingCartBusiness.getProducts())
        .build();
  }

  @POST
  public Response finalizePurchase(){
    if(shoppingCartBusiness.getProducts().isEmpty()){
      return Response
          .status(Response.Status.BAD_REQUEST)
          .build();
    }
    logger.info("Checkout carrito " + shoppingCartBusiness.getProducts());
    Purchase purchase = shoppingCartBusiness.finalizeCart();
    return Response.created(URI.create(String.format("/purchases/%d",purchase.getId()))).build();
  }

  @GET
  @Path("/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response findById(@PathParam("id") Long id){
    Optional<Purchase> purchase = purchaseBusiness.findById(id);
    if(!purchase.isPresent()){
      return Response
          .status(Response.Status.NOT_FOUND)
          .build();
    }
    return Response
        .ok(purchase.get())
        .build();
  }

}
