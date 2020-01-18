package org.mountcloud.springcloud.common.mybatis.property;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.env.EnvironmentPostProcessor;
import org.springframework.cloud.bootstrap.config.PropertySourceLocator;
import org.springframework.core.annotation.Order;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MapPropertySource;
import org.springframework.core.env.PropertySource;

import java.util.HashMap;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: MyBatisProperty，这个算是钻一个空子，由于
 * BeanDefinitionRegistryPostProcessors是要优先于BeanFactoryProcessor执行的，先执行BeanDefinitionRegistryPostProcessors中的方法，最后再统一执行BeanFactoryProcessor。
 * 所以MapperScannerConfigurer无法获取到配置，甚至在此版本无法获取Environment，所以只能自定义一个MyBatisProperty来将Environment存起来，供后续使用。
 * 2020/1/17.
 */
@Order(1)
public class MyBatisProperty implements PropertySourceLocator, EnvironmentPostProcessor {

    private static MyBatisProperty myBatisProperty;

    private Environment environment;

    public MyBatisProperty(){
        myBatisProperty = this;
    }

    public static MyBatisProperty getInstance(){
        return myBatisProperty;
    }

    @Override
    public PropertySource<?> locate(Environment environment) {
        this.environment = environment;
        return new MapPropertySource("mybatisproperty",new HashMap<>());
    }

    public Environment getEnvironment() {
        return environment;
    }

    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void postProcessEnvironment(ConfigurableEnvironment environment, SpringApplication application) {

    }
}
