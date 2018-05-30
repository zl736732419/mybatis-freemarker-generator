<#compress>
package ${package}.dao;

import ${package}.domain.${entityUppercase};
<#if mapperAnnotationStyle == "mapper">
import org.apache.ibatis.annotations.Mapper;
<#else>
import org.springframework.stereotype.Repository;
</#if>

/**
 * @Author ${author}
 * @Date ${createTime?string["HH:mm yyyy-MM-dd"]}
 */
<#if mapperAnnotationStyle == "mapper">
@Mapper
<#else>
@Repository
</#if>
public interface ${entityUppercase}Dao extends BaseDao<${entityUppercase}> {
}
</#compress>