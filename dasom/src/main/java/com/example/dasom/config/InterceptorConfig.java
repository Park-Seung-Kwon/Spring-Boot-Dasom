package com.example.dasom.config;

import com.example.dasom.interceptor.LoginInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class InterceptorConfig implements WebMvcConfigurer {
    private final LoginInterceptor loginInterceptor;

    //로그인 여부 체크 인터셉터
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(loginInterceptor)
                //봉사활동 상세보기 진입단계
                .addPathPatterns("/cs/detail/**")
                //후원 결제페이지 진입단계
                .addPathPatterns("/donate/payPage/**")
                .order(1);
    }
}