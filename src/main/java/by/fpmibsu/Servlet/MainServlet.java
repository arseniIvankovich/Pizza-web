package by.fpmibsu.Servlet;


import by.fpmibsu.Dao.DaoImpl.AddressDaoImpl;
import by.fpmibsu.Dao.DaoImpl.OrderDaoImpl;
import by.fpmibsu.Dao.DaoImpl.RoleDaoImpl;
import by.fpmibsu.Dao.DaoImpl.UserDaoImpl;
import by.fpmibsu.Entity.User;
import by.fpmibsu.Services.RoleService;
import by.fpmibsu.Services.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet("")
public class MainServlet extends HttpServlet {

    protected void doGet (HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/jsp/index.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RoleService roleSetvice = new RoleService();
        UserService userService = new UserService();
        req.setCharacterEncoding("UTF-8");
        String value = req.getParameter("profileButton");
        if (value.equals("Войти"))
            resp.sendRedirect(req.getContextPath() + "/login");
        else{
            User user;
            try {
                user = userService.findEntityById((Long)req.getSession().getAttribute("userId"));
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            if (user.getRole().getRole().equals("Администратор"))
                resp.sendRedirect(req.getContextPath() + "/admin");
            else if (user.getRole().getRole().equals("Курьер"))
                resp.sendRedirect(req.getContextPath() + "/courier");
            else
                resp.sendRedirect(req.getContextPath() + "/profile");
            }
        }

}
