package com.softserve.academy.dao.impl;

import com.softserve.academy.dao.GuideDao;
import com.softserve.academy.db.Database;
import com.softserve.academy.entity.GuideEntity;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GuideDaoImpl implements GuideDao {
    @Override
    public boolean saveGuide(GuideEntity guide) {

        try (PreparedStatement insertToGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("INSERT INTO guide(id_position,firstname,lastname)" +
                        "VALUES(?,?,?)")
        ) {
            insertToGuide.setInt(1, 2);
            insertToGuide.setString(2, guide.getFirstname());
            insertToGuide.setString(3, guide.getLastname());
            insertToGuide.execute();
            System.out.println("Successfully added new guide: " + guide.getFirstname() + " " + guide.getLastname());
        } catch (SQLException e) {
            System.out.println("Oh...");
            return false;
        }
        return true;
    }

    @Override
    public List<GuideEntity> readAllGuides()
    {
        Connection conn = Database.getInstance().getConnection();
        String query = "SELECT id_guide, firstname,lastname,position_name  FROM guide g join guide_position p on " +
                "g.id_position=p.id_guide_position group by id_guide";
        List<GuideEntity> guides = new ArrayList<>();
        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

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

    @Override
    public int updateGuide(GuideEntity guide) {
        try (PreparedStatement updateGuide = Database.getInstance()
                .getConnection()
                .prepareStatement("UPDATE guide SET id_position = ?, firstname = ?, lastname = ?  WHERE id_guide=?")
        ) {
            System.out.println("===================UPDATE GUIDE====================");

            updateGuide.setInt(1, 2);
            updateGuide.setString(2, guide.getFirstname());
            updateGuide.setString(3, guide.getLastname());
            updateGuide.setInt(4, guide.getId());
            System.out.println("Successfully updated guide: " + guide.getFirstname() + " " + guide.getLastname());
            int rowsAffected = updateGuide.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully updated " + rowsAffected + " row");
                return 1;
            } else {
                System.out.println("Nothing was updated");
                return 0;
            }

        } catch (SQLException e) {
            System.out.println("Database fail");
            return 0;
        }
    }

    @Override
    public int deleteGuide(int id_guide) {
        System.out.println("===================Delete Guide====================");
        try (

                PreparedStatement deleteGuide = Database.getInstance()
                        .getConnection()
                        .prepareStatement(" DELETE FROM guide\n" +
                                "WHERE id_guide = ?;")
        ) {

            deleteGuide.setInt(1, id_guide);
            int rowsAffected = deleteGuide.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Successfully deleted " + rowsAffected + " row");
            return 1;
            } else {
                System.out.println("Nothing was deleted");
                return 0;

            }
        } catch (SQLException e) {
            System.out.println("Database fail");
            return 0;

        }

    }
}
