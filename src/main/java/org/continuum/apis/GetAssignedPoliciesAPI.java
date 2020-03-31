package org.continuum.apis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.continuum.setup.TestSetUp;

import io.restassured.response.Response;

public class GetAssignedPoliciesAPI extends TestSetUp
{
	public static Response getAssignedPoliciesAPIWithValidDetails(Hashtable<String, String> data)
	{
		Response response  = given().
				get(data.get("partnerID")
				+ configProperties.getBrightGaugeAssignedPoliciesEndPoint());
		return response;
	}
}