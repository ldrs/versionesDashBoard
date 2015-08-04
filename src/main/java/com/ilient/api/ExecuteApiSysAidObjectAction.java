
package com.ilient.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for executeApiSysAidObjectAction complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="executeApiSysAidObjectAction">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="sessionId" type="{http://www.w3.org/2001/XMLSchema}long"/>
 *         &lt;element name="apiSysObj" type="{http://api.ilient.com/}apiSysaidObject" minOccurs="0"/>
 *         &lt;element name="actionName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="actionParams" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "executeApiSysAidObjectAction", propOrder = {
    "sessionId",
    "apiSysObj",
    "actionName",
    "actionParams"
})
public class ExecuteApiSysAidObjectAction {

    protected long sessionId;
    protected ApiSysaidObject apiSysObj;
    protected String actionName;
    @XmlElement(nillable = true)
    protected List<Object> actionParams;

    /**
     * Gets the value of the sessionId property.
     * 
     */
    public long getSessionId() {
        return sessionId;
    }

    /**
     * Sets the value of the sessionId property.
     * 
     */
    public void setSessionId(long value) {
        this.sessionId = value;
    }

    /**
     * Gets the value of the apiSysObj property.
     * 
     * @return
     *     possible object is
     *     {@link ApiSysaidObject }
     *     
     */
    public ApiSysaidObject getApiSysObj() {
        return apiSysObj;
    }

    /**
     * Sets the value of the apiSysObj property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiSysaidObject }
     *     
     */
    public void setApiSysObj(ApiSysaidObject value) {
        this.apiSysObj = value;
    }

    /**
     * Gets the value of the actionName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getActionName() {
        return actionName;
    }

    /**
     * Sets the value of the actionName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setActionName(String value) {
        this.actionName = value;
    }

    /**
     * Gets the value of the actionParams property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the actionParams property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getActionParams().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Object }
     * 
     * 
     */
    public List<Object> getActionParams() {
        if (actionParams == null) {
            actionParams = new ArrayList<Object>();
        }
        return this.actionParams;
    }

}
