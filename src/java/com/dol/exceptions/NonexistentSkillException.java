/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dol.exceptions;

/**
 *
 * @author bjw
 */
public class NonexistentSkillException extends Exception {
    private String skillID;
    
    public NonexistentSkillException(String skillID) {
        this.skillID = skillID;
    }
    
    public String getSkillID() {
        return this.skillID;
    }
    
    @Override
    public String getMessage() {
        return "Skill does not exist: " + this.skillID;
    }
}
