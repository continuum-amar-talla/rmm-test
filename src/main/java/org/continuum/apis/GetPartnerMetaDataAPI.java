package org.continuum.apis;

import static io.restassured.RestAssured.given;

import java.util.Hashtable;

import org.continuum.setup.TestSetUp;

import io.restassured.response.Response;

public class GetPartnerMetaDataAPI extends TestSetUp
{
	public static Response getPartnerMetaDataWithValidDetailsAPI(Hashtable<String, String> data)
	{
		Response response  = given().param("Operation", data.get("operation")).
				get(data.get("partnerID") +
				configProperties.getPartnerMetaDataEndPoint());
		return response;
	}
}