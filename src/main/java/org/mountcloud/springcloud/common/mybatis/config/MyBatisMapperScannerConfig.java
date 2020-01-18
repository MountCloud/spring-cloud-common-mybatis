package org.mountcloud.springcloud.common.mybatis.config;

import org.mountcloud.springcloud.common.mybatis.property.MyBatisProperty;
import org.mybatis.spring.mapper.MapperScannerConfigurer;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.annotation.Order;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO MyBatis的MapperScannerConfigurer
 * 2017/11/6.
 */
public class MyBatisMapperScannerConfig {

    /**
     * 创建 MapperScannerConfigurer
     * 可以自己定义，只要Order小于10就会被使用，则不会自动创建，
     * @return MapperScannerConfigurer
     */
    @Order(10)
    @ConditionalOnMissingBean({MapperScannerConfigurer.class})
    @Bean
    public MapperScannerConfigurer mapperScannerConfigurer() {
        MyBatisProperty myBatisProperty = MyBatisProperty.getInstance();
        String pkg = myBatisProperty.getEnvironment().getProperty("mybatis.mapper.package");
        MapperScannerConfigurer mapperScannerConfigurer = new MapperScannerConfigurer();
        mapperScannerConfigurer.setSqlSessionFactoryBeanName("sqlSessionFactory");
        mapperScannerConfigurer.setBasePackage(pkg);
        return mapperScannerConfigurer;
    }
}
