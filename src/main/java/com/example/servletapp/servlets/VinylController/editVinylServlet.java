package com.example.servletapp.servlets.VinylController;

import com.example.servletapp.repos.VinylRepository;
import com.example.servletapp.models.VinylModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class editVinylServlet extends HttpServlet {

    private VinylRepository vinylDao = VinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            VinylModel vinyl = vinylDao.find(id);
            if(vinyl!=null) {
                req.setAttribute("vinyl", vinyl);
                getServletContext().getRequestDispatcher("/vinyl/editVinyl.jsp").forward(req, resp);
            }
            else {
                getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
            }
        }
        catch(Exception ex) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            String id = req.getParameter("id");
            String author = req.getParameter("author");
            String title = req.getParameter("title");
            String country = req.getParameter("country");
            Integer price = Integer.parseInt(req.getParameter("price"));
            VinylModel vinyl = new VinylModel();
            vinyl.setId(Integer.parseInt(id));
            vinyl.setAuthor(author);
            vinyl.setTitle(title);
            vinyl.setCountryIssued(country);
            vinyl.setPrice(price);
            vinylDao.update(vinyl);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
