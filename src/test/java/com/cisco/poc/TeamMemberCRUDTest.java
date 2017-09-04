package com.cisco.poc;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.List;

import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.cisco.poc.model.Member;
import com.cisco.poc.model.SkillSet;
import com.cisco.poc.service.TeamMemberService;
import com.cisco.poc.service.TeamMemberServiceImpl;
import com.cisco.poc.util.Constants;

/*
*
* @author amigaura
*/
public class TeamMemberCRUDTest extends BaseTest{

	TeamMemberService teamMemberService = null;

    @BeforeTest
    public void init() throws UnknownHostException {
    	teamMemberService = TeamMemberServiceImpl.getInstance();
    }

    @Test(enabled = false, priority = 1)
    public void createIndex() throws IOException {
        Member member = new Member();
        
        member.setName("Amit Gaurav");
        member.setAge((short) 33);
        member.setCiscoDesignation("Lead Engineer");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("amigaura");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Technology Architect");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("amit.aurav");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Married");
        member.setModuleOwner("All Modules");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Java", "Database"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }

    @Test(enabled = false, priority = 1)
    public void createIndex2() throws IOException {
        Member member = new Member();
        
        member.setName("Ram Dwivedi");
        member.setAge((short) 33);
        member.setCiscoDesignation("Lead Engineer");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("radwived");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Technology Architect");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("Ram.Dwivedi");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Married");
        member.setModuleOwner("All Modules");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Database", "Java"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }

    @Test(enabled = false, priority = 1)
    public void createIndex3() throws IOException {
        Member member = new Member();
        
        member.setName("Rayaan Jha");
        member.setAge((short) 1);
        member.setCiscoDesignation("Beginner");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("rjha");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Beginner Engineer");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("Rayaan.Jha");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Single");
        member.setModuleOwner("Zero Module");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Bigdata", "Scala"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }
    
    @Test(enabled = false, priority = 1)
    public void createIndex11() throws IOException {
        Member member = new Member();
        
        member.setName("Amit Kumar");
        member.setAge((short) 33);
        member.setCiscoDesignation("Lead Engineer");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("amigaura");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Technology Architect");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("amit.aurav");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Married");
        member.setModuleOwner("All Modules");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Java", "Database"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "111", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }

    @Test(enabled = false, priority = 1)
    public void createIndex12() throws IOException {
        Member member = new Member();
        
        member.setName("Ram Dwivedi");
        member.setAge((short) 33);
        member.setCiscoDesignation("Lead Engineer");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("radwived");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Technology Architect");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("Ram.Dwivedi");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Married");
        member.setModuleOwner("All Modules");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Database", "Java"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "12", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }

    @Test(enabled = false, priority = 1)
    public void createIndex13() throws IOException {
        Member member = new Member();
        
        member.setName("Rayaan Jha");
        member.setAge((short) 1);
        member.setCiscoDesignation("Beginner");
        
        member.setCiscoEmpNumber(999999L);
        member.setCiscoId("rjha");
        member.setDeploymentLocation("Offshore");
        
        member.setDob(new Date(System.currentTimeMillis()));
        member.setGender("Male");
        member.setInfyDesignation("Beginner Engineer");
        
        member.setInfyEmpNumber(736278L);
        member.setInfyId("Rayaan.Jha");
        member.setLocation("Bangalore");
        
        member.setMaritalStatus("Single");
        member.setModuleOwner("Zero Module");
        member.setProjectJoiningDate(new Date(System.currentTimeMillis()));
        member.setSkillSet(new SkillSet("Bigdata", "Scala"));
        
        JSONObject jsonObject = new JSONObject(member);
        
        int responseCode = teamMemberService.createMember("team", "member", "13", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK2, responseCode);
    }
    
    @Test(enabled = false, priority = 2)
    public void findDocumentByIndex() {
    	List<Member> members = teamMemberService.findMemberByIndex("team", "member", "111");
    	Assert.assertEquals(members.get(0).getName(), "Amit Kumar");
    }
    
    @Test(enabled = false, priority = 3)
    public void findDocumentByValue() throws JSONException {
    	
	    StringBuilder searchQuery = new StringBuilder();
	    searchQuery.append("	{");
		searchQuery.append("		  \"query\": {");
		searchQuery.append("\"match\": {");
		searchQuery.append("\"name\": \"Amit Gaurav\"");
		searchQuery.append("    		    }");
		searchQuery.append("}");
		searchQuery.append("}");
		
		List<Member> members = teamMemberService.findMember("team", "member", searchQuery.toString());
		Assert.assertEquals(members.size(), 5);
    }
    
    @Test(enabled = false, priority = 4)
    public void UpdateDocument() throws IOException, JSONException {

        StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("{\"doc\":{\"ciscoId\":\"akgaurav\"}}");
        JSONObject jsonObject = new JSONObject(updateQuery.toString());
        
        int responseCode = teamMemberService.updateMember("team", "member", "111", jsonObject);
        Assert.assertEquals(Constants.RESPONSE_CODE_OK1, responseCode);
    }
    
    @Test(enabled = true, priority = 5)
    public void RemoveDocument() throws IOException {
    	int responseCode = teamMemberService.removeMember("team", "member", "111");
        Assert.assertEquals(Constants.RESPONSE_CODE_OK1, responseCode);
    }
}
