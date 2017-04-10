package twitter.configuration;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

/**
 * Created by Nikolay on 03.04.2017.
 */

@Configuration
@ComponentScan("twitter.dao")
@PropertySource("classpath:database.properties")
public class DatabaseConf {

  private final Environment env;

  @Autowired
  public DatabaseConf(Environment env) {
    this.env = env;
  }

  @Bean
  BasicDataSource dataSource(){
    BasicDataSource ds = new BasicDataSource();
    ds.setDriverClassName(env.getRequiredProperty("db.jdbc.driver"));
    ds.setUrl(env.getRequiredProperty("db.url"));
    ds.setUsername(env.getRequiredProperty("db.user"));
    ds.setPassword(env.getRequiredProperty("db.password"));

    ds.setMinIdle(5);
    ds.setMaxIdle(10);
    ds.setMaxOpenPreparedStatements(100);
    return ds;
  }
}
