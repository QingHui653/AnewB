# AnewB
### 一些所学技术的demo
#### 所用技术
  基础：Spring+SpringMVC+Mybatis
  1. 配置文件在main.resources
  2. controller在newb.c.controller
  3. dao、mapping、service、model在newb.c.backend
  
* 增加Mybatis 通用mapper，分页插件 newb.c.backend.service.common

* 增加dangdang 分库分表插件 具体查看spring-mybatis.xml 和newb.c.sharding

* 增加guava缓存 查看spring-guava.xml newb.c.cache

* 增加redis缓存 查看spring-redis.xml newb.c.cache

* 增加dubbo 后转为 dubbox 需 zookeeper

* 增加shiro 权限管理

* 增加ActiveMQ 

* 增加quartz定时任务，和测试spring task 定时任务 

* 增加Spring JavaMail

* 增加spring-session-data-redis

* 增加rmi

* 增加webservice cxf
