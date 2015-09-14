package com.mycompany.integration.project.tests.services;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.mycompany.integration.project.services.XmlProcessingService;
import com.mycompany.integration.project.utils.FileUtils;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:META-INF/spring/domain.xml"})
@DirtiesContext(classMode = ClassMode.AFTER_EACH_TEST_METHOD)
public class XmlProcessingServiceTests {

	String xmlFilePath = "src/exemplar/CustomersOrders-v.1.0.0.xml";
	
	@Autowired
    private XmlProcessingService xmlProcessingService;
	
	@Test
	public void test_service_gets_injected() throws Exception {
		assert(xmlProcessingService != null);
	}

	@Test
	public void test_doing_something() throws Exception {
		// Arrange
		String xml = FileUtils.getFileString(xmlFilePath);
		
		// Act
		Boolean result = xmlProcessingService.processTransaction(xml);
		
		// Assert
		assert(result);
	}
}
