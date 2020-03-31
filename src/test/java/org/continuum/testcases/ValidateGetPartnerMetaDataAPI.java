package org.continuum.testcases;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.continuum.apis.GetPartnerMetaDataAPI;
import org.continuum.setup.TestSetUp;
import org.continuum.utils.APIUtils;
import org.continuum.utils.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateGetPartnerMetaDataAPI extends TestSetUp
{
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetPartnerMetaDataAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response = GetPartnerMetaDataAPI.getPartnerMetaDataWithValidDetailsAPI(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "CompanyCode"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "CompanyName"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "Country"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "EmailId"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "HourFormat"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "Language"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "State"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "TimeZone"));
		Assert.assertEquals(APIUtils.getKeyValue(outdataList, "CompanyCode"), 
				data.get("companyCode"));
		Assert.assertEquals(APIUtils.getKeyValue(outdataList, "CompanyName"), 
				data.get("companyName"));
		Assert.assertEquals(APIUtils.getKeyValue(outdataList, "EmailId"), data.get("emailID"));
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetPartnerMetaDataAPIWithInvalidPartnerID(Hashtable<String, String> data)
	{
		Response response = GetPartnerMetaDataAPI.getPartnerMetaDataWithValidDetailsAPI(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertNull(APIUtils.getKeyValue(outdataList, "CompanyCode"));
		Assert.assertNotNull(APIUtils.getKeyValue(outdataList, "Error"));
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetPartnerMetaDataAPIWithInvalidOperation(Hashtable<String, String> data)
	{
		Response response = GetPartnerMetaDataAPI.getPartnerMetaDataWithValidDetailsAPI(data);
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		Assert.assertNull(jsonPath.get("outdata"));
	}
}