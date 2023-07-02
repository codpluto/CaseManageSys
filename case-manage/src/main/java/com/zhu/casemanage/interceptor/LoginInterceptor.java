package com.zhu.casemanage.interceptor;

import com.zhu.casemanage.constant.UserConstant;
import com.zhu.casemanage.exception.BusinessException;
import com.zhu.casemanage.utils.JwtUtil;
import com.zhu.casemanage.utils.RedisUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private RedisUtil redisUtil;



    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = request.getHeader("token");
        log.info("进入拦截器-------------------{}",token);
        if (token == null){
            throw new BusinessException("token不存在");
        }
        String account = jwtUtil.parseToken(token);
        String tokenCache = (String) redisUtil.get(UserConstant.getTokenKey(account));
        if (tokenCache == null){
            throw new BusinessException("token已过期");
        }
        return true;
    }
}
