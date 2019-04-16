package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.ExhibitGuideDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.dao.impl.ExhibitGuideDaoImpl;
import com.softserve.academy.entity.ExhibitEntity;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DragnDropUpdate extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitGuideDao exhibitGuideDao = new ExhibitGuideDaoImpl();
        req.setAttribute("exhibit", exhibitGuideDao.getExhibitsByGuideId());
        req.getRequestDispatcher("addExhibit.jsp").forward(req, resp);
        if(req.getParameter("idsToUpdate")!=null){

        }
        req.getRequestDispatcher("dragndropaddguideexhibit.jsp");
    }
}
