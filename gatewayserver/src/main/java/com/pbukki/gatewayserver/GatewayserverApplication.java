package com.pbukki.gatewayserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;

import java.time.LocalDateTime;

@SpringBootApplication
public class GatewayserverApplication {

	public static void main(String[] args) {
		SpringApplication.run(GatewayserverApplication.class, args);
	}




	@Bean
	public RouteLocator pbukkiRouteLocator(RouteLocatorBuilder routeLocatorBuilder){
		return  routeLocatorBuilder
				.routes()
				.route(p -> p
						.path("pbukki/accounts/**")
				        .filters(f->f
								.rewritePath("/pbukki/accounts/(?<remaining>.*)", "/$\\{remaining}")
								.addResponseHeader("X-Response-Time", LocalDateTime.now().toString()))
						.uri("lb://ACCOUNTS"))
				.route(p -> p
						.path("pbukki/loans/**")
						.filters(f->f
								.rewritePath("/pbukki/loans/(?<remaining>.*)", "/$\\{remaining}")
								.addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
						.uri("lb://LOANS"))
				.route(p -> p
						.path("pbukki/cards/**")
						.filters(f->f
								.rewritePath("/pbukki/cards/(?<remaining>.*)", "/$\\{remaining}")
								.addResponseHeader("X-Response-Time",LocalDateTime.now().toString()))
						.uri("lb://CARDS"))
				.build();


	}

}
