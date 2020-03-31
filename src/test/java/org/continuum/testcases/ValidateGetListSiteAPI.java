package org.continuum.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.continuum.apis.GetListSiteAPI;
import org.continuum.setup.TestSetUp;
import org.continuum.utils.APIUtils;
import org.continuum.utils.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateGetListSiteAPI extends TestSetUp
{
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetListSiteAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response = GetListSiteAPI.getListSiteAPIWithValidDetails(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertEquals(APIUtils.getKeyValue(outdataList, "SiteId"), 
				Integer.parseInt(data.get("siteID")));
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetListSiteAPIWithInvalidDetails(Hashtable<String, String> data)
	{
		Response response = GetListSiteAPI.getListSiteAPIWithValidDetails(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertNull(APIUtils.getKeyValue(outdataList, "SiteId"));
		Assert.assertNotNull(APIUtils.getKeyValue(outdataList, "Error"));
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateListSiteAPIWithInvalidOperation(Hashtable<String, String> data)
	{
		Response response = GetListSiteAPI.getListSiteAPIWithValidDetails(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		Assert.assertNull(jsonPath.get("outdata"));
	}
}