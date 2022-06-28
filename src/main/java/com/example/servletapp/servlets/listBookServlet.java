package com.example.servletapp.servlets;

import com.example.servletapp.models.BookModel;
import com.example.servletapp.repos.BookRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class listBookServlet extends HttpServlet {

    private BookRepository bookRepository = BookRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<BookModel> listBook = null;
        try {
            listBook = bookRepository.findAll();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        req.setAttribute("bookList", listBook);

        getServletContext().getRequestDispatcher("/listBook.jsp").forward(req, resp);
    }
}
