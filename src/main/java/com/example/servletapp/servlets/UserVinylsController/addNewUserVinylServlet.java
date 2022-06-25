package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.Dao.UserVinylDaoClass;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addNewUserVinylServlet extends HttpServlet {

    private UserVinylDaoClass userVinylDao = UserVinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/userVinyl/addNewUserVinyl.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addNewVinyl(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addNewVinyl(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        String username = req.getParameter("username");
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        userVinylDao.saveNew(username, author, title);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
