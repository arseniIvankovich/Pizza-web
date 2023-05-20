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
        ds = new HikariDataSource( config );
    }

    public static void rebase () {
        HikariConfig config = new HikariConfig("/home/arseni/IdeaProjects/Pizza-web/src/main/java/resources/db1.properties");
        ds = new HikariDataSource( config );
    }
    public static DataSource getDataSource() {
        return ds;
    }

}