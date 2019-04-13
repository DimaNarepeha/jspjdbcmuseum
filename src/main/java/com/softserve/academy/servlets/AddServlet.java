package com.softserve.academy.servlets;

import com.softserve.academy.dao.impl.GuideDaoImpl;
import com.softserve.academy.entity.GuideEntity;
import com.sun.net.httpserver.HttpServer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class AddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException{
        PrintWriter writer = resp.getWriter();
        //writer.println("Method GET from AddServlet");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("add.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("firstname");
        String password = req.getParameter("lastname");
        GuideEntity guide = new GuideEntity(name, password);
      GuideDaoImpl model = new GuideDaoImpl();
        model.saveGuide(guide);
        req.setAttribute("userName", name);
        doGet(req, resp);
    }
}
