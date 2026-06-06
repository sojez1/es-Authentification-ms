package com.jpstechno.auth_ms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.RequiredArgsConstructor;

@SpringBootApplication
@EnableAsync
@EnableFeignClients
@RequiredArgsConstructor
public class AuthMsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthMsApplication.class, args);

	}
}
