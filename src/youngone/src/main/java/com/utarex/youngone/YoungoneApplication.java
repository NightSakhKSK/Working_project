package com.utarex.youngone;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class YoungoneApplication {
	private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(YoungoneApplication.class);

	public static void main(String[] args) {
		log.debug("영원코퍼레이션 스프링 부트 시작");
		SpringApplication.run(YoungoneApplication.class, args);
	}

}
