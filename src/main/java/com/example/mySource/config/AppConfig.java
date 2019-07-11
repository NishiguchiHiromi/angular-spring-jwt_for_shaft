package com.example.mySource.config;

import com.example.mySource.architecture.CustomFilter;
import com.example.mySource.architecture.CustomFilter2;
import com.example.mySource.architecture.CustomHandlerInterceptor;
import com.example.mySource.auth.LoginUserDemo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.Optional;

@Configuration
@ComponentScan("com.example.mySource")
public class AppConfig implements WebMvcConfigurer {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Bean
    public AuditorAware<Integer> auditorAware() {
        return new AuditorAware<Integer>() {

            @Override
            public Optional<Integer> getCurrentAuditor() {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                if(authentication == null || authentication.isAuthenticated() == false) {
                    return Optional.ofNullable(-1);
                }

                return Optional.ofNullable(Integer.parseInt(authentication.getPrincipal().toString()));
            }
        };
    }


    // Filter
    @Bean
    public FilterRegistrationBean firstFilter() {
        // FilterをnewしてFilterRegistrationBeanのコンストラクタに渡す
        FilterRegistrationBean bean = new FilterRegistrationBean(new CustomFilter());
        // Filterのurl-patternを指定（可変長引数なので複数指定可能）
//        bean.addUrlPatterns("/*");
        // Filterの実行順序。整数値の昇順に実行される
        bean.setOrder(1);
        return bean;
    }

    @Bean
    public FilterRegistrationBean secondFilter() {
        FilterRegistrationBean bean = new FilterRegistrationBean(new CustomFilter2());
        bean.setOrder(2);
        return bean;
    }

    // HandlerInterceptor
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CustomHandlerInterceptor())
                .addPathPatterns("/**") // 適用対象のパス(パターン)を指定する
                .excludePathPatterns("/static/**"); // 除外するパス(パターン)を指定する
    }
}
