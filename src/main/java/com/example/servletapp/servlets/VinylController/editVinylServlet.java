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

public class editVinylServlet extends HttpServlet {

    private VinylDaoClass vinylDao = VinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        try {
            String id = req.getParameter("id");
            VinylModel vinyl = vinylDao.find(id).get();
            if(vinyl!=null) {
                req.setAttribute("vinyl", vinyl);
                getServletContext().getRequestDispatcher("/editVinyl.jsp").forward(req, resp);
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
            VinylModel vinyl = new VinylModel(Integer.parseInt(id), author, title);
            vinylDao.update(vinyl);
            resp.sendRedirect(req.getContextPath() + "/index.jsp");
        }
        catch(Exception ex) {

            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }
    }
}
