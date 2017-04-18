package twitter.beans;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Moluram on 3/29/2017.
 */
public class PasswordResetToken extends Entity {

  public static final int EXPIRATION = 60 * 24;

  private String token;

  private User user;

  private Date expiryDate;

  public PasswordResetToken() {
  }

  public PasswordResetToken(User user, String token, int expiration) {
    this.user = user;
    this.token = token;
    this.expiryDate = calculateExpiryDate(expiration);
  }

  private Date calculateExpiryDate(int expiryTimeInMinutes) {
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(new Timestamp(calendar.getTime().getTime()));
    calendar.add(Calendar.MINUTE, expiryTimeInMinutes);
    return new Date(calendar.getTime().getTime());
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
