<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package}.dao.${entityUppercase}Dao">

	<resultMap idAttr="BaseMap" type="${entityPackageClsName}">
    <#if attrs?? && attrs?size gt 0>
        <#list attrs as attr>
            <@printResultMap attr/>
        </#list>
    </#if>
	</resultMap>

	<sql idAttr="tableName">${tableName}</sql>
	<sql idAttr="Base_Column_List">
        <@printColumnList attrs />
	</sql>
	
	<select idAttr="selectById" resultMap="BaseMap">
		SELECT <include refid="Base_Column_List"/>
		FROM <include refid="tableName"/>
		WHERE ${dbEntityId} = ${r"#{"}${entityId}}
        <#if deleteAttr?? >
        AND ${dbDeleteAttr} = 0
        </#if>
	</select>

    <select idAttr="listPageByFilter" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <where>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printFilterWhere attr />
                </#list>
            </#if>
        </where>
        order by ${dbEntityId} asc
    </select>

    <select idAttr="listPage" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
            where ${dbDeleteAttr} = 0
        </#if>
        order by ${dbEntityId} asc
    </select>

    <select idAttr="listByFilter" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <where>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printFilterWhere attr />
                </#list>
            </#if>
        </where>
        order by ${dbEntityId} asc
    </select>

    <select idAttr="findAll" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
            WHERE ${dbDeleteAttr} = 0
        </#if>
    </select>

    <update idAttr="deleteById">
        <#if deleteAttr??>
            UPDATE <include refid="tableName"/>
            SET ${dbDeleteAttr} = 1
            WHERE ${dbEntityId} = ${r"#{"}${entityId}}
            <#else>
            DELETE FROM <include refid="tableName"/>
            WHERE ${dbEntityId} = ${r"#{"}${entityId}}
        </#if>
	</update>
	
	<update idAttr="update" parameterType="${entityPackageClsName}">
		UPDATE <include refid="tableName"/>
		<set>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printWhere attr />
                </#list>
            </#if>
		</set>
		where ${dbEntityId} = ${r"#{"}${entityId}}
	</update>
	
	<insert idAttr="insert" parameterType="${entityPackageClsName}"
			useGeneratedKeys="true" keyProperty="${dbEntityId}">
		INSERT INTO <include refid="tableName"/>
        (<@printColumnList attrs/>)
		VALUES (<@printAttrValueList attrs/>)
	</insert>
</mapper>
<#-- 打印ResultMap字段映射 -->
<#macro printResultMap attr>
    <#if attr.attrName != entityId>
        <result column="${attr.dbFieldName}" property="${attr.attrName}" />
    <#else>
        <idAttr column="${attr.dbFieldName}" property="${attr.attrName}" />
    </#if>
</#macro>

<#-- 打印属性列表 -->
<#macro printColumnList attrs>
    <#if attrs?? && attrs?size gt 0>
        <#list attrs as attr>
            ${attr.dbFieldName}<#if attr?index != attrs?size-1>,</#if>
        </#list>
    </#if>
</#macro>

<#-- 打印insert属性值列表 -->
<#macro printAttrValueList attrs>
    <#if attrs?? && attrs?size gt 0>
        <#list attrs as attr>
            ${r"#{"}${attr.attrName}}<#if attr?index != attrs?size-1>,</#if>
        </#list>
    </#if>

</#macro>

<#-- 打印filter条件查询 -->
<#macro printFilterWhere attr>
    <#if attr.attrName != entityId>
        <#if deleteAttr?? && attr.attrName == deleteAttr>
            and ${dbDeleteAttr} = 0
        <#else>
            <if test="filter.${attr.attrName} != null<#if attr.attrType?lower_case == 'string'> and filter.${attr.attrName} != ''</#if>">
                <#if attr.attrType?lower_case == 'string'>
                    and ${attr.dbFieldName} like concat('%', ${r"#{filter."}${attr.attrName}}, '%')
                <#else>
                    and ${attr.dbFieldName} = ${r"#{filter."}${attr.attrName}}
                </#if>
            </if>
        </#if>
    </#if>
</#macro>

<#macro printWhere attr>
    <#if attr.attrName != entityId>
        <if test="${attr.attrName} != null">
            ${attr.dbFieldName} = ${r"#{"}${attr.attrName}},
        </if>
    </#if>

</#macro>

