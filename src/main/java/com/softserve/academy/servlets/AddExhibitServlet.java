package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.entity.ExhibitEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AddExhibitServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibits", exhibitDao.readAllExhibits());
        req.getRequestDispatcher("addExhibit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitEntity exhibitEntity = new ExhibitEntity(0);
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        String exhibitName = req.getParameter("exhibitName");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String hall = req.getParameter("hall");
        String material = req.getParameter("material");
        String technique = req.getParameter("technique");
        exhibitEntity.setExhibit_name(exhibitName);
        exhibitEntity.setFirstName(firstname);
        exhibitEntity.setLastName(lastname);
        exhibitEntity.setMaterial_name(material);
        exhibitEntity.setTechnique_name(technique);
        exhibitEntity.setHall_name(hall);
        if (!exhibitName.equals("") && !firstname.equals("")
                && !lastname.equals("") && !hall.equals("")
                && !material.equals("") && !technique.equals("")
                && exhibitDao.saveExhibit(exhibitEntity)) {
            req.setAttribute("success", 1);
            System.out.println("Success = 1");
        } else {
            System.out.println("Success = 0");
            req.setAttribute("success", 0);
        }
        doGet(req, resp);
    }
}
