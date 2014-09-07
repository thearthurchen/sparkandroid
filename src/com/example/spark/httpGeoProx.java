package com.example.spark;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

import android.os.AsyncTask;
import android.util.Log;

public class httpGeoProx {
	
	
	public httpGeoProx () {

	}
	
	static String webserver = "http://sparkhack.herokuapp.com/";

	/*
	 * AsyncTask to perform network task on main thread
	 */
	public static class httpAsyncGet extends AsyncTask<String, Void, String> {
		
		private List<NameValuePair> paramData = new ArrayList<NameValuePair>();
		
		public String httpGet(String path, List<NameValuePair> params) throws IOException
		{
			String url = webserver + path + "?";
			StringBuilder query = new StringBuilder();
			for (Iterator <NameValuePair> iter = params.iterator(); iter.hasNext();) {
				Log.v("wtf", "im ok here");
				NameValuePair pair = iter.next();
				Log.v("wtf", "pair is " + pair.toString());
				query.append(pair.getName() + "=" + pair.getValue() + "&");
				Log.v("wtf", query.toString());
			}
			HttpClient httpclient = new DefaultHttpClient();
			HttpGet request = new HttpGet(url+query.toString());
			HttpResponse response = httpclient.execute(request);

			// Get the response
			BufferedReader rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));

			String readLine;
			String responseBody = "";
			while (((readLine = rd.readLine()) != null)) {
			 responseBody += "\n" + readLine;
			}
			return responseBody;
		}
		
		public void addData(String key, String pair) {
			Log.v("wtf", key);
			Log.v("wtf", pair);
			paramData.add(new BasicNameValuePair(key, pair));
		}
		
        @Override
        protected String doInBackground(String... url) {
        	String response = "NO RESPONSE";
            try {
				response = httpGet(url[0], paramData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return response;
        }
	}
	
	/*
	 * AsyncTask to perform network task on main thread
	 */
	public static class httpAsyncPost extends AsyncTask<String, Void, String> {
		
		private List<NameValuePair> bodyData = new ArrayList<NameValuePair>(3);
		
		public String httpPost(String path, List<NameValuePair> body) throws IOException
		{
			String url = webserver + path;
			HttpClient httpclient = new DefaultHttpClient();
			//http post type
			HttpPost request = new HttpPost(url);
			//add key-pair into body of post
			request.setEntity(new UrlEncodedFormEntity(body));
			//execute response
			HttpResponse response = httpclient.execute(request);

			// Get the response
			BufferedReader rd = new BufferedReader
			  (new InputStreamReader(response.getEntity().getContent()));

			String readLine;
			String responseBody = "";
			while (((readLine = rd.readLine()) != null)) {
			 responseBody += "\n" + readLine;
			}
			return responseBody;
		}
		
		public void addData(String key, String pair) {
			bodyData.add(new BasicNameValuePair(key, pair));
		}
		
        @Override
        protected String doInBackground(String... url) {
        	String response = "NO RESPONSE";
            try {
				response = httpPost(url[0], bodyData);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            return response;
        }
	}

}
