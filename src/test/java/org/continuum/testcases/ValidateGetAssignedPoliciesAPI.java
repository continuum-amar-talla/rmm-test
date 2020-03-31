package org.continuum.testcases;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.List;

import org.continuum.apis.GetAssignedPoliciesAPI;
import org.continuum.setup.TestSetUp;
import org.continuum.utils.APIUtils;
import org.continuum.utils.Data;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class ValidateGetAssignedPoliciesAPI extends TestSetUp
{
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetAssignedPoliciesAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response = GetAssignedPoliciesAPI.getAssignedPoliciesAPIWithValidDetails(data);
		//response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "CreatedBy"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "DcDtime"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "IsNoc"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "MemberId"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "NocUserName"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyID"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyName"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyType"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "UpdatedBy"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "UpdcdTime"));
		String policyNames = data.get("policyNames");
		String[] policyName = policyNames.split(",");
		List<String> listOfPolicies = Arrays.asList(policyName);
		Assert.assertTrue(APIUtils.checkListOfJSONValue(outdataList, "PolicyName", listOfPolicies));
		String policyTypes = data.get("policyTypes");
		String[] policyType = policyTypes.split(",");
		List<String> listOfPolicyTypes = Arrays.asList(policyType);
		Assert.assertTrue(APIUtils.checkListOfJSONValue(outdataList, "PolicyType", 
				listOfPolicyTypes));
		Assert.assertTrue(APIUtils.checkJSONKeyValueNotNull(outdataList, "MemberId"));
		Assert.assertTrue(APIUtils.checkJSONKeyValueNotNull(outdataList, "PolicyID"));
	}
	
	@Test(dataProviderClass = Data.class, dataProvider = "data")
	public void validateGetAssignedPoliciesAPIWithDefaultDetails(Hashtable<String, String> data)
	{
		Response response = GetAssignedPoliciesAPI.getAssignedPoliciesAPIWithValidDetails(data);
		//response.prettyPrint();
		Assert.assertEquals(response.statusCode(), 
				Integer.parseInt(data.get("expectedStatusCode")));
		JsonPath jsonPath = response.jsonPath();
		List<HashMap<String, Object>> outdataList = jsonPath.get("outdata");
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "CreatedBy"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "DcDtime"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "IsNoc"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "MemberId"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "NocUserName"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyID"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyName"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "PolicyType"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "UpdatedBy"));
		Assert.assertTrue(APIUtils.checkJsonArrayHasKey(response, "outdata", "UpdcdTime"));
		String policyNames = data.get("policyNames");
		String[] policyName = policyNames.split(",");
		List<String> listOfPolicies = Arrays.asList(policyName);
		Assert.assertTrue(APIUtils.checkListOfJSONValue(outdataList, "PolicyName", listOfPolicies));
		String policyTypes = data.get("policyTypes");
		String[] policyType = policyTypes.split(",");
		List<String> listOfPolicyTypes = Arrays.asList(policyType);
		Assert.assertTrue(APIUtils.checkListOfJSONValue(outdataList, "PolicyType", 
				listOfPolicyTypes));
		Assert.assertTrue(APIUtils.checkJSONKeyValueNotNull(outdataList, "MemberId"));
		Assert.assertTrue(APIUtils.checkJSONKeyValueNotNull(outdataList, "PolicyID"));
	}
}