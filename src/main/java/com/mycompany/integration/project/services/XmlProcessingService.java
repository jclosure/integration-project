package com.mycompany.integration.project.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class XmlProcessingService {

	private static final Logger logger = LoggerFactory.getLogger(XmlProcessingService.class);
	
	public Boolean processTransaction(String xmlContent){
		logger.info("done!");
		return true;
	}
}
