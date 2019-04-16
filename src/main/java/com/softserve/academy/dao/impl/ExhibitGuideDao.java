package com.softserve.academy.dao.impl;

import com.softserve.academy.db.Database;
import com.softserve.academy.entity.ExhibitEntity;
import com.softserve.academy.entity.GuideEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ExhibitGuideDao {

    public Map<String,List<String>> readAllGuides()
    {
        Map<String,List<String>> map = new HashMap<>();
        Connection conn = Database.getInstance().getConnection();
        String query = "select g.lastname,e.exhibit_name from exhibit_guide eg\n" +
                " join guide g on g.id_guide = eg.id_guide\n" +
                " join exhibit e on e.id_exhibit = eg.id_exhibit;\n";
        List<String> guides = new ArrayList<>();

        PreparedStatement pstmt = null;
        try {
            pstmt = conn.prepareStatement(query);

            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                String name =rs.getString("lastname");
                String exhibit = rs.getString("exhibit_name");
                if(map.keySet().contains(exhibit))map.get(exhibit).add(name);
                else{
                    List<String> array = new ArrayList();
                    array.add(name);
                    map.put(exhibit,array);}
            }
            rs.close();
        } catch (SQLException e) {
            System.out.println("Database fail");
            return null;
        }
        return map;
    }
}


