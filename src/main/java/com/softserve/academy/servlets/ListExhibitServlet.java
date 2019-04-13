package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ListExhibitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao=new ExhibitDaoImpl();
        req.setAttribute("exhibits",exhibitDao.readAllExhibits());
        req.getRequestDispatcher("listExhibit.jsp").forward(req,resp);
    }
}
