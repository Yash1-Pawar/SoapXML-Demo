package com.soap.service;

import java.io.StringWriter;

import javax.annotation.PostConstruct;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.springframework.context.annotation.Configuration;

import com.soap.model.GetUserResponse;
import com.soap.model.User;

@Configuration
public class JavaObjToSoapXml {
	
	@PostConstruct
	public void name() throws JAXBException {
		System.out.println("obj to xml");
		// create a new instance of the JAXB context for the User and GetUserResponse classes
		JAXBContext jaxbContext = JAXBContext.newInstance(User.class, GetUserResponse.class);

		// create a new instance of the GetUserResponse object
		GetUserResponse response = new GetUserResponse();

		// create a new instance of the first User object and set its properties
		User user1 = new User();
		user1.setId(1);
		user1.setName("userName");
		user1.setValue("yash");

		// create a new instance of the second User object and set its properties
		User user2 = new User();
		user2.setId(2);
		user2.setName("email");
		user2.setValue("yash@gmail.com");

		// add the two User objects to the list of users
		response.getUsers().add(user1);
		response.getUsers().add(user2);

		// create a new StringWriter to hold the marshalled XML
		StringWriter writer = new StringWriter();

		// marshal the GetUserResponse object to XML using the JAXB context
		Marshaller marshaller = jaxbContext.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		marshaller.marshal(new JAXBElement<>(new QName("http://example.com/soap", "GetUserResponse", "Soap"), GetUserResponse.class, response), writer);

		// get the marshalled XML as a string
		String xmlString = writer.toString();
		
		System.out.println(xmlString);
	}

}
