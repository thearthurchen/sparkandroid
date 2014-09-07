package com.example.spark;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class CreateJSON {
	
	public static JSONArray createJSONArray(String key, String value){
		JSONObject jo = new JSONObject();
    	try {
			jo.put(key, value);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONArray ja = new JSONArray();
    	ja.put(jo);
    	return ja;
	}
	
	//create JSON object with two key value pairs
	public static JSONArray createJSONArray(String key, String value, String key2, String value2){
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
    	try {
			jo.put(key, value);
			jo.put(key2, value2);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONArray ja = new JSONArray();
    	ja.put(jo);
    	return ja;
	}
	public static JSONArray createJSONArray(String key, String value, String key2, String value2, String key3, String value3){
		JSONObject jo = new JSONObject();
		JSONObject jo2 = new JSONObject();
    	try {
			jo.put(key, value);
			jo.put(key2, value2);
			jo.put(key3, value3);
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	JSONArray ja = new JSONArray();
    	ja.put(jo);
    	return ja;
	}

}
