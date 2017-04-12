package twitter.beans;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by moluram on 24.3.17.
 */
@Entity
public class Privilege {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String name;

  public Privilege() {
  }

  public Privilege(String privilegeName) {
    this.name = privilegeName;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
