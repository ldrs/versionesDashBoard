
package com.ilient.api;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for apiCatalogItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiCatalogItem">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="CIId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="catalogNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="manufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="model" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiCatalogItem", propOrder = {
    "ciId",
    "catalogNumber",
    "custDate1",
    "custDate2",
    "manufacturer",
    "model",
    "name",
    "notes",
    "supplierID",
    "version"
})
public class ApiCatalogItem
    extends ApiSysaidObject
{

    @XmlElement(name = "CIId")
    protected int ciId;
    protected String catalogNumber;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDate1;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDate2;
    protected String manufacturer;
    protected String model;
    protected String name;
    protected String notes;
    protected int supplierID;
    protected int version;

    /**
     * Gets the value of the ciId property.
     * 
     */
    public int getCIId() {
        return ciId;
    }

    /**
     * Sets the value of the ciId property.
     * 
     */
    public void setCIId(int value) {
        this.ciId = value;
    }

    /**
     * Gets the value of the catalogNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCatalogNumber() {
        return catalogNumber;
    }

    /**
     * Sets the value of the catalogNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCatalogNumber(String value) {
        this.catalogNumber = value;
    }

    /**
     * Gets the value of the custDate1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDate1() {
        return custDate1;
    }

    /**
     * Sets the value of the custDate1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDate1(XMLGregorianCalendar value) {
        this.custDate1 = value;
    }

    /**
     * Gets the value of the custDate2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDate2() {
        return custDate2;
    }

    /**
     * Sets the value of the custDate2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDate2(XMLGregorianCalendar value) {
        this.custDate2 = value;
    }

    /**
     * Gets the value of the manufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getManufacturer() {
        return manufacturer;
    }

    /**
     * Sets the value of the manufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setManufacturer(String value) {
        this.manufacturer = value;
    }

    /**
     * Gets the value of the model property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getModel() {
        return model;
    }

    /**
     * Sets the value of the model property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setModel(String value) {
        this.model = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the supplierID property.
     * 
     */
    public int getSupplierID() {
        return supplierID;
    }

    /**
     * Sets the value of the supplierID property.
     * 
     */
    public void setSupplierID(int value) {
        this.supplierID = value;
    }

    /**
     * Gets the value of the version property.
     * 
     */
    public int getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     */
    public void setVersion(int value) {
        this.version = value;
    }

}
