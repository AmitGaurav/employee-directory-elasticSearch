package com.cisco.poc.dao;

import java.sql.Connection;
import java.util.List;

import com.cisco.poc.model.Member;

public interface EmployeeDirectoryDAO {

	public int addEmployees(Connection connection, List<Member> employees);
	
	public List<Member> searchEmployee(Connection connection, Member employee);
	
	public int updateEmployee(Connection connection, Member employee);
}
