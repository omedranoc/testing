package com.me.testing

import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.EnableAutoConfiguration
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration

@SpringBootApplication
//@EnableAutoConfiguration(exclude=DataSourceAutoConfiguration.class)
class TestingApplication {

	static void main(String[] args) {
		SpringApplication.run TestingApplication, args
		//app that was develop for testing purposes  that  brings up all the apartment data  collected in  the  https://www.fincaraiz.com.co/ website and proccess it,
		// some of  the variables are in spanish since its semantic meaning does not correspond directly with  some words in English
	}
}
