package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.ExhibitGuideDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ExhibitGuideDaoImpl implements ExhibitGuideDao {
    @Override
    public List<GuideEntity> getGuidesByExhibitId() {
        return null;
    }

    @Override
    public List<ExhibitEntity> getExhibitsByGuideId() {
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
}
