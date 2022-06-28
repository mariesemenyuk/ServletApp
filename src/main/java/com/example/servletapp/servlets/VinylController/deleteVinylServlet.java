package com.example.servletapp.servlets.VinylController;

import com.example.servletapp.repos.VinylRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class deleteVinylServlet extends HttpServlet {

    private VinylRepository vinylDao = VinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            vinylDao.delete(id);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        } catch (SQLException e) {
            req.setAttribute("error", "Vinyl cannot be deleted");
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
