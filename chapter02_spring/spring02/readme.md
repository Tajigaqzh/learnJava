# Spring+mybatis

## 扫描mapper的方式：
- 一、xml方式
  - 1.application.xml中设置mapperScan的basePackage
  - 2.mybatisConfig.xml中设置<mappers>，可以扫描一个包也可以单独一个一个配置
  - 3.`<mybatis:scan/>`方式。需要引入xmlns:mybatis="http://mybatis.org/schema/mybatis-spring"
- 二、注解方式

  - 在配置类上添加@MapperScan注解，并指定包路径