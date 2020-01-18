package org.mountcloud.springcloud.common.mybatis.config;

import org.mountcloud.springcloud.common.mybatis.property.MyBatisProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO:
 * 2020/1/17.
 */
@Configuration
public class MyBatisPropertyConfig {

    @Bean
    public MyBatisProperty getMyBatisProperty(){
        return new MyBatisProperty();
    }

}
