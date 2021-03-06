package com.dol.business;

import com.dol.db.DolDB;
import com.dol.db.DolDB2;
import com.dol.exceptions.NonexistentOccupationException;
import com.dol.exceptions.NonexistentSkillException;
import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class DOLServiceImpl {
	private DolDB2 db;
	
	public DOLServiceImpl() {
            db = new DolDB2();
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
        
        public Occupation getOccupation(String occupationID) 
                throws NonexistentOccupationException {
            Occupation occupation = null;
            try {
                occupation = db.getOccupation(occupationID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (occupation == null) {
                throw new NonexistentOccupationException(occupationID);
            }
            return occupation;
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
        
        public Skill getSkill(String skillID) 
                throws NonexistentSkillException {
            Skill skill = null;
            try {
                skill = db.getSkill(skillID);
            } catch (SQLException e) {
                e.printStackTrace();
            }
            if (skill == null) {
                throw new NonexistentSkillException(skillID);
            }
            return skill;
        }
	
        /*
	public void addSkillToOccupation( Integer occupationID, Integer skillID)  {
            try {
                db.createOccupationSkill(occupationID, skillID);
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
	}*/
	
        /*
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
        */
	
	public List<Skill> getSkillsForOccupation(String occupationID) {
            List<Skill> skillList = new LinkedList<Skill>();
            try {	

                    skillList = db.getAllSkillsForOccupation(occupationID);
            }
            catch (SQLException sqlE) {
                    sqlE.printStackTrace();
            }
            return skillList;
	}
	
	public List<Occupation> getOccupationsForSkill(String skillID) {
            List<Occupation> occupations = new LinkedList<Occupation>();
            try {
                occupations = db.getOccupationsForSkill(skillID);
            }
            catch (Exception e) {
                e.printStackTrace();
            }
            return occupations;
	}
	
        /*
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
        */
        
        /*
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
        */
        
}
