package pe.joedayz.samples.jakartaejb.stateless;

import jakarta.ejb.Stateless;
import jakarta.ejb.TransactionAttribute;
import jakarta.ejb.TransactionAttributeType;
import jakarta.ejb.TransactionManagement;
import jakarta.ejb.TransactionManagementType;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import pe.joedayz.samples.jakartaejb.entity.Purchase;

@Stateless
public class PurchaseBusiness implements Serializable {

  @PersistenceContext
  private EntityManager entityManager;

  public List<Purchase> findAll(){
    return entityManager
        .createQuery("select p from Purchase p join fetch p.products", Purchase.class)
        .getResultList();
  }

  public void save(Purchase purchase){
    entityManager.persist(purchase);
  }

  public Optional<Purchase> findById(Long id){
    return Optional.ofNullable(entityManager.find(Purchase.class,id));
  }
}
