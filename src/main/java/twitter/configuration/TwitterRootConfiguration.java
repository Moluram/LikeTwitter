package twitter.configuration;

import org.springframework.context.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Configuration
@ComponentScan(basePackages={"twitter"},
        excludeFilters={
                @ComponentScan.Filter(type= FilterType.ANNOTATION, value=EnableWebMvc.class)
        })
public class TwitterRootConfiguration {
}
