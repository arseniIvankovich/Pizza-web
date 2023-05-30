package by.fpmibsu.Filters;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter({"/order", "/profile", "/vacancy"})
public class AuthFilter implements Filter {
    static final Logger courierServletLogger = LogManager.getLogger(AuthFilter.class);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;

        HttpSession session = req.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            courierServletLogger.warn("Enter no authorized user");
            servletRequest.setAttribute("authError", true);
            servletRequest.getServletContext().getRequestDispatcher("/").forward(req, resp);


        }
        filterChain.doFilter(req, resp);
    }
}
