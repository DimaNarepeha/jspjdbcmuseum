package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExhibitDaoImpl implements ExhibitDao {
    @Override
    public boolean saveExhibit(ExhibitEntity exhibit) {
        return false;
    }

    @Override
    public List<ExhibitEntity> readAllExhibits() {
        List<ExhibitEntity> result = new ArrayList<>();
        try (PreparedStatement selectFromExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT exhibit.id_exhibit,exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit\n" +
                        "INNER JOIN hall ON hall.id_hall=exhibit.id_hall\n" +
                        "INNER JOIN material ON material.id_material=exhibit.id_material\n" +
                        "INNER JOIN technique ON technique.id_technique=exhibit.id_technique\n" +
                        "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit\n" +
                        "INNER JOIN author ON author.id_author=author_exhibit.id_author;")) {
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
        }
        return result;
    }

    @Override
    public int updateExhibit(ExhibitEntity exhibit) {
        return 0;
    }

    @Override
    public int deleteExhibit(int id_exhibit) {
        try (PreparedStatement deleteExhibit = Database.getInstance().getConnection().prepareStatement("DELETE FROM exhibit WHERE id_exhibit = ?");
             PreparedStatement delete_author_exhibit = Database.getInstance().getConnection().prepareStatement("DELETE FROM author_exhibit WHERE id_exhibit = ?")) {
            deleteExhibit.setInt(1, id_exhibit);
            delete_author_exhibit.setInt(1, id_exhibit);
            delete_author_exhibit.execute();
            int rowsAffected = deleteExhibit.executeUpdate();
            return rowsAffected;
        } catch (NumberFormatException e) {
            System.out.println("Maybe you have entered letters or space somewhere");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
