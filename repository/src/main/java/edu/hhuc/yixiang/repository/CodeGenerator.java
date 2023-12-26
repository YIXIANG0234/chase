package edu.hhuc.yixiang.repository;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.zaxxer.hikari.HikariDataSource;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 10:48:08
 */
public class CodeGenerator {
    public static void main(String[] args) {
        // 配置数据源
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/starry?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        // 通过datasource和globalConfig创建代码生成器
        Generator generator = new Generator(dataSource, createGlobalConfigUseStyle1());

        // 生成代码
        generator.generate();
    }
    public static GlobalConfig createGlobalConfigUseStyle1() {
        //创建配置内容
        GlobalConfig globalConfig = new GlobalConfig();
        //设置根包
        globalConfig.setSourceDir("./common/src/main/java");
        globalConfig.setBasePackage("edu.hhuc.yixiang.common");
        //设置表前缀和只生成哪些表
        // globalConfig.setTablePrefix("");
        globalConfig.setGenerateTable("user");

        //设置生成 entity 并启用 Lombok
        globalConfig.setEntityGenerateEnable(true);
        globalConfig.setEntityWithLombok(true);

        //设置生成 mapper
        globalConfig.setMapperGenerateEnable(true);

        //可以单独配置某个列
        // ColumnConfig columnConfig = new ColumnConfig();
        // columnConfig.setColumnName("tenant_id");
        // columnConfig.setLarge(true);
        // columnConfig.setVersion(true);
        // globalConfig.setColumnConfig("tb_account", columnConfig);

        return globalConfig;
    }
}
