package org.mountcloud.springcloud.common.mybatis.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.Ordered;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.TransactionManagementConfigurer;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zhanghaishan
 * @version V1.0
 * TODO  JDBC的配置
 * 2017/11/6.
 */
@EnableTransactionManagement
public class JdbcConfig implements TransactionManagementConfigurer {

    @Value("${mybatis.db.url}")
    private String url;

    @Value("${mybatis.db.driverClassName}")
    private String driverClassName;

    @Value("${mybatis.db.username}")
    private String username;

    @Value("${mybatis.db.password}")
    private String password;

    @Value("${mybatis.db.filters}")
    private String filters;

    @Value("${mybatis.db.maxActive}")
    private String maxActive;

    @Value("${mybatis.db.initialSize}")
    private String initialSize;

    @Value("${mybatis.db.maxWait}")
    private String maxWait;

    @Value("${mybatis.db.minIdle}")
    private String minIdle;

    @Value("${mybatis.db.timeBetweenEvictionRunsMillis}")
    private Integer timeBetweenEvictionRunsMillis;

    @Value("${mybatis.db.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${mybatis.db.validationQuery}")
    private String validationQuery;

    @Value("${mybatis.db.testWhileIdle}")
    private Boolean testWhileIdle;

    @Value("${mybatis.db.testOnBorrow}")
    private Boolean testOnBorrow;

    @Value("${mybatis.db.testOnReturn}")
    private Boolean testOnReturn;

    @Value("${mybatis.db.maxOpenPreparedStatements}")
    private Integer maxOpenPreparedStatements;

    @Value("${mybatis.db.removeAbandoned}")
    private Boolean removeAbandoned;

    @Value("${mybatis.db.removeAbandonedTimeout}")
    private Integer removeAbandonedTimeout;

    @Value("${mybatis.db.logAbandoned}")
    private Boolean logAbandoned;

    @Value("${mybatis.mapper.resource}")
    private String mapperResource;

    /**
     * 创建dataSource
     * @return DruidDataSource
     * @throws SQLException ex
     */
    @Bean(name="dataSource")
    public DruidDataSource dataSource() throws SQLException {
        DruidDataSource druidDataSource = new DruidDataSource();

        druidDataSource.setUrl(url);
        druidDataSource.setDriverClassName(driverClassName);
        druidDataSource.setUsername(username);
        druidDataSource.setPassword(password);
        druidDataSource.setFilters(filters);
        druidDataSource.setMaxActive(Integer.parseInt(maxActive));
        druidDataSource.setInitialSize(Integer.parseInt(initialSize));
        druidDataSource.setMaxActive(Integer.parseInt(maxWait));
        druidDataSource.setMinIdle(Integer.parseInt(minIdle));
        druidDataSource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        druidDataSource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        druidDataSource.setValidationQuery(validationQuery);
        druidDataSource.setTestWhileIdle(testWhileIdle);
        druidDataSource.setTestOnReturn(testOnReturn);
        druidDataSource.setMaxOpenPreparedStatements(maxOpenPreparedStatements);
        druidDataSource.setRemoveAbandoned(removeAbandoned);
        druidDataSource.setRemoveAbandonedTimeout(removeAbandonedTimeout);
        druidDataSource.setLogAbandoned(logAbandoned);

        return druidDataSource;
    }

    /**
     * 创建 SqlSessionFactory
     * @param dataSource dataSource
     * @return SqlSessionFactory
     * @throws Exception ex
     */
    @Bean(name = "sqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("dataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        Resource[] resources = new PathMatchingResourcePatternResolver().getResources(mapperResource);
        sqlSessionFactoryBean.setMapperLocations(resources);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 创建 SqlSessionTemplate
     * @param sqlSessionFactory sqlSessionFactory
     * @return SqlSessionTemplate
     */
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    /**
     * 这个是事务的管理器
     * @return PlatformTransactionManager
     */
    @Override
    public PlatformTransactionManager annotationDrivenTransactionManager() {
        try {
            return new DataSourceTransactionManager(dataSource());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
