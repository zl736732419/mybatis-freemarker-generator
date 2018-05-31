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
		WHERE id = #{id}
		AND is_delete = 0
	</select>
    <select id="listByFilter" resultMap="BaseMap">
        select <include refid="Base_Column_List"/>
        from <include refid="tableName"/>
        <where>
            <if test="filter.projectName != null and filter.projectName != ''">
                and project_name like concat('%', #{filter.projectName}, '%')
            </if>
            <if test="filter.projectTel != null and filter.projectTel != ''">
                and project_tel like concat('%', #{filter.projectTel}, '%')
            </if>
            <if test="filter.supplierName != null and filter.supplierName != ''">
                and supplier_name like concat('%', #{filter.supplierName}, '%')
            </if>
            <if test="filter.status != null">
                and status = #{filter.status}
            </if>
            <if test="filter.type != null">
                and `type` = #{filter.type}
            </if>
        </where>
        order by create_time asc
    </select>

    <update id="deleteById">
		UPDATE <include refid="tableName"/>
		SET is_delete = 1
		WHERE id = #{id}
	</update>
	
	<update id="update" parameterType="com.carhouse.product.invite.api.domain.InviteProject">
		UPDATE <include refid="tableName"/>
		<set>
			<if test="projectName != null">
				project_name = #{projectName},
			</if>
			<if test="projectType != null">
				project_type = #{projectType},
			</if>
			<if test="fee != null">
                fee = #{fee},
			</if>
			<if test="supplierUserType != null">
				supplier_user_type = #{supplierUserType},
			</if>
			<if test="supplierUserId != null">
                supplier_user_id = #{supplierUserId},
			</if>
            <if test="supplierName != null">
                supplier_name = #{supplierName},
            </if>
            <if test="projectTel != null">
                project_tel = #{projectTel},
            </if>
            <if test="simpleDesc != null">
                simple_desc = #{simpleDesc},
            </if>
            <if test="description != null">
                description = #{description},
            </if>
            <if test="status != null">
                status = #{status},
            </if>
            <if test="startTime != null">
                start_time = #{startTime},
            </if>
            <if test="endTime != null">
                end_time = #{endTime},
            </if>
            <if test="type != null">
                `type` = #{type},
            </if>
            <if test="createTime != null">
                create_time = #{createTime},
            </if>
            <if test="isDelete != null">
                is_delete = #{isDelete},
            </if>
            <if test="sortNum != null">
                sort_num = #{sortNum},
            </if>
			update_time = now()
		</set>
		where id = #{id}
	</update>
	
	<insert id="insert" parameterType="com.carhouse.product.invite.api.domain.InviteProject"
			useGeneratedKeys="true" keyProperty="id">
		INSERT INTO <include refid="tableName"/>
        (id,project_name,project_type,fee,
        supplier_user_type,supplier_user_id,supplier_name,project_tel,
        simple_desc,description,status,start_time,end_time,
        `type`,sort_num,create_time,update_time,is_delete)
		VALUES (#{id}, #{projectName},#{projectType},#{fee},
        #{supplierUserType},#{supplierUserId},#{supplierName},#{projectTel},
        #{simpleDesc}, #{description},#{status},#{startTime},#{endTime},
        #{type}, #{sortNum}, now(), now(), 0)
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

