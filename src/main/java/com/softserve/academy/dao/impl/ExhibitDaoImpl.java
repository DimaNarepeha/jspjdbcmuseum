/*
 *Open source project 2019
 *
 */
package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * this class represents dao
 *
 * @author Dmytro Narepekha
 */
public class ExhibitDaoImpl implements ExhibitDao {
    /**
     * This method add all corresponding objects that are not in database yet
     * and that are needed to be add for exhibit.
     *
     * @param exhibit object to check links for
     */
    private void checkAndAddWhatIsNotInDatabase(final ExhibitEntity exhibit) {
        try (PreparedStatement checkAuthor = Database.getInstance()
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
                             "VALUES(?)")) {
            /**check all and add all elements that are not in database*/
            checkAuthor.setString(1, exhibit.getFirstName());
            checkAuthor.setString(2, exhibit.getLastName());
            ResultSet resultSet = checkAuthor.executeQuery();
            if (!resultSet.first()) { //then add
                addAuthor.setString(1, exhibit.getFirstName());
                addAuthor.setString(2, exhibit.getLastName());
                addAuthor.execute();
            }
            resultSet.close();


            checkHall.setString(1, exhibit.getHall_name());
            resultSet = checkHall.executeQuery();
            if (!resultSet.first()) { //then add
                addHall.setString(1, exhibit.getHall_name());
                addHall.execute();
            }
            resultSet.close();


            checkMaterial.setString(1, exhibit.getMaterial_name());
            resultSet = checkMaterial.executeQuery();
            if (!resultSet.first()) { //then add
                addMaterial.setString(1, exhibit.getMaterial_name());
                addMaterial.execute();
            }
            resultSet.close();


            checkTechnique.setString(1, exhibit.getTechnique_name());
            resultSet = checkTechnique.executeQuery();
            if (!resultSet.first()) { //then add
                addTechnique.setString(1, exhibit.getTechnique_name());
                addTechnique.execute();
            }
            resultSet.close();

        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * to save exhibit into database.
     *
     * @param exhibit to be saved
     * @return true if successful or false if not
     */
    @Override
    public boolean saveExhibit(final ExhibitEntity exhibit) {
        try (PreparedStatement insertToExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO exhibit(id_material,id_technique,id_hall,exhibit_name)" +
                        "VALUES(?,?,?,?)");
             PreparedStatement checkAuthor = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_author FROM author WHERE FIRSTNAME=? AND LASTNAME=?");
             PreparedStatement checkMaterial = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_material FROM material WHERE material_name=?");
             PreparedStatement checkHall = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_hall FROM hall WHERE hall_name=?");
             PreparedStatement checkTechnique = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_technique FROM technique WHERE technique_name=?");
             PreparedStatement insertToAuthor_Exhibit = Database.getInstance()
                     .getConnection()
                     .prepareStatement("INSERT INTO author_exhibit(id_exhibit,id_author)" +
                             "VALUES(?,?)");
             PreparedStatement selectFromExhibitLast = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_exhibit FROM exhibit ORDER BY id_exhibit DESC LIMIT 1 ")) {
            checkAndAddWhatIsNotInDatabase(exhibit);
            /**check all and add all elements that are not in database*/
            checkAuthor.setString(1, exhibit.getFirstName());
            checkAuthor.setString(2, exhibit.getLastName());
            ResultSet resultSet = checkAuthor.executeQuery();
            resultSet.first();
            int authorId = resultSet.getInt(1);

            checkHall.setString(1, exhibit.getHall_name());

            resultSet.close();
            resultSet = checkHall.executeQuery();
            resultSet.first();
            int hallId = resultSet.getInt(1);

            checkMaterial.setString(1, exhibit.getMaterial_name());

            resultSet.close();
            resultSet = checkMaterial.executeQuery();
            resultSet.first();
            int materialId = resultSet.getInt(1);

            checkTechnique.setString(1, exhibit.getTechnique_name());

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

    /**
     * returns all exhibits in database.
     *
     * @return list of exhibits
     */
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

    /**
     * returns exhibit by id
     * or null.
     *
     * @param id to be found
     * @return exhibit by id
     * or null if not found
     */
    @Override
    public ExhibitEntity getExhibitById(int id) {
        ExhibitEntity result;
        try (PreparedStatement selectFromExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("SELECT exhibit.id_exhibit,exhibit_name, hall_name, FIRSTNAME, LASTNAME, material_name, technique_name FROM exhibit\n" +
                        "INNER JOIN hall ON hall.id_hall=exhibit.id_hall\n" +
                        "INNER JOIN material ON material.id_material=exhibit.id_material\n" +
                        "INNER JOIN technique ON technique.id_technique=exhibit.id_technique\n" +
                        "INNER JOIN author_exhibit ON author_exhibit.id_exhibit=exhibit.id_exhibit\n" +
                        "INNER JOIN author ON author.id_author=author_exhibit.id_author " +
                        "WHERE exhibit.id_exhibit=?;")) {
            selectFromExhibit.setInt(1, id);
            ResultSet resultSet = selectFromExhibit.executeQuery();
            resultSet.first();
            result = new ExhibitEntity(resultSet.getInt(1));
            result.setExhibit_name(resultSet.getString(2));
            result.setHall_name(resultSet.getString(3));
            result.setFirstName(resultSet.getString(4));
            result.setLastName(resultSet.getString(5));
            result.setMaterial_name(resultSet.getString(6));
            result.setTechnique_name(resultSet.getString(7));
            resultSet.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return result;
    }

    /**
     * updates exhibit in database with all links.
     *
     * @param exhibit to be updated
     * @return number of affected rows
     */
    @Override
    public int updateExhibit(ExhibitEntity exhibit) {
        int result = 0;
        try (PreparedStatement updateExhibit = Database.getInstance()
                .getConnection()
                .prepareStatement("UPDATE exhibit SET id_material=?,id_technique=?,id_hall=?,exhibit_name=? " +
                        "WHERE id_exhibit = ?");
             PreparedStatement checkAuthor = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_author FROM author WHERE FIRSTNAME=? AND LASTNAME=?");
             PreparedStatement checkMaterial = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_material FROM material WHERE material_name=?");
             PreparedStatement checkHall = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_hall FROM hall WHERE hall_name=?");
             PreparedStatement checkTechnique = Database.getInstance()
                     .getConnection()
                     .prepareStatement("SELECT id_technique FROM technique WHERE technique_name=?");
             PreparedStatement updateAuthor_Exhibit = Database.getInstance()
                     .getConnection()
                     .prepareStatement("UPDATE author_exhibit SET id_author=? " +
                             "WHERE id_exhibit=?")) {
            checkAndAddWhatIsNotInDatabase(exhibit);
            /**check all and add all elements that are not in database*/
            checkAuthor.setString(1, exhibit.getFirstName());
            checkAuthor.setString(2, exhibit.getLastName());
            ResultSet resultSet = checkAuthor.executeQuery();
            resultSet.first();
            int newAuthorId = resultSet.getInt(1);

            checkHall.setString(1, exhibit.getHall_name());
            resultSet.close();
            resultSet = checkHall.executeQuery();
            resultSet.first();
            int newHallId = resultSet.getInt(1);

            checkMaterial.setString(1, exhibit.getMaterial_name());
            resultSet.close();
            resultSet = checkMaterial.executeQuery();
            resultSet.first();
            int newMaterialId = resultSet.getInt(1);

            checkTechnique.setString(1, exhibit.getTechnique_name());
            resultSet = checkTechnique.executeQuery();
            resultSet.first();
            int newTechniqueId = resultSet.getInt(1);
            resultSet.close();

            String exhibitName = exhibit.getExhibit_name();
            updateExhibit.setInt(1, newMaterialId);
            updateExhibit.setInt(2, newTechniqueId);
            updateExhibit.setInt(3, newHallId);
            updateExhibit.setString(4, exhibitName);
            updateExhibit.setInt(5, exhibit.getId_exhibit());
            result = updateExhibit.executeUpdate();
            updateAuthor_Exhibit.setInt(1, newAuthorId);
            updateAuthor_Exhibit.setInt(2, exhibit.getId_exhibit());
            updateAuthor_Exhibit.execute();
            return result;
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return result;
        } catch (SQLException e) {
            e.printStackTrace();
            return result;
        }
    }

    /**
     * delete exhibit by id.
     *
     * @param id_exhibit to be deleted
     * @return number of affected rows
     */
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
