package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.Dao.VinylDaoClass;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class deleteUserVinylServlet extends HttpServlet {

    private VinylDaoClass vinylDao = VinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            String user_id = req.getParameter("user-id");
            vinylDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
