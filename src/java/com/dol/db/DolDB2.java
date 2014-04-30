package com.dol.db;

import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author bjw
 */
public class DolDB2 {
    public DolDB2() {
    }

    //connection

    public Connection dbConnection() {
    Connection conn = null;
    String url = "jdbc:oracle:thin:@dbsvcs.cs.uno.edu:1521:orcl";

        String driver = "oracle.jdbc.driver.OracleDriver";
        String userName = "jphobbs"; 
        String password = "fPCHfZZn";
        try {
              Class.forName(driver).newInstance();
              conn = DriverManager.getConnection(url,userName, password);
              } catch (ClassNotFoundException | 
                       InstantiationException | 
                       IllegalAccessException | 
                       SQLException e) {
                      e.printStackTrace();
              }
              return conn;
    }
    
    public Occupation getOccupation(String occupationID) 
            throws SQLException {
        Connection connection = this.dbConnection();
        String query  = "SELECT onetsoc_code, title, description "
                + "FROM jh_occupation_data "
                + "WHERE onetsoc_code = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, occupationID);
        ResultSet results = stmt.executeQuery();
        results.next();
        
        Occupation occupation = new Occupation();
        occupation.setOccupationID(occupationID);
        occupation.setTitle(results.getString("title"));
        occupation.setDescription(results.getString("description"));
        
        return occupation;
    }
    
    public List<Occupation> getAllOccupations() throws SQLException {
        List<Occupation> list = new ArrayList<Occupation>();
        Connection connection = this.dbConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT onetsoc_code, title, description "
                + "FROM jh_occupation_data ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Occupation occupation = new Occupation();
                occupation.setOccupationID(rs.getString("onetsoc_code"));
                occupation.setTitle(rs.getString("title"));
                occupation.setDescription(rs.getString("description"));
                list.add(occupation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        
        return list;
    }
    
    public Skill getSkill(String skillID) 
            throws SQLException {
        Connection connection = this.dbConnection();
        String query  = "SELECT element_id, element_name, description "
                    + " FROM jh_content_model_reference "
                    + " WHERE element_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, skillID);
        ResultSet results = stmt.executeQuery();
        results.next();
        
        Skill skill = new Skill();
        skill.setSkillID(skillID);
        skill.setName(results.getString("element_name"));
        skill.setDescription(results.getString("description"));
        return skill;
    }
    
    public List<Skill> getAllSkills() throws SQLException {
        List<Skill> list = new ArrayList<Skill>();
        Connection connection = this.dbConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT element_id, element_name, description "
                    + " FROM jh_content_model_reference ";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillID(rs.getString("element_id"));
                skill.setName(rs.getString("element_name"));
                skill.setDescription(rs.getString("description"));
                list.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
    
    public List<Skill> getAllSkillsForOccupation(String occupationID)
            throws SQLException {
        Connection connection = this.dbConnection();
        List<Skill> list = new ArrayList<Skill>();
        String query  = "SELECT element_id " +
                " FROM JH_ABILITIES " +
                " WHERE onetsoc_code = ? " +
                " UNION " +
                " SELECT element_id " +
                " FROM JH_INTERESTS " +
                " WHERE onetsoc_code = ? " +
                " UNION " +
                " SELECT element_id " +
                " FROM JH_KNOWLEDGE " +
                " WHERE onetsoc_code = ? " +
                " UNION " +
                " SELECT element_id " +
                " FROM JH_SKILLS " +
                " WHERE onetsoc_code = ? ";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, occupationID);
        stmt.setString(2, occupationID);
        stmt.setString(3, occupationID);
        stmt.setString(4, occupationID);
        ResultSet results = stmt.executeQuery();
        
        while (results.next()) {
                Skill skill = new Skill();
                skill.setSkillID(results.getString("element_id"));
                skill.setName(results.getString("element_name"));
                skill.setDescription(results.getString("description"));
                list.add(skill);
            }
        return list;
    }
    
    public List<Occupation> getOccupationsForSkill(String skillID) 
            throws SQLException {
        Connection connection = this.dbConnection();
        List<Occupation> list = new ArrayList<Occupation>();
        String query  = " SELECT onetsoc_code " +
                        " FROM JH_ABILITIES " +
                        " WHERE element_id = ? " +
                        " UNION " +
                        " SELECT onetsoc_code " +
                        " FROM JH_INTERESTS " +
                        " WHERE element_id = ? " +
                        " UNION " +
                        " SELECT onetsoc_code " +
                        " FROM JH_KNOWLEDGE " +
                        " WHERE element_id = ? " +
                        " UNION " +
                        " SELECT onetsoc_code " +
                        " FROM JH_SKILLS " +
                        " WHERE element_id = ? ";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, skillID);
        stmt.setString(2, skillID);
        stmt.setString(3, skillID);
        stmt.setString(4, skillID);
        ResultSet results = stmt.executeQuery();
        
        while (results.next()) {
                Occupation occupation = new Occupation();
                occupation.setOccupationID(results.getString(""));
                occupation.setTitle(results.getString(""));
                occupation.setDescription(results.getString(""));
                list.add(occupation);
            }
        return list;
 
    }
}
