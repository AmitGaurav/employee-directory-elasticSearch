package com.cisco.poc.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
/**
 * 
 * @author amigaura
 *
 */
public class TeamMemberUtil {

	/***
	 * 
	 * @param requestMethod
	 * @param doOutput A URL connection can be used for input and/or output. 
	 * 			Set the DoOutput flag to true if you intend to use the URL 
	 * 			connection for output, false if not. The default is false.
	 * @return httpURLConnection
	 * @throws IOException 
	 */
	public static HttpURLConnection getHttpConnection(String url,
														RequestMethod requestMethod, 
														Boolean doOutput) throws IOException {
		URL obj = new URL(url);
		HttpURLConnection httpURLConnection = (HttpURLConnection) obj.openConnection();
		httpURLConnection.setRequestMethod(requestMethod.toString());
		httpURLConnection.setRequestProperty("Content-Type", "application/json");
		httpURLConnection.setDoOutput(doOutput);
		return httpURLConnection;
	}
}
