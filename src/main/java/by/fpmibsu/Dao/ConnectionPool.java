package by.fpmibsu.Dao;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionPool {
    public static ConnectionPool getInstance() {
        return ConnectionPoolSingleton.INSTANCE.get();
    }

    public void open() throws SQLException {
        try
        {
            if(this.con==null || this.con.isClosed())
                this.con = src.getConnection();
        }
        catch(SQLException e) { throw e; }
    }

    public void close() throws SQLException {
        try
        {
            if(this.con!=null && !this.con.isClosed())
                this.con.close();
        }
        catch(SQLException e) { throw e; }
    }

    private DataSource src;
    private Connection con;

    private ConnectionPool() throws Exception {
        try {
            InitialContext ctx = new InitialContext();
            this.src = (DataSource) ctx.lookup("jndi/MYSQL");
        } catch (Exception e) {
            throw e;
        }
    }
    private static class ConnectionPoolSingleton {
        public static final ThreadLocal<ConnectionPool> INSTANCE;

        static {
            ThreadLocal<ConnectionPool> dm;
            try {
                dm = new ThreadLocal<ConnectionPool>() {
                    @Override
                    protected ConnectionPool initialValue() {
                        try {
                            return new ConnectionPool();
                        } catch (Exception e) {
                            return null;
                        }
                    }
                };
            } catch (Exception e) {
                dm = null;
            }
            INSTANCE = dm;
        }
    }
}
