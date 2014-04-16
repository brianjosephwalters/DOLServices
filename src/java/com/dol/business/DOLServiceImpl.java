package com.dol.business;

import com.dol.models.Skill;
import com.dol.models.Occupation;
import com.dol.db.DolDB;
import java.util.LinkedList;
import java.sql.SQLException;

public class DOLServiceImpl {
	private DolDB db;
	
	public DOLServiceImpl() {
		db = new DolDB();
	}
	
	public String getMyName() {
		return "Chris Travis";
	}
	
	public void addSkillToPosition( Integer occupationID, Integer skillID)  {
		try {
			db.createOccupationSkill(occupationID, skillID);
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
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
	
	public LinkedList<Skill> getSkillsFromPosition(Integer occupationID) {
		LinkedList<Skill> skillList = new LinkedList<Skill>();
		try {	
			
			skillList = db.getOccupationSkills(occupationID);
		}
		catch (SQLException sqlE) {
			sqlE.printStackTrace();
		}
			return skillList;
	}
	
	
	public LinkedList<Occupation> getOccupationsFromSkill(Integer skillID) {
		LinkedList<Occupation> occupations = new LinkedList<Occupation>();
		try {
			occupations = db.getOccupationsFromSkill(skillID);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return occupations;
	}
	
	public LinkedList<Skill> getSkillsByType(String type)  {
		LinkedList<Skill> skillList = new LinkedList<Skill>();
		try {
			skillList = db.getSkillsByType(type);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return skillList;
	}
	
	
	public LinkedList<Occupation> getOccupationsByType(String type)  {
		LinkedList<Occupation> occupations = new LinkedList<Occupation>();
		try {
			occupations = db.getOccupationsByType(type);
		}
		catch (Exception e) {
			e.printStackTrace();
		}	
		return occupations;
	}
	

}
