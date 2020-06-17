package com.ne.weixincar.onlearn.config;




import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author:
 * @Date: 2020/3/9
 * Time: 17:58
 * description:
 */
@Configuration
public class MybatisplusConfiguration {



    /*
     * 分页插件，自动识别数据库类型
     * 多租户，请参考官网【插件扩展】
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor paginationInterceptor = new PaginationInterceptor();
        // 开启 PageHelper 的支持
        //paginationInterceptor.setLocalPage(true);
        return paginationInterceptor;
    }

    /**
     * SQL执行效率插件
     *  mp3.2.0 以后取消了这个插件。。
     */
/*
    @Bean
    @Profile({"dev","qa"})// 设置 dev test 环境开启
    public PerformanceInterceptor performanceInterceptor() {
        PerformanceInterceptor performanceInterceptor = new PerformanceInterceptor();
        performanceInterceptor.setMaxTime(1000);
        performanceInterceptor.setFormat(true);
        return performanceInterceptor;
    }
*/


}
