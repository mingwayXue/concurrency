package com.xue.concurrency;

import com.xue.concurrency.filter.HttpFilter;
import com.xue.concurrency.filter.HttpInterceptor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
public class ConcurrencyApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(ConcurrencyApplication.class, args);
	}

	/**
	 * 注册拦截器
	 * @return
	 */
	@Bean
	public FilterRegistrationBean httpFilter() {
		FilterRegistrationBean registrationBean = new FilterRegistrationBean();
		registrationBean.setFilter(new HttpFilter());	//设置过滤器
		registrationBean.addUrlPatterns("/threadLocal/*");	//设置拦截路径
		return registrationBean;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(new HttpInterceptor()).addPathPatterns("/**");	//定义拦截器，拦截所有请求

	}
}
