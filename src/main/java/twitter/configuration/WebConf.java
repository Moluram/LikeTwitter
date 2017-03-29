package twitter.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.mail.MailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.CookieLocaleResolver;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;
import twitter.web.handlers.CustomAuthenticationFailureHandler;

import java.util.Locale;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "twitter.web")
@Import(SecurityConf.class)
public class WebConf extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
		viewResolver.setViewClass(JstlView.class);
		viewResolver.setPrefix("/WEB-INF/views/");
		viewResolver.setSuffix(".jsp");
		return viewResolver;
	}

	@Bean
	public MailSender mailSender() {
		return new JavaMailSenderImpl();
	}

	@Bean
    @Primary
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource =
				new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("WEB-INF/i18/messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	@Bean
	public LocaleResolver localResolver() {
		CookieLocaleResolver resolver = new CookieLocaleResolver();
		resolver.setDefaultLocale(new Locale("en"));
		resolver.setCookieName("myLocaleCookie");
		resolver.setCookieMaxAge(4800);
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