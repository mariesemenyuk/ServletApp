package com.example.servletapp.servlets.VinylController;

import com.example.servletapp.repos.VinylRepository;
import com.example.servletapp.models.VinylModel;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addVinylServlet extends HttpServlet {

    private VinylRepository vinylDao = VinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/vinyl/addVinyl.jsp");
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
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        String country = req.getParameter("country");
        Integer price = Integer.parseInt(req.getParameter("price"));
        VinylModel newVinyl = new VinylModel();
        newVinyl.setAuthor(author);
        newVinyl.setTitle(title);
        newVinyl.setCountryIssued(country);
        newVinyl.setPrice(price);
        vinylDao.save(newVinyl);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
