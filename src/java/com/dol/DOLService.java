/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dol;

import com.dol.business.DOLServiceImpl;
import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.util.LinkedList;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

/**
 *
 * @author bjw
 */
@WebService(serviceName = "DOLService")
public class DOLService {

    DOLServiceImpl impl;
    
    public DOLService() {
        impl = new DOLServiceImpl();
    }
    
    @WebMethod(operationName = "addSkillToPosition")
    public void addSkillToPosition( @WebParam(name = "occupationID") Integer occupationID, 
                                    @WebParam(name = "skillID") Integer skillID) {
        impl.addSkillToPosition(occupationID, skillID);
    }
    
    @WebMethod(operationName = "dropSkillFromOccupation")
    public boolean dropSkillFromOccupation( @WebParam(name = "skillID") Integer skillID,
                                            @WebParam(name = "occupationID") Integer occupationID) {
        return impl.dropSkillFromOccupation(skillID, occupationID);
    }
    
    @WebMethod(operationName = "getSkillsFromPosition")
    public List<Skill> getSkillsFromPosition( @WebParam(name = "occupationID") Integer occupationID) {
        return impl.getSkillsFromPosition(occupationID);
    }
    
    @WebMethod(operationName = "getOccupationFromSkill")
    public List<Occupation> getOccupationsFromSkill( @WebParam(name = "skillID") Integer skillID) {
        return impl.getOccupationsFromSkill(skillID);
    }
    
    @WebMethod(operationName = "getSkillsByType")
    public List<Skill> getSkillsByType( @WebParam(name = "skillType") String skillType) {
        return impl.getSkillsByType(skillType);
    }
    
    @WebMethod(operationName = "getOccupationsByType")
    public List<Occupation> getOccupationsByType( @WebParam(name = "skillType") String skillType) {
        return impl.getOccupationsByType(skillType);
    }
    
    
}
