package com.zhu.casemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.aeert","com.zhu"})
public class CaseManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseManageApplication.class, args);
	}

}
