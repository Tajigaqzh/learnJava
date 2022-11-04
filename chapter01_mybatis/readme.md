# mybatis
## mybatis工作流程与使用的设计模式

1.创建SqlSessionFactoryBuilder对象

is = Resources.getResourceAsStream("SqlMapConfig.xml");
SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();

2.SqlSessionFactoryBuilder对象构建了SqlSessionFactory对象：构造者模式

SqlSessionFactory factory = builder.build(is);

3.SqlSessionFactory对象生产了SqlSession对象：工厂模式

session = factory.openSession();

4.SqlSession对象创建了持久层接口的代理对象：动态代理模式

T mapper = session.getMapper(Class type);

5.代理对象操作数据库

sqlSession也可以直接操作数据库
```
public void testSqlSession(){
    session.selectList("com.haiping.mapper.UserMapper.findAll");
}
```

## mybatis配置文件
xml文件结构

```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="db.properties"/>
<!--    配置属性-->
    <settings>
<!--        全局配置-->
        <setting name="cacheEnabled" value="true"/>
<!--        是否开启缓存-->
    </settings>
    <typeAliases>
        <!-- type:全类名  alias:别名 -->
        <!--        <typeAlias type="com.haiping.pojo.User" alias="user"></typeAlias>-->
        <!--MyBatis对常用类有默认别名支持，比如java.lang.Stirng的别名为string。除此之外，我们也可以使用 <typeAliases> 设置自定义别名。-->
        <package name="com.haiping.pojo"/>
        <!-- 为该包下的所有类配置别名，别名省略包名，和类名相同   -->
    </typeAliases>
    <environments default="mysql">
<!--        环境-->
        <environment id="mysql">
            
            <transactionManager type="JDBC"></transactionManager>
            <!--            事务类型-->
            
            <dataSource type="POOLED">
                <property name="driver" value="${jdbc.driver}"/>
                <property name="url" value="${jdbc.url}"/>
                <property name="username" value="${jdbc.username}"/>
                <property name="password" value="${jdbc.password}"/>
            </dataSource>
            <!--数据源-->
        </environment>
    </environments>

    <mappers>
<!--        映射器-->
        <package name="com.haiping.mapper"/>
<!--        <package name=""/>-->
    </mappers>

</configuration>
```
### dataSource的type属性：
POOLED：使用连接池管理连接，使用MyBatis自带的连接池。

UNPOOLED：不使用连接池，直接由JDBC连接。

JNDI：由JAVAEE服务器管理连接，如果使用Tomcat作为服务器则使用Tomcat自带的连接池管理。

## #和$的区别：
1. '#'表示sql模板的占位符，$表示将字符串拼接到sql模板中。
2. '#'可以防止sql注入，一般能用#就不用$。
3. ${}内部的参数名必须写value。

## 模糊查询
如果不想在调用方法时参数加%，可以使用拼接参数的方式设置Sql：

拼接参数
```xml
<select id="findByUsernameLike"
parameterType="string" resultType="com.haiping.pojo.User">
    select * from user where username like '%${value}%'
</select>
```

使用bind定义参数
```xml
<select id="findByUsernameLike" parameterType="string" resultType="com.haiping.pojo.User">
    <bind name="likeName" value="'%'+username+'%'"/>
    select * from user where username like # {likeName}
</select>
```
###映射文件
resultMap 标签的作用的自定义映射关系。

sql标签用来定义可重用的Sql片段，通过 <include> 引入该片段。如：Sql语句的查询字段起与POJO属性相同的别名，该Sql片段就可以重用。

## 分页查询
顺序传参

Sql中的参数使用arg0，arg1...或param1，param2...表示参数的顺
序。此方法可读性较低，在开发中不建议使用。
```xml
<select id="findPage" resultType="com.haiping.mapper.User">
    select * from user limit #{arg0},#{arg1}
</select>
```
@Param传参

在接口方法的参数列表中通过@Param定义参数名称，在Sql语句中
通过注解中所定义的参数名称指定参数位置。此方式参数比较直观
的，推荐使用。
```java
public interface UserMapper{
    List<User> findPage1(@Param("startIndex") int startIndex, @Param("pageSize")int pageSize);
}
```
```xml
<select id="findPage1" resultType="com.haiping.mapper.User">
 select * from user limit #{startIndex},#{pageSize}
</select>
```
pojo传参
自定义POJO类，该类的属性就是要传递的参数，在SQL语句中绑定
参数时使用POJO的属性名作为参数名即可。此方式推荐使用。

```java
public class PageQuery { 
    private int startIndex;
    private int pageSize;
    // 省略getter/setter/构造方法
}
```
```xml
<select id="findPage2" resultType="com.haiping.pojo.User" parameterType="com.haiping.pojo.PageQuery">
    select * from user limit #{startIndex},#{pageSize}
</select>
```

Map传参
如果不想自定义POJO，可以使用Map作为传递参数的载体，在SQL
语句中绑定参数时使用Map的Key作为参数名即可。此方法推荐使
用。
```java
public interface UserMapper{
    List<User> findPage3(Map<String,Object> params);
}
```
```xml
<select id="findPage3" resultType="com.haiping.pojo.User" parameterType="map">
    select * from user limit #{startIndex},#{pageSize}
</select>
```
## 聚合查询
```java
public interface UserMapper{
    int findCount();
}
```
```xml
<select id="findCount" resultType="int">
    select count(id) from user
</select>
```

## 主键回填
有时我们需要获取新插入数据的主键值。如果数据库中主键是自增的，这时我们就需要使用MyBatis的主键回填功能。

```java
public interface UserMapper {
    void add(User user);
}
```
```xml
<insert id="add" parameterType="com.haiping.user.User">
 <!-- keyProperty:主键属性名，keyColumn:主键列名，resultType:主键类型，order:执行时机 -->
    <selectKey keyProperty="id" keyColumn="id" resultType="int" order="AFTER">
        SELECT LAST_INSERT_ID();
    </selectKey>
    insert into user(username,birthday,sex,address) values(#{username},#{birthday},#{sex},#{address})
</insert>
```
SELECT LAST_INSERT_ID()：查询刚刚插入的记录的主键值，只适用于自增主键，且必须和insert语句一起执行。
```java
public class UserMapperTest{
    @Test
    public void testAdd(){
        User user = new User("张三", new Date(), "男", "北京");
        userMapper.add(user);
    }
    
}
```


