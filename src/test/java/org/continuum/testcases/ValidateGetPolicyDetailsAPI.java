package org.continuum.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.continuum.apis.GetPolicyDetailsAPI;
import org.continuum.setup.TestSetUp;
import org.continuum.utils.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateGetPolicyDetailsAPI extends TestSetUp
{
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetPolicyDetailsAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response = GetPolicyDetailsAPI.getPolicyDetailsAPIWithValidDetails(data);
		Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		for (HashMap<String, Object> singleElement : outdataList)
		{
			Assert.assertEquals(singleElement.get("PolicyName"), data.get("policyName"));
			Assert.assertEquals(singleElement.get("PolicyType"), data.get("policyType"));
			Assert.assertEquals(singleElement.get("SiteId"), Integer.parseInt(data.get("siteID")));
		}
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetPolicyDetailsAPIWithInvalidDetails(Hashtable<String, String> data)
	{
		Response response = GetPolicyDetailsAPI.getPolicyDetailsAPIWithValidDetails(data);
		Assert.assertEquals(response.statusCode(), Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		System.out.println("" + jsonPath.get("outdata"));
		
	}
}