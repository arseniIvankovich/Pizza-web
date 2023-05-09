package by.fpmibsu.ContextListener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Connection;
import java.sql.SQLException;

import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Services.UserService;
import by.fpmibsu.Services.Util;
public class ContextListener extends Util implements ServletContextListener {

    private UserService userService;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        this.userService = new UserService(new UserDaoImpl(),new OrderDaoImpl(), new AddressDaoImpl(), new RoleDaoImpl());
        ServletContext servletContext = sce.getServletContext();
        servletContext.setAttribute("userService", userService);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        Connection connection = getConnection();
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
