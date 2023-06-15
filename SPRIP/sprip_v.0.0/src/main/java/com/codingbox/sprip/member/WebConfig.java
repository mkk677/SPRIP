package com.codingbox.sprip.member;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor())
                .addPathPatterns("/**") // 모든 경로에 대해 인터셉터를 적용합니다.
                .excludePathPatterns("/css/**"); // 인터셉터에서 제외할 경로 패턴을 설정합니다.
    }
}
