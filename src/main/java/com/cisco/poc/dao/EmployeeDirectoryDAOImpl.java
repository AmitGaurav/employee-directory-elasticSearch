package com.cisco.poc.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import com.cisco.poc.model.Member;

public class EmployeeDirectoryDAOImpl implements EmployeeDirectoryDAO{

	public int addEmployees(Connection connection, List<Member> employees) {
    	try{
    		PreparedStatement preparedStatement = connection.prepareStatement("insert into ECMOTHAD.EMPLOYEE_DIRECTORY ("
					+ " NAME, "
					+ " ID, "
					+ " DEPT_ID ) "
				+ " values ( "
						+ "	?, "
						+ "	?, "
						+ " ?)");
    		
    		for(Member employee: employees){
    			preparedStatement.setString(1, employee.getName());
//    			preparedStatement.setLong(2, employee.getId());
//    			preparedStatement.setLong(3, employee.getDepartment().getId());
    			
    			preparedStatement.addBatch();
    		}
    		
			preparedStatement.executeUpdate();
			connection.commit();
			preparedStatement.close();
    		
    	}catch(SQLException sqlException){
    		sqlException.printStackTrace();
    	}catch(Exception exception){
    		exception.printStackTrace();
    	}
		return 0;
	}

	public int updateEmployee(Member employee) {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<Member> searchEmployee(Connection connection,
			Member employee) {
		// TODO Auto-generated method stub
		return null;
	}

	public int updateEmployee(Connection connection, Member employee) {
		// TODO Auto-generated method stub
		return 0;
	}


}
