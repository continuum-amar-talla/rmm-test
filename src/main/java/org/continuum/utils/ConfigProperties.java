package org.continuum.utils;

import org.aeonbits.owner.Config;
import org.aeonbits.owner.Config.Sources;

@Sources({"file:src/test/resources/propertyFiles/config.properties"})
public interface ConfigProperties extends Config
{
	@Key("brightGaugeBaseURI")
	public String getBrightGaugeBaseURI();
	
	@Key("brightGaugeBasePath")
	public String getBrightGaugeBasePath();
	
	@Key("sheetName")
	public String getSheetName();
	
	@Key("brightGaugeGetPolicyDetailsEndPointPartnerPath")
	public String getBrightGaugeGetPolicyDetailsEndPointPartnerPath();
	
	@Key("brightGaugeGetPolicyDetailsEndPointUserPath")
	public String getBrightGaugeGetPolicyDetailsEndPointUserPath();
	
	@Key("brightGaugeAssignedPoliciesEndPoint")
	public String getBrightGaugeAssignedPoliciesEndPoint();
	
	@Key("partnerMetaDataEndPoint")
	public String getPartnerMetaDataEndPoint();
	
	@Key("getListSiteEndPoint")
	public String getListSiteEndPoint();
}