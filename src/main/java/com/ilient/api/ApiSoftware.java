
package com.ilient.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for apiSoftware complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiSoftware">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="CIId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custInt1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custInt2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custList1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custList2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custText1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custText2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeware" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="historyVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="inst_names" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="licenses" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="productName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="softwareID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="supplierID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="supportExpiration" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="vendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiSoftware", propOrder = {
    "ciId",
    "company",
    "custDate1",
    "custDate2",
    "custInt1",
    "custInt2",
    "custList1",
    "custList2",
    "custNotes",
    "custText1",
    "custText2",
    "freeware",
    "historyVersion",
    "instNames",
    "licenses",
    "notes",
    "productName",
    "purchaseDate",
    "softwareID",
    "supplierID",
    "supportExpiration",
    "vendor",
    "version"
})
public class ApiSoftware
    extends ApiSysaidObject
{

    @XmlElement(name = "CIId")
    protected int ciId;
    protected int company;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDate1;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDate2;
    protected int custInt1;
    protected int custInt2;
    protected int custList1;
    protected int custList2;
    protected String custNotes;
    protected String custText1;
    protected String custText2;
    protected boolean freeware;
    protected int historyVersion;
    @XmlElement(name = "inst_names", nillable = true)
    protected List<Object> instNames;
    protected int licenses;
    protected String notes;
    protected String productName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar purchaseDate;
    protected int softwareID;
    protected int supplierID;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar supportExpiration;
    protected String vendor;
    protected String version;

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
     * Gets the value of the company property.
     * 
     */
    public int getCompany() {
        return company;
    }

    /**
     * Sets the value of the company property.
     * 
     */
    public void setCompany(int value) {
        this.company = value;
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
     * Gets the value of the custInt1 property.
     * 
     */
    public int getCustInt1() {
        return custInt1;
    }

    /**
     * Sets the value of the custInt1 property.
     * 
     */
    public void setCustInt1(int value) {
        this.custInt1 = value;
    }

    /**
     * Gets the value of the custInt2 property.
     * 
     */
    public int getCustInt2() {
        return custInt2;
    }

    /**
     * Sets the value of the custInt2 property.
     * 
     */
    public void setCustInt2(int value) {
        this.custInt2 = value;
    }

    /**
     * Gets the value of the custList1 property.
     * 
     */
    public int getCustList1() {
        return custList1;
    }

    /**
     * Sets the value of the custList1 property.
     * 
     */
    public void setCustList1(int value) {
        this.custList1 = value;
    }

    /**
     * Gets the value of the custList2 property.
     * 
     */
    public int getCustList2() {
        return custList2;
    }

    /**
     * Sets the value of the custList2 property.
     * 
     */
    public void setCustList2(int value) {
        this.custList2 = value;
    }

    /**
     * Gets the value of the custNotes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustNotes() {
        return custNotes;
    }

    /**
     * Sets the value of the custNotes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustNotes(String value) {
        this.custNotes = value;
    }

    /**
     * Gets the value of the custText1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustText1() {
        return custText1;
    }

    /**
     * Sets the value of the custText1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustText1(String value) {
        this.custText1 = value;
    }

    /**
     * Gets the value of the custText2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustText2() {
        return custText2;
    }

    /**
     * Sets the value of the custText2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustText2(String value) {
        this.custText2 = value;
    }

    /**
     * Gets the value of the freeware property.
     * 
     */
    public boolean isFreeware() {
        return freeware;
    }

    /**
     * Sets the value of the freeware property.
     * 
     */
    public void setFreeware(boolean value) {
        this.freeware = value;
    }

    /**
     * Gets the value of the historyVersion property.
     * 
     */
    public int getHistoryVersion() {
        return historyVersion;
    }

    /**
     * Sets the value of the historyVersion property.
     * 
     */
    public void setHistoryVersion(int value) {
        this.historyVersion = value;
    }

    /**
     * Gets the value of the instNames property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the instNames property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getInstNames().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getInstNames() {
        if (instNames == null) {
            instNames = new ArrayList<Object>();
        }
        return this.instNames;
    }

    /**
     * Gets the value of the licenses property.
     * 
     */
    public int getLicenses() {
        return licenses;
    }

    /**
     * Sets the value of the licenses property.
     * 
     */
    public void setLicenses(int value) {
        this.licenses = value;
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
     * Gets the value of the productName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Sets the value of the productName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProductName(String value) {
        this.productName = value;
    }

    /**
     * Gets the value of the purchaseDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPurchaseDate() {
        return purchaseDate;
    }

    /**
     * Sets the value of the purchaseDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPurchaseDate(XMLGregorianCalendar value) {
        this.purchaseDate = value;
    }

    /**
     * Gets the value of the softwareID property.
     * 
     */
    public int getSoftwareID() {
        return softwareID;
    }

    /**
     * Sets the value of the softwareID property.
     * 
     */
    public void setSoftwareID(int value) {
        this.softwareID = value;
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
     * Gets the value of the supportExpiration property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSupportExpiration() {
        return supportExpiration;
    }

    /**
     * Sets the value of the supportExpiration property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSupportExpiration(XMLGregorianCalendar value) {
        this.supportExpiration = value;
    }

    /**
     * Gets the value of the vendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVendor() {
        return vendor;
    }

    /**
     * Sets the value of the vendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVendor(String value) {
        this.vendor = value;
    }

    /**
     * Gets the value of the version property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVersion() {
        return version;
    }

    /**
     * Sets the value of the version property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVersion(String value) {
        this.version = value;
    }

}
