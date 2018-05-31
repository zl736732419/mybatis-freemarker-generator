### 一款mybatis基本CRUD从java代码到mapper.xml配置的生成工具

所有的用户设置都集中在mybatis-freemarker-example/resources/application.properties配置文件中

#### 快速指南
只需要3步就可以快速生成常用的CRUD代码和映射文件
1. 配置扫描实体的包路径:domain.package = com.zheng.example.domain
2. 根据步骤1生成实体包路径，并放入实体类文件，比如com/zheng/example/domain/User.java
4. 运行Application.java中的main方法即可

### 目标文件位置
最终生成的目标文件位于mybatis-freemarker-example项目classpath下：

java文件位于classpath/src/main/java/${domain.package}/../;

mapper配置文件位于classpath/src/main/resources/mappers/下


### 实例
假设用户设置实体包为com.zheng.example.domain,并添加User.java到当前包中，运行程序后会生成如下项目结构：
```
mybatis-generator-example/
  src/main/
    java/
        com/zheng/example/
            dao/
                BaseDao.java
                UserDao.java
            domain/
                User.java
            filter
                UserFilter.java
            service/
                BaseService.java
                UserService.java
                impl/
                    BaseServiceImpl.java
                    UserServiceImpl.java
        Application.java
     resources/
        mappers/
            UserMapper.xml

```
