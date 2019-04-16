package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitGuideDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class ExhibitGuideDaoImpl implements ExhibitGuideDao {
    @Override
    public List<GuideEntity> getGuidesByExhibitId(int id) {

        Connection conn = Database.getInstance().getConnection();
        List<GuideEntity> guides = new ArrayList<>();
        try (PreparedStatement getGuides = conn.prepareStatement("SELECT g.id_guide, firstname,lastname,position_name  FROM guide g join guide_position p on  +\n" +
                "                g.id_position=p.id_guide_position " +
                " INNER JOIN exhibit_guide ON exhibit_guide.id_guide=g.id_guide " +
                "WHERE exhibit_guide.id_exhibit=?")) {
            getGuides.setInt(1, id);
            ResultSet rs = getGuides.executeQuery();
            while (rs.next()) {
                GuideEntity guideEntity = new GuideEntity(rs.getInt("id_guide"), rs.getString("firstname"), rs.getString("lastname"));
                guides.add(guideEntity);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Database fail");
            return null;
        }
        return guides;
    }

    @Override
    public List<ExhibitEntity> getExhibitsByGuideId(int id) {
        List<ExhibitEntity> result = new ArrayList<>();
        try (PreparedStatement selectFromExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT exhibit.id_exhibit,exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit\n" +
                        "INNER JOIN hall ON hall.id_hall=exhibit.id_hall\n" +
                        "INNER JOIN material ON material.id_material=exhibit.id_material\n" +
                        "INNER JOIN technique ON technique.id_technique=exhibit.id_technique\n" +
                        "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit\n" +
                        "INNER JOIN author ON author.id_author=author_exhibit.id_author\n" +
                        "INNER JOIN exhibit_guide ON exhibit_guide.id_exhibit=exhibit.id_exhibit " +
                        "WHERE exhibit_guide.id_guide = ?;")) {
            selectFromExhibit.setInt(1, id);
            ResultSet resultSet = selectFromExhibit.executeQuery();

            while (resultSet.next()) {
                ExhibitEntity exhibitEntity = new ExhibitEntity(resultSet.getInt(1));
                exhibitEntity.setExhibit_name(resultSet.getString(2));
                exhibitEntity.setHall_name(resultSet.getString(3));
                exhibitEntity.setFirstName(resultSet.getString(4));
                exhibitEntity.setLastName(resultSet.getString(5));
                exhibitEntity.setMaterial_name(resultSet.getString(6));
                exhibitEntity.setTechnique_name(resultSet.getString(7));
                result.add(exhibitEntity);
            }
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
        return result;
    }

    @Override
    public List<GuideEntity> getGuidesThatAreNotInThisExhibitById(int id) {
        Connection conn = Database.getInstance().getConnection();
        try (PreparedStatement getGuides = conn.prepareStatement("SELECT id_guide, firstname,lastname,position_name  FROM guide g join guide_position p on  +\n" +
                "                g.id_position=p.id_guide_position " +
                "INNER JOIN exhibit_guide ON exhibit_guide.id_guide=g.id_guide " +
                " where NOT g.id_exhibit=?")) {
            List<GuideEntity> guideEntities = new GuideDaoImpl().readAllGuides();
            List<GuideEntity> guidesByExhibitId = getGuidesByExhibitId(id);
            Iterator iterator = guidesByExhibitId.iterator();
            while (iterator.hasNext()) {
                GuideEntity g = (GuideEntity) iterator.next();
                Iterator iterator1 = guideEntities.iterator();
                while (iterator1.hasNext()) {
                    GuideEntity g2 = (GuideEntity) iterator1.next();
                    if (g.getId() == g2.getId()) {
                        iterator1.remove();
                    }
                }
            }
            return guideEntities;
        } catch (SQLException e) {
            System.out.println("Database fail");
            return null;
        }
    }

    @Override
    public int reconnectRelations(HashSet<Integer> guidesToExhibit, int exhibitId) {
        int result = 0; //if null then nothing was changed
        HashSet<Integer> guidesThatAlreadyPresent = new HashSet<>();
        try (PreparedStatement selectAllConnections = Database.getInstance() //between guide and exhibit
                .getConnection()
                .prepareStatement("SELECT exhibit_guide.id_guide FROM exhibit\n" +
                        "INNER JOIN exhibit_guide ON exhibit_guide.id_exhibit=exhibit.id_exhibit " +
                        "WHERE exhibit_guide.id_exhibit = ?;");
             PreparedStatement deleteFromGuideExhibit = Database.getInstance() //between guide and exhibit
                     .getConnection()
                     .prepareStatement("DELETE FROM exhibit_guide WHERE id_guide = ? AND id_exhibit=? ;");
             PreparedStatement addGuideExhibit = Database.getInstance() //between guide and exhibit
                     .getConnection()
                     .prepareStatement("INSERT INTO exhibit_guide(id_guide,id_exhibit)" +
                             "VALUES (?,?)")) {
            selectAllConnections.setInt(1, exhibitId);
            ResultSet resultSet = selectAllConnections.executeQuery();
            while (resultSet.next()) {
                guidesThatAlreadyPresent.add(resultSet.getInt(1));
            }

            Iterator iterator = guidesThatAlreadyPresent.iterator();
//            boolean isIdentical = true;
//            while (iterator.hasNext()) {//check if identical
//                if (!guidesToExhibit.contains(iterator.next())) {
//                    isIdentical = false;
//                    break;
//                }
//            }
//            if (isIdentical) {//if identical then quit
//                System.out.println("NOOOO");
//                return result;
//            }
            iterator = guidesThatAlreadyPresent.iterator();
            while (iterator.hasNext()) {
                deleteFromGuideExhibit.setInt(1, (Integer) iterator.next());
                deleteFromGuideExhibit.setInt(2, exhibitId);
                deleteFromGuideExhibit.execute();
            }
            iterator = guidesToExhibit.iterator();
            while (iterator.hasNext()) {
                addGuideExhibit.setInt(1, (Integer) iterator.next());
                addGuideExhibit.setInt(2, exhibitId);
                addGuideExhibit.execute();
            }
            return 1;
        } catch (SQLException e) {
            System.out.println("Database fail");
            return result;
        }
    }
}
