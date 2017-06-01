package twitter.entity;

/**
 * Created by moluram on 24.3.17.
 */
public class Privilege extends Entity {

  private String name;

  public Privilege() {
  }

  public Privilege(String privilegeName) {
    this.name = privilegeName;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return "Privilege{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
  }
}
