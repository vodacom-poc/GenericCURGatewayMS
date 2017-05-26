package com.vodacom.GenericCURGatewayMS.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributesResponse;
import com.vodacom.GenericCURGatewayMS.service.GenericCURGatewayService;
import com.vodacom.GenericCURGatewayMS.webServiceClient.GenericCURGatewayWSClient;

@Service
public class GenericCURGatewayServiceImpl implements GenericCURGatewayService {
	public static final Logger log = LoggerFactory.getLogger(GenericCURGatewayServiceImpl.class);
	
	@Autowired 
	GenericCURGatewayWSClient genericCURGatewayWSClient;

	public GetCurAttributesResponse processCURQuery(GetCurAttributes getCurAttributes){
		log.info("Inside VodacomBalanceEnquiryServiceImpl  getSubscriptionBalance request Params::" + getCurAttributes.toString());
		GetCurAttributesResponse curResponse = genericCURGatewayWSClient.getCURAttributes(getCurAttributes);
		return curResponse;
	}
	
	
}
