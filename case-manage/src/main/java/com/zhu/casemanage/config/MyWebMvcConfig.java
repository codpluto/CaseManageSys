package com.zhu.casemanage.config;

import com.aeert.jfilter.converter.JsonFilterHttpMessageConverter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.zhu.casemanage.interceptor.LoginInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;

@Configuration
@Slf4j
public class MyWebMvcConfig extends WebMvcConfigurationSupport {
    private static List<String> excludePathList = Arrays.asList("/login/token","/login/register");

    @Autowired
    private LoginInterceptor loginInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        log.info("-----进入Config------");
        registry.addInterceptor(loginInterceptor).addPathPatterns("/**").excludePathPatterns(excludePathList);
    }

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        JsonFilterHttpMessageConverter fastConverter = new JsonFilterHttpMessageConverter();
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(new SerializerFeature[]{SerializerFeature.PrettyFormat, SerializerFeature.DisableCircularReferenceDetect});
        fastConverter.setSupportedMediaTypes(Arrays.asList(MediaType.APPLICATION_JSON, MediaType.APPLICATION_JSON_UTF8, MediaType.APPLICATION_OCTET_STREAM));
        fastConverter.setFastJsonConfig(fastJsonConfig);
        converters.add(fastConverter);
    }
}
