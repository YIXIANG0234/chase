package edu.hhuc.yixiang.repository;

import com.mybatisflex.codegen.Generator;
import com.mybatisflex.codegen.config.GlobalConfig;
import com.mybatisflex.codegen.config.TableDefConfig;
import com.mybatisflex.codegen.dialect.IDialect;
import com.mybatisflex.codegen.dialect.JdbcTypeMapping;
import com.zaxxer.hikari.HikariDataSource;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * @author guwanghuai
 * @version 1.0
 * @project chase
 * @description
 * @date 2023/12/26 10:48:08
 */
public class CodeGenerator {
    public static void main(String[] args) {
        HikariDataSource dataSource = new HikariDataSource();
        dataSource.setJdbcUrl("jdbc:mysql://127.0.0.1:3306/starry?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        Generator generator = new Generator(dataSource, createGlobalConfigUseStyle1(), IDialect.MYSQL);

        generator.generate();
    }
    public static GlobalConfig createGlobalConfigUseStyle1() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.getPackageConfig().setSourceDir("./common/src/main/java");
        globalConfig.getPackageConfig().setBasePackage("edu.hhuc.yixiang.common");
        globalConfig.getPackageConfig().setMapperXmlPath("./common/src/main/resources/mapper");

        // entity生成设置
        globalConfig.enableEntity();
        globalConfig.getEntityConfig().setOverwriteEnable(true);
        globalConfig.getEntityConfig().setWithLombok(true);

        // mapper生成设置，不覆盖
        globalConfig.enableMapper();

        // TableDef生成设置
        globalConfig.enableTableDef();
        globalConfig.getTableDefConfig().setPropertiesNameStyle(TableDefConfig.NameStyle.UPPER_CASE);
        globalConfig.getTableDefConfig().setOverwriteEnable(true);

        // Mapper xml文件，不覆盖，自定义sql写在这里面
        globalConfig.enableMapperXml();
        globalConfig.getMapperXmlConfig().setOverwriteEnable(false);

        // 设置逻辑删除字段，和需要生成的表
        globalConfig.getStrategyConfig().setLogicDeleteColumn("is_deleted");
        // 不生成的表，生成的表
        globalConfig.getStrategyConfig().setUnGenerateTable("leaf_alloc");
        // globalConfig.getStrategyConfig().setGenerateTable("user");

        // 设置时间类型为Date
        JdbcTypeMapping.registerMapping(LocalDateTime.class, Date.class);
        JdbcTypeMapping.registerMapping(BigInteger.class, Long.class);
        return globalConfig;
    }
}
