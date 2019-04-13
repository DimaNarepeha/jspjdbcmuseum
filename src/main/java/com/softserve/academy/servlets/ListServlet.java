package com.softserve.academy.servlets;

import com.softserve.academy.dao.impl.GuideDaoImpl;
import com.softserve.academy.entity.GuideEntity;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class ListServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        GuideDaoImpl model = new GuideDaoImpl();
        List<GuideEntity> guides = model.readAllGuides();
        req.setAttribute("userNames", guides);

        RequestDispatcher requestDispatcher = req.getRequestDispatcher("list.jsp");
        requestDispatcher.forward(req, resp);
    }
}
