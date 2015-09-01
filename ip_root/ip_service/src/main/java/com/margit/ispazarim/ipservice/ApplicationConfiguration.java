package com.margit.ispazarim.ipservice;

import javax.servlet.Filter;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.filter.CommonsRequestLoggingFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;
//import com.github.isrsal.logging.LoggingFilter;

/**
 * @author firat.eren Application context configuration
 */
@Configuration
public class ApplicationConfiguration {

	/**
	 * fix for lazy loaded proxy object serialization
	 * 
	 * @return
	 */
	@Bean
	public MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter() {
		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.registerModule(new Hibernate4Module());
		jsonConverter.setObjectMapper(objectMapper);
		return jsonConverter;
	}
	
//	@Bean
//	public Filter corsFilter() {
//		SimpleCORSFilter filter = new SimpleCORSFilter();
//		return filter;
//	}	

//	@Bean
//	public Filter loggingFitler() {
//		LoggingFilter filter = new LoggingFilter();
//		return filter;
//	}

//	@Bean
//	public CommonsRequestLoggingFilter requestLoggingFilter() {
//		CommonsRequestLoggingFilter2 crlf = new CommonsRequestLoggingFilter2();
//		crlf.setIncludeClientInfo(true);
//		crlf.setIncludeQueryString(true);
//		crlf.setIncludePayload(true);
//		return crlf;
//	}

	// @Override
	// public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
	// taskRegistrar.setScheduler(taskExecutor());
	// taskRegistrar.addTriggerTask(
	// new Runnable() {
	// public void run() {
	// getNotificationJob().work();
	// }
	// },
	// new Trigger
	// );
	// }
	//
	// @Bean(destroyMethod = "shutdown")
	// public Executor taskExecutor() {
	// ScheduledExecutorService service = new
	// return Executors.newScheduledThreadPool(5);
	// }
	//
	// @Bean
	// public NotificationJob getNotificationJob() {
	// return new NotificationJob();
	// }

	// @Bean
	// public MessageSource messageSource() {
	// ResourceBundleMessageSource messageSource = new
	// ResourceBundleMessageSource();
	// messageSource.setBasename("messages");
	// messageSource.setDefaultEncoding("UTF-8");
	// return messageSource;
	// }

	// @Bean
	// public Validator validator() {
	// LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
	// factoryBean.setValidationMessageSource(messageSource());
	// return factoryBean;
	// }

}