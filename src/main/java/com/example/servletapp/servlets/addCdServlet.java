package com.example.servletapp.servlets;

import com.example.servletapp.models.BookModel;
import com.example.servletapp.models.CdModel;
import com.example.servletapp.repos.BookRepository;
import com.example.servletapp.repos.CdRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addCdServlet extends HttpServlet {

    private CdRepository cdRepository = CdRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addCd.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            addCd(req, resp);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    private void addCd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException, SQLException{
        String author = req.getParameter("author");
        String title = req.getParameter("title");
        Integer yearIssued = Integer.parseInt(req.getParameter("year"));
        CdModel newCd = new CdModel();
        newCd.setAuthor(author);
        newCd.setTitle(title);
        newCd.setYearIssued(yearIssued);
        cdRepository.save(newCd);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
