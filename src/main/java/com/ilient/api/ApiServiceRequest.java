
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
 * <p>Java class for apiServiceRequest complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiServiceRequest">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="agreement" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="archive" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *         &lt;element name="assignCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="assignedGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="assignedTo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="CIId" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="cc" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="changeCategory" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="closeTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="closureInformation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="computerID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="currentSupportLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
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
 *         &lt;element name="description" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="dueDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="emailAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="escalation" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="followupActualDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="followupPlannedDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="followupText" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="followupUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="impact" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="insertTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="maxSupportLevel" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parentLink" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="projectID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="reopenCounter" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="requestUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="resolution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="responsibleManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="solution" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="source" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="srSubType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="srType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="submitUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="successRating" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="taskID" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="thirdLevelCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="title" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="updateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="updateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="urgency" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="userManager" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="version" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="workaround" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiServiceRequest", propOrder = {
    "agreement",
    "archive",
    "assignCounter",
    "assignedGroup",
    "assignedTo",
    "ciId",
    "category",
    "cc",
    "changeCategory",
    "closeTime",
    "closureInformation",
    "computerID",
    "currentSupportLevel",
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
    "description",
    "dueDate",
    "emailAccount",
    "escalation",
    "followupActualDate",
    "followupPlannedDate",
    "followupText",
    "followupUser",
    "id",
    "impact",
    "insertTime",
    "location",
    "maxSupportLevel",
    "notes",
    "parentLink",
    "priority",
    "projectID",
    "reopenCounter",
    "requestUser",
    "resolution",
    "responsibleManager",
    "solution",
    "source",
    "srSubType",
    "srType",
    "status",
    "subCategory",
    "submitUser",
    "successRating",
    "taskID",
    "thirdLevelCategory",
    "title",
    "updateTime",
    "updateUser",
    "urgency",
    "userManager",
    "version",
    "workaround"
})
public class ApiServiceRequest
    extends ApiSysaidObject
{

    protected int agreement;
    protected boolean archive;
    protected int assignCounter;
    protected String assignedGroup;
    protected String assignedTo;
    @XmlElement(name = "CIId")
    protected int ciId;
    protected String category;
    protected String cc;
    protected int changeCategory;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar closeTime;
    protected int closureInformation;
    protected String computerID;
    protected int currentSupportLevel;
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
    protected ApiServiceRequest.CustomDateFields customDateFields;
    @XmlElement(required = true)
    protected ApiServiceRequest.CustomFields customFields;
    protected String description;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar dueDate;
    protected String emailAccount;
    protected int escalation;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar followupActualDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar followupPlannedDate;
    protected String followupText;
    protected String followupUser;
    protected int id;
    protected int impact;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar insertTime;
    protected int location;
    protected int maxSupportLevel;
    protected String notes;
    protected int parentLink;
    protected int priority;
    protected int projectID;
    protected int reopenCounter;
    protected String requestUser;
    protected String resolution;
    protected String responsibleManager;
    protected String solution;
    protected int source;
    protected int srSubType;
    protected int srType;
    protected int status;
    protected String subCategory;
    protected String submitUser;
    protected int successRating;
    protected int taskID;
    protected String thirdLevelCategory;
    protected String title;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar updateTime;
    protected String updateUser;
    protected int urgency;
    protected String userManager;
    protected int version;
    protected String workaround;

    /**
     * Gets the value of the agreement property.
     * 
     */
    public int getAgreement() {
        return agreement;
    }

    /**
     * Sets the value of the agreement property.
     * 
     */
    public void setAgreement(int value) {
        this.agreement = value;
    }

    /**
     * Gets the value of the archive property.
     * 
     */
    public boolean isArchive() {
        return archive;
    }

    /**
     * Sets the value of the archive property.
     * 
     */
    public void setArchive(boolean value) {
        this.archive = value;
    }

    /**
     * Gets the value of the assignCounter property.
     * 
     */
    public int getAssignCounter() {
        return assignCounter;
    }

    /**
     * Sets the value of the assignCounter property.
     * 
     */
    public void setAssignCounter(int value) {
        this.assignCounter = value;
    }

    /**
     * Gets the value of the assignedGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedGroup() {
        return assignedGroup;
    }

    /**
     * Sets the value of the assignedGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedGroup(String value) {
        this.assignedGroup = value;
    }

    /**
     * Gets the value of the assignedTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAssignedTo() {
        return assignedTo;
    }

    /**
     * Sets the value of the assignedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAssignedTo(String value) {
        this.assignedTo = value;
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
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * Gets the value of the cc property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCc() {
        return cc;
    }

    /**
     * Sets the value of the cc property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCc(String value) {
        this.cc = value;
    }

    /**
     * Gets the value of the changeCategory property.
     * 
     */
    public int getChangeCategory() {
        return changeCategory;
    }

    /**
     * Sets the value of the changeCategory property.
     * 
     */
    public void setChangeCategory(int value) {
        this.changeCategory = value;
    }

    /**
     * Gets the value of the closeTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCloseTime() {
        return closeTime;
    }

    /**
     * Sets the value of the closeTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCloseTime(XMLGregorianCalendar value) {
        this.closeTime = value;
    }

    /**
     * Gets the value of the closureInformation property.
     * 
     */
    public int getClosureInformation() {
        return closureInformation;
    }

    /**
     * Sets the value of the closureInformation property.
     * 
     */
    public void setClosureInformation(int value) {
        this.closureInformation = value;
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
     * Gets the value of the currentSupportLevel property.
     * 
     */
    public int getCurrentSupportLevel() {
        return currentSupportLevel;
    }

    /**
     * Sets the value of the currentSupportLevel property.
     * 
     */
    public void setCurrentSupportLevel(int value) {
        this.currentSupportLevel = value;
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
     *     {@link ApiServiceRequest.CustomDateFields }
     *     
     */
    public ApiServiceRequest.CustomDateFields getCustomDateFields() {
        return customDateFields;
    }

    /**
     * Sets the value of the customDateFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiServiceRequest.CustomDateFields }
     *     
     */
    public void setCustomDateFields(ApiServiceRequest.CustomDateFields value) {
        this.customDateFields = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link ApiServiceRequest.CustomFields }
     *     
     */
    public ApiServiceRequest.CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiServiceRequest.CustomFields }
     *     
     */
    public void setCustomFields(ApiServiceRequest.CustomFields value) {
        this.customFields = value;
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
     * Gets the value of the dueDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getDueDate() {
        return dueDate;
    }

    /**
     * Sets the value of the dueDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setDueDate(XMLGregorianCalendar value) {
        this.dueDate = value;
    }

    /**
     * Gets the value of the emailAccount property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmailAccount() {
        return emailAccount;
    }

    /**
     * Sets the value of the emailAccount property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmailAccount(String value) {
        this.emailAccount = value;
    }

    /**
     * Gets the value of the escalation property.
     * 
     */
    public int getEscalation() {
        return escalation;
    }

    /**
     * Sets the value of the escalation property.
     * 
     */
    public void setEscalation(int value) {
        this.escalation = value;
    }

    /**
     * Gets the value of the followupActualDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFollowupActualDate() {
        return followupActualDate;
    }

    /**
     * Sets the value of the followupActualDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFollowupActualDate(XMLGregorianCalendar value) {
        this.followupActualDate = value;
    }

    /**
     * Gets the value of the followupPlannedDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getFollowupPlannedDate() {
        return followupPlannedDate;
    }

    /**
     * Sets the value of the followupPlannedDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setFollowupPlannedDate(XMLGregorianCalendar value) {
        this.followupPlannedDate = value;
    }

    /**
     * Gets the value of the followupText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFollowupText() {
        return followupText;
    }

    /**
     * Sets the value of the followupText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFollowupText(String value) {
        this.followupText = value;
    }

    /**
     * Gets the value of the followupUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFollowupUser() {
        return followupUser;
    }

    /**
     * Sets the value of the followupUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFollowupUser(String value) {
        this.followupUser = value;
    }

    /**
     * Gets the value of the id property.
     * 
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(int value) {
        this.id = value;
    }

    /**
     * Gets the value of the impact property.
     * 
     */
    public int getImpact() {
        return impact;
    }

    /**
     * Sets the value of the impact property.
     * 
     */
    public void setImpact(int value) {
        this.impact = value;
    }

    /**
     * Gets the value of the insertTime property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getInsertTime() {
        return insertTime;
    }

    /**
     * Sets the value of the insertTime property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setInsertTime(XMLGregorianCalendar value) {
        this.insertTime = value;
    }

    /**
     * Gets the value of the location property.
     * 
     */
    public int getLocation() {
        return location;
    }

    /**
     * Sets the value of the location property.
     * 
     */
    public void setLocation(int value) {
        this.location = value;
    }

    /**
     * Gets the value of the maxSupportLevel property.
     * 
     */
    public int getMaxSupportLevel() {
        return maxSupportLevel;
    }

    /**
     * Sets the value of the maxSupportLevel property.
     * 
     */
    public void setMaxSupportLevel(int value) {
        this.maxSupportLevel = value;
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
     * Gets the value of the parentLink property.
     * 
     */
    public int getParentLink() {
        return parentLink;
    }

    /**
     * Sets the value of the parentLink property.
     * 
     */
    public void setParentLink(int value) {
        this.parentLink = value;
    }

    /**
     * Gets the value of the priority property.
     * 
     */
    public int getPriority() {
        return priority;
    }

    /**
     * Sets the value of the priority property.
     * 
     */
    public void setPriority(int value) {
        this.priority = value;
    }

    /**
     * Gets the value of the projectID property.
     * 
     */
    public int getProjectID() {
        return projectID;
    }

    /**
     * Sets the value of the projectID property.
     * 
     */
    public void setProjectID(int value) {
        this.projectID = value;
    }

    /**
     * Gets the value of the reopenCounter property.
     * 
     */
    public int getReopenCounter() {
        return reopenCounter;
    }

    /**
     * Sets the value of the reopenCounter property.
     * 
     */
    public void setReopenCounter(int value) {
        this.reopenCounter = value;
    }

    /**
     * Gets the value of the requestUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestUser() {
        return requestUser;
    }

    /**
     * Sets the value of the requestUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestUser(String value) {
        this.requestUser = value;
    }

    /**
     * Gets the value of the resolution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResolution() {
        return resolution;
    }

    /**
     * Sets the value of the resolution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResolution(String value) {
        this.resolution = value;
    }

    /**
     * Gets the value of the responsibleManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getResponsibleManager() {
        return responsibleManager;
    }

    /**
     * Sets the value of the responsibleManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setResponsibleManager(String value) {
        this.responsibleManager = value;
    }

    /**
     * Gets the value of the solution property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSolution() {
        return solution;
    }

    /**
     * Sets the value of the solution property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSolution(String value) {
        this.solution = value;
    }

    /**
     * Gets the value of the source property.
     * 
     */
    public int getSource() {
        return source;
    }

    /**
     * Sets the value of the source property.
     * 
     */
    public void setSource(int value) {
        this.source = value;
    }

    /**
     * Gets the value of the srSubType property.
     * 
     */
    public int getSrSubType() {
        return srSubType;
    }

    /**
     * Sets the value of the srSubType property.
     * 
     */
    public void setSrSubType(int value) {
        this.srSubType = value;
    }

    /**
     * Gets the value of the srType property.
     * 
     */
    public int getSrType() {
        return srType;
    }

    /**
     * Sets the value of the srType property.
     * 
     */
    public void setSrType(int value) {
        this.srType = value;
    }

    /**
     * Gets the value of the status property.
     * 
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the value of the status property.
     * 
     */
    public void setStatus(int value) {
        this.status = value;
    }

    /**
     * Gets the value of the subCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubCategory() {
        return subCategory;
    }

    /**
     * Sets the value of the subCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubCategory(String value) {
        this.subCategory = value;
    }

    /**
     * Gets the value of the submitUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSubmitUser() {
        return submitUser;
    }

    /**
     * Sets the value of the submitUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSubmitUser(String value) {
        this.submitUser = value;
    }

    /**
     * Gets the value of the successRating property.
     * 
     */
    public int getSuccessRating() {
        return successRating;
    }

    /**
     * Sets the value of the successRating property.
     * 
     */
    public void setSuccessRating(int value) {
        this.successRating = value;
    }

    /**
     * Gets the value of the taskID property.
     * 
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Sets the value of the taskID property.
     * 
     */
    public void setTaskID(int value) {
        this.taskID = value;
    }

    /**
     * Gets the value of the thirdLevelCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getThirdLevelCategory() {
        return thirdLevelCategory;
    }

    /**
     * Sets the value of the thirdLevelCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setThirdLevelCategory(String value) {
        this.thirdLevelCategory = value;
    }

    /**
     * Gets the value of the title property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the value of the title property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTitle(String value) {
        this.title = value;
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
     * Gets the value of the updateUser property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUpdateUser() {
        return updateUser;
    }

    /**
     * Sets the value of the updateUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUpdateUser(String value) {
        this.updateUser = value;
    }

    /**
     * Gets the value of the urgency property.
     * 
     */
    public int getUrgency() {
        return urgency;
    }

    /**
     * Sets the value of the urgency property.
     * 
     */
    public void setUrgency(int value) {
        this.urgency = value;
    }

    /**
     * Gets the value of the userManager property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserManager() {
        return userManager;
    }

    /**
     * Sets the value of the userManager property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserManager(String value) {
        this.userManager = value;
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
     * Gets the value of the workaround property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkaround() {
        return workaround;
    }

    /**
     * Sets the value of the workaround property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkaround(String value) {
        this.workaround = value;
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

        protected List<ApiServiceRequest.CustomDateFields.Entry> entry;

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
         * {@link ApiServiceRequest.CustomDateFields.Entry }
         * 
         * 
         */
        public List<ApiServiceRequest.CustomDateFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiServiceRequest.CustomDateFields.Entry>();
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

        protected List<ApiServiceRequest.CustomFields.Entry> entry;

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
         * {@link ApiServiceRequest.CustomFields.Entry }
         * 
         * 
         */
        public List<ApiServiceRequest.CustomFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiServiceRequest.CustomFields.Entry>();
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
