<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${package}.dao.${entityUppercase}Dao">

	<resultMap id="BaseMap" type="${entityPackageClsName}">
    <#if attrs?? && attrs?size gt 0>
        <#list attrs as attr>
            <@printResultMap attr/>
        </#list>
    </#if>
	</resultMap>

	<sql id="tableName">${tableName}</sql>
	<sql id="Base_Column_List">
        <@printColumnList attrs />
	</sql>
	
	<select id="selectById" resultMap="BaseMap">
		SELECT <include refid="Base_Column_List"/>
		FROM <include refid="tableName"/>
		WHERE ${dbEntityId} = ${r"#{"}${entityId}}
        <#if deleteAttr?? >
        AND ${dbDeleteAttr} = 0
        </#if>
	</select>

    <select id="listPageByFilter" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <where>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printFilterWhere attr />
                </#list>
            </#if>
        </where>
        ORDER BY ${updateTimeAttr} DESC
    </select>

    <select id="listPage" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
        WHERE ${dbDeleteAttr} = 0
        </#if>
        ORDER BY ${updateTimeAttr} DESC
    </select>

    <select id="listByFilter" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <where>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printFilterWhere attr />
                </#list>
            </#if>
        </where>
        ORDER BY ${updateTimeAttr} DESC
    </select>

    <select id="findAll" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
        WHERE ${dbDeleteAttr} = 0
        </#if>
        ORDER BY ${updateTimeAttr} DESC
    </select>

    <update id="deleteById">
        UPDATE <include refid="tableName"/>
        SET ${dbDeleteAttr} = 1
        WHERE ${dbEntityId} = ${r"#{"}${entityId}}
	</update>
	
	<update id="update" parameterType="${entityPackageClsName}">
		UPDATE <include refid="tableName"/>
		<set>
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printWhere attr />
                </#list>
            </#if>
		</set>
		WHERE ${dbEntityId} = ${r"#{"}${entityId}}
	</update>
	
	<insert id="insert" parameterType="${entityPackageClsName}"
			useGeneratedKeys="true" keyProperty="${dbEntityId}">
		INSERT INTO <include refid="tableName"/>(
        <@printColumnList attrs/>
        )
		VALUES (
		<@printAttrValueList attrs/>
		)
	</insert>
</mapper>
<#-- 打印ResultMap字段映射 -->
<#macro printResultMap attr>
    <#if attr.attrName != entityId>
        <result column="${attr.dbFieldName}" property="${attr.attrName}" />
    <#else>
        <id column="${attr.dbFieldName}" property="${attr.attrName}" />
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
            <#if attr.attrName == createTimeAttr || attr.attrName == updateTimeAttr>
                now()<#if attr?index != attrs?size-1>,</#if>
            <#elseif attr.attrName == deleteAttr>
                0<#if attr?index != attrs?size-1>,</#if>
            <#else>
                ${r"#{"}${attr.attrName}}<#if attr?index != attrs?size-1>,</#if>
            </#if>
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
        <#if attr.attrName == updateTimeAttr>
            ${attr.dbFieldName} = now(),
        <#else>
            <if test="${attr.attrName} != null">
                ${attr.dbFieldName} = ${r"#{"}${attr.attrName}},
            </if>
        </#if>
    </#if>
</#macro>

