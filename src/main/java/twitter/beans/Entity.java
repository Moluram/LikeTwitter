package twitter.beans;


/**
 * Created by Nikolay on 14.04.2017.
 */
public abstract class Entity {

  protected Long id;

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
