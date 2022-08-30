package com.ecwo.bot.telegram.config;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;

import javax.sql.DataSource;

/**
 * User: tor
 * Date: 008, 08.04.2022
 * Time: 15:59
 * To change this template use File | Settings | File Templates.
 */
@Slf4j
class DefaultDbConfig {

    protected DataSource hikariDataSource(String tag, DBConfig.SpringDataJdbcProperties properties) {
        log.info("[{}] настройки БД: [{}]", tag, properties.toString());

        HikariDataSource ds = new HikariDataSource();
        ds.setJdbcUrl(properties.getUrl());
        ds.setDriverClassName(properties.getDriver());
        ds.setUsername(properties.getUser());
        ds.setPassword(properties.getPassword());
        ds.setMaximumPoolSize(Integer.parseInt(properties.getPoolSize()));
        return ds;
    }
}
