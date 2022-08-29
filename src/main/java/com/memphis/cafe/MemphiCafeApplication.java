package com.memphis.cafe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.Bean;

import com.memphis.filter.AdminFilter;

@ServletComponentScan
@SpringBootApplication
public class MemphiCafeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MemphiCafeApplication.class, args);
	}

	
}
