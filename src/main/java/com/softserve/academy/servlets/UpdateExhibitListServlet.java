package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.entity.ExhibitEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExhibitListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibits", exhibitDao.readAllExhibits());
        req.getRequestDispatcher("updateExhibitList.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("/updateExhibit?id="+req.getParameter("id"));
    }
}
