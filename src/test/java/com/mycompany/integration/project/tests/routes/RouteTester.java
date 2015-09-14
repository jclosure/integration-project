package com.mycompany.integration.project.tests.routes;

import org.apache.camel.CamelContext;
import org.apache.camel.ConsumerTemplate;
import org.apache.camel.ProducerTemplate;
import org.apache.camel.component.mock.MockEndpoint;
import org.apache.camel.spring.SpringCamelContext;
import org.apache.camel.test.spring.CamelSpringTestSupport;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.AbstractXmlApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.mycompany.integration.project.models.*;
import com.mycompany.integration.project.utils.FileUtils;
import com.mycompany.integration.project.utils.StackTraceInfo;

public class RouteTester extends CamelSpringTestSupport {

	public String testXmlContextPath = "/test-camel-context.xml";
	
	
	@Autowired
	protected CamelContext camelContext;

	@Override
	public String isMockEndpoints() {
		// override this method and return the pattern for which endpoints to
		// mock.
		// use * to indicate all
		return "*";
	}

	private ProducerTemplate producer;
	private ConsumerTemplate consumer;

	protected CamelContext getCamelContext() throws Exception {
		applicationContext = createApplicationContext();
		return SpringCamelContext.springCamelContext(applicationContext);
	}

	@Override
	protected AbstractXmlApplicationContext createApplicationContext() {
		return new ClassPathXmlApplicationContext(testXmlContextPath);
	}

	
	String inputExemplarFilePath = "src/exemplar/CustomersOrders-v.1.0.0.xml";
	String inputExemplar;
	
	String outputExemplarFilePath = "src/exemplar/CustomersOrders-v.1.0.0-transformed.xml";
	String outputExemplar;
	
	@Before
	public void setUp() throws Exception {

		System.out.println("Calling setUp");
		
		// load i/o exemplars
		inputExemplar = FileUtils.getFileString(inputExemplarFilePath);
		outputExemplar = FileUtils.getFileString(outputExemplarFilePath);
		
		camelContext = getCamelContext();

		camelContext.start();

		producer = camelContext.createProducerTemplate();
		consumer = camelContext.createConsumerTemplate();

	}

	@Test
	public void process_messages_as_models_test() throws Exception {

		System.out.println("Calling " + StackTraceInfo.getCurrentMethodName());
		
		String inputUri = "file:src/data1";
		String outputUri = "file:target/output1";
		
		// Set expectations
		int SEND_COUNT = 1;
		
		MockEndpoint mockOutput = camelContext.getEndpoint("mock:" + outputUri, MockEndpoint.class);
		//mockOutput.expectedBodiesReceived(outputExemplar);
		mockOutput.expectedHeaderReceived("status", "SUCCESS");
		mockOutput.expectedMessageCount(SEND_COUNT);
		

		// Perform Test

		for (int i = 0; i < SEND_COUNT; i++) {
			System.out.println("sending message.");

			// do send/receive, aka. run the route end-to-end
			producer.sendBody(inputUri, inputExemplar); 
			String output = consumer.receiveBody(outputUri, String.class); 
		}

	
		// ensure that the order got through to the mock endpoint
		mockOutput.setResultWaitTime(10000);
		mockOutput.assertIsSatisfied();
	}
	
	@Test
	public void process_messages_as_xml_test() throws Exception {

		System.out.println("Calling " + StackTraceInfo.getCurrentMethodName());
		
		// Set expectations
		int SEND_COUNT = 1;

		String inputUri = "file:src/data2";
		String outputUri = "file:target/output2";
		
		MockEndpoint mockOutput = camelContext.getEndpoint("mock:" + outputUri, MockEndpoint.class);
		//mockOutput.expectedBodiesReceived(outputExemplar);
		mockOutput.expectedHeaderReceived("status", "SUCCESS");
		mockOutput.expectedMessageCount(SEND_COUNT);

		// Perform Test

		for (int i = 0; i < SEND_COUNT; i++) {
			System.out.println("sending message.");

			// do send/receive, aka. run the route end-to-end
			producer.sendBody(inputUri, inputExemplar); 
			String output = consumer.receiveBody(outputUri, String.class); 
		}

		// ensure that the order got through to the mock endpoint
		mockOutput.setResultWaitTime(100000);
		mockOutput.assertIsSatisfied();
	}

	
	@Test
	public void process_jetty_messages_as_xml_test() throws Exception {

		System.out.println("Calling " + StackTraceInfo.getCurrentMethodName());
		
		// Set expectations
		int SEND_COUNT = 1;

		String inputUri = "jetty:http://0.0.0.0:8888/myapp/myservice/?sessionSupport=true";
		String outputUri = "file:target/output3";
		
		MockEndpoint mockOutput = camelContext.getEndpoint("mock:" + outputUri, MockEndpoint.class);
		mockOutput.expectedBodiesReceived(inputExemplar);
		mockOutput.expectedHeaderReceived("status", "BAD");
		mockOutput.expectedMessageCount(SEND_COUNT);

		// Perform Test

		for (int i = 0; i < SEND_COUNT; i++) {
			System.out.println("sending message.");

			// do send/receive, aka. run the route end-to-end
			String result = producer.requestBody(inputUri, inputExemplar, String.class); 
			String output = consumer.receiveBody(outputUri, String.class); 
			
			assertEquals("OK", result);
		}

		// ensure that the order got through to the mock endpoint
		mockOutput.setResultWaitTime(10000);
		mockOutput.assertIsSatisfied();
	}
}
