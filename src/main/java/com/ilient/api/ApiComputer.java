
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
 * <p>Java class for apiComputer complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiComputer">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="agentVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="biosType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="building" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="catalogNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectionParams" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="collectionType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="companySerial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="computerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="computerName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpuCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cpuModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cpuSpeed" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="cpuVendor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cubic" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custDate1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDate2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custInt1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custInt2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custList1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custList2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custNotes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custText1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custText2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="customDateFields">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="customFields">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *                             &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="department" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceCurrentCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceHomeCarrier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceICC" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceIMEI" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="deviceOwnership" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="devicePhoneNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="devicePolicy" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="deviceStatus" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="disable" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="disksCount" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="disksSize" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayAdapter" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="displayMemory" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="displayResolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="externalSerial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="floor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="freeMemBanks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="groupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ipAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="lastMaintenanceDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="locationIdx" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="macAddress" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="maintenanceSupplierID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="memBanks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="memoryPhysical" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="monitor" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="monitorSerial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OSName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OSPlatform" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OSServicePack" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="OSVersion" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="occupiedMemBanks" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="packetsIn" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="packetsOut" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="parentAsset" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="purchaseDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="purchasePrice" type="{http://www.w3.org/2001/XMLSchema}float"/>
 *         &lt;element name="serial" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="snmpCustText9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplierID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="systemManufacturer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="systemModel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="userName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="warrantyExpirationDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiComputer", propOrder = {
    "agentVersion",
    "biosType",
    "building",
    "ciId",
    "catalogNumber",
    "collectionParams",
    "collectionType",
    "company",
    "companySerial",
    "computerID",
    "computerName",
    "cpuCount",
    "cpuModel",
    "cpuSpeed",
    "cpuVendor",
    "cubic",
    "custDate1",
    "custDate2",
    "custInt1",
    "custInt2",
    "custList1",
    "custList2",
    "custNotes",
    "custText1",
    "custText2",
    "customDateFields",
    "customFields",
    "department",
    "description",
    "deviceCurrentCarrier",
    "deviceHomeCarrier",
    "deviceICC",
    "deviceIMEI",
    "deviceOwnership",
    "devicePhoneNumber",
    "devicePolicy",
    "deviceStatus",
    "disable",
    "disksCount",
    "disksSize",
    "displayAdapter",
    "displayMemory",
    "displayResolution",
    "externalSerial",
    "floor",
    "freeMemBanks",
    "groupName",
    "ipAddress",
    "lastMaintenanceDate",
    "locationIdx",
    "macAddress",
    "maintenanceSupplierID",
    "memBanks",
    "memoryPhysical",
    "monitor",
    "monitorSerial",
    "osName",
    "osPlatform",
    "osServicePack",
    "osVersion",
    "occupiedMemBanks",
    "packetsIn",
    "packetsOut",
    "parentAsset",
    "purchaseDate",
    "purchasePrice",
    "serial",
    "snmpCustText1",
    "snmpCustText10",
    "snmpCustText11",
    "snmpCustText12",
    "snmpCustText13",
    "snmpCustText14",
    "snmpCustText15",
    "snmpCustText16",
    "snmpCustText17",
    "snmpCustText18",
    "snmpCustText19",
    "snmpCustText2",
    "snmpCustText20",
    "snmpCustText3",
    "snmpCustText4",
    "snmpCustText5",
    "snmpCustText6",
    "snmpCustText7",
    "snmpCustText8",
    "snmpCustText9",
    "supplierID",
    "systemManufacturer",
    "systemModel",
    "updateTime",
    "userName",
    "version",
    "warrantyExpirationDate"
})
public class ApiComputer
    extends ApiSysaidObject
{

    protected String agentVersion;
    protected String biosType;
    protected String building;
    @XmlElement(name = "CIId")
    protected int ciId;
    protected String catalogNumber;
    protected String collectionParams;
    protected int collectionType;
    protected int company;
    protected String companySerial;
    protected String computerID;
    protected String computerName;
    protected int cpuCount;
    protected String cpuModel;
    protected int cpuSpeed;
    protected String cpuVendor;
    protected String cubic;
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
    @XmlElement(required = true)
    protected ApiComputer.CustomDateFields customDateFields;
    @XmlElement(required = true)
    protected ApiComputer.CustomFields customFields;
    protected int department;
    protected String description;
    protected String deviceCurrentCarrier;
    protected String deviceHomeCarrier;
    protected String deviceICC;
    protected String deviceIMEI;
    protected int deviceOwnership;
    protected String devicePhoneNumber;
    protected int devicePolicy;
    protected int deviceStatus;
    protected boolean disable;
    protected int disksCount;
    protected int disksSize;
    protected String displayAdapter;
    protected int displayMemory;
    protected String displayResolution;
    protected String externalSerial;
    protected String floor;
    protected int freeMemBanks;
    protected String groupName;
    protected String ipAddress;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastMaintenanceDate;
    protected int locationIdx;
    protected String macAddress;
    protected int maintenanceSupplierID;
    protected int memBanks;
    protected long memoryPhysical;
    protected String monitor;
    protected String monitorSerial;
    @XmlElement(name = "OSName")
    protected String osName;
    @XmlElement(name = "OSPlatform")
    protected String osPlatform;
    @XmlElement(name = "OSServicePack")
    protected String osServicePack;
    @XmlElement(name = "OSVersion")
    protected String osVersion;
    protected int occupiedMemBanks;
    protected float packetsIn;
    protected float packetsOut;
    protected String parentAsset;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar purchaseDate;
    protected float purchasePrice;
    protected String serial;
    protected String snmpCustText1;
    protected String snmpCustText10;
    protected String snmpCustText11;
    protected String snmpCustText12;
    protected String snmpCustText13;
    protected String snmpCustText14;
    protected String snmpCustText15;
    protected String snmpCustText16;
    protected String snmpCustText17;
    protected String snmpCustText18;
    protected String snmpCustText19;
    protected String snmpCustText2;
    protected String snmpCustText20;
    protected String snmpCustText3;
    protected String snmpCustText4;
    protected String snmpCustText5;
    protected String snmpCustText6;
    protected String snmpCustText7;
    protected String snmpCustText8;
    protected String snmpCustText9;
    protected int supplierID;
    protected String systemManufacturer;
    protected String systemModel;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    protected String userName;
    protected int version;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar warrantyExpirationDate;

    /**
     * Gets the value of the agentVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAgentVersion() {
        return agentVersion;
    }

    /**
     * Sets the value of the agentVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAgentVersion(String value) {
        this.agentVersion = value;
    }

    /**
     * Gets the value of the biosType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBiosType() {
        return biosType;
    }

    /**
     * Sets the value of the biosType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBiosType(String value) {
        this.biosType = value;
    }

    /**
     * Gets the value of the building property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuilding() {
        return building;
    }

    /**
     * Sets the value of the building property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuilding(String value) {
        this.building = value;
    }

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
     * Gets the value of the collectionParams property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCollectionParams() {
        return collectionParams;
    }

    /**
     * Sets the value of the collectionParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCollectionParams(String value) {
        this.collectionParams = value;
    }

    /**
     * Gets the value of the collectionType property.
     * 
     */
    public int getCollectionType() {
        return collectionType;
    }

    /**
     * Sets the value of the collectionType property.
     * 
     */
    public void setCollectionType(int value) {
        this.collectionType = value;
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
     * Gets the value of the companySerial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCompanySerial() {
        return companySerial;
    }

    /**
     * Sets the value of the companySerial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCompanySerial(String value) {
        this.companySerial = value;
    }

    /**
     * Gets the value of the computerID property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComputerID() {
        return computerID;
    }

    /**
     * Sets the value of the computerID property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComputerID(String value) {
        this.computerID = value;
    }

    /**
     * Gets the value of the computerName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComputerName() {
        return computerName;
    }

    /**
     * Sets the value of the computerName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComputerName(String value) {
        this.computerName = value;
    }

    /**
     * Gets the value of the cpuCount property.
     * 
     */
    public int getCpuCount() {
        return cpuCount;
    }

    /**
     * Sets the value of the cpuCount property.
     * 
     */
    public void setCpuCount(int value) {
        this.cpuCount = value;
    }

    /**
     * Gets the value of the cpuModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuModel() {
        return cpuModel;
    }

    /**
     * Sets the value of the cpuModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuModel(String value) {
        this.cpuModel = value;
    }

    /**
     * Gets the value of the cpuSpeed property.
     * 
     */
    public int getCpuSpeed() {
        return cpuSpeed;
    }

    /**
     * Sets the value of the cpuSpeed property.
     * 
     */
    public void setCpuSpeed(int value) {
        this.cpuSpeed = value;
    }

    /**
     * Gets the value of the cpuVendor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCpuVendor() {
        return cpuVendor;
    }

    /**
     * Sets the value of the cpuVendor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCpuVendor(String value) {
        this.cpuVendor = value;
    }

    /**
     * Gets the value of the cubic property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCubic() {
        return cubic;
    }

    /**
     * Sets the value of the cubic property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCubic(String value) {
        this.cubic = value;
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
     * Gets the value of the customDateFields property.
     * 
     * @return
     *     possible object is
     *     {@link ApiComputer.CustomDateFields }
     *     
     */
    public ApiComputer.CustomDateFields getCustomDateFields() {
        return customDateFields;
    }

    /**
     * Sets the value of the customDateFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiComputer.CustomDateFields }
     *     
     */
    public void setCustomDateFields(ApiComputer.CustomDateFields value) {
        this.customDateFields = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link ApiComputer.CustomFields }
     *     
     */
    public ApiComputer.CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiComputer.CustomFields }
     *     
     */
    public void setCustomFields(ApiComputer.CustomFields value) {
        this.customFields = value;
    }

    /**
     * Gets the value of the department property.
     * 
     */
    public int getDepartment() {
        return department;
    }

    /**
     * Sets the value of the department property.
     * 
     */
    public void setDepartment(int value) {
        this.department = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

    /**
     * Gets the value of the deviceCurrentCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceCurrentCarrier() {
        return deviceCurrentCarrier;
    }

    /**
     * Sets the value of the deviceCurrentCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceCurrentCarrier(String value) {
        this.deviceCurrentCarrier = value;
    }

    /**
     * Gets the value of the deviceHomeCarrier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceHomeCarrier() {
        return deviceHomeCarrier;
    }

    /**
     * Sets the value of the deviceHomeCarrier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceHomeCarrier(String value) {
        this.deviceHomeCarrier = value;
    }

    /**
     * Gets the value of the deviceICC property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceICC() {
        return deviceICC;
    }

    /**
     * Sets the value of the deviceICC property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceICC(String value) {
        this.deviceICC = value;
    }

    /**
     * Gets the value of the deviceIMEI property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDeviceIMEI() {
        return deviceIMEI;
    }

    /**
     * Sets the value of the deviceIMEI property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDeviceIMEI(String value) {
        this.deviceIMEI = value;
    }

    /**
     * Gets the value of the deviceOwnership property.
     * 
     */
    public int getDeviceOwnership() {
        return deviceOwnership;
    }

    /**
     * Sets the value of the deviceOwnership property.
     * 
     */
    public void setDeviceOwnership(int value) {
        this.deviceOwnership = value;
    }

    /**
     * Gets the value of the devicePhoneNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDevicePhoneNumber() {
        return devicePhoneNumber;
    }

    /**
     * Sets the value of the devicePhoneNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDevicePhoneNumber(String value) {
        this.devicePhoneNumber = value;
    }

    /**
     * Gets the value of the devicePolicy property.
     * 
     */
    public int getDevicePolicy() {
        return devicePolicy;
    }

    /**
     * Sets the value of the devicePolicy property.
     * 
     */
    public void setDevicePolicy(int value) {
        this.devicePolicy = value;
    }

    /**
     * Gets the value of the deviceStatus property.
     * 
     */
    public int getDeviceStatus() {
        return deviceStatus;
    }

    /**
     * Sets the value of the deviceStatus property.
     * 
     */
    public void setDeviceStatus(int value) {
        this.deviceStatus = value;
    }

    /**
     * Gets the value of the disable property.
     * 
     */
    public boolean isDisable() {
        return disable;
    }

    /**
     * Sets the value of the disable property.
     * 
     */
    public void setDisable(boolean value) {
        this.disable = value;
    }

    /**
     * Gets the value of the disksCount property.
     * 
     */
    public int getDisksCount() {
        return disksCount;
    }

    /**
     * Sets the value of the disksCount property.
     * 
     */
    public void setDisksCount(int value) {
        this.disksCount = value;
    }

    /**
     * Gets the value of the disksSize property.
     * 
     */
    public int getDisksSize() {
        return disksSize;
    }

    /**
     * Sets the value of the disksSize property.
     * 
     */
    public void setDisksSize(int value) {
        this.disksSize = value;
    }

    /**
     * Gets the value of the displayAdapter property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayAdapter() {
        return displayAdapter;
    }

    /**
     * Sets the value of the displayAdapter property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayAdapter(String value) {
        this.displayAdapter = value;
    }

    /**
     * Gets the value of the displayMemory property.
     * 
     */
    public int getDisplayMemory() {
        return displayMemory;
    }

    /**
     * Sets the value of the displayMemory property.
     * 
     */
    public void setDisplayMemory(int value) {
        this.displayMemory = value;
    }

    /**
     * Gets the value of the displayResolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDisplayResolution() {
        return displayResolution;
    }

    /**
     * Sets the value of the displayResolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDisplayResolution(String value) {
        this.displayResolution = value;
    }

    /**
     * Gets the value of the externalSerial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExternalSerial() {
        return externalSerial;
    }

    /**
     * Sets the value of the externalSerial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExternalSerial(String value) {
        this.externalSerial = value;
    }

    /**
     * Gets the value of the floor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFloor() {
        return floor;
    }

    /**
     * Sets the value of the floor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFloor(String value) {
        this.floor = value;
    }

    /**
     * Gets the value of the freeMemBanks property.
     * 
     */
    public int getFreeMemBanks() {
        return freeMemBanks;
    }

    /**
     * Sets the value of the freeMemBanks property.
     * 
     */
    public void setFreeMemBanks(int value) {
        this.freeMemBanks = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }

    /**
     * Gets the value of the ipAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIpAddress() {
        return ipAddress;
    }

    /**
     * Sets the value of the ipAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIpAddress(String value) {
        this.ipAddress = value;
    }

    /**
     * Gets the value of the lastMaintenanceDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastMaintenanceDate() {
        return lastMaintenanceDate;
    }

    /**
     * Sets the value of the lastMaintenanceDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastMaintenanceDate(XMLGregorianCalendar value) {
        this.lastMaintenanceDate = value;
    }

    /**
     * Gets the value of the locationIdx property.
     * 
     */
    public int getLocationIdx() {
        return locationIdx;
    }

    /**
     * Sets the value of the locationIdx property.
     * 
     */
    public void setLocationIdx(int value) {
        this.locationIdx = value;
    }

    /**
     * Gets the value of the macAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMacAddress() {
        return macAddress;
    }

    /**
     * Sets the value of the macAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMacAddress(String value) {
        this.macAddress = value;
    }

    /**
     * Gets the value of the maintenanceSupplierID property.
     * 
     */
    public int getMaintenanceSupplierID() {
        return maintenanceSupplierID;
    }

    /**
     * Sets the value of the maintenanceSupplierID property.
     * 
     */
    public void setMaintenanceSupplierID(int value) {
        this.maintenanceSupplierID = value;
    }

    /**
     * Gets the value of the memBanks property.
     * 
     */
    public int getMemBanks() {
        return memBanks;
    }

    /**
     * Sets the value of the memBanks property.
     * 
     */
    public void setMemBanks(int value) {
        this.memBanks = value;
    }

    /**
     * Gets the value of the memoryPhysical property.
     * 
     */
    public long getMemoryPhysical() {
        return memoryPhysical;
    }

    /**
     * Sets the value of the memoryPhysical property.
     * 
     */
    public void setMemoryPhysical(long value) {
        this.memoryPhysical = value;
    }

    /**
     * Gets the value of the monitor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonitor() {
        return monitor;
    }

    /**
     * Sets the value of the monitor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonitor(String value) {
        this.monitor = value;
    }

    /**
     * Gets the value of the monitorSerial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMonitorSerial() {
        return monitorSerial;
    }

    /**
     * Sets the value of the monitorSerial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMonitorSerial(String value) {
        this.monitorSerial = value;
    }

    /**
     * Gets the value of the osName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSName() {
        return osName;
    }

    /**
     * Sets the value of the osName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSName(String value) {
        this.osName = value;
    }

    /**
     * Gets the value of the osPlatform property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSPlatform() {
        return osPlatform;
    }

    /**
     * Sets the value of the osPlatform property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSPlatform(String value) {
        this.osPlatform = value;
    }

    /**
     * Gets the value of the osServicePack property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSServicePack() {
        return osServicePack;
    }

    /**
     * Sets the value of the osServicePack property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSServicePack(String value) {
        this.osServicePack = value;
    }

    /**
     * Gets the value of the osVersion property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOSVersion() {
        return osVersion;
    }

    /**
     * Sets the value of the osVersion property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOSVersion(String value) {
        this.osVersion = value;
    }

    /**
     * Gets the value of the occupiedMemBanks property.
     * 
     */
    public int getOccupiedMemBanks() {
        return occupiedMemBanks;
    }

    /**
     * Sets the value of the occupiedMemBanks property.
     * 
     */
    public void setOccupiedMemBanks(int value) {
        this.occupiedMemBanks = value;
    }

    /**
     * Gets the value of the packetsIn property.
     * 
     */
    public float getPacketsIn() {
        return packetsIn;
    }

    /**
     * Sets the value of the packetsIn property.
     * 
     */
    public void setPacketsIn(float value) {
        this.packetsIn = value;
    }

    /**
     * Gets the value of the packetsOut property.
     * 
     */
    public float getPacketsOut() {
        return packetsOut;
    }

    /**
     * Sets the value of the packetsOut property.
     * 
     */
    public void setPacketsOut(float value) {
        this.packetsOut = value;
    }

    /**
     * Gets the value of the parentAsset property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentAsset() {
        return parentAsset;
    }

    /**
     * Sets the value of the parentAsset property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentAsset(String value) {
        this.parentAsset = value;
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
     * Gets the value of the purchasePrice property.
     * 
     */
    public float getPurchasePrice() {
        return purchasePrice;
    }

    /**
     * Sets the value of the purchasePrice property.
     * 
     */
    public void setPurchasePrice(float value) {
        this.purchasePrice = value;
    }

    /**
     * Gets the value of the serial property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerial() {
        return serial;
    }

    /**
     * Sets the value of the serial property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerial(String value) {
        this.serial = value;
    }

    /**
     * Gets the value of the snmpCustText1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText1() {
        return snmpCustText1;
    }

    /**
     * Sets the value of the snmpCustText1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText1(String value) {
        this.snmpCustText1 = value;
    }

    /**
     * Gets the value of the snmpCustText10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText10() {
        return snmpCustText10;
    }

    /**
     * Sets the value of the snmpCustText10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText10(String value) {
        this.snmpCustText10 = value;
    }

    /**
     * Gets the value of the snmpCustText11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText11() {
        return snmpCustText11;
    }

    /**
     * Sets the value of the snmpCustText11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText11(String value) {
        this.snmpCustText11 = value;
    }

    /**
     * Gets the value of the snmpCustText12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText12() {
        return snmpCustText12;
    }

    /**
     * Sets the value of the snmpCustText12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText12(String value) {
        this.snmpCustText12 = value;
    }

    /**
     * Gets the value of the snmpCustText13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText13() {
        return snmpCustText13;
    }

    /**
     * Sets the value of the snmpCustText13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText13(String value) {
        this.snmpCustText13 = value;
    }

    /**
     * Gets the value of the snmpCustText14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText14() {
        return snmpCustText14;
    }

    /**
     * Sets the value of the snmpCustText14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText14(String value) {
        this.snmpCustText14 = value;
    }

    /**
     * Gets the value of the snmpCustText15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText15() {
        return snmpCustText15;
    }

    /**
     * Sets the value of the snmpCustText15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText15(String value) {
        this.snmpCustText15 = value;
    }

    /**
     * Gets the value of the snmpCustText16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText16() {
        return snmpCustText16;
    }

    /**
     * Sets the value of the snmpCustText16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText16(String value) {
        this.snmpCustText16 = value;
    }

    /**
     * Gets the value of the snmpCustText17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText17() {
        return snmpCustText17;
    }

    /**
     * Sets the value of the snmpCustText17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText17(String value) {
        this.snmpCustText17 = value;
    }

    /**
     * Gets the value of the snmpCustText18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText18() {
        return snmpCustText18;
    }

    /**
     * Sets the value of the snmpCustText18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText18(String value) {
        this.snmpCustText18 = value;
    }

    /**
     * Gets the value of the snmpCustText19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText19() {
        return snmpCustText19;
    }

    /**
     * Sets the value of the snmpCustText19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText19(String value) {
        this.snmpCustText19 = value;
    }

    /**
     * Gets the value of the snmpCustText2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText2() {
        return snmpCustText2;
    }

    /**
     * Sets the value of the snmpCustText2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText2(String value) {
        this.snmpCustText2 = value;
    }

    /**
     * Gets the value of the snmpCustText20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText20() {
        return snmpCustText20;
    }

    /**
     * Sets the value of the snmpCustText20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText20(String value) {
        this.snmpCustText20 = value;
    }

    /**
     * Gets the value of the snmpCustText3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText3() {
        return snmpCustText3;
    }

    /**
     * Sets the value of the snmpCustText3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText3(String value) {
        this.snmpCustText3 = value;
    }

    /**
     * Gets the value of the snmpCustText4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText4() {
        return snmpCustText4;
    }

    /**
     * Sets the value of the snmpCustText4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText4(String value) {
        this.snmpCustText4 = value;
    }

    /**
     * Gets the value of the snmpCustText5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText5() {
        return snmpCustText5;
    }

    /**
     * Sets the value of the snmpCustText5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText5(String value) {
        this.snmpCustText5 = value;
    }

    /**
     * Gets the value of the snmpCustText6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText6() {
        return snmpCustText6;
    }

    /**
     * Sets the value of the snmpCustText6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText6(String value) {
        this.snmpCustText6 = value;
    }

    /**
     * Gets the value of the snmpCustText7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText7() {
        return snmpCustText7;
    }

    /**
     * Sets the value of the snmpCustText7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText7(String value) {
        this.snmpCustText7 = value;
    }

    /**
     * Gets the value of the snmpCustText8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText8() {
        return snmpCustText8;
    }

    /**
     * Sets the value of the snmpCustText8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText8(String value) {
        this.snmpCustText8 = value;
    }

    /**
     * Gets the value of the snmpCustText9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSnmpCustText9() {
        return snmpCustText9;
    }

    /**
     * Sets the value of the snmpCustText9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSnmpCustText9(String value) {
        this.snmpCustText9 = value;
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
     * Gets the value of the systemManufacturer property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemManufacturer() {
        return systemManufacturer;
    }

    /**
     * Sets the value of the systemManufacturer property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemManufacturer(String value) {
        this.systemManufacturer = value;
    }

    /**
     * Gets the value of the systemModel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSystemModel() {
        return systemModel;
    }

    /**
     * Sets the value of the systemModel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSystemModel(String value) {
        this.systemModel = value;
    }

    /**
     * Gets the value of the updateTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUpdateTime() {
        return updateTime;
    }

    /**
     * Sets the value of the updateTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUpdateTime(XMLGregorianCalendar value) {
        this.updateTime = value;
    }

    /**
     * Gets the value of the userName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Sets the value of the userName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
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

    /**
     * Gets the value of the warrantyExpirationDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getWarrantyExpirationDate() {
        return warrantyExpirationDate;
    }

    /**
     * Sets the value of the warrantyExpirationDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setWarrantyExpirationDate(XMLGregorianCalendar value) {
        this.warrantyExpirationDate = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class CustomDateFields {

        protected List<ApiComputer.CustomDateFields.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApiComputer.CustomDateFields.Entry }
         * 
         * 
         */
        public List<ApiComputer.CustomDateFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiComputer.CustomDateFields.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected String key;
            @XmlSchemaType(name = "dateTime")
            protected XMLGregorianCalendar value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setKey(String value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public XMLGregorianCalendar getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link XMLGregorianCalendar }
             *     
             */
            public void setValue(XMLGregorianCalendar value) {
                this.value = value;
            }

        }

    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="entry" maxOccurs="unbounded" minOccurs="0">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
     *                   &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "entry"
    })
    public static class CustomFields {

        protected List<ApiComputer.CustomFields.Entry> entry;

        /**
         * Gets the value of the entry property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the entry property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getEntry().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ApiComputer.CustomFields.Entry }
         * 
         * 
         */
        public List<ApiComputer.CustomFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiComputer.CustomFields.Entry>();
            }
            return this.entry;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
         *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}anyType" minOccurs="0"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "key",
            "value"
        })
        public static class Entry {

            protected Object key;
            protected Object value;

            /**
             * Gets the value of the key property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getKey() {
                return key;
            }

            /**
             * Sets the value of the key property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setKey(Object value) {
                this.key = value;
            }

            /**
             * Gets the value of the value property.
             * 
             * @return
             *     possible object is
             *     {@link Object }
             *     
             */
            public Object getValue() {
                return value;
            }

            /**
             * Sets the value of the value property.
             * 
             * @param value
             *     allowed object is
             *     {@link Object }
             *     
             */
            public void setValue(Object value) {
                this.value = value;
            }

        }

    }

}
