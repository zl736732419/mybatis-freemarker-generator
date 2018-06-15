package ${package}.service.impl;

import ${package}.dao.BaseDao;
import ${package}.dao.${entityUppercase}Dao;
import ${package}.domain.${entityUppercase};
import ${package}.service.${entityUppercase}Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
/**
 * ${entityUppercase}业务逻辑实现
 * @Author ${author}
 * @Date ${createTime?string["HH:mm yyyy-MM-dd"]}
 */
@Service
public class ${entityUppercase}ServiceImpl extends BaseServiceImpl<${entityUppercase}> implements ${entityUppercase}Service {

    @Resource
    private ${entityUppercase}Dao ${entityLowercase}Dao;

    @Override
    protected BaseDao<${entityUppercase}> getBaseDao() {
        return ${entityLowercase}Dao;
    }
}