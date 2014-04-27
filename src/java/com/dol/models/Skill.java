package com.dol.models;

import java.util.Date;
import java.io.Serializable;

public class Skill implements Serializable {
	private static long uid = 11111;
	private String skillID;
	private String name;
	private String description;
	private String type;
	private Integer level;
	private Date creationDate;
	private Date endDate;
	
	
	public Skill() {
		
	}
	
	
	public Skill(String skillID, String name, String description, String type, Integer level) {
		this.skillID = skillID;
		this.name = name;
		this.description = description;
		this.type = type;
		this.level = level;
	}
	
	
	public Skill(String skillID, String name, String description, String type, Integer level, Date creationDate, Date endDate) {
		this.skillID = skillID;
		this.name = name;
		this.description = description;
		this.type = type;
		this.level = level;
		this.creationDate = creationDate;
		this.endDate = endDate;
	}
	
	public String getSkillID() {
		return this.skillID;
	}
	
	public void setSkillID(String id) {
		this.skillID = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String newName){
		this.name = newName;
	}
	
	
	public String getDescription(){
		return this.description;
	}
	
	public void setDescription(String newDescription) {
		this.description = newDescription;
	}
	
	
	public String getType(){
		return this.type;
	}
	
	public void setType(String newType) {
		this.type = newType;
	}
	
	public Integer getLevel(){
		return this.level;
	}
	
	public void setLevel (Integer newLevel){
		this.level = newLevel;
	}
	
	
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	
	public Date getEndDate() {
		return this.endDate;
	}
	
}


