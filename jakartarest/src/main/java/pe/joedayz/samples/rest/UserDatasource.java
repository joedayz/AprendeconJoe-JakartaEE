package pe.joedayz.samples.rest;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class UserDatasource {

  private Map<String, User> users;

  @PostConstruct
  public void init(){
    users = new HashMap<>();
  }

  public Optional<User> findById(String id){
    return Optional.ofNullable(users.get(id));
  }

  public User persist(User user){
    if(user.getId()==null){
      String id = UUID.randomUUID().toString();
      user.setId(id);
    }
    users.put(user.getId(), user);
    return user;
  }

  public List<User> listAll(){
    return users.values().stream().collect(Collectors.toList());
  }

  public List<User> listByEmail(String email){
    return users.values().stream().filter(u -> u.getEmail().equals(email))
        .collect(Collectors.toList());
  }
}
