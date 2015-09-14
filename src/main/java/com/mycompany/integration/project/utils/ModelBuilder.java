package com.mycompany.integration.project.utils;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.StringWriter;

import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.integration.project.processors.CustomersOrdersModelProcessor;

public class ModelBuilder {

	private static final Logger logger = LoggerFactory
			.getLogger(ModelBuilder.class);

	public static Object fastDeserialize(String xmlContent, Class cls)
			throws Exception {
		ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream(
				xmlContent.getBytes());
		JAXBContext jc = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = jc.createUnmarshaller();

		XMLInputFactory xmlif = XMLInputFactory.newInstance();
		XMLStreamReader xmler = xmlif.createXMLStreamReader(xmlContentBytes);
		// XMLEventReader xmler = xmlif.createXMLEventReader(xmlContentBytes);

		Object obj = cls.cast(unmarshaller.unmarshal(xmler));

		return obj;
	}

	public static Object deserialize(String xmlContent, Class cls)
			throws JAXBException {
		ByteArrayInputStream xmlContentBytes = new ByteArrayInputStream(
				xmlContent.getBytes());
		JAXBContext context = JAXBContext.newInstance(cls);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		// note: setting schema to null will turn validator off
		unmarshaller.setSchema(null);

		Object obj = cls.cast(unmarshaller.unmarshal(xmlContentBytes));

		return obj;
	}

	public static String serialize(Object obj) throws Exception {

		return serialize(obj, null, false);
	}

	public static String serialize(Object obj, File schemaFile,
			Boolean prettyPrint) throws Exception {
		JAXBContext context = JAXBContext.newInstance(obj.getClass());
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

		// TODO: !!!!
		if (schemaFile != null) {
			SchemaFactory sf = SchemaFactory
					.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = sf.newSchema(schemaFile);
			marshaller.setSchema(schema);
		}
		
		StringWriter sw = new StringWriter();
		marshaller.marshal(obj, sw);
		return sw.toString();
		
	}

	/**
	 * Marshall input object to a formatted XML String
	 */
	protected <T> String marshal(T input) throws JAXBException {
		StringWriter writer = new StringWriter();

		JAXBContext jc = JAXBContext.newInstance(input.getClass());
		Marshaller marshaller = jc.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
		marshaller.marshal(input, writer);
		return writer.toString();
	}
}
