package com.cisco.poc.service;

import java.util.List;

import org.elasticsearch.action.get.MultiGetResponse;
import org.json.JSONObject;

import com.cisco.poc.model.ElasticSearchRequest;
import com.cisco.poc.model.Member;

public interface TeamMemberService {
	
	public boolean isIndexExist(String id);
	
	public int createMember(String index, String type, String id, JSONObject jsonObject);
	
	public int updateMember(String index, String type, String id, JSONObject jsonObject);
	
	public int removeMember(String index, String type, String id);
	
	public MultiGetResponse findByMultipleIndexs(List<ElasticSearchRequest> requests);
	
	public List<String> getAlldata(String index, String type);
	
	public List<Member> findMemberByIndex(String index, String type, String id);
	
	public List<Member> findMember(String index, String type, String searchQuery);
}
