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
		<#if attrs?? && attrs?size gt 0>
            <#list attrs as attr>
                <@printColumnList attr attr?index attrs?size />
            </#list>
        </#if>
	</sql>
	
	<select id="selectById" resultMap="BaseMap">
		SELECT <include refid="Base_Column_List"/>
		FROM <include refid="tableName"/>
		WHERE ${dbEntityId} = ${r"#{"}${entityId}}
        <#if deleteAttr?? >
            ${dbDeleteAttr} = 0
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
        order by ${dbEntityId} asc
    </select>

    <select id="listPage" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
            where ${dbDeleteAttr} = 0
        </#if>
        order by ${dbEntityId} asc
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
        order by ${dbEntityId} asc
    </select>

    <select id="findAll" resultMap="BaseMap">
        SELECT <include refid="Base_Column_List"/>
        FROM <include refid="tableName"/>
        <#if deleteAttr??>
            WHERE ${dbDeleteAttr} = 0
        </#if>
    </select>

    <update id="deleteById">
        <#if deleteAttr??>
            UPDATE <include refid="tableName"/>
            SET ${dbDeleteAttr} = 1
            WHERE ${dbEntityId} = ${r"#{"}${entityId}}
            <#else>
            DELETE FROM <include refid="tableName"/>
            WHERE ${dbEntityId} = ${r"#{"}${entityId}}
        </#if>
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
		where ${dbEntityId} = ${r"#{"}${entityId}}
	</update>
	
	<insert id="insert" parameterType="${entityPackageClsName}"
			useGeneratedKeys="true" keyProperty="${dbEntityId}">
		INSERT INTO <include refid="tableName"/>
        (
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printColumnList attr attr?index attrs?size/>
                </#list>
            </#if>
        )
		VALUES (
            <#if attrs?? && attrs?size gt 0>
                <#list attrs as attr>
                    <@printAttrValueList attr attr?index attrs?size/>
                </#list>
            </#if>
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
<#macro printColumnList attr index size>
    ${attr.dbFieldName}<#if index != size-1>,</#if>
</#macro>

<#-- 打印insert属性值列表 -->
<#macro printAttrValueList attr index size>
    ${r"#{"}${attr.attrName}}<#if index != size-1>,</#if>
</#macro>

<#-- 打印filter条件查询 -->
<#macro printFilterWhere attr>
    <#if attr.attrName != entityId>
        <#if deleteAttr?? && attr.attrName == deleteAttr>
            and ${dbDeleteAttr} = 0
        <#else>
            <if test="filter.${attr.attrName} != null<#if attr.attrType?string == 'string'> and filter.${attr.attrName} != ''</#if>">
                and ${attr.dbFieldName} like concat('%', ${r"#{filter."}${attr.attrName}}, '%')
            </if>
        </#if>
    </#if>
</#macro>

<#macro printWhere attr>
    <#if attr.attrName != entityId>
        <if test="${attr.attrName} != null">
            ${attr.dbFieldName} = ${r"#{"}${attr.attrName},
        </if>
    </#if>

</#macro>

