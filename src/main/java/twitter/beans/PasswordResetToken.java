package twitter.beans;

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

  public User getUser() {
    return user;
  }

  public Date getExpiryDate() {
    return expiryDate;
  }
}
