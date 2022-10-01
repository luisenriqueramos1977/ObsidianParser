

/*
 * Copyright [2022] [Luis Enrique Ramos García].
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */


package com.ramos.obsidian.models;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;


public abstract class HttpURLFlureeDBConnection {
	
	//post query function
	/**
	 * @param base_url server url
	 * @param fluree_ledger target database
	 * @param operation to be done e.g sparql, transaction, etc
	 * @param message content of the operation (crud).
	 * @return post_response the response obtained from the server
	 * @throws IOException 
	 */
	public String sendPost(String base_url, String fluree_ledger, String operation, String message) throws IOException {
		String post_response = null;
		URL obj = new URL(base_url+fluree_ledger+operation+message);
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();
		con.setRequestMethod("POST");
		con.setRequestProperty("Content-Type", "text/plain");
		// For POST only - START
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(null);
		os.flush();
		os.close();
		// For POST only - END
		int responseCode = con.getResponseCode();
		System.out.println("POST Response Code :: " + responseCode);
		//processing the response
		if (responseCode == HttpURLConnection.HTTP_OK) { //success
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			StringBuffer response = new StringBuffer();

			while ((post_response = in.readLine()) != null) {
				response.append(post_response);
			}
			in.close();
		} else {
			post_response="POST request not worked";
		}
		return post_response;
	}
	
	public static String sendOkHttpClientPost(String content_type, String target_url, String http_method, String http_body) throws IOException {
		String responseBody =null;
		OkHttpClient client = new OkHttpClient().newBuilder()
				  .build();
		MediaType mediaType = MediaType.parse(content_type);
		RequestBody body = RequestBody.create(mediaType, http_body);
		Request request = new Request.Builder()
				  .url(target_url)
				  .method(http_method, body)
				  .addHeader("Content-Type", content_type)
				  .build();
		try (Response response = client.newCall(request).execute()) {
			  responseBody = response.body().string();
			  // ... do something with response
			 }
		
		return responseBody;
	}
	
	
}//end class
