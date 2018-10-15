package com.learn.learnspringcloud;

import com.sun.glass.ui.Application;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class LearnspringcloudApplication {

	public static void main(String[] args) {
		new SpringApplicationBuilder(LearnspringcloudApplication.class).web(true).run(args);
	}

}
