package com.zhu.casemanage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

//@SpringBootApplication(scanBasePackages = {"com.aeert","com.zhu","com.zhu.casemanage.config"})
@SpringBootApplication()
//@ComponentScan({"com.zhu.casemanage","com.aeert"})
public class CaseManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(CaseManageApplication.class, args);
	}

}
