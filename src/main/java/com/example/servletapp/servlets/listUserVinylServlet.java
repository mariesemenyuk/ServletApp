package com.example.servletapp.servlets;

import com.example.servletapp.Dao.VinylDaoClass;
import com.example.servletapp.models.VinylModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class listUserVinylServlet extends HttpServlet {

    private VinylDaoClass vinylDao = VinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("listVinyl.jsp");

        List<VinylModel> listVinyl = null;
        try {
            listVinyl = vinylDao.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("vinylList", listVinyl);

        getServletContext().getRequestDispatcher("/listVinyl.jsp").forward(req, resp);
    }
}
