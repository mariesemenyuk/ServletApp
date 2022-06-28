package com.example.servletapp.servlets.VinylController;

import com.example.servletapp.models.VinylModel;
import com.example.servletapp.repos.VinylRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class listVinylOver2000Servlet extends HttpServlet {

    private VinylRepository vinylDao = VinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<VinylModel> listVinyl = null;
        try {
            listVinyl = vinylDao.findMoreThanTwoThousand();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("vinylList", listVinyl);

        getServletContext().getRequestDispatcher("/vinyl/listVinyl.jsp").forward(req, resp);
    }
}
