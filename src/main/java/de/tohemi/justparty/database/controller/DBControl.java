package de.tohemi.justparty.database.controller;

import de.tohemi.justparty.util.SystemProperties;
import de.tohemi.justparty.util.logger.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.datasource.DataSourceUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by Micha Piertzik on 10.05.2016.
 */
public abstract class DBControl {
    protected static final Logger LOGGER = SystemProperties.getLogger();

    protected DataSource getDataSource() {
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring-database.xml");
        DataSource dataSource = (DataSource) ctx.getBean("dataSource");
        ctx.close();
        return dataSource;
    }

    protected void releaseConnection(DataSource ds, Connection c) {
        try {
            c.close();
        } catch (SQLException e) {
            LOGGER.logException(e, "");
        }
        DataSourceUtils.releaseConnection(c, ds);
    }
}
