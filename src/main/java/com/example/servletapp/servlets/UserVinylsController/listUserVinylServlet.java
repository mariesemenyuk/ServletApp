package com.example.servletapp.servlets.UserVinylsController;

import com.example.servletapp.repos.UserVinylRepository;
import com.example.servletapp.models.VinylModel;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Set;

public class listUserVinylServlet extends HttpServlet {

    private UserVinylRepository userVinylDao = UserVinylRepository.getInstance();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Set<VinylModel> listVinyl = null;
        String user_id = "";
        try {
            user_id = req.getParameter("id");
            listVinyl = userVinylDao.find(Integer.parseInt(user_id));
        } catch (SQLException e) {
            getServletContext().getRequestDispatcher("/notfound.jsp").forward(req, resp);
        }

        req.setAttribute("vinylList", listVinyl);
        req.setAttribute("userId", user_id);

        getServletContext().getRequestDispatcher("/userVinyl/listUserVinyl.jsp").forward(req, resp);
    }
}
