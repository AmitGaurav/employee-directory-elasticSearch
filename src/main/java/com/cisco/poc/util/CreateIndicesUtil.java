package com.cisco.poc.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import org.json.JSONObject;

import com.cisco.poc.model.Member;
import com.cisco.poc.model.SkillSet;
import com.cisco.poc.service.TeamMemberService;
import com.cisco.poc.service.TeamMemberServiceImpl;

public class CreateIndicesUtil {

	public static void main(String[] args){
		Connection connection = (Connection) DBManager.getConnection();
		try {
			int count = indexMembers(connection);
			System.out.println("Total Members Indexed: "+count);
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		}finally{
			if(null != connection){
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private static int indexMembers(Connection connection) throws SQLException{
		
		StringBuilder query = new StringBuilder();
		query.append("select M.NAME, "
							+ " M.INFY_ID, "
							+ " M.CISCO_ID, "
							+ " M.INFY_EMP_NUMBER, "
							+ " M.CISCO_EMP_NUMBER, "
							+ " M.INFY_DESIGNATION, "
							+ " M.CISCO_DESIGNATION, "
							+ " M.MODULE_OWNER, "
							+ " M.GENDER, "
							+ " M.DEPLOYMENT_LOCATION, "
							+ " M.LOCATION, "
							+ " M.MARITAL_STATUS, "
							+ " M.DOB, "
							+ " M.PROJECT_JOINING_DATE, "
							+ " M.HOBBIES, "
							+ " SS.PRIMARY_SKILL, "
							+ " SS.SECONDARY_SKILL "
						+ " from MEMBER M left outer join SKILL_SET SS on M.INFY_ID = SS.INFY_ID "
						+ " order by INFY_EMP_NUMBER ");
		
		System.out.println("Query: "+query.toString());
		
		PreparedStatement preparedStatement = connection.prepareStatement(query.toString());
		ResultSet resultSet = preparedStatement.executeQuery();
		
		TeamMemberService teamMemberService = TeamMemberServiceImpl.getInstance();
		Member member = null;
		int id = 10000;
		int count = 0;
		while (resultSet.next()) {
			member = new Member();

			member.setName(resultSet.getString("NAME"));
			member.setInfyId(resultSet.getString("INFY_ID"));
			member.setCiscoId(resultSet.getString("CISCO_ID"));
			
			member.setInfyEmpNumber(resultSet.getLong("INFY_EMP_NUMBER"));
			member.setCiscoEmpNumber(resultSet.getLong("CISCO_EMP_NUMBER"));
			member.setInfyDesignation(resultSet.getString("INFY_DESIGNATION"));
			
			member.setCiscoDesignation(resultSet.getString("CISCO_DESIGNATION"));
			member.setModuleOwner(resultSet.getString("MODULE_OWNER"));
			member.setGender(resultSet.getString("GENDER"));
			
			member.setDeploymentLocation(resultSet.getString("DEPLOYMENT_LOCATION"));
			member.setLocation(resultSet.getString("LOCATION"));
			member.setMaritalStatus(resultSet.getString("MARITAL_STATUS"));

			member.setDob(new Date());
			member.setProjectJoiningDate(new Date());
			member.setHobbies(getHobbies(resultSet.getString("HOBBIES")));
			member.setSkillSet(new SkillSet(resultSet.getString("PRIMARY_SKILL"), resultSet.getString("SECONDARY_SKILL")));
			
			System.out.print("Indexing member: "+member+" ...");
			JSONObject jsonObject = new JSONObject(member);
			teamMemberService.createMember("team", "member", ++id+"", jsonObject);
			System.out.println("...done.");
			
			++count;
		}
		resultSet.close();
		return count;
	}
	
	private static List<String> getHobbies(String hobbies){
		List<String> hobbiesList = new ArrayList<String>();
		StringTokenizer st = new StringTokenizer(hobbies, "#");
		while (st.hasMoreElements()) {
			hobbiesList.add((String) st.nextElement());
		}
		return hobbiesList;
	}
}
