package com.example.servletapp.servlets;

import com.example.servletapp.models.CdModel;
import com.example.servletapp.repos.CdRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class listCdServlet extends HttpServlet {

    private CdRepository cdRepository = CdRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<CdModel> listCd = null;
        try {
            listCd = cdRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("cdList", listCd);

        getServletContext().getRequestDispatcher("/listCd.jsp").forward(req, resp);
    }
}
