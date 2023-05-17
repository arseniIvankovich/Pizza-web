package by.fpmibsu.Dao;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class HikariCPDataSource {

    private static DataSource ds;

    static {
        HikariConfig config = new HikariConfig("/home/arseni/IdeaProjects/Pizza-web/src/main/java/resources/db.properties");
       /* config.setJdbcUrl("jdbc:postgresql://localhost:5432/Pizza-web");
        config.setUsername("Teamlead");
        config.setPassword("password");
        config.setDriverClassName("org.postgresql.Driver");
        config.addDataSourceProperty( "cachePrepStmts" , "true" );
        config.addDataSourceProperty( "prepStmtCacheSize" , "250" );
        config.addDataSourceProperty( "prepStmtCacheSqlLimit" , "2048" );*/
        ds = new HikariDataSource( config );
    }

    public static DataSource getDataSource() {
        return ds;
    }

}