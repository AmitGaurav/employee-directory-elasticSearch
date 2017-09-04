package com.cisco.poc.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;

public class Common {
	
	private static final String BASE_URL = "http://localhost:9200/users/employee";
	
	public static void main(String[] args){
		test();
	}
	
	private static String getMainQuery(long id){
		StringBuilder query = new StringBuilder();
		
		query.append("{");
		query.append("    \"full_name\" : \"Amit Gaurav"+id+"\",");
		query.append("\"bio\" : \"I am Amit Gaurav"+id+"...a developer.\",");
		query.append("    \"age\" : 34,");
		query.append("\"location\" : \"37.7749290,-122.4194160\",");
		query.append("\"enjoys_coffee\" : true,");
		query.append("\"created_on\" : \"2015-05-02T16:45:10.000-04:00\"");
		query.append("}");

		return query.toString();
	}
	
	private static String getSearchQuery(long id){
		StringBuilder query = new StringBuilder();
		
		query.append("{\"query\":{\"match\":{\"_id\":\""+id+"\"}}}");

		return query.toString();
	}
	
	
	
	private static HttpURLConnection con = null;
	
	
	private static JSONObject getJSONObject(){
		
		JSONObject requestDetailsJSON = new JSONObject();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		try{
			connection = (Connection) DBManager.getConnection();
			//conn.setAutoCommit(false);
			String request_id="8902";
			String requestdetailsSQL="select REQUEST_ID, TEMPLATE_ID, STATUS,CREATOR_USERID from map_request_details where request_id="+request_id;
			
			preparedStatement = connection.prepareStatement(requestdetailsSQL);
			ResultSet resultSet = preparedStatement.executeQuery();			
			
			resultSet.next();
			int REQUEST_ID  = resultSet.getInt("REQUEST_ID");
			System.out.println("REQUEST_ID: "+REQUEST_ID);
			
			ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
			int numColumns = resultSetMetaData.getColumnCount();
			System.out.println("numColumns: "+numColumns);
			
			for (int i = 1; i < numColumns + 1; i++) {
				String column_name = resultSetMetaData.getColumnName(i).toLowerCase();
				System.out.println("column_name: " + column_name);

				if (resultSetMetaData.getColumnType(i) == java.sql.Types.VARCHAR) {
					if (resultSet.getString(column_name) != null) {
						requestDetailsJSON.put(column_name, resultSet.getString(column_name));
					}
				} else if (resultSetMetaData.getColumnType(i) == java.sql.Types.NUMERIC) {
					requestDetailsJSON.put(column_name, resultSet.getInt(column_name));
				} else if (resultSetMetaData.getColumnType(i) == java.sql.Types.DATE) {
					if (resultSet.getDate(column_name) != null) {
						requestDetailsJSON.put(column_name, resultSet.getDate(column_name).toString());
					}
				}
			}
			      
			System.out.println("Jsonaary: "+requestDetailsJSON.toString());
			String requestmetadataSQL="select QUESTION_TAG_NAME,ANSWER from request_metadata where request_id="+request_id;
			      
			PreparedStatement metadataStmt = connection.prepareStatement(requestmetadataSQL);
			ResultSet metadataRS = metadataStmt.executeQuery();
			JSONArray metadataList = new JSONArray();
			resultSetMetaData = metadataRS.getMetaData();
			numColumns = resultSetMetaData.getColumnCount();
								
			while(metadataRS.next()){
				Map metadataMapper = new HashMap();   
						
			    for (int i=1; i<numColumns+1; i++) {
			        String column_name = resultSetMetaData.getColumnName(i).toLowerCase();
			        
			        System.out.println("column_name"+column_name);
			        if(resultSetMetaData.getColumnType(i)==java.sql.Types.VARCHAR){
			        	if(metadataRS.getString(column_name)!=null){metadataMapper.put(column_name, metadataRS.getString(column_name));}
			           }
			        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.NUMERIC){
			            metadataMapper.put(column_name, metadataRS.getInt(column_name));
			         }
			        else if(resultSetMetaData.getColumnType(i)==java.sql.Types.DATE){
			            metadataMapper.put(column_name, metadataRS.getInt(column_name));
			         }
			    }
				metadataList.put(metadataMapper);
			}
			
			int i=0;
			for (i = 0; i < metadataList.length(); i++) {
				System.out.println("length"+metadataList.length());
			}
			//add party doc to contract	
			requestDetailsJSON.put("answers", metadataList); 	
			metadataRS.close();
			metadataStmt.close();
			
		}catch(Exception exception){
			exception.printStackTrace();
		}
		
		return requestDetailsJSON;
	}
	
	private static boolean searchEmployee(long id) throws IOException{
		
		URL obj = new URL(BASE_URL+"/"+id);
		HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
		// optional default is GET
		httpURLConnection.setRequestMethod("POST");
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		httpURLConnection.setDoOutput(true);
		OutputStream outputStream = httpURLConnection.getOutputStream();

		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
		JSONObject requestDetailsJSON = getJSONObject();
		System.out.println(requestDetailsJSON.toString());
		
		outputStreamWriter.write(requestDetailsJSON.toString());
		outputStreamWriter.flush();
		outputStreamWriter.close();
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

		String inputLine;
		StringBuffer responseData = new StringBuffer();
		while ((inputLine = bufferedReader.readLine()) != null) {
			responseData.append(inputLine);
		}
		bufferedReader.close();

		String output = responseData.toString();
		System.out.println(output);

		return true;
	}
	
	private static boolean createEmployee(long id) throws IOException{
		URL obj = new URL(BASE_URL+"/"+id);
		/*HttpURLConnection*/ 
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
	
		String urlParameters = getMainQuery(id);
		System.out.println("URL Parameter: "+urlParameters);
		con.setDoOutput(true);
		
		OutputStream os = con.getOutputStream();
		
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		osw.write(urlParameters);
		osw.flush();
		osw.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : "+BASE_URL);
		System.out.println("Response Code: " + responseCode);
		
		return true;
	}
	
	private static boolean deleteEmployee(long id) throws IOException{
		URL obj = new URL(BASE_URL+"/"+id);
		/*HttpURLConnection*/ 
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("DELETE");
		con.setRequestProperty("Content-Type", "application/json");
	
		String urlParameters = getMainQuery(id);
		System.out.println("URL Parameter: "+urlParameters);
		con.setDoOutput(true);
		
		OutputStream os = con.getOutputStream();
		
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		osw.write(urlParameters);
		osw.flush();
		osw.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : "+BASE_URL);
		System.out.println("Response Code: " + responseCode);
		
		return true;
	}
	
	private static void searchEmployee1(String query) throws IOException{
		URL obj = new URL(BASE_URL+"/_search");
		/*HttpURLConnection*/ 
		con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "application/json");
	
		String urlParameters = query;
		System.out.println("URL Parameter: "+urlParameters);
		con.setDoOutput(true);
		
		OutputStream os = con.getOutputStream();
		
		OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
		osw.write(urlParameters);
		osw.flush();
		osw.close();
		
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : "+BASE_URL);
		System.out.println("Response Code: " + responseCode);
		
		
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String inputLine;
		StringBuffer responseData = new StringBuffer();
		while ((inputLine = bufferedReader.readLine()) != null) {
			responseData.append(inputLine);
		}
		bufferedReader.close();

		String output = responseData.toString();
		System.out.println(output);
		
	}
	
	private static void test(){
		long startTime = System.currentTimeMillis();
		try{
//			createEmployee(3L);
			
			searchEmployee1(getSearchQuery(3));
			
			deleteEmployee(3);
			
			
			}catch(Exception exception){
				exception.printStackTrace();
		}
		long endTime = System.currentTimeMillis();
		System.out.println("Time Takne: "+(endTime - startTime) / 1000 * 60 +" mins.");
	}
	
	
}
