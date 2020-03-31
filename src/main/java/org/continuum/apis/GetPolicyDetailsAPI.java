package org.continuum.apis;

import java.util.Hashtable;

import org.continuum.setup.TestSetUp;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class GetPolicyDetailsAPI extends TestSetUp
{
	public static Response getPolicyDetailsAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response  = given().
				get(data.get("partnerID")
				+ configProperties.getBrightGaugeGetPolicyDetailsEndPointPartnerPath()
				+ data.get("policyID")
				+ configProperties.getBrightGaugeGetPolicyDetailsEndPointUserPath());
		return response;
	}
}