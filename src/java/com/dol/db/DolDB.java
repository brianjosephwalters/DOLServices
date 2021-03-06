package com.dol.db;

import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class DolDB {
	
    public DolDB() {
    }

    //connection

    public Connection dbConnection() {
    Connection conn = null;
    String url = "jdbc:oracle:thin:@dbsvcs.cs.uno.edu:1521:orcl";

        String driver = "oracle.jdbc.driver.OracleDriver";
        String userName = "bwalters"; 
        String password = "TZZQGkMQ";
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
	
	
    //create
    public Integer createSkill(Skill skill) throws SQLException {
            Integer id = 0;
            Connection conn = this.dbConnection();
            String returnCourseId = "";
              try {
                      Statement stmt = conn.createStatement();
                      stmt.executeUpdate("insert into skill (skill_name, skill_description, skill_level, skill_type, creation_date)" +
                                    " values " +
                                    "("+ skill.getName() +", '"+ skill.getDescription() +"', '"+ skill.getLevel() +"', '"+ skill.getType() +"', to_date(SYDATE,'mm/dd/yyyy')");

                      //The one just added would have the last course_id because the course_id in the table are an auto increment
                      String courseIdQuery = "select max(training_id) from job_training";
                      ResultSet rs = stmt.executeQuery(courseIdQuery);
                      while(rs.next()){
                      returnCourseId = rs.getString(1);
                      }
                      //stmt.executeBatch();
                      conn.close();
              } catch (SQLException e) {
                      e.printStackTrace(); 
              }

              id = Integer.parseInt(returnCourseId);
            return id;
    }


    public Integer createSkill(String name, 
                               String description, 
                               String level, 
                               String type) throws SQLException {
            Integer id = 0;
            Connection conn = this.dbConnection();
            String returnCourseId = "";
              try {
                      Statement stmt = conn.createStatement();
                      stmt.executeUpdate("insert into skill (skill_name, skill_description, skill_level, skill_type, creation_date)" +
                                    " values " +
                                    "("+ name +", '"+ description +"', '"+ level +"', '"+ type +"', to_date(SYDATE,'mm/dd/yyyy')");

                      //The one just added would have the last course_id because the course_id in the table are an auto increment
                      String courseIdQuery = "select max(training_id) from job_training";
                      ResultSet rs = stmt.executeQuery(courseIdQuery);
                      while(rs.next()){
                      returnCourseId = rs.getString(1);
                      }
                      //stmt.executeBatch();
                      conn.close();
              } catch (SQLException e) {
                      e.printStackTrace(); 
              }

              id = Integer.parseInt(returnCourseId);
            return id;
    }


    public Integer createOccupation(Occupation occupation) throws SQLException {
        Integer id = 0;
        Connection conn = this.dbConnection();
        String returnCourseId = "";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("insert into occupation (occupation_title, occupation_description, occupation_type, creation_date)" +
                               " values " +
                               "("+ occupation.getTitle() + ", '" + occupation.getDescription() +"', '"+ occupation.getType() +"', to_date(SYDATE,'mm/dd/yyyy')");

            //The one just added would have the last course_id because the course_id in the table are an auto increment
            String courseIdQuery = "select max(training_id) from job_training";
            ResultSet rs = stmt.executeQuery(courseIdQuery);
            while(rs.next()){
                returnCourseId = rs.getString(1);
            }
            //stmt.executeBatch();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }

        id = Integer.parseInt(returnCourseId);
        return id;
    }


    public int createOccupation(String title, String desc, String type) throws SQLException {
        Connection conn = this.dbConnection();
        String returnPositionId = "";
        try {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate( "insert into position(position_title, position_description, position_type, creation_date)"+
                                " values " +
                                "('" + title + "', '" + desc + "', '" + type + "', '" + "', to_date("+ new Date().toString() + ",'mm/dd/yyyy')");

            //The one just added would have the last course_id because the course_id in the table are an auto increment
            String personIdQuery = "select max(position_id) from position";
            ResultSet rs = stmt.executeQuery(personIdQuery);
            while (rs.next()){
                    returnPositionId = rs.getString(1);
            }
            //stmt.executeBatch();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }
        return Integer.parseInt(returnPositionId);
      }
	
		
    public void createOccupationSkill(Integer occupationID, Integer skillID) throws SQLException{
    	Connection conn = this.dbConnection();
    	try {
            Statement stmt = conn.createStatement();
            if (occupationID != -1){
                stmt.executeUpdate( "insert into occupation_skill(occupation_id, skill_id)" +
                                    " values " +
                                    "(" + occupationID + ", " + skillID + ")");
            }
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); 
        }    	
    }
    //read
    /*
     * Get occupation
     * @param occupationID
     * @return the occupation that holds the parameter id.
     */
    
    public Occupation getOccupation(String occupationID) 
            throws SQLException {
        Connection connection = this.dbConnection();
        String query  = "SELECT occupation_id, occupation_title, occupation_description, occupation_type, creation_date "
                + "FROM occupation "
                + "WHERE occupation_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, occupationID);
        ResultSet results = stmt.executeQuery();
        results.next();
        
        Occupation occupation = new Occupation();
        occupation.setOccupationID(occupationID);
        occupation.setTitle(results.getString("occupation_title"));
        occupation.setDescription(results.getString("occupation_description"));
        occupation.setType(results.getString("occupation_type"));
        
        return occupation;
    }
    
    public List<Occupation> getAllOccupations() throws SQLException {
        List<Occupation> list = new ArrayList<Occupation>();
        Connection connection = this.dbConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT occupation_id, occupation_title, occupation_description, occupation_type, creation_date from occupation";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Occupation occupation = new Occupation();
                occupation.setOccupationID(rs.getString(1));
                occupation.setTitle(rs.getString(2));
                occupation.setDescription(rs.getString(3));
                occupation.setType(rs.getString(4));
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
        String query  = "SELECT skill_id, skill_name, skill_description, skill_type, skill_level "
                    + " FROM skill "
                    + " WHERE skill_id = ?";

        PreparedStatement stmt = connection.prepareStatement(query);
        stmt.setString(1, skillID);
        ResultSet results = stmt.executeQuery();
        results.next();
        
        Skill skill = new Skill();
        skill.setSkillID(skillID);
        skill.setName(results.getString("skill_name"));
        skill.setDescription(results.getString("skill_description"));
        skill.setType(results.getString("skill_type"));
        skill.setLevel(results.getInt("skill_level"));
        return skill;
    }
    
    public List<Skill> getAllSkills() throws SQLException {
        List<Skill> list = new ArrayList<Skill>();
        Connection connection = this.dbConnection();
        try {
            Statement statement = connection.createStatement();
            String query = "SELECT skill_id, skill_name, skill_description, skill_type, skill_level from skill";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                Skill skill = new Skill();
                skill.setSkillID(rs.getString(1));
                skill.setName(rs.getString(2));
                skill.setDescription(rs.getString(3));
                skill.setType(rs.getString(4));
                skill.setLevel(rs.getInt(5));
                list.add(skill);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            connection.close();
        }
        return list;
    }
    
    public LinkedList<Skill> getOccupationSkills(Integer occupationID) throws SQLException {
    	LinkedList<Skill> skillList = new LinkedList<Skill>();
    	Connection connection = this.dbConnection();
    	try {
            String query = "SELECT sk.skill_id, sk.skill_name, sk.skill_description, sk.skill_type, sk.skill_level FROM skill sk NATURAL JOIN occupation_skill osk WHERE occupation_id = " + occupationID.toString() + " AND sk.end_date is null";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String type = rs.getString(4);
                Integer level = rs.getInt(5);
                Skill skill = new Skill(id, name, description, type, level);
                skillList.add(skill);
            }
    	}
    	catch (SQLException e) {
    	}
    	finally {
            connection.close();
    	}
    	return skillList;
    }
    
    public LinkedList<Occupation> getOccupationsFromSkill(Integer skillID) throws SQLException {
    	LinkedList<Occupation> occupations = new LinkedList<Occupation>();
    	Connection connection = this.dbConnection();
    	try {
            String query = "SELECT occupation_id, occupation_title, occupation_description, occupation_type FROM occupation oc NATURAL JOIN occupation_skill ocs WHERE skill_id = " + skillID.toString() + " AND oc.end_date is null";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String id = rs.getString(1);
                String title = rs.getString(2);
                String description = rs.getString(3);
                String type = rs.getString(4);
                Occupation occupation = new Occupation(id, title, description, type);
                occupations.add(occupation);
            }
    	}
    	catch (SQLException e){
            e.printStackTrace();
    	}
    	finally {
            connection.close();
    	}
    	return occupations;	
    }

    public LinkedList<Skill> getSkillsByType(String type) throws SQLException {
    	LinkedList<Skill> skillList = new LinkedList<Skill>();
    	Connection connection = this.dbConnection();
    	try {
            Statement statement = connection.createStatement();
            String query = "SELECT skill_id, skill_name, skill_description, skill_type, skill_level FROM skill WHERE skill_type = '" + type + "' AND end_date is null";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String id = rs.getString(1);
                String name = rs.getString(2);
                String description = rs.getString(3);
                String skillType = rs.getString(4);
                Integer level = rs.getInt(5);
                Skill skill = new Skill(id, name, description, skillType, level);
                skillList.add(skill);
            }
    }
    	catch (SQLException e) {
    	}
    	finally {
            connection.close();
    	}
    	return skillList;
    }
    
    
    public LinkedList<Occupation> getOccupationsByType(String type) throws SQLException {
    	LinkedList<Occupation> occupations = new LinkedList<Occupation>();
    	Connection connection = this.dbConnection();
    	try {
            Statement statement = connection.createStatement();
            String query = "SELECT occupation_id, occupation_title, occupation_description, occupation_type FROM occupation WHERE occupation_type = '" + type + "' AND end_date is null";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                    String id = rs.getString(1);
                    String title = rs.getString(2);
                    String description = rs.getString(3);
                    String oType = rs.getString(4);
                    Occupation occupation = new Occupation(id, title, description, oType);
                    occupations.add(occupation);
            }
    	}
    	catch (SQLException e) {
            e.printStackTrace();
    	}
    	finally {
            connection.close();
    	}
    	return occupations;
    }
	
	
	//update
    public void updateOccupation(Integer id, String title, String description, String type) throws SQLException {
    	Connection connection = this.dbConnection();
    	try {
  		  Statement stmt = connection.createStatement();
  		  stmt.executeUpdate("UPDATE occupation SET occupation_title = '"+title+"', "+
  				     " occupation_description = '" + description + "', "+
                                     " occupation_type = '" + type + "' "+
                                     " WHERE occupation_id = "+ id.toString());
  		 }
    	catch (SQLException e) {
  		  e.printStackTrace(); 
  	  	}
    	finally {
    		connection.close();
    	}
    }
    
    public void updateSkill(Integer id, String name, String description, Integer level, String type) throws SQLException {
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		statement.executeUpdate("UPDATE skill SET skill_name = '" + name + "', " +
    					" skill_description = '" + description + "', " +
    					" skill_level = " + level +
    					" skill_type = '" + type + "'" + 
    					" WHERE skill_id = " + id.toString());
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    }
	
	//delete

    public boolean deleteSkill(Integer skillID) throws SQLException {
    	boolean result = false;
    //delete from occupation_skill
    	this.deleteSkillFromOccupationSkill(skillID);
    	
    //delete from skill
    	
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		result = statement.execute("DELETE FROM skill WHERE skill_id = " + skillID.toString());
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    	return result;
    }
    
    public boolean deleteOccupation(Integer occupationID) throws SQLException {
    	boolean result = false;
    	//delete from occupation_skill
    	this.deleteOccupationFromOccupationSkill(occupationID);
    	//delete from occupation
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		result = statement.execute("DELETE FROM occupation WHERE occupation_id = " + occupationID.toString());
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    	return result;
    }
    
    public boolean deleteSkillFromOccupation(Integer skillID, Integer occupationID) throws SQLException {
    	boolean result = false;
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		result = statement.execute("DELETE FROM occupation_skill WHERE skill_id = " + skillID.toString() + " AND occupation_id = " + occupationID.toString());
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    	return result;
    }
    
    
    public boolean deleteOccupationFromOccupationSkill(Integer occupationID) throws SQLException {
    	boolean result = false;
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		result = statement.execute("DELETE FROM occupation_skill WHERE occupation_id = " + occupationID.toString());
    	}
    	catch (Exception e) {
    		e.printStackTrace();
    		
    	}
    	finally {
    		connection.close();
    	}
    	return result;
    }
    
    public boolean deleteSkillFromOccupationSkill(Integer skillID) throws SQLException {
    	boolean result = false;
    	Connection connection = this.dbConnection();
    	try {
    		Statement statement = connection.createStatement();
    		result = statement.execute("DELETE FROM occupation_skill WHERE skill_id = " + skillID.toString());
    	}
    	catch (SQLException e) {
    		e.printStackTrace();
    	}
    	finally {
    		connection.close();
    	}
    	return result;
    }
}
