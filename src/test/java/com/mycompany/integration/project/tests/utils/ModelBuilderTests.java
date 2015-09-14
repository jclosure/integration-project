package com.mycompany.integration.project.tests.utils;

import org.junit.Test;

import com.mycompany.integration.project.models.*;
import com.mycompany.integration.project.utils.*;

public class ModelBuilderTests {

	String xmlFilePath = "src/exemplar/CustomersOrders-v.1.0.0.xml";

	@Test
	public void test_can_fast_deserialize() throws Exception {
		// arrange
		String xmlString = FileUtils.getFileString(xmlFilePath);

		// act
		CustomersOrders customersOrders = CustomersOrders.class
				.cast(ModelBuilder.fastDeserialize(xmlString,
						CustomersOrders.class));

		// assert
		assert (customersOrders != null);
	}

	@Test
	public void test_can_deserialize() throws Exception {
		// arrange
		String axmlString = FileUtils.getFileString(xmlFilePath);

		// act
		CustomersOrders customersOrders = CustomersOrders.class
				.cast(ModelBuilder.deserialize(axmlString,
						CustomersOrders.class));

		// assert
		assert (customersOrders != null);
	}
}
