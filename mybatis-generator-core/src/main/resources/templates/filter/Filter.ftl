package ${package}.filter;

import java.io.Serializable;

/**
 * ${entityUppercase}实体条件查询过滤器
 * @Author ${author}
 * @Date ${createTime?string["HH:mm yyyy-MM-dd"]}
 */
public class ${entityUppercase}Filter extends AbstractFilter {
    <#if attrs?? && attrs?size gt 0>
        <#list attrs as attr>
            <#if attr.attrName != entityId>
                <@printAttr attr/>
            </#if>
        </#list>

        <#list attrs as attr>
            <#if attr.attrName != entityId>
                <@printGetter attr/>
                <@printSetter attr/>
            </#if>
        </#list>
    </#if>
}

<#-- 打印属性 -->
<#macro printAttr attr>
    private ${attr.attrType} ${attr.attrName};
</#macro>

<#-- 打印getter方法 -->
<#macro printGetter attr>
    public ${attr.attrType} get${attr.attrName?cap_first}() {
        return ${attr.attrName};
    }
</#macro>

<#-- 打印setter方法 -->
<#macro printSetter attr>
    public void set${attr.attrName?cap_first}(${attr.attrType} ${attr.attrName}) {
        this.${attr.attrName} = ${attr.attrName};
    }
</#macro>