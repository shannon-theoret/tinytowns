package com.shannontheoret.tinytowns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication
public class TinytownsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TinytownsApplication.class, args);
	}

}
