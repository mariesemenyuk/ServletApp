package com.example.servletapp.servlets;

import com.example.servletapp.models.BookModel;
import com.example.servletapp.models.VinylModel;
import com.example.servletapp.repos.BookRepository;
import com.example.servletapp.repos.VinylRepository;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

public class addBookServlet extends HttpServlet {

    private BookRepository bookRepository = BookRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("/addBook.jsp");
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
        String publisher = req.getParameter("publisher");
        BookModel newBook = new BookModel();
        newBook.setAuthor(author);
        newBook.setTitle(title);
        newBook.setPublisher(publisher);
        bookRepository.save(newBook);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(req, resp);
    }
}
