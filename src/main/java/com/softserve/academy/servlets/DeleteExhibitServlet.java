package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DeleteExhibitServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibits", exhibitDao.readAllExhibits());
        req.getRequestDispatcher("deleteExhibit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        int amountOfDeleted = 0;
        String[] parametersToDelete = req.getParameterValues("toDelete");
        for (String str : parametersToDelete) {
            amountOfDeleted += exhibitDao.deleteExhibit(Integer.parseInt(str));
        }
        req.setAttribute("deleted", amountOfDeleted);
        doGet(req, resp);
    }
}
