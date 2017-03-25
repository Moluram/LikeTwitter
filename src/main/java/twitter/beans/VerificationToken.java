package twitter.beans;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Represent an verification token to the user
 * @author Aliaksei Chorny
 */
@Entity
public class VerificationToken {
  private static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  private Date expiryDate;

  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Timestamp(calendar.getTime().getTime()));
    calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(calendar.getTime().getTime());
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }
}
