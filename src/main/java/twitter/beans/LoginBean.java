package twitter.beans;

/**
 * Created by Lenka on 23.03.2017.
 */
public class LoginBean {

        private String username;

        private String password;

        public String getPassword()
        {
            return this.password;
        }

        public String getUsername()
        {
            return this.username;
        }

        public void setUsername(String username)
        {
            this.username = username;
        }

        public void setPassword(String password)
        {
            this.password = password;
        }
}
