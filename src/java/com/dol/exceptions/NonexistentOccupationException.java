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
public class NonexistentOccupationException extends Exception {
    private String occupationID;
    
    public NonexistentOccupationException(String occupationID) {
        this.occupationID = occupationID;
    }
    
    public String getOccupationID() {
        return this.occupationID;
    }
    
    @Override
    public String getMessage() {
        return "Occupation does not exist: " + this.occupationID;
    }
}
