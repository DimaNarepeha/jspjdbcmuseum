package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteExhibitServlet extends HttpServlet {
    /**
     * the page is firstly loaded by this method.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibits", exhibitDao.readAllExhibits());
        req.getRequestDispatcher("deleteExhibit.jsp").forward(req, resp);
    }

    /**
     * when the page is loaded
     * user will select exhibits to delete
     * and this method wil do the deletion from database through dao.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        int amountOfDeleted = 0;
        if (req.getParameter("toDelete") != null) {
            String[] parametersToDelete = req.getParameterValues("toDelete");
            for (String str : parametersToDelete) {
                amountOfDeleted += exhibitDao.deleteExhibit(Integer.parseInt(str));
            }
        }
        req.setAttribute("deleted", amountOfDeleted);
        doGet(req, resp);
    }
}
