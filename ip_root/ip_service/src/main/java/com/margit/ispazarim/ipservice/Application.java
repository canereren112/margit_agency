package com.margit.ispazarim.ipservice;

import java.util.Arrays;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.WebApplicationInitializer;

/**
 * @author firat.eren Spring boot application context loader
 */
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.margit.ispazarim.ipcore.repository")
@EntityScan(basePackages = "com.margit.ispazarim.ipcore.entity")
@EnableAutoConfiguration
@ComponentScan
//@EnableScheduling
//@EnableAsync
//@EnableTransactionManagement
public class Application extends SpringBootServletInitializer implements WebApplicationInitializer {

	// public static void main(String[] args) {
	// SpringApplication.run(Application.class, args);
	// }
	//
	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(Application.class, "--debug");

		System.out.println("Let's inspect the beans provided by Spring Boot:");

		String[] beanNames = ctx.getBeanDefinitionNames();
		Arrays.sort(beanNames);
		for (String beanName : beanNames) {
			System.out.println(beanName);
		}
	}

}
