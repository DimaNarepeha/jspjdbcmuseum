package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.entity.ExhibitEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UpdateExhibitServlet extends HttpServlet {
    /**
     * this method loads all fields to the user
     * and provides an opportunity to change fields.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("IN GET");
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibitToUpdate", exhibitDao.getExhibitById(Integer.parseInt(req.getParameter("id"))));
        req.getRequestDispatcher("updateExhibit.jsp").forward(req, resp);
    }

    /**
     * when the fields are updated and submit button is pushed
     * this method will firstly check if there are no empty fields
     * and then will update a database through dao.
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        ExhibitEntity exhibitEntity = exhibitDao.getExhibitById(Integer.parseInt(req.getParameter("id")));
        if (req.getParameter("exhibitName") != null) {
            System.out.println("IN change");
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
                    && !material.equals("") && !technique.equals("")) {
                req.setAttribute("updated", exhibitDao.updateExhibit(exhibitEntity));
                System.out.println("Success = 1");
                req.getRequestDispatcher("updateExhibit.jsp").forward(req, resp);
            } else {
                System.out.println("Success = 0");
                req.setAttribute("updated", 0);
                req.getRequestDispatcher("updateExhibit.jsp").forward(req, resp);
            }
        } else {
            req.getRequestDispatcher("updateExhibit.jsp").forward(req, resp);
        }

    }
}
