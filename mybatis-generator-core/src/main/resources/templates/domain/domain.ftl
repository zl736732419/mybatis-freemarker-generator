package ${domainPackage};
<#if domainEntity.includeUtilField>
import java.util.Date;
</#if>

/**
 * @Author ${author}
 * @Date ${createTime?string["HH:mm yyyy-MM-dd"]}
 */
public class ${domainEntity.domainName} {

    <#assign fields=domainEntity.fields/>

    <#if fields?? && fields?size gt 0>
        <#list fields as field>
            <@printField field/>
        </#list>
    </#if>

    <#if fields?? && fields?size gt 0>
        <#list fields as field>
            <@printGetter field/>
            <@printSetter field/>
        </#list>
    </#if>
}

<#-- 输出属性声明 -->
<#macro printField field>
    private ${field.fieldType} ${field.fieldName};
</#macro>

<#-- 打印getter方法 -->
<#macro printGetter field>
    public ${field.fieldType} get${field.fieldName?cap_first}() {
        return ${field.fieldName};
    }
</#macro>

<#-- 打印setter方法 -->
<#macro printSetter field>
    public void set${field.fieldName?cap_first}(${field.fieldType} ${field.fieldName}) {
        this.${field.fieldName} = ${field.fieldName};
    }
</#macro>