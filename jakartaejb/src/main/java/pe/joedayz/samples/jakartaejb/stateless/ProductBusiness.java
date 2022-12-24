package pe.joedayz.samples.jakartaejb.stateless;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import pe.joedayz.samples.jakartaejb.entity.Product;

@Stateless
public class ProductBusiness implements Serializable {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Product> findAll(){
    return entityManager
        .createQuery("select p from Product p", Product.class)
        .getResultList();
  }

  public void save(Product product){
    entityManager.persist(product);
  }

  public Optional<Product> findById(Long id){
    return Optional.ofNullable(entityManager.find(Product.class,id));
  }
}
