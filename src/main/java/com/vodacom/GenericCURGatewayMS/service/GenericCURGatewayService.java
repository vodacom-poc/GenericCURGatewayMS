package com.vodacom.GenericCURGatewayMS.service;

import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributesResponse;

//@SpringBootApplication
//@FeignClient(name = "GENERICCURGatewayWS")
public interface GenericCURGatewayService {
	/**
	 * 
	 * @param getCurAttributes
	 * @return GetCurAttributesResponse
	 */
	//@RequestMapping(value = "/curportalbalances",method = RequestMethod.POST)
	public GetCurAttributesResponse processCURQuery(GetCurAttributes getCurAttributes);

}
