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
        try (PreparedStatement insertToExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO exhibit(id_material,id_technique,id_hall,exhibit_name)" +
                        "VALUES(?,?,?,?)");
             PreparedStatement checkAuthor = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_author FROM author WHERE FIRSTNAME=? AND LASTNAME=?");
             PreparedStatement addAuthor = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO author(FIRSTNAME,LASTNAME)" +
                             "VALUES(?,?)");
             PreparedStatement checkMaterial = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_material FROM material WHERE material_name=?");
             PreparedStatement addMaterial = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO material(material_name)" +
                             "VALUES(?)");
             PreparedStatement checkHall = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_hall FROM hall WHERE hall_name=?");
             PreparedStatement addHall = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO hall(hall_name)" +
                             "VALUES(?)");
             PreparedStatement checkTechnique = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_technique FROM technique WHERE technique_name=?");
             PreparedStatement addTechnique = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO technique(technique_name)" +
                             "VALUES(?)");
             PreparedStatement insertToAuthor_Exhibit = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO author_exhibit(id_exhibit,id_author)" +
                             "VALUES(?,?)");
             PreparedStatement selectFromExhibitLast = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_exhibit FROM exhibit ORDER BY id_exhibit DESC LIMIT 1 ")) {
            /**check all and add all elements that are not in database*/
            checkAuthor.setString(1, exhibit.getFirstName());
            checkAuthor.setString(2, exhibit.getLastName());
            ResultSet resultSet = checkAuthor.executeQuery();
            if (!resultSet.first()) {//then add
                addAuthor.setString(1, exhibit.getFirstName());
                addAuthor.setString(2, exhibit.getLastName());
                addAuthor.execute();
            }
            resultSet.close();
            resultSet = checkAuthor.executeQuery();
            resultSet.first();
            int authorId = resultSet.getInt(1);

            checkHall.setString(1, exhibit.getHall_name());
            resultSet = checkHall.executeQuery();
            if (!resultSet.first()) {//then add
                addHall.setString(1, exhibit.getHall_name());
                addHall.execute();
            }
            resultSet.close();
            resultSet = checkHall.executeQuery();
            resultSet.first();
            int hallId = resultSet.getInt(1);

            checkMaterial.setString(1, exhibit.getMaterial_name());
            resultSet = checkMaterial.executeQuery();
            if (!resultSet.first()) {//then add
                addMaterial.setString(1, exhibit.getMaterial_name());
                addMaterial.execute();
            }
            resultSet.close();
            resultSet = checkMaterial.executeQuery();
            resultSet.first();
            int materialId = resultSet.getInt(1);

            checkTechnique.setString(1, exhibit.getTechnique_name());
            resultSet = checkTechnique.executeQuery();
            if (!resultSet.first()) {//then add
                addTechnique.setString(1, exhibit.getTechnique_name());
                addTechnique.execute();
            }
            resultSet.close();
            resultSet = checkTechnique.executeQuery();
            resultSet.first();
            int techniqueId = resultSet.getInt(1);

            String exhibitName = exhibit.getExhibit_name();

            insertToExhibit.setInt(1, materialId);
            insertToExhibit.setInt(2, techniqueId);
            insertToExhibit.setInt(3, hallId);
            insertToExhibit.setString(4, exhibitName);
            insertToExhibit.execute();
            resultSet = selectFromExhibitLast.executeQuery();
            resultSet.first();
            int idOfNewExhibit = resultSet.getInt(1);
            resultSet.close();
            insertToAuthor_Exhibit.setInt(1, idOfNewExhibit);
            insertToAuthor_Exhibit.setInt(2, authorId);
            insertToAuthor_Exhibit.execute();
            System.out.println("Successfully added new exhibit " + exhibitName);
            return true;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
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
            return result;
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
            return 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }
}
