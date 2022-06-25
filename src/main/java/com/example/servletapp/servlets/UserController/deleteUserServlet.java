package com.example.servletapp.servlets.UserController;

import com.example.servletapp.Dao.UserDaoClass;
import com.example.servletapp.Dao.VinylDaoClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class deleteUserServlet extends HttpServlet {

    private UserDaoClass userDao = UserDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            userDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            req.setAttribute("error", "User cannot be deleted now. Try to delete user's collection");
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
