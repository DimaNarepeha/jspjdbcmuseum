package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.dao.impl.ExhibitGuideDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListRelationsServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitGuideDao exhibitDao=new ExhibitGuideDao();
        req.setAttribute("relations",exhibitDao.readAllGuides());
        req.getRequestDispatcher("relations.jsp").forward(req,resp);
    }
}

