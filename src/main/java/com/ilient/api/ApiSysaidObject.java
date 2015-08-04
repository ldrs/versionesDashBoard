
package com.ilient.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for apiSysaidObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiSysaidObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiSysaidObject")
@XmlSeeAlso({
    ApiServiceRequestActivity.class,
    ApiCI.class,
    ApiProject.class,
    ApiServiceRequest.class,
    ApiCompany.class,
    ApiComputer.class,
    ApiCatalogItem.class,
    ApiComputerItemList.class,
    ApiSysAidUser.class,
    ApiSoftware.class,
    ApiTask.class,
    ApiSupplier.class
})
public abstract class ApiSysaidObject {


}
