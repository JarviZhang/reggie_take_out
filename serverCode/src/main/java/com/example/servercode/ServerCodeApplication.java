package com.example.servercode;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//可以直接输出日志
@Slf4j
@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
public class ServerCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerCodeApplication.class, args);
	}

}
