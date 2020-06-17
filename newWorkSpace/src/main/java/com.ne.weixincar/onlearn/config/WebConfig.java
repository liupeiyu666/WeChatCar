/**
 * Copyright 2018-2020 stylefeng & fengshuonan (sn93@qq.com)
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ne.weixincar.onlearn.config;


import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * web 配置类
 *
 * @author fengshuonan
 * @date 2016年11月12日 下午5:03:32
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {


    /**
     * 静态资源映射
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //open swagger
//        if (true) {
//
//            //swagger
//            registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
//            registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
//        }

        //本应用
        registry.addResourceHandler("/static/**").addResourceLocations("classpath:/resources/static/",
                "classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/", "classpath:/public/");

        //覆盖默认/**资源，只允许指定目录
        registry.addResourceHandler("/**").addResourceLocations("classpath:/assets/",
                "classpath:/META-INF/resources/","classpath:/resources/","classpath:/static/", "classpath:/public/");
    }
//
//    /**
//     * 增加对rest api鉴权的spring mvc拦截器
//     */
//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(new RestApiInteceptor()).addPathPatterns("/gunsApi/**");
//        registry.addInterceptor(new AttributeSetInteceptor()).excludePathPatterns(Const.NONE_PERMISSION_RES).addPathPatterns("/**");
//    }

//    /**
//     * 默认错误页面，返回json
//     */
//    @Bean("error")
//    public GunsErrorView error() {
//        return new GunsErrorView();
//    }

    /**
     * druidServlet注册
     */
    @Bean
    public ServletRegistrationBean druidServletRegistration() {
        ServletRegistrationBean registration = new ServletRegistrationBean(new StatViewServlet());
        registration.addUrlMappings("/druid/*");
        return registration;
    }

    /**
     * druid监控 配置URI拦截策略
     */
    @Bean
    public FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        //添加过滤规则.
        filterRegistrationBean.addUrlPatterns("/*");
        //添加不需要忽略的格式信息.
        filterRegistrationBean.addInitParameter(
                "exclusions", "/static/*,*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid,/druid/*");
        //用于session监控页面的用户名显示 需要登录后主动将username注入到session里
        filterRegistrationBean.addInitParameter("principalSessionName", "username");
        return filterRegistrationBean;
    }




}
