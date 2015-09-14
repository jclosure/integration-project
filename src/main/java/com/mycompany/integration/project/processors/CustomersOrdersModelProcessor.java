package com.mycompany.integration.project.processors;


import java.io.ByteArrayInputStream;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.apache.camel.Exchange;
import org.apache.camel.Message;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanInitializationException;
import org.springframework.beans.factory.InitializingBean;




import com.mycompany.integration.project.models.CustomersOrders;
import com.mycompany.integration.project.utils.ModelBuilder;


public class CustomersOrdersModelProcessor implements Processor {

	private static final Logger logger = LoggerFactory.getLogger(CustomersOrdersModelProcessor.class);
	
	public void process(Exchange exchange) throws Exception {
		logger.debug("Entered Transform.");

		Message messageIn = exchange.getIn();
		Message messageOut = exchange.getOut();
		
		String xmlString = messageIn.getBody(String.class);
		
		try {
			
			// WORK WITH THE AGILEDATA BUSINESS OBJECT
			CustomersOrders customersOrders = CustomersOrders.class.cast(ModelBuilder.fastDeserialize(xmlString, CustomersOrders.class));
			
			// make a change
			customersOrders.getOrders().getOrder().forEach(order -> order.setEmployeeID("Joel Holder"));			
			
			// now do something else like save it to a db...
			// ...
			
			messageIn.setHeader("status", "SUCCESS");

			
			// Transformation
			logger.debug("got JMSMessageID = " + messageIn.getHeader("JMSMessageID"));
			exchange.setOut(messageIn);
			
			//exchange.getOut().setHeader("JMSCorrelationID", exchange.getIn().getHeader("JMSMessageID"));
			logger.debug("Message body = " + exchange.getOut().getBody());
			logger.debug("Finished Transform.");
		} catch (Exception e) {

			logger.warn("Error while invoking on the AddressService", e);
			exchange.setException(e);
			exchange.getOut();
			//exchange.getFault(true).setBody(new SoapFault("Unable to process request due to server error.", SoapFault.FAULT_CODE_SERVER));
		}
	}



}
