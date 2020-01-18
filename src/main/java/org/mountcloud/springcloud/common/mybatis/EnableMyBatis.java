package org.mountcloud.springcloud.common.mybatis;

import org.mountcloud.springcloud.common.mybatis.config.JdbcConfig;
import org.mountcloud.springcloud.common.mybatis.config.MyBatisMapperScannerConfig;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO: 是否启用MyuBatis
 * 2020/1/17.
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({JdbcConfig.class,MyBatisMapperScannerConfig.class})
public @interface EnableMyBatis {
}
