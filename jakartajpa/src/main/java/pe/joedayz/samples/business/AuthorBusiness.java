package pe.joedayz.samples.business;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.LockModeType;
import jakarta.persistence.Persistence;
import java.util.List;
import pe.joedayz.samples.entities.Author;

@ApplicationScoped
public class AuthorBusiness {

  private EntityManagerFactory entityManagerFactory;

  @PostConstruct
  public void init(){
    entityManagerFactory =
        Persistence.createEntityManagerFactory("jakartaee-unit");
  }

  public List<Author> findAll(){
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    List<Author> authors = entityManager.createQuery("select a from Author a")
            .setLockMode(LockModeType.PESSIMISTIC_FORCE_INCREMENT)
                .getResultList();

    entityManager.getTransaction().commit();
    entityManager.close();
    return authors;
  }

  public List<Author> findByName(String name){
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    List<Author> authors = entityManager
        .createQuery("select a from Author a where a.name=:name")
        .setParameter("name",name)
        .getResultList();
    entityManager.close();
    return authors;
  }

  public Author save (Author author){
    EntityManager entityManager = entityManagerFactory.createEntityManager();
    entityManager.getTransaction().begin();

    //If it has an ID than it is an update
    if(author.getId() != null){
      entityManager.lock(author, LockModeType.PESSIMISTIC_FORCE_INCREMENT);
    }
    entityManager.persist(author);

    entityManager.getTransaction().commit();
    entityManager.close();
    return author;
  }
}
