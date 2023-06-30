package com.zhu.casemanage;

import com.zhu.casemanage.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CaseManageApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Test
	void contextLoads() {
		userService.addUser(null,null,1,null,null,null);
	}


}
