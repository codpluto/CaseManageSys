package com.zhu.casemanage.utils;

import com.zhu.casemanage.exception.BusinessException;
import io.jsonwebtoken.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {

    private static final String SECURITY_KRY = "f.tL3^4eds@#@Syb5";

    public String createToken(String account){
        JwtBuilder jwtBuilder = Jwts.builder().setSubject(account).claim("account",account)
                .claim("uuid", RandomStringUtils.randomAlphabetic(16)).signWith(SignatureAlgorithm.HS256,SECURITY_KRY);
        return jwtBuilder.compact();
    }

    public String parseToken(String token){
        try {
            Claims body = (Claims) Jwts.parser().setSigningKey(SECURITY_KRY).parse(token).getBody();
            return (String) body.get("account");
        } catch (ExpiredJwtException | MalformedJwtException | SignatureException | IllegalArgumentException e){
            throw new BusinessException("非法token!");
        }
    }
}
