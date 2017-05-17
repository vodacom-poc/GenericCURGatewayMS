package com.vodacom.GenericCURGatewayWS.service;

import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributesResponse;

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
