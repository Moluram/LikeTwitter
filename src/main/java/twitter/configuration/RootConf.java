package twitter.configuration;

import org.springframework.context.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import twitter.service.user.UserService;

@Configuration
@ComponentScan(basePackages = {"twitter.listeners", "twitter.service"})
@Import(SecurityConf.class)
public class RootConf {

}
