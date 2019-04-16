package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitGuideDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExhibitGuideDaoImpl implements ExhibitGuideDao {
    @Override
    public List<GuideEntity> getGuidesByExhibitId() {
        return null;
    }

    @Override
    public List<ExhibitEntity> getExhibitsByGuideId() {
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
}
