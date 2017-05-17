package com.vodacom.GenericCURGatewayWS.webServiceClient;

import java.io.ByteArrayOutputStream;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Iterator;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.namespace.QName;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConnection;
import javax.xml.soap.SOAPConnectionFactory;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.soap.SOAPPart;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import com.vodacom.GenericCURGatewayWS.domain.AdapterRequest;
import com.vodacom.GenericCURGatewayWS.domain.CURAttribute;
import com.vodacom.GenericCURGatewayWS.domain.CURAttributes;
import com.vodacom.GenericCURGatewayWS.domain.CURAttributesRequest;
import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributes;
import com.vodacom.GenericCURGatewayWS.domain.GetCurAttributesResponse;

@SpringBootApplication
@Configuration
public class GenericCURGatewayWSClient{
	private static final Logger log = LoggerFactory.getLogger(GenericCURGatewayWSClient.class);
	
	
	@Value("${vodacom.wsdl.url}")
	private String wsdlURL;
	
	
	public GetCurAttributesResponse getCURAttributes(GetCurAttributes getCurAttributes) {
		log.debug("Inside GenericCURGatewayWSClient  getCURAttributes ::" + getCurAttributes.toString());
		GetCurAttributesResponse curAttributesResponse = null;
		

		
		try {

			log.debug("WSDL URL::" + wsdlURL);
			curAttributesResponse = callwsWS(getCurAttributes, wsdlURL);
			

		} catch (Exception e) {
			e.printStackTrace();
		}
		return curAttributesResponse;

	}

	private GetCurAttributesResponse callwsWS(GetCurAttributes getCurAttributes, String url) {
		GetCurAttributesResponse response = null;
		try {

			SOAPConnectionFactory soapConnectionFactory = SOAPConnectionFactory.newInstance();
			SOAPConnection soapConnection = soapConnectionFactory.createConnection();

			SOAPMessage soapResponse = soapConnection.call(createSOAPRequest(getCurAttributes), url);
			log.debug("soapResponse::" + soapResponse.toString());
			response = printSOAPResponse(soapResponse);
			soapConnection.close();
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}
		return response;
	}

	private SOAPMessage createSOAPRequest(GetCurAttributes getCurAttributes) throws Exception {
		StringWriter stringWriter = new StringWriter();
		//CURAttributesRequest curAttributesRequest = new CURAttributesRequest();
		SOAPMessage soapMessage = null;
		try {
			log.debug("Inside GenericCURGatewayWSClient  curAttributesRequest ::" + getCurAttributes.getCurAttributesRequest().getQueryString());
			JAXBContext jaxbContext = JAXBContext.newInstance(GetCurAttributes.class,CURAttributesRequest.class, AdapterRequest.class,java.util.LinkedHashMap.class);
			Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
			
			jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
			jaxbMarshaller.setProperty("jaxb.fragment",true);
			QName qName = new QName("http://www.vodacom.co.za/", "getCurAttributes", "vod");
			JAXBElement<GetCurAttributes> root = new JAXBElement<>(qName, GetCurAttributes.class, getCurAttributes);
			log.debug("JAXBElement ::" + root.getDeclaredType());
			jaxbMarshaller.marshal(root, stringWriter);

			log.debug("Request xml after marshalling ::" + stringWriter.toString());
			soapMessage = MessageFactory.newInstance().createMessage();
			SOAPPart part = soapMessage.getSOAPPart();
			SOAPEnvelope env = part.getEnvelope();
			SOAPHeader header = env.getHeader();

			SOAPBody body = env.getBody();

			Document doc = convertStringToDocument(stringWriter.toString());
			body.addDocument(doc);

			log.debug("added raw xml::" + doc.getTextContent());

			ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
			soapMessage.writeTo(outputStream);

			String output = new String(outputStream.toByteArray());
			log.debug("output xml::" + output);
		} catch (Exception e) {
			System.err.println("Error occurred while sending SOAP Request to Server");
			e.printStackTrace();
		}

		return soapMessage;
	}

	private GetCurAttributesResponse printSOAPResponse(SOAPMessage soapResponse) throws Exception {
		GetCurAttributesResponse curAttributesResponse = null;
		StringWriter sw = new StringWriter();
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		Source sourceContent = soapResponse.getSOAPPart().getContent();
		log.debug("sourceContent::" + sourceContent);
		StreamResult result = new StreamResult(sw);
		transformer.transform(sourceContent, result);
		
		Document xmldoc = convertStringToDocument(sw.toString());
		log.debug("xmldoc ::" + convertDocumentToString(xmldoc));
		log.debug("xmldoc elements::" + xmldoc.getTextContent());
		NodeList  nodeList =  xmldoc.getElementsByTagNameNS("http://www.vodacom.co.za/", "*");
		log.debug("xmldoc elements::" + nodeList.getLength());
		if(null!=nodeList && null!= nodeList.item(0)){	
			log.debug("nodeList::" + nodeList.item(0));
			Node bodyContent =  nodeList.item(0);
			log.debug("\n bodyContent = \n" + bodyContent.getTextContent());		
			
			Unmarshaller unmarshaller = JAXBContext.newInstance(GetCurAttributesResponse.class).createUnmarshaller();
			JAXBElement<GetCurAttributesResponse> root =unmarshaller.unmarshal(bodyContent,GetCurAttributesResponse.class);
			if(null!=root){
				curAttributesResponse = root.getValue();
				if(null!=curAttributesResponse && null!=curAttributesResponse.getReturn()){
					CURAttributes attributes  = curAttributesResponse.getReturn().getCURAttributes().get(0);
					Iterator<CURAttribute> itr = attributes.getCURAttribute().iterator();
					while(itr.hasNext()){
						CURAttribute attribute = itr.next();
						if(null!=attribute){
							log.debug("attribute.key ::" + attribute.getKeyName());
							log.debug("attribute.value ::" + attribute.getKeyValue());
						}
					}
					log.debug("GetCurAttributesResponse::" + curAttributesResponse.getReturn());
				}
			}
		}
		return curAttributesResponse;
	}


	private static Document convertStringToDocument(String xmlStr) {		
		DocumentBuilder builder;
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			factory.setNamespaceAware(true);
			builder = factory.newDocumentBuilder();
			log.debug("xmlStr ::" + xmlStr.toString()); 
			InputSource is = new InputSource();
		    is.setCharacterStream(new StringReader(xmlStr));
			log.debug("is ::"+ is.toString());
			Document doc = builder.parse(is);
			log.debug("doc::" + doc.getTextContent());
			return doc;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String convertDocumentToString(Document doc) {
        TransformerFactory tf = TransformerFactory.newInstance();
        StringWriter writer = new StringWriter();
        Transformer transformer;
        try {
            transformer = tf.newTransformer();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));
            String output = writer.getBuffer().toString();
            return output;
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        
        return null;
    }


}
