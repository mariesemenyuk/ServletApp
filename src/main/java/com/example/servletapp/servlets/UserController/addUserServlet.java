package com.example.servletapp.servlets.UserController;

import com.example.servletapp.repos.UserRepository;
import com.example.servletapp.models.UserModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addUserServlet extends HttpServlet {

    private UserRepository userDao = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/users/addUser.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addUser(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addUser(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        String username = req.getParameter("username");
        String name = req.getParameter("name");
        String password = req.getParameter("password");
        UserModel newUser = new UserModel(username, name, password);
        userDao.save(newUser);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
