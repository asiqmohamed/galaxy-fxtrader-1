package com.swapstech.galaxy.fxtrader;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.AutoConfigurationExcludeFilter;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.liquibase.LiquibaseAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.TypeExcludeFilter;
//import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;

@SpringBootApplication(scanBasePackages = {}, exclude = { DataSourceAutoConfiguration.class,
		HibernateJpaAutoConfiguration.class, LiquibaseAutoConfiguration.class })

@ComponentScan(basePackages = {
		"com.swapstech.galaxy.fxtrader",
		"com.swapstech.galaxy.common.tenant",
		"com.swapstech.galaxy.legalentity",
		"com.swapstech.galaxy.security",
		"com.swapstech.galaxy.activity.client",
		"com.swapstech.galaxy.persistence",
		"com.finzly.bankos.sqs.messaging.publisher",
		"com.swapstech.galaxy.audit"
}, excludeFilters = {
		@Filter(type = FilterType.CUSTOM, classes = TypeExcludeFilter.class),
		@Filter(type = FilterType.CUSTOM, classes = AutoConfigurationExcludeFilter.class) })

@EntityScan(basePackages = { "com.swapstech.galaxy.fxtrader.**" })
@EnableAutoConfiguration(exclude = { org.springframework.boot.autoconfigure.gson.GsonAutoConfiguration.class })
public class Application {

	public static void main(String[] args) throws Exception {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	ObjectMapper objectMapper() {
		ObjectMapper obj = new ObjectMapper();
		obj.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		return obj;
	}

	@Bean
	ModelMapper modelMapper() {
		ModelMapper modelMapper = new ModelMapper();
		modelMapper.getConfiguration().setPropertyCondition(Conditions.isNotNull())
				.setMatchingStrategy(MatchingStrategies.STRICT);
		return modelMapper;
	}

}
