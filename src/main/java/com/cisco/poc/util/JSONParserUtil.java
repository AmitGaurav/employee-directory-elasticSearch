package com.cisco.poc.util;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.cisco.poc.model.Member;
import com.cisco.poc.model.SkillSet;

public class JSONParserUtil {
	
	public static List<Member> parseToGetMember(String output){

		List<Member> members = new ArrayList<Member>();
		
		try {
			JSONObject jsonObject = new JSONObject(output);
			JSONObject hits = jsonObject.getJSONObject("hits");
			int total = hits.getInt("total");
			System.out.println("Total hits count :"+total);

			JSONArray innerHits = hits.getJSONArray("hits");
			
			for (int i=0; i<innerHits.length(); i++) {
				Member member = new Member();
				
				JSONObject hitsObj = (JSONObject) innerHits.get(i);
				JSONObject source = hitsObj.getJSONObject("_source");
				System.out.println("source: "+source.toString());
				
				if(source.has("name")){
					member.setName((String) source.get("name"));
				}
				if(source.has("infyId")){
					member.setInfyId((String) source.get("infyId"));
				}
				if(source.has("ciscoId")){
					member.setCiscoId((String) source.get("ciscoId"));
				}
				if(source.has("infyEmpNumber")){
					member.setInfyEmpNumber(Long.valueOf(String.valueOf(source.get("infyEmpNumber"))));
				}
				if(source.has("ciscoEmpNumber")){
					member.setCiscoEmpNumber(Long.valueOf(String.valueOf(source.get("ciscoEmpNumber"))));
				}
				if(source.has("infyDesignation")){
					member.setInfyDesignation((String) source.get("infyDesignation"));
				}
				if(source.has("ciscoDesignation")){
					member.setCiscoDesignation((String) source.get("ciscoDesignation"));
				}
				if(source.has("moduleOwner")){
					member.setModuleOwner((String) source.get("moduleOwner"));
				}
				if(source.has("gender")){
					member.setGender((String) source.get("gender"));
				}
				if(source.has("deploymentLocation")){
					member.setDeploymentLocation((String) source.get("deploymentLocation"));
				}
				if(source.has("location")){
					member.setLocation((String) source.get("location"));
				}
				if(source.has("age")){
					member.setAge(Short.valueOf(String.valueOf(source.get("age"))));
				}
				if(source.has("maritalStatus")){
					member.setMaritalStatus((String) source.get("maritalStatus"));
				}
//				if(source.has("dob")){
					//member.setDob(Date.parse((String) source.get("dob"));
//				}
//				if(source.has("projectJoiningDate")){
					//member.setProjectJoiningDate((Date) source.get("projectJoiningDate"));
//				}
				if(source.has("skillSet")){
					JSONObject skillSet = (JSONObject) source.get("skillSet");
					member.setSkillSet(new SkillSet((String)skillSet.get("primarySkill"), (String)skillSet.get("secondarySkill")));
				}
				if(source.has("hobbies")){
					member.setHobbies(getList((JSONArray) source.get("hobbies")));
				}
				System.out.println("Member: "+member);
				
				members.add(member);
			}
		} catch (JSONException jsonException) {
			jsonException.printStackTrace();
		}
		return members;
	}
	
	private static List<String> getList(JSONArray jsonArray) throws JSONException{
		
		List<String> list = new ArrayList<String>();
		if(null == jsonArray){
			return list;
		}
		for(int i =0; i<jsonArray.length(); i++){
			list.add(new String(jsonArray.getString(i)));
		}
		return list;
	}
}
