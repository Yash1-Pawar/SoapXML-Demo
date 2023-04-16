package com.soap.service;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.SOAPMessage;

import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.soap.model.GetUserResponse;
import com.soap.model.User;

@Configuration
public class SoapXmlToJavaObj {

//	@PostConstruct
	public void name() {
		try {
			ObjectMapper objectMapper = new ObjectMapper();
			// Sample SOAP response
			String soapResponse = "<soap:Envelope xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">\n"
					+ "   <soap:Body>\n" + "      <GetUserResponse>\n"
					+ "         <User id=\"1\" name=\"userName\">yash</User>\n"
					+ "         <User id=\"1\" name=\"userName\">yash1</User>\n"
					+ "         <User id=\"2\" name=\"email\">yash@gamil.com</User>\n" + "      </GetUserResponse>\n"
					+ "   </soap:Body>\n" + "</soap:Envelope>";
			
//			soapResponse = "{\"users\":[{\"id\":1,\"name\":\"userName\",\"value\":\"yash\"},{\"id\":1,\"name\":\"userName\",\"value\":\"yash1\"},{\"id\":2,\"name\":\"email\",\"value\":\"yash@gamil.com\"}]}";

			// Create a SOAP message from the response string
			SOAPMessage soapMessage = MessageFactory.newInstance().createMessage(null,
					new ByteArrayInputStream(soapResponse.getBytes(StandardCharsets.UTF_8)));

			// Get the SOAP body as a JAXB object
			JAXBContext jaxbContext = JAXBContext.newInstance(GetUserResponse.class);
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			System.out.println("DDDDDDD");
			GetUserResponse getUserResponse = (GetUserResponse) unmarshaller
					.unmarshal(soapMessage.getSOAPBody().extractContentAsDocument());
			System.out.println("get user ---> "+ objectMapper.writeValueAsString(getUserResponse));

			// Print the unmarshalled object
			List<User> userList = getUserResponse.getUsers();
			for (User user : userList) {
				System.out.println(user.getId());
				System.out.println(user.getName());
				System.out.println(user.getValue());
			}
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}

}
