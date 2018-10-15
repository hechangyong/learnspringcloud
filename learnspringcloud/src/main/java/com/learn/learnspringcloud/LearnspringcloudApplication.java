package com.learn.learnspringcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class LearnspringcloudApplication {

	public static void main(String[] args) {
		SpringApplication.run(LearnspringcloudApplication.class, args);
	}
}
