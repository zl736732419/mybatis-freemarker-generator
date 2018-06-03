package com.zheng.generator.config;

import com.zheng.generator.domain.DBColumn;
import com.zheng.generator.domain.DBTable;
import com.zheng.generator.domain.DataSourceProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * 数据库配置信息
 *
 * @Author zhenglian
 * @Date 2018/6/3 22:43
 */
@Configuration
public class DBConfig {
    @Autowired
    private DataSourceProperties dbProperties;
    @Value("${domain.create.by.db}")
    private Boolean domainFromDB;

    /**
     * 加载数据库驱动
     */
    private void loadDBDriver() throws Exception {
        if (!domainFromDB) {
            return;
        }
        //初始化JDBC驱动并让驱动加载到jvm中  
        Class.forName(dbProperties.getDriverClassName());
    }

    /**
     * 获取连接
     *
     * @return
     */
    private Connection getConnection() throws Exception {
        if (!domainFromDB) {
            return null;
        }
        loadDBDriver();
        Connection conn = DriverManager.getConnection(dbProperties.getUrl(), dbProperties.getUser(),
                dbProperties.getPassword());
        conn.setAutoCommit(true);
        return conn;
    }

    /**
     * 关闭资源
     *
     * @param rs
     * @param stat
     * @param conn
     * @throws Exception
     */
    private void close(ResultSet rs, Statement stat, Connection conn) throws Exception {
        if (Optional.ofNullable(rs).isPresent()) {
            rs.close();
        }
        if (Optional.ofNullable(stat).isPresent()) {
            rs.close();
        }
        if (Optional.ofNullable(conn).isPresent()) {
            rs.close();
        }
    }


    /**
     * 获取表信息
     *
     * @return
     */
    public List<DBTable> getDBTables(String tableNames) {
        Connection conn = null;
        List<DBTable> tables;
        try {
            conn = getConnection();
            DatabaseMetaData dbmd = conn.getMetaData();
            String[] types = {"TABLE"};
            if (StringUtils.isEmpty(tableNames)) {
                tables = parseAllTables(dbmd, types);
            } else {
                tables = parseNamedTables(dbmd, types, tableNames);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException(e.getLocalizedMessage());
        } finally {
            try {
                close(null, null, conn);
            } catch (Exception e) {
                e.printStackTrace();
                throw new RuntimeException(e.getLocalizedMessage());
            }
        }
        return tables;
    }

    /**
     * 解析指定的数据表
     *  @param dbmd
     * @param types
     * @param tableNames
     */
    private List<DBTable> parseNamedTables(DatabaseMetaData dbmd, String[] types, 
                                           String tableNames) throws Exception {
        if (StringUtils.isEmpty(tableNames)) {
            return null;
        }
        List<DBTable> tables = new ArrayList<>();
        String[] tableNameArr = org.springframework.util.StringUtils.tokenizeToStringArray(tableNames, ",; \t\n");
        DBTable table;
        for (String tableName : tableNameArr) {
            table = parseOneDBTable(tableName);
            if (!Optional.ofNullable(table).isPresent()) {
                continue;
            }
            tables.add(table);
        }
        return tables;
    }

    /**
     * 解析全部的表
     *
     * @param dbmd
     * @param types
     */
    private List<DBTable> parseAllTables(DatabaseMetaData dbmd, String[] types) throws Exception {
        ResultSet rs = dbmd.getTables(null, null, "%", types);
        List<DBTable> tables = new ArrayList<>();
        String tableName;
        DBTable table;
        while (rs.next()) {
            tableName = rs.getString("TABLE_NAME");  //表名
            table = parseOneDBTable(tableName);
            if (Optional.ofNullable(table).isPresent()) {
                tables.add(table);
            }
        }
        close(rs, null, null);
        return tables;
    }

    /**
     * 解析指定的数据表
     *
     * @param tableName
     * @return
     */
    private DBTable parseOneDBTable(String tableName) throws Exception {
        if (StringUtils.isEmpty(tableName)) {
            return null;
        }
        Connection conn = getConnection();
        ResultSet rs = null;

        DBTable table = new DBTable();
        table.setTableName(tableName);
        
        try {
            DatabaseMetaData dbmd = conn.getMetaData();
            rs = dbmd.getColumns(null, null, tableName, null);
            List<DBColumn> columns = new ArrayList<>();
            String columnName;
            String columnTypeName;
            DBColumn column;
            while (rs.next()) {
                //列名  
                columnName = rs.getString("COLUMN_NAME");
                //列类型名称
                columnTypeName = rs.getString("TYPE_NAME");
                // 获取简单类型名，并且小写，便于查询枚举
                columnTypeName = columnTypeName.substring(columnTypeName.lastIndexOf(".") + 1).toLowerCase();
                column = new DBColumn();
                column.setName(columnName);
                column.setType(columnTypeName);
                columns.add(column);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } finally {
            close(rs, null, conn);
        }
        return table;
    }


}
