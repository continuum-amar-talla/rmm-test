package org.continuum.utils;

import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

import io.restassured.response.Response;

public class APIUtils
{
	public static boolean checkJsonHasKey(Response response, String key)
	{
		JSONObject jsonObject = new JSONObject(response.asString());
		return jsonObject.has(key);
	}
	
	public static boolean checkJsonArrayHasKey(Response response, String jsonArray, String key)
	{
		int count = 0;
		JSONObject jsonObject = new JSONObject(response.asString());
		JSONArray jsonArrayOfKey = jsonObject.getJSONArray(jsonArray);
		for(int i = 0; i < jsonArrayOfKey.length(); i++)
		{
			JSONObject innerJsonObjects = jsonArrayOfKey.getJSONObject(i);
			if(innerJsonObjects.has(key))
			{
				count++;
			}
		}
		if(count == jsonArrayOfKey.length())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public static Object getKeyValue(List<HashMap<String, Object>> outdataList, String key)
	{
		for(HashMap<String , Object> singleOutdata : outdataList)
		{
			return singleOutdata.get(key);
		}
		return -1;
	}
	
	public static boolean checkListOfJSONValue(List<HashMap<String, Object>> outdataList, String key,
			List<String> values)
	{
		boolean flag = true;
		int i = 0;
		for(HashMap<String , Object> singleOutdata : outdataList)
		{
			if(!(singleOutdata.get(key).equals(values.get(i))))
			{
				flag = false;
				return flag;
			}
			i++;
		}
		return flag;
	}
	
	public static boolean checkJSONKeyValueNotNull(List<HashMap<String, Object>> outdataList, 
			String key)
	{
		boolean flag = true;
		for(HashMap<String , Object> singleOutdata : outdataList)
		{
			if(singleOutdata.get(key).equals(null))
			{
				flag = false;
				return flag;
			}
		}
		return flag;
	}
}