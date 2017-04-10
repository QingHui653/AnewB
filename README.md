#### 此项目已废弃不在更新，全线迁至Spring系列，预计使用Spring Boot+Spring Cloud，请查看AnewB_Boot.

#### 此项目 重新启用 Spring Boot进展缓慢。。。

# AnewB
### 一些所学技术的demo
#### 所用技术
  基础：Spring+SpringMVC+Mybatis
  1. 配置文件在main.resources
  2. controller在newb.c.controller
  3. dao、mapping、service、model在newb.c.backend
  
* 增加Mybatis 通用mapper，分页插件 newb.c.backend.service.common,增加swagger2 查看API

* 增加dangdang 分库分表插件 具体查看spring-mybatis.xml 和newb.c.sharding

* 增加guava缓存 查看spring-guava.xml newb.c.cache

* 增加redis缓存 查看spring-redis.xml newb.c.cache

* 增加ehcache缓存 spring-ehcache.xml

* 增加dubbo 后转为 dubbox 需 zookeeper

* 增加shiro 权限管理

* 增加ActiveMQ 

* 增加quartz定时任务，和测试spring task 定时任务 

* 增加Spring JavaMail

* 增加spring-session-data-redis

* 增加rmi，validator校验框架
* 增加webservice cxf

* 增加webmagic 爬虫框架，在newb.c.api下有github和80s的爬取程序

* 增加 atomikos 分布式事务。

#### 其他尝试工具
* 增加Spring 的 Interceptor，增加LogProxy 日志代理切面

* 增加qrcode zxing 二维码工具

* 自行尝试实现SpringMVC，实现个简单的View层工具，自定义JSP标签

* 尝试log4j,log4j2,logback

#### Java基础
在test包中有对Java8新特性，算法，Arrays，Collections，数据结构，日期，设计模式，异常，io，nio，bio，jdbc，NetSocket,netty框架，反射，序列化，动态代理，正则，多线程等等

#### 前端
flv.js,JQ插件select2，chosen使用，打印插件jqprint，上传插件uploadify，表单校验validate, vue.js的简单使用
