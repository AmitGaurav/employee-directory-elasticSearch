package com.cisco.poc.util;

import java.util.Date;

import org.json.JSONObject;

import com.cisco.poc.model.Member;

public class JsonTest {

	public static void main(String[] args) {
		test1();	
		test2();
    }

	private static void test1() {
		Member member = new Member();
		member.setName( "Amit Gaurav" );
		member.setDob(new Date(System.currentTimeMillis()) );

        JSONObject jsonObj = new JSONObject(member);
        System.out.println( jsonObj );
	}
	
	private static void test2(){
		StringBuilder updateQuery = new StringBuilder();
        updateQuery.append("{");
        updateQuery.append("    \"doc\" : {");
        updateQuery.append("         \"ciscoId\" : \"rayaanjha\"");
        updateQuery.append("    }");
        updateQuery.append("}");

        JSONObject jsonObject = new JSONObject(updateQuery);
        System.out.println(jsonObject);
	}
}
