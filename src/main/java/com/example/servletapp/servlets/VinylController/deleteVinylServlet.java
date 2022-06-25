package com.example.servletapp.servlets.VinylController;

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

public class deleteVinylServlet extends HttpServlet {

    private VinylDaoClass vinylDao = VinylDaoClass.getInstance();

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
