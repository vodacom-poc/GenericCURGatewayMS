package com.vodacom.GenericCURGatewayMS.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.vodacom.GenericCURGatewayMS.domain.CURAttributesResponse;
import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributesResponse;
import com.vodacom.GenericCURGatewayMS.service.GenericCURGatewayService;
import com.vodacom.GenericCURGatewayMS.webServiceClient.GenericCURGatewayWSClient;

@Service
public class GenericCURGatewayServiceImpl implements GenericCURGatewayService {
	public static final Logger log = LoggerFactory.getLogger(GenericCURGatewayServiceImpl.class);
	
	@Autowired 
	GenericCURGatewayWSClient genericCURGatewayWSClient;
	
	@HystrixCommand(fallbackMethod = "processCURQueryFallBack")
	public GetCurAttributesResponse processCURQuery(GetCurAttributes getCurAttributes){
		log.info("Inside GenericCURGatewayServiceImpl  processCURQuery request Params::" + getCurAttributes.toString());
		GetCurAttributesResponse curResponse = genericCURGatewayWSClient.getCURAttributes(getCurAttributes);
		return curResponse;
	}
	
	
	public GetCurAttributesResponse processCURQueryFallBack(GetCurAttributes getCurAttributes,Throwable t){
		log.info("fallback method  processCURQueryFallBack called,the error thrown is: " + getErrorStackTrace(t));
		GetCurAttributesResponse curResponse = new GetCurAttributesResponse();
		CURAttributesResponse curAttributesResponse = new CURAttributesResponse();
		curAttributesResponse.setErrorCode("Error returned");
		curResponse.setReturn(curAttributesResponse);
		return curResponse;
	}
	
	private String getErrorStackTrace(Throwable t) {

		if (t != null) {
			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			t.printStackTrace(pw);
			return sw.toString();
		} else {
			return null;
		}

	}
}
