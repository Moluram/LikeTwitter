package twitter.beans;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/**
 * Created by Nikolay on 14.04.2017.
 */
public abstract class Entity {

  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
