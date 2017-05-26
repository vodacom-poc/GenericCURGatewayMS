package com.vodacom.GenericCURGatewayMS.resources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayMS.domain.GetCurAttributesResponse;
import com.vodacom.GenericCURGatewayMS.service.GenericCURGatewayService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
/**
 * @author mahima.agarwal
 *
 */


@RestController
@Api(tags = "Vodacom CUR  Balance Enquiry")
public class GenericCURGatewayController {
	
	private static final Logger log = LoggerFactory.getLogger(GenericCURGatewayController.class); 
	
	@Autowired
	GenericCURGatewayService genericCURGatewayWSService;
	
    @ApiOperation(value = "CURAttributesResponse", nickname = "processCURQuery")
	@RequestMapping(value = "/curportalbalances", method=RequestMethod.POST, consumes = {MediaType.APPLICATION_XML_VALUE, MediaType.APPLICATION_JSON_VALUE} , produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public GetCurAttributesResponse processCURQuery(@RequestBody GetCurAttributes getCurAttributes ){
    	GetCurAttributesResponse curAttributesResponse = null;
    	if(null!=getCurAttributes){
	    	log.info("Started getSubscriptionBalance request Params::" + getCurAttributes.getCurAttributesRequest().toString());
	    	curAttributesResponse = genericCURGatewayWSService.processCURQuery(getCurAttributes);
    	}
		return curAttributesResponse;
	
	}
}
