# Spring
## Spring概念
Spring是一个开源框架，为简化企业级开发而生。它以IOC（控制 反转）和AOP（面向切面）为思想内核，提供了控制层SpringMVC、数据层SpringData、服务层
事务管理等众多技术，并可以整合众多第三方框架。
## 官网
[spring官网，点击链接跳转](https://spring.io)

## Spring构成
![Alt](images/spring.png)
+ Core Container：Spring核心模块，任何功能的使用都离不开该模块，是其他模块建立的基础。
+ Data Access/Integration：该模块提供了数据持久化的相应功能。
+ Web：该模块提供了web开发的相应功能。
+ AOP：提供了面向切面编程实现
+ Aspects：提供与AspectJ框架的集成，该框架是一个面向切面编程框架。
+ Instrumentation：提供了类工具的支持和类加载器的实现，可以在特定的应用服务器中使用。
+ Messaging：为Spring框架集成一些基础的报文传送应用
+ Test：提供与测试框架的集成

## springIOC
在传统开发过程中，对象实例的创建是由调用者管理的，这会产生两个问题：
- 浪费资源：每一次调用方法时即会创建一个对象，如果不断调用方法则会创建大量对象。
- 代码耦合度高：随着项目的迭代，新增实现类需要对原有代码结构进行大量更改
IOC就是把创建销毁对象的工作从开发者自己转交给了容器，当spring启动时先读取配置文件，创建容器并存储起来。
### 容器接口

BeanFactory：BeanFactory是Spring容器中的顶层接口，它可以对Bean对象进行管理。

ApplicationContext：ApplicationContext是BeanFactory的子接口。它除了继承 BeanFactory的所有功能外，还添加了对国际
化、资源访问、事件传播等方面的良好支持。
ApplicationContext有以下三个常用实现类：
- ClassPathXmlApplicationContext：该类可以从项目中读取配置文件
- FileSystemXmlApplicationContext：该类从磁盘中读取配置文件
- AnnotationConfigApplicationContext：使用该类不读取配置文件，而是会读取注解

### IOC创建对象方式

#### 使用构造方法
Spring默认使用类的空参构造方法创建bean
```xml
<bean id="studentDao" class="com.hp.dao.StudentDaoImpl"/>
        <!--构造方法创建bean对象-->
```
#### 使用工厂类的方法
Spring可以调用工厂类的方法创建bean
```xml
<beans>
    <bean id="studentFactory" class="com.hp.dao.StudentDaoFactory2"/>
    <bean id="studentDao2" factory-bean="studentFactory" factory-method="getStudentDao"/>
    <!--普通工厂创建-->
</beans>
```
#### 使用工厂类的静态方法
Spring可以调用工厂类的静态方法创建bean
```xml
<bean id="studentDao1" class="com.hp.dao.StudentDaoFactory1" factory-method="getStudentDao"/>
    <!--静态工厂创建bean对象-->
```

### 创建对象的策略
Spring通过配置 <bean> 中的 scope 属性设置对象的创建策略，共有五种创建策略
- singleton：单例，默认策略。整个项目只会创建一个对象，通过 <bean> 中的 lazy-init 属性可以设置单例对象的创建时机
- prototype：多例，每次从容器中获取时都会创建对象。
- request：每次请求创建一个对象，只在web环境有效。
- session 每次会话创建一个对象，只在web环境有效。
- gloabal-session 一次集群环境的会话创建一个对象，只在web环境有效。

### 销毁机制
- singleton：对象随着容器的销毁而销毁。
- prototype：使用JAVA垃圾回收机制销毁对象。
- request：当处理请求结束，bean实例将被销毁。
- session：当HTTP Session最终被废弃的时候，bean也会被销毁掉。
- gloabal-session：集群环境下的session销毁，bean实例也将被销毁。

### 生命周期方法
Bean对象的生命周期包含创建——使用——销毁，Spring可以配置
Bean对象在创建和销毁时自动执行的方法
```xml
<!-- init-method:创建对象时执行的方法 
destroy-method:销毁对象时执行的方法 -->
<bean id="studentDao"
      class="com.hp.dao.StudentDaoImpl2"
      scope="singleton"
      init-method="init" 
      destroy-method="destroy">
    
</bean>
```





### 获取bean对象
- 通过id/name获取
```java
public class TestContainer {
    @Test
    public void container1(){
        StudentDao studentDao = (StudentDao)context.getBean("studentDao");
    }
}
```
- 通过类型获取，不需要强制转换

```java
public class TestContainer {
    @Test
    public void container2(){
        StudentDao studentDao = context.getBean(StudentDao.class);
    }
}
```
- 通过类型+id/name获取
```xml
<beans>
    <bean name="studentDao"
          class="com.hp.dao.StudentDaoImpl2">
    </bean>
    <bean name="studentDao1"
          class="com.hp.dao.StudentDaoImpl">
    </bean>
</beans>
```
```java
public class TestContainer {
    @Test
    public void container2(){
        StudentDao studentDao = context.getBean("studentDao",StudentDao.class);
    }
}
```

## DI依赖注入
依赖注入（Dependency Injection，简称DI），它是Spring控制反转思想的具体实现
- setter注入
- 自动注入
### 依赖注入的类型
- Bean
- 基本数据类型
- String
- List
- Set
- Map
- Properties

