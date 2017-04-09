package twitter.beans;

import java.sql.Timestamp;
import java.util.Calendar;
import javax.persistence.*;
import java.util.Date;

/**
 * Created by Moluram on 3/29/2017.
 */
public class PasswordResetToken {
  public static final int EXPIRATION = 60 * 24;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  private String token;

  @OneToOne(targetEntity = User.class, fetch = FetchType.EAGER)
  @JoinColumn(nullable = false, name = "user_id")
  private User user;

  private Date expiryDate;

  public PasswordResetToken() {
  }

  public PasswordResetToken(User user, String token) {
    this.user = user;
    this.token = token;
    this.expiryDate = calculateExpiryDate(EXPIRATION);
  }

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

  public void setUser(User user) {
    this.user = user;
  }

  public void setExpiryDate(Date expiryDate) {
    this.expiryDate = expiryDate;
  }

  public User getUser() {
    return user;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }

  @Override
  public String toString() {
    return "PasswordResetToken{" +
        "id=" + id +
        ", token='" + token + '\'' +
        ", user=" + user +
        ", expiryDate=" + expiryDate +
        '}';
  }
}
