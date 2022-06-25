package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.Dao.UserVinylDao;
import com.example.servletapp.Dao.UserVinylDaoClass;
import com.example.servletapp.Dao.VinylDaoClass;
import com.example.servletapp.models.VinylModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addUserVinylServlet extends HttpServlet {

    private UserVinylDaoClass userVinylDao = UserVinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addUserVinyl.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addVinyl(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addVinyl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        String username = req.getParameter("username");
        String title = req.getParameter("title");
        userVinylDao.save(username, title);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
