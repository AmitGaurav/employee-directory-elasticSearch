package com.cisco.poc.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.util.List;

import org.elasticsearch.action.get.MultiGetResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cisco.poc.model.ElasticSearchRequest;
import com.cisco.poc.model.Member;
import com.cisco.poc.util.Constants;
import com.cisco.poc.util.JSONParserUtil;
import com.cisco.poc.util.RequestMethod;
import com.cisco.poc.util.TeamMemberUtil;

public class TeamMemberServiceImpl implements TeamMemberService{
	
	private static final Logger logger = LoggerFactory.getLogger(TeamMemberServiceImpl.class);

    private static TeamMemberService instance = null;

    public static TeamMemberService getInstance() {
        if (instance == null) {
            instance = new TeamMemberServiceImpl();
        }
        return instance;
    }

	public boolean isIndexExist(String id) {
		// TODO Auto-generated method stub
		return false;
	}

	public int createMember(String index, 
							String type, 
							String id,
							JSONObject jsonObject) {
		int responseCode = -1;
		String url = null;
		if(null == id || "".equals(id.trim())){
			url = Constants.BASE_URL+"/"+index+"/"+type;
		}else{
			url = Constants.BASE_URL+"/"+index+"/"+type+"/"+id;
		}
		
		try {
			HttpURLConnection httpURLConnection = TeamMemberUtil.getHttpConnection(url, 
																	RequestMethod.POST, 
																	Boolean.TRUE);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(jsonObject.toString());
			outputStreamWriter.flush();
			outputStreamWriter.close();
			
			responseCode = httpURLConnection.getResponseCode();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return responseCode;
	}

	public int updateMember(String index, 
										String type, 
										String id, 
										JSONObject jsonObject) {
		int responseCode = -1;
		String url = Constants.BASE_URL+"/"+index+"/"+type+"/"+id+"/_update";
		
		try {
			HttpURLConnection httpURLConnection = TeamMemberUtil.getHttpConnection(url, 
																	RequestMethod.POST, 
																	Boolean.TRUE);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(jsonObject.toString());
			outputStreamWriter.flush();
			outputStreamWriter.close();
			
			responseCode = httpURLConnection.getResponseCode();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return responseCode;
	}

	public int removeMember(String index, String type, String id) {

		int responseCode = -1;
		String url = Constants.BASE_URL+"/"+index+"/"+type+"/"+id;
		
		try {
			HttpURLConnection httpURLConnection = TeamMemberUtil.getHttpConnection(url, 
																	RequestMethod.DELETE, 
																	Boolean.TRUE);
			responseCode = httpURLConnection.getResponseCode();
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return responseCode;
	}

	public MultiGetResponse findByMultipleIndexs(
			List<ElasticSearchRequest> requests) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<String> getAlldata(String index, String type) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Member> findMemberByIndex(String index, String type, String id) {

		List<Member> members = null;
		String url = Constants.BASE_URL+"/"+index+"/"+type+"/_search";
		
		try {
			HttpURLConnection httpURLConnection = TeamMemberUtil.getHttpConnection(url, 
																	RequestMethod.POST, 
																	Boolean.TRUE);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(getSearchQueryAsPerId(id));
			outputStreamWriter.flush();
			outputStreamWriter.close();
			
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("responseCode: "+responseCode);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

			String inputLine = null;
			StringBuffer responseData = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				responseData.append(inputLine);
			}
			bufferedReader.close();

			String output = responseData.toString();
			System.out.println(output);	
			
			members = JSONParserUtil.parseToGetMember(output);
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return members;
	}
	
	private String getSearchQueryAsPerId(String id){
		StringBuilder query = new StringBuilder();
		query.append("{\"query\":{\"match\":{\"_id\":\""+id+"\"}}}");
		return query.toString();
	}

	public List<Member> findMember(String index, String type, String searchQuery) {
		List<Member> members = null;
		String url = Constants.BASE_URL+"/"+index+"/"+type+"/_search";
		
		try {
			HttpURLConnection httpURLConnection = TeamMemberUtil.getHttpConnection(url, 
																	RequestMethod.POST, 
																	Boolean.TRUE);
			OutputStream outputStream = httpURLConnection.getOutputStream();
			OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream, "UTF-8");
			outputStreamWriter.write(searchQuery);
			outputStreamWriter.flush();
			outputStreamWriter.close();
			
			int responseCode = httpURLConnection.getResponseCode();
			System.out.println("responseCode: "+responseCode);
			
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));

			String inputLine = null;
			StringBuffer responseData = new StringBuffer();
			while ((inputLine = bufferedReader.readLine()) != null) {
				responseData.append(inputLine);
			}
			bufferedReader.close();

			String output = responseData.toString();
			System.out.println(output);	
			
			members = JSONParserUtil.parseToGetMember(output);
			
		} catch (IOException ioException) {
			ioException.printStackTrace();
		} catch (Exception exception){
			exception.printStackTrace();
		}
		return members;
	}
}
