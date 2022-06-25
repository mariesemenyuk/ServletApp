package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.Dao.UserVinylDaoClass;
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

public class listUserVinylServlet extends HttpServlet {

    private UserVinylDaoClass userVinylDao = UserVinylDaoClass.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<VinylModel> listVinyl = null;
        String user_id = "";
        try {
            user_id = req.getParameter("id");
            listVinyl = userVinylDao.find(Integer.parseInt(user_id));
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }

        req.setAttribute("vinylList", listVinyl);
        req.setAttribute("user-id", user_id);

        getServletContext().getRequestDispatcher("/listUserVinyl.jsp").forward(req, resp);
    }
}
