package com.example.servletapp.servlets.VinylController;

import com.example.servletapp.repos.VinylRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class addMillionsVinylServlet extends HttpServlet {

    private VinylRepository vinylDao = VinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        vinylDao.addMillionsVinyls();

        resp.sendRedirect(req.getContextPath() + "/index.jsp");
    }
}
