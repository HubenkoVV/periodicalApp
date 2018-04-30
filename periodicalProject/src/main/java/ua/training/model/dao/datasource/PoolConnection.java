package ua.training.model.dao.datasource;

import org.apache.commons.dbcp.BasicDataSource;

import javax.sql.DataSource;

public class PoolConnection {
    private static volatile DataSource dataSource;
    public static DataSource getDataSource(){

        if (dataSource == null){
            synchronized (PoolConnection.class) {
                if (dataSource == null) {
                    BasicDataSource ds = new BasicDataSource();
                    ds.setUrl("jdbc:mysql://localhost:3306/project_db");
                    ds.setUsername("root");
                    ds.setPassword("root");
                    ds.setMinIdle(5);
                    ds.setMaxIdle(10);
                    ds.setMaxOpenPreparedStatements(100);
                    dataSource = ds;
                }
            }
        }
        return dataSource;

    }
}
