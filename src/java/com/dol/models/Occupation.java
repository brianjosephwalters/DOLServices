package com.dol.models;

import java.util.Date;
import java.io.Serializable;

public class Occupation implements Serializable {

	private Integer occupationID;
	private String title;
	private String description;
	private String type;
	private Date creationDate;
	private Date endDate;
	
	public Occupation(){
		
	}
	
	
	public Occupation(Integer occupationID, String title, String description, String type) {
		this.occupationID = occupationID;
		this.title = title;
		this.description = description;
		this.type = type;

	}
	
	public Occupation(Integer occupationID, String title, String description, String type, Date creationDate, Date endDate) {
		this.occupationID = occupationID;
		this.title = title;
		this.description = description;
		this.type = type;
		this.creationDate = creationDate;
		this.endDate = endDate;
	}
	
	public Integer getOccupationID() {
		return this.occupationID;
	}
	
	public void setOccupationID(Integer newID){
		this.occupationID = newID;
	}
	
	public String getTitle(){
		return this.title;
	}
	
	public void setTitle(String newTitle){
		this.title = newTitle;
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
	
	public void setType(String newType){
		this.type = newType;
	}
	
	public Date getCreationDate(){
		return this.creationDate;
	}
	
	public Date getEndDate(){
		return this.endDate;
	}
	
	
}
