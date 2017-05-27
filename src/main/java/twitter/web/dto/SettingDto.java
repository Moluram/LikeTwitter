package twitter.web.dto;

/**
 * Created by moluram on 23.5.17.
 */
public class SettingDto {
  private String id;
  private String value;

  public SettingDto(String id, String value) {
    this.id = id;
    this.value = value;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getValue() {
    return value;
  }

  public void setValue(String value) {
    this.value = value;
  }
}
