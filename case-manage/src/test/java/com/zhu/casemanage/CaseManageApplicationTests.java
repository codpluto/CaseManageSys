package com.zhu.casemanage;

import com.zhu.casemanage.service.UserServiceImpl;
import com.zhu.casemanage.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CaseManageApplicationTests {

	@Autowired
	UserServiceImpl userService;

	@Test
	void contextLoads() {
//		userService.addUser(null,null,1,null,null,null);
	}

	@Autowired
	private RedisUtil redisUtil;

	@Test
	public void setTest(){
		redisUtil.set("username","zzy");
	}

	@Test
	public void setExpireTest(){
		redisUtil.set("username","zzzyy",6);
	}

}
