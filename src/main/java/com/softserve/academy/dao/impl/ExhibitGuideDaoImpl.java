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
import java.util.List;

public class ExhibitGuideDaoImpl implements ExhibitGuideDao {
    @Override
    public List<GuideEntity> getGuidesByExhibitId(int id) {
        return null;
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
            selectFromExhibit.setInt(1,id);
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
        List<GuideEntity> guides = new ArrayList<>();
        try(PreparedStatement getGuides = conn.prepareStatement("SELECT id_guide, firstname,lastname,position_name  FROM guide g join guide_position p on  +\n" +
                "                g.id_position=p.id_guide_position where NOT g.id_guide=?")) {
            getGuides.setInt(1,1);
            ResultSet rs = getGuides.executeQuery();
            while (rs.next()) {
                GuideEntity guideEntity = new GuideEntity(rs.getInt("id_guide"),rs.getString("firstname"), rs.getString("lastname"));
                guides.add(guideEntity);
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Database fail");
            return null;
        }
        return guides;
    }
}
