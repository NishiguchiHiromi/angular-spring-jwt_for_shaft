package com.example.mySource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@ServletComponentScan// filterなどを有効にするときに必要
@SpringBootApplication
public class MySourceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MySourceApplication.class, args);
	}
}
