package cn.ruoshy.platform.config;

import cn.ruoshy.platform.config.interceptor.PageInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.Resource;

@Configuration
public class PageAdapter implements WebMvcConfigurer {

    @Resource
    private PageInterceptor pageInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(pageInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/index.html", "/index", "/login","/sv/**","/error","/chess/**")
                .excludePathPatterns("/css/**", "/img/**")
                .excludePathPatterns("/favicon.ico");
    }
}
