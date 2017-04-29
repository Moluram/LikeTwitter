package twitter.configuration;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.Ordered;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.annotation.Resource;
import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "twitter.web")
@PropertySource("classpath:mail.properties")
@Import({SecurityConf.class,DatabaseConf.class})
public class WebConf extends WebMvcConfigurerAdapter {

	@Override
	public void addResourceHandlers(final ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setContentType("text/html;charset=UTF-8");
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
  public static PropertySourcesPlaceholderConfigurer
  propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }



	@Bean
  @Autowired
	public MailSender mailSender(ApplicationContext context) {
	  Environment env = context.getEnvironment();

	  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	  mailSender.setHost(env.getProperty("mail.host"));
	  mailSender.setPort(Integer.parseInt(env.getProperty("mail.smtp.port")));
	  mailSender.setUsername(env.getProperty("mail.username"));
	  mailSender.setPassword(env.getProperty("mail.password"));
	  mailSender.setProtocol(env.getProperty("mail.smtp.protocol"));

    Properties props = System.getProperties();
    props.put("mail.smtp.starttls.enable", env.getProperty("mail.smtp.starttls.enable"));
    props.put("mail.smtp.quitwait", env.getProperty("mail.smtp.quitwait"));
    props.put("mail.smtp.auth",  env.getProperty("mail.smtp.auth"));
    mailSender.setJavaMailProperties(props);
		return mailSender;
	}

	@Bean
	public ObjectMapper objectMapper() {
		return new ObjectMapper();
	}

	@Bean
	@Primary
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/i18/messages");
		messageSource.setDefaultEncoding("UTF-8");
		messageSource.setUseCodeAsDefaultMessage(false);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver resolver=new CommonsMultipartResolver();
		resolver.setDefaultEncoding("utf-8");
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName("mylocale");
		registry.addInterceptor(interceptor);
	}

	/**
	 * Configure static content handling.
	 */
	@Override
	public void configureDefaultServletHandling( DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}


}