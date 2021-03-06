
package com.chenzhou.crm.service.impl;

import java.util.List;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;
import com.chenzhou.crm.service.Customer;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "CustomerService", targetNamespace = "http://service.crm.chenzhou.com/")
@XmlSeeAlso({
})
public interface CustomerService {


    /**
     * 
     * @return
     *     returns java.util.List<com.chenzhou.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaIdIsNull", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByFixedAreaIdIsNull")
    @ResponseWrapper(localName = "findByFixedAreaIdIsNullResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByFixedAreaIdIsNullResponse")
    public List<Customer> findByFixedAreaIdIsNull();

    /**
     * 
     * @param arg0
     * @return
     *     returns com.chenzhou.crm.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByTelephone", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByTelephone")
    @ResponseWrapper(localName = "findByTelephoneResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByTelephoneResponse")
    public Customer findByTelephone(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "activeCustomer", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.ActiveCustomer")
    @ResponseWrapper(localName = "activeCustomerResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.ActiveCustomerResponse")
    public void activeCustomer(
        @WebParam(name = "arg0", targetNamespace = "")
        Integer arg0);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.util.List<com.chenzhou.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByFixedAreaId", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByFixedAreaId")
    @ResponseWrapper(localName = "findByFixedAreaIdResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByFixedAreaIdResponse")
    public List<Customer> findByFixedAreaId(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     * @return
     *     returns com.chenzhou.crm.service.Customer
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findByTelephoneAndPassword", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByTelephoneAndPassword")
    @ResponseWrapper(localName = "findByTelephoneAndPasswordResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindByTelephoneAndPasswordResponse")
    public Customer findByTelephoneAndPassword(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

    /**
     * 
     * @param arg0
     * @return
     *     returns java.lang.String
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findFixedAreaIdByAddress", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindFixedAreaIdByAddress")
    @ResponseWrapper(localName = "findFixedAreaIdByAddressResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindFixedAreaIdByAddressResponse")
    public String findFixedAreaIdByAddress(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0);

    /**
     * 
     * @return
     *     returns java.util.List<com.chenzhou.crm.service.Customer>
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "findAll", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindAll")
    @ResponseWrapper(localName = "findAllResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.FindAllResponse")
    public List<Customer> findAll();

    /**
     * 
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "regist", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.Regist")
    @ResponseWrapper(localName = "registResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.RegistResponse")
    public void regist(
        @WebParam(name = "arg0", targetNamespace = "")
        Customer arg0);

    /**
     * 
     * @param arg1
     * @param arg0
     */
    @WebMethod
    @RequestWrapper(localName = "assignCustomers2FixedArea", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.AssignCustomers2FixedArea")
    @ResponseWrapper(localName = "assignCustomers2FixedAreaResponse", targetNamespace = "http://service.crm.chenzhou.com/", className = "com.chenzhou.crm.service.AssignCustomers2FixedAreaResponse")
    public void assignCustomers2FixedArea(
        @WebParam(name = "arg0", targetNamespace = "")
        List<Integer> arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1);

}
