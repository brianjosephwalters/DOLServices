package com.dol.business;

import com.dol.db.DolDB;
import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DOLServiceImpl {
	private DolDB db;
	
	public DOLServiceImpl() {
            db = new DolDB();
        }
        
        public List<Occupation> getAllOccupations() {
            List<Occupation> occupations = null;
            try {
                occupations = db.getAllOccupations();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return occupations;
        }
        
        public List<Skill> getAllSkills() {
            List<Skill> skills = null;
            try {
                skills = db.getAllSkills();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            return skills;
        }
	
	public void addSkillToOccupation( Integer occupationID, Integer skillID)  {
            try {
                db.createOccupationSkill(occupationID, skillID);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
	}
	
	public boolean dropSkillFromOccupation(Integer skillID, Integer occupationID){
		boolean result = false;
		try {
			result = db.deleteSkillFromOccupation(skillID, occupationID); 
		}
		catch (Exception e){
			e.printStackTrace();
		}
		return result;
	}
	
	public List<Skill> getSkillsFromOccupation(Integer occupationID) {
		List<Skill> skillList = new LinkedList<Skill>();
		try {	
			
			skillList = db.getOccupationSkills(occupationID);
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
			return skillList;
	}
	
	public List<Occupation> getOccupationsFromSkill(Integer skillID) {
            List<Occupation> occupations = new LinkedList<Occupation>();
            try {
                occupations = db.getOccupationsFromSkill(skillID);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return occupations;
	}
	
	public List<Skill> getSkillsByType(String type)  {
            List<Skill> skillList = new LinkedList<Skill>();
            try {
                skillList = db.getSkillsByType(type);
            }
            catch (Exception e) {
                e.printStackTrace();
            }	
            return skillList;
	}
        
	public List<Occupation> getOccupationsByType(String type)  {
            List<Occupation> occupations = new LinkedList<Occupation>();
            try {
                occupations = db.getOccupationsByType(type);
            }
            catch (Exception e) {
                e.printStackTrace();
            }	
            return occupations;
	}
}
