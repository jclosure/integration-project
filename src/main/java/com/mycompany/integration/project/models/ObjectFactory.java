//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.09.12 at 08:45:40 PM CDT 
//


package com.mycompany.integration.project.models;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.mycompany.integration.project.models package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.mycompany.integration.project.models
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link CustomersOrders }
     * 
     */
    public CustomersOrders createCustomersOrders() {
        return new CustomersOrders();
    }

    /**
     * Create an instance of {@link CustomersOrders.Customers }
     * 
     */
    public CustomersOrders.Customers createCustomersOrdersCustomers() {
        return new CustomersOrders.Customers();
    }

    /**
     * Create an instance of {@link CustomersOrders.Orders }
     * 
     */
    public CustomersOrders.Orders createCustomersOrdersOrders() {
        return new CustomersOrders.Orders();
    }

    /**
     * Create an instance of {@link ShipInfoType }
     * 
     */
    public ShipInfoType createShipInfoType() {
        return new ShipInfoType();
    }

    /**
     * Create an instance of {@link OrderType }
     * 
     */
    public OrderType createOrderType() {
        return new OrderType();
    }

    /**
     * Create an instance of {@link AddressType }
     * 
     */
    public AddressType createAddressType() {
        return new AddressType();
    }

    /**
     * Create an instance of {@link CustomerType }
     * 
     */
    public CustomerType createCustomerType() {
        return new CustomerType();
    }

}
