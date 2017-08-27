package com.me.testing

import groovy.sql.Sql
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType

import javax.sql.DataSource

@Configuration
class AppConfig {


    @Bean
    public Sql Sql(DataSource dataSource) {
        return new Sql(dataSource);
    }

    @Bean
    public DataSource datasource() {
        return (DataSource)new EmbeddedDatabaseBuilder().setType(EmbeddedDatabaseType.H2).addScript("property.sql").build();
    }

}
