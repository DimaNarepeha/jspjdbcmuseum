package com.softserve.academy.servlets;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.dao.ExhibitGuideDao;
import com.softserve.academy.dao.impl.ExhibitDaoImpl;
import com.softserve.academy.dao.impl.ExhibitGuideDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public class DragnDropUpdate extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        req.setAttribute("exhibits", exhibitDao.readAllExhibits());
        System.out.println("IN GET");
        req.getRequestDispatcher("ListExhibitWithButtons.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ExhibitGuideDao exhibitGuideDao = new ExhibitGuideDaoImpl();
        ExhibitDao exhibitDao = new ExhibitDaoImpl();
        int id = Integer.parseInt(req.getParameter("id"));

        if (req.getParameter("idsToUpdate") != null) {
            List<String> stringList = Arrays.asList(req.getParameter("idsToUpdate").split(" "));
            HashSet<Integer> ids = new HashSet<>();
            for (int i = 0; i < stringList.size(); i++) {
                try {
                    ids.add(Integer.valueOf(stringList.get(i)));
                } catch (NumberFormatException e) {

                }
            }
            System.out.println(ids);
            exhibitGuideDao.reconnectRelations(ids, id);
            req.setAttribute("success", 1);
        }
        req.setAttribute("idOldExhibit", id);
        req.setAttribute("exhibit", exhibitDao.getExhibitById(id));
        req.setAttribute("currentGuides", exhibitGuideDao.getGuidesByExhibitId(id));
        req.setAttribute("guidesInDatabase", exhibitGuideDao.getGuidesThatAreNotInThisExhibitById(id));
        req.getRequestDispatcher("dragndropaddguideexhibit.jsp").forward(req, resp);
    }
}
