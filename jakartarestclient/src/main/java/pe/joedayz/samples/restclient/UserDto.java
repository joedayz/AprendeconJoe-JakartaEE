package pe.joedayz.samples.restclient;

public class UserDto {

  private String id;
  private String name;
  private String email;

  public static UserDto of(String name, String email) {
    UserDto userDto = new UserDto();
    userDto.setName(name);
    userDto.setEmail(email);
    return userDto;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}
