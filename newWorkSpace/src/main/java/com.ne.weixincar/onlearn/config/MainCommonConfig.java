package com.ne.weixincar.onlearn.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@MapperScan(basePackages = {"com.ne.weixincar.onlearn.mapper",""})
public class MainCommonConfig {

}
