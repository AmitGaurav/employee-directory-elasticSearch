package com.cisco.poc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {
	public static Object getConnection(){
			
			System.out.print("Establishing  DB connection...");
			Connection connection = null;
			
	        //String URL = "jdbc:oracle:thin:@(DESCRIPTION=(LOAD_BALANCE=on) (ADDRESS=(PROTOCOL=TCP)(HOST=fntr2stg1-db.cisco.com) (PORT=1805)) (ADDRESS=(PROTOCOL=TCP)(HOST=fntr2stg2-db.cisco.com) (PORT=1805)) (CONNECT_DATA=(SERVICE_NAME=FNTR2STG.CISCO.COM)))";
			String URL = "jdbc:oracle:thin:@ldap://ldap-ldprd.cisco.com:5000/cn=OracleContext,dc=cisco,dc=com/FNTR2STG";
	        String USER = "ECMOTHAD";
	        String PASS = "ecmothad123";
	        
			try {
				connection = DriverManager.getConnection(URL, USER, PASS);
	            System.out.println("connection established.");
	        }
	        catch (SQLException e) {
	        	System.out.println(" ===== Failed to establish DB connection  ===== ");
	            e.printStackTrace();
	        } catch (Exception e) {
	        	System.out.println(" ===== DB connection error ===== ");
	            e.printStackTrace();
	        }
	        return connection;
		}
}
