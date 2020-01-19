# DEMO
https://github.com/MountCloud/spring-cloud-common-mybatis-demo

# USE 使用
```
Application Main class add
@ComponentScan(basePackages={"you project package","org.mountcloud.springcloud"})
```
##  USE spring cloud common ，使用spring cloud common方式
```
<parent>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-common-parent</artifactId>
  <version>2.2.1.RELEASE-Hoxton.RELEASE-1.1</version>
</parent>
<dependency>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-common-mybatis</artifactId>
</dependency>
```
## OR Use alone，或者单独引用。
```
<dependency>
  <groupId>org.mountcloud</groupId>
  <artifactId>spring-cloud-common-mybatis</artifactId>
  <version>1.1</version>
</dependency>
```

# USE and Config,使用与配置
  You need to use this annotation to enable mybatis.需要使用此注解开启mybatis功能。
```
@EnableMyBatis

need config，需要配置。

mybatis:
   db:
      minIdle: 10
      validationQuery: SELECT 1
      initialSize: 1
      maxWait: 60000
      filters: stat
      url: jdbc:mysql://127.0.0.1:3306/test?useUnicode=true&characterEncoding=UTF-8&tinyInt1isBit=false&serverTimezone=UTC
      username: root
      password: 123456
      logAbandoned: true
      maxIdle: 15
      testWhileIdle: true
      maxOpenPreparedStatements: 20
      testOnBorrow: false
      removeAbandoned: true
      timeBetweenEvictionRunsMillis: 60000
      minEvictableIdleTimeMillis: 300000
      testOnReturn: false
      removeAbandonedTimeout: 1800
      driverClassName: com.mysql.cj.jdbc.Driver
      maxActive: 200
   mapper:
      resource: classpath:org/mountcloud/testmysql/mapper/*.xml
      package: org.mountcloud.testmysql.mapper.**
```

# NOTE
   For the mysql project, I prefer to use mybatis, so I integrated mybtis into the spring cloud, so that the spring cloud project has mybatis easily.
  
# Conditions of Use
## Condition 1
   Depends on:
```
https://github.com/MountCloud/spring-project-common
https://github.com/MountCloud/mybatisplugin

You can refer to the pom.xml configuration in the demo dependencies.
```
## Condition 2
   Depends on mybatis-generator-maven-plugin, you can refer to the plugin configuration in the pom.xml of the demo.

# 描述
  对于mysql的项目我比较喜欢使用mybatis，于是将mybtis集成到了spring cloud中，让spring cloud项目轻松拥有mybatis。
  
# 使用条件
## 条件1
  依赖以下项目：
```
https://github.com/MountCloud/spring-project-common
https://github.com/MountCloud/mybatisplugin

可以参考demo中的pom.xml配置dependencies。
```
## 条件2
  依赖mybatis-generator-maven-plugin，可以参考demo的pom.xml中的plugins配置。
  
# Files 文件列表
```
.
├── .gitignore
├── pom.xml
├── README.md
└── src
    ├── main
    │   ├── java
    │   │   └── org
    │   │       └── mountcloud
    │   │           └── springcloud
    │   │               └── common
    │   │                   └── mybatis
    │   │                       ├── config
    │   │                       │   ├── JdbcConfig.java
    │   │                       │   ├── MyBatisMapperScannerConfig.java
    │   │                       │   └── MyBatisPropertyConfig.java
    │   │                       ├── dao
    │   │                       │   └── BaseDao.java
    │   │                       ├── EnableMyBatis.java
    │   │                       ├── entity
    │   │                       │   └── BaseExample.java
    │   │                       ├── mapper
    │   │                       │   └── BaseMapper.java
    │   │                       ├── property
    │   │                       │   └── MyBatisProperty.java
    │   │                       └── service
    │   │                           └── BaseMybatisService.java
    │   └── resources
    │       └── META-INF
    │           └── spring.factories
    └── test
        └── java
            └── org
                └── mountcloud
                    └── springcloud
                        └── common
                            └── mybatis
                                └── util
                                    └── TestClass.java
```
