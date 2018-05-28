<#compress>
package ${package}.service.impl;

import ${package}.dao.BaseDao;
import ${package}.dao.${entityUppercase}Dao;
import ${package}.domain.${entityUppercase};
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author ${author}
 * @Date ${createTime?string["HH:mm yyyy-MM-dd"]}
 */
@Service
public class ${entityUppercase}ServiceImpl extends BaseServiceImpl<${entityUppercase}> {

    @Autowired
    private ${entityUppercase}Dao ${entityLowercase}Dao;

    @Override
    protected BaseDao<${entityUppercase}> getBaseDao() {
        return ${entityLowercase}Dao;
    }
}
</#compress>