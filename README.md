### 一款mybatis基本CRUD从java代码到mapper.xml配置的生成工具

所有的用户设置都集中在mybatis-freemarker-example/resources/application.properties配置文件中

#### 实现功能
* 根据用户给定的实体类生成对应的业务代码 (Done)
* 用户直接指定数据库连接，通过访问数据库直接生成业务代码 (Done)

#### 快速指南
只需要3步就可以快速生成常用的CRUD代码和映射文件
1. 配置扫描实体的包路径:domain.package = com.zheng.example.domain
2. 根据步骤1生成实体包路径，并放入实体类文件，比如com/zheng/example/domain/User.java
4. 运行Application.java中的main方法即可

### 目标文件位置
最终生成的目标文件位于mybatis-freemarker-example项目classpath下：

java文件位于classpath/src/main/java/${domain.package}/../;

mapper配置文件位于classpath/src/main/resources/mappers/下

### 项目模块
#### mybatis-generator-core 该工具的核心实现代码模块
#### mybatis-generator-example 用户需要关注的代码模块，在该模块中将会生成目标文件
#### mybatis-generator-testdb 通过使用example模块生成的目标文件对数据库操作进行测试


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
### 项目生成的基本方法
本项目生成的Service方法囊括基本的CRUD查询，都实现在基础业务接口BaseService和BaseDao中，其他业务方法需要写到具体的业务类中
```
public interface BaseService<T> {
    /**
     * 插入对象
     * @param record 对象
     * @return 插入条数
     */
    int insert(T record);

    /**
     * 删除对象
     * @param idAttr 删除对象id
     * @return 被删除记录条数
     */
    int deleteById(Integer idAttr);

    /**
     * 更新对象
     * @param record 对象
     * @return 更新的条数
     */
    int update(T record);

    /**
     * 查询对象
     * @param idAttr 对象id
     * @return 对象
     */
    T selectById(Integer idAttr);

    /**
     * 查询所有记录
     * @return
     */
    List<T> findAll();

    /**
    * 根据查询条件分页查询
    * @param filter
    * @param myPageBounds
    * @return
    */
    MyPageList<T> listPageByFilter(@Param("filter") BaseFilter filter, MyPageBounds myPageBounds);

    /**
    * 无条件分页查询
    * @param myPageBounds
    * @return
    */
    MyPageList<T> listPage(MyPageBounds myPageBounds);

    /**
    * 根据查询条件查询列表
    * @param filter
    * @return
    */
    List<T> listByFilter(@Param("filter") BaseFilter filter);
}
```
