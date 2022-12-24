package pe.joedayz.samples.jakartaejb.stateful;

import jakarta.annotation.PostConstruct;
import jakarta.ejb.Stateful;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import pe.joedayz.samples.jakartaejb.entity.Product;
import pe.joedayz.samples.jakartaejb.entity.Purchase;
import pe.joedayz.samples.jakartaejb.stateless.PurchaseBusiness;

@Stateful
public class ShoppingCartBusiness implements Serializable {

  @Inject
  private PurchaseBusiness purchaseBusiness;

  private List<Product> products;

  @PostConstruct
  public void initialize(){
    this.products =  new ArrayList<>();
  }


  public void add(Product product){
    this.products.add(product);
  }

  public void remove(Product product){
    this.products.remove(product);
  }

  public void clear(){
    this.products.clear();
  }

  public List<Product> getProducts(){
    return this.products;
  }

  public Purchase finalizeCart(){
    Purchase purchase = new Purchase();
    purchase.setProducts(products);
    purchaseBusiness.save(purchase);
    return purchase;
  }



}
