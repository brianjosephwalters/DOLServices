package com.dol;

import com.dol.business.DOLServiceImpl;
import com.dol.models.Occupation;
import com.dol.models.Skill;
import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

@WebService(serviceName = "DOLService")
public class DOLService {

    DOLServiceImpl impl;
    
    public DOLService() {
        impl = new DOLServiceImpl();
    }
    
    @WebMethod(operationName = "getAllSkills")
    public List<Skill> getAllSkills() {
        return impl.getAllSkills();
    }
    
    @WebMethod(operationName = "getSkill")
    public Skill getSkill(@WebParam(name="skillID") String skillID) {
        return impl.getSkill(skillID);
    }
    
    @WebMethod(operationName = "getAllOccupations")
    public List<Occupation> getAllOccupations() {
        return impl.getAllOccupations();
    }
    
    @WebMethod(operationName = "getOccupation")
    public Occupation getOccupation(@WebParam(name="occupationID") String occupationID) {
        return impl.getOccupation(occupationID);
    }
    
    @WebMethod(operationName = "addSkillToOccupation")
    public void addSkillToOccupation( @WebParam(name = "occupationID") Integer occupationID, 
                                    @WebParam(name = "skillID") Integer skillID) {
        impl.addSkillToOccupation(occupationID, skillID);
    }
    
    @WebMethod(operationName = "dropSkillFromOccupation")
    public boolean dropSkillFromOccupation( @WebParam(name = "skillID") Integer skillID,
                                            @WebParam(name = "occupationID") Integer occupationID) {
        return impl.dropSkillFromOccupation(skillID, occupationID);
    }
    
    @WebMethod(operationName = "getSkillsFromOccupation")
    public List<Skill> getSkillsFromOccupation( @WebParam(name = "occupationID") Integer occupationID) {
        return impl.getSkillsFromOccupation(occupationID);
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
