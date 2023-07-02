package com.zhu.casemanage.test;

import com.zhu.casemanage.CaseManageApplication;
import com.zhu.casemanage.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class RedisUtilTest {
    @Autowired
    private RedisUtil redisUtil;

    @Test
    private void setTest(){
        redisUtil.set("username","zzy");
    }
}
