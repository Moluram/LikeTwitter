package twitter.web.managers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by Moluram on 4/8/2017.
 */

@Component
@PropertySource("classpath:mail.properties")
public class PropertyManager {
  private Environment env;

  @Autowired
  public void setEnv(ApplicationContext app) {
    env = app.getEnvironment();
  }

  public String getProperty(String name) {
    return env.getProperty(name);
  }
}
