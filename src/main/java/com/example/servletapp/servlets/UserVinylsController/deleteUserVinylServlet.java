package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.Dao.UserVinylDaoClass;
import com.example.servletapp.Dao.VinylDaoClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class deleteUserVinylServlet extends HttpServlet {

    private UserVinylDaoClass userVinylDao = UserVinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            Integer id = Integer.parseInt(req.getParameter("id"));
            Integer user_id = Integer.parseInt(req.getParameter("userid"));
            userVinylDao.delete(user_id, id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
