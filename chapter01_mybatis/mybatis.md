# mybatis
MyBatis是一个半自动的ORM框架，其本质是对JDBC的封装。使用 MyBatis不需要写JDBC代码，但需要程序员编写SQL语句
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
### xml文件结构
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

### `#和$的区别：`
1. '#'表示sql模板的占位符，$表示将字符串拼接到sql模板中。
2. '#'可以防止sql注入，一般能用#就不用$。
3. ${}内部的参数名必须写value。

### 模糊查询
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

### 映射文件标签
#### `<resultMap>`标签

标签的作用的自定义映射关系。

####`<sql>`标签

标签用来定义可重用的Sql片段，通过 include标签 引入该片段。如：Sql语句的查询字段起与POJO属性相同的别名，该Sql片段就可以重用。

#### 特殊符号
|符号|实体|
|:---:|:---:|
|<|`&lt;`|
|>|`&gt;`|
|&|`&amp;`|
|,|`&apos;`|
|"|`&quot;`|
--------------

####动态SQL `<if>`

标签内的Sql片段在满足条件后才会添加，用法为： `<if test="条件">` 

#### 动态SQL `<where>`
```xml
<select id="findByCondition" resultType="com.haiping.user.User" parameterType="com.haiping.user.User">
    select * from user
    <where>
        <if test="username != null and username.length() != 0">
            username like #{username}
        </if>
        <if test="sex != null and sex.length() != 0">
            and sex = #{sex}
        </if>
    </where>
</select>
```
####动态SQL `<set>`

`<set>` 标签用在update语句中。借助 `<if>` ，可以只对有具体值的字段
进行更新。 `<set>` 会自动添加set关键字，并去掉最后一个if语句中多
余的逗号
```xml
<update id="update" parameterType="com.haiping.user.User">
    update user
    <set>
        <if test="username != null and username.length() > 0">
            username = #{username},
        </if>
        <if test="sex != null and sex.length() > 0">
            sex = #{sex},
        </if>
     </set>
    <where>
        id = #{id}
    </where>
</update>
```
####动态SQL `<choose>、<when>、<otherwise>`

这些标签表示多条件分支，类似JAVA中的 switch...case 。 <choose> 类似switch ， <when> 类似 case ， <otherwise> 类似 default ，用法如下：
```xml
<select id="findByCondition" resultType="com.haiping.user.User" parameterType="com.haiping.user.User">
    select * from user
    <where>
        <choose>
            <when test="username.length()&lt; 5">
                username like #{username}
            </when>
            <when test="username.length()&lt; 10">
                username = #{username}
            </when>
            <otherwise>
                id = 1
            </otherwise>
        </choose>
    </where>
</select>
```
#### 动态SQL`<foreach>`

`<foreach>` 类似JAVA中的for循环，可以遍历集合或数组。 `<foreach>` 有如下属性：
+ collection：遍历的对象类型
+ open：开始的sql语句
+ close：结束的sql语句
+ separator：遍历每项间的分隔符
+ item：表示本次遍历获取的元素，遍历List、Set、数组时表示每项元素，遍历map时表示键值对的值。
+ index：遍历List、数组时表示遍历的索引，遍历map时表示键值对的键。
```xml
<delete id="deleteBatch" parameterType="int">
    delete from user
    <where>
        <foreach open="id in(" close=")" separator="," collection="array" item="id">
            #{id}
        </foreach>
    </where>
</delete>
```
```java
public class TestUser{
    @Test
    public void testDeleteBatch(){
        int[] ids = {9,11};
        userMapper.deleteBatch(ids);
        session.commit();
    }
}
```


`<foreach>` 遍历List和Set的方法是一样的，我们使用 `<foreach>` 遍历List进
行批量添加。
```xml
<insert id="insertBatch" parameterType="com.haiping.user.User">
    insert into user values
    <foreach collection="list" item="user" separator=",">
        (null ,#{user.username},#{user.birthday},#{user.sex},#{user.address})
    </foreach>
</insert>
```
```java
public class Test{
    @Test
    public void testDeleteBatch(){
        int[] ids = {1,2,3};
        userMapper.deleteBatch(ids);
        session.commit();
    }
}
```
遍历map
```java
public interface UserMapper{
    List<User> findUser(@Param("queryMap") Map<String,Object> map);
}
```
```xml
<select id="findUser" parameterType="map" resultType="com.haiping.pojo.User">
    select * from user
    <where>
        <foreach collection="queryMap" separator="and" index="key" item="value">
            ${key} = #{value}
        </foreach>
    </where>
</select>
```
### mybatis缓存
#### 一级缓存
MyBatis一级缓存也叫本地缓存。SqlSession对象中包含一个Executor对象，Executor对象中包含一个PerpetualCache对象，在该对象存放一级缓存数据。
由于一级缓存是在SqlSession对象中，所以只有使用同一个SqlSession对象操作数据库时才能共享一级缓存。
MyBatis的一级缓存是默认开启的，不需要任何的配置.

清空缓存
SqlSession 调用 close() ：操作后SqlSession对象不可用，该对象的缓存数据也不可用。 1
SqlSession 调用 clearCache() / commit() ：操作会清空一级缓存数据。 2
SqlSession 调用增删改方法：操作会清空一级缓存数据，因为增删改后数据库发生改变，缓存数据将不准确

####二级缓存
MyBatis二级缓存也叫全局缓存。数据存放在SqlSessionFactory中，只要是同一个工厂对象创建的SqlSession，在进行查询时都
能共享数据。一般在项目中只有一个SqlSessionFactory对象，所以二级缓存的数据是全项目共享的。
MyBatis一级缓存存放的是对象，二级缓存存放的是对象的数据。所以要求二级缓存存放的POJO必须是可序列化的，也就是要实现Serializable接口。

MyBatis二级缓存默认不开启，手动开启后数据先存放在一级缓存中，只有一级缓存数据清空后，数据才会存到二级缓存中。
SqlSession 调用 clearCache() 无法将数据存到二级缓存中。

####开启二级缓存
+ POJO类实现Serializable接口
+ mybatis配置文件添加```<settings><setting name="cacheEnabled" value="true"/></settings>```由于cacheEnabled默认值是true，所以该设置可以省略。
+ 在映射文件添加 `<cache />` 标签，该映射文件下的所有方法都支持二级缓存。(如果查询到的集合中对象过多，二级缓存只能缓存1024个对
  象引用。可以通过 <cache /> 标签的size属性修改该数量。)

### MyBatis关联查询

### 分页查询
#### 顺序传参

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

#### 聚合查询
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

#### 主键回填
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


