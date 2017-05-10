package com.vodacom.GenericCURGatewayWS.service;

import GenericCURGatewayWS.wsdl.GetCurAttributes;
import GenericCURGatewayWS.wsdl.GetCurAttributesResponse;

//@SpringBootApplication
//@FeignClient(name = "GENERICCURGatewayWS")
public interface GenericCURGatewayWSService {
	/**
	 * 
	 * @param getCurAttributes
	 * @return GetCurAttributesResponse
	 */
	//@RequestMapping(value = "/curportalbalances",method = RequestMethod.POST)
	public GetCurAttributesResponse processCURQuery(GetCurAttributes getCurAttributes);

}
