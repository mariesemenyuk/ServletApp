package com.example.servletapp.servlets.UserController;

import com.example.servletapp.repos.UserRepository;
import com.example.servletapp.models.UserModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class listUserServlet extends HttpServlet {

    private UserRepository userDao = UserRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<UserModel> listUsers = null;
        try {
            listUsers = userDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("userList", listUsers);

        getServletContext().getRequestDispatcher("/users/listUsers.jsp").forward(req, resp);
    }
}
