package twitter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import twitter.web.constants.MailConstants;
import twitter.web.managers.PropertyManager;

import java.util.Locale;
import java.util.Properties;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "twitter.web")
@Import(SecurityConf.class)
public class WebConf extends WebMvcConfigurerAdapter {
	private static final String MESSAGES_BASENAME 				= "WEB-INF/i18/messages";
	private static final String ENCODING_TYPE							= "UTF-8";

	private static final String DEFAULT_LOCALE 						= "en";
	private static final String COOKIE_NAME 							= "myLocaleCookie";
	private static final String LOCALE_PARAM_NAME 				= "mylocale";
	private static final String VIEWS_SUFFIX 							= ".jsp";
	private static final String VIEWS_BASE = "/WEB-INF/views/";

	private PropertyManager propertyManager;

	@Autowired
	public void setPropertyManager(PropertyManager manager) {
		propertyManager = manager;
	}

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix(VIEWS_BASE);
		viewResolver.setSuffix(VIEWS_SUFFIX);
		return viewResolver;
	}

	@Bean
  public static PropertySourcesPlaceholderConfigurer
  propertySourcesPlaceholderConfigurer() {
    return new PropertySourcesPlaceholderConfigurer();
  }



	@Bean
	public MailSender mailSender() {
	  JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	  mailSender.setHost(propertyManager.getProperty(MailConstants.HOST));
	  mailSender.setPort(Integer.parseInt(propertyManager.getProperty(MailConstants.SMPT_PORT)));
	  mailSender.setUsername(propertyManager.getProperty(MailConstants.USERNAME));
	  mailSender.setPassword(propertyManager.getProperty(MailConstants.PASSWORD));
	  mailSender.setProtocol(propertyManager.getProperty(MailConstants.SMPT_PROTOCOL));

    Properties props = System.getProperties();
    props.put(MailConstants.SMPT_STARTTLS_ENABLE, propertyManager.getProperty(MailConstants.
				SMPT_STARTTLS_ENABLE));
    props.put(MailConstants.SMPT_QUITWAIT, propertyManager.getProperty(MailConstants.SMPT_QUITWAIT));
    props.put(MailConstants.SMPT_AUTH,  propertyManager.getProperty(MailConstants.SMPT_AUTH));
    mailSender.setJavaMailProperties(props);
		return mailSender;
	}

	@Bean
    @Primary
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename(MESSAGES_BASENAME);
		messageSource.setDefaultEncoding(ENCODING_TYPE);
		return messageSource;
	}

	@Bean
	public LocaleResolver localeResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale(DEFAULT_LOCALE));
		resolver.setCookieName(COOKIE_NAME);
		resolver.setCookieMaxAge(4800);
		return resolver;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		LocaleChangeInterceptor interceptor = new LocaleChangeInterceptor();
		interceptor.setParamName(LOCALE_PARAM_NAME);
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