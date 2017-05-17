package com.vodacom.GenericCURGatewayWS.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributesResponse;
import com.vodacom.GenericCURGatewayWS.service.GenericCURGatewayWSService;
import com.vodacom.GenericCURGatewayWS.webServiceClient.GenericCURGatewayWSClient;

@Service
public class GenericCURGatewayWSServiceImpl implements GenericCURGatewayWSService {
	public static final Logger log = LoggerFactory.getLogger(GenericCURGatewayWSServiceImpl.class);
	
	@Autowired 
	GenericCURGatewayWSClient genericCURGatewayWSClient;

	public GetCurAttributesResponse processCURQuery(GetCurAttributes getCurAttributes){
		log.info("Inside VodacomBalanceEnquiryServiceImpl  getSubscriptionBalance request Params::" + getCurAttributes.toString());
		GetCurAttributesResponse curResponse = genericCURGatewayWSClient.getCURAttributes(getCurAttributes);
		return curResponse;
	}
	
	
}
