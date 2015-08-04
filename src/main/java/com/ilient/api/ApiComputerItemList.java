
package com.ilient.api;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for apiComputerItemList complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiComputerItemList">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="accountId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="computerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="computerItems" type="{http://api.ilient.com/}apiComputerItem" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiComputerItemList", propOrder = {
    "accountId",
    "computerId",
    "computerItems"
})
public class ApiComputerItemList
    extends ApiSysaidObject
{

    protected String accountId;
    protected String computerId;
    @XmlElement(nillable = true)
    protected List<ApiComputerItem> computerItems;

    /**
     * Gets the value of the accountId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAccountId() {
        return accountId;
    }

    /**
     * Sets the value of the accountId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAccountId(String value) {
        this.accountId = value;
    }

    /**
     * Gets the value of the computerId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComputerId() {
        return computerId;
    }

    /**
     * Sets the value of the computerId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComputerId(String value) {
        this.computerId = value;
    }

    /**
     * Gets the value of the computerItems property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the computerItems property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComputerItems().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ApiComputerItem }
     * 
     * 
     */
    public List<ApiComputerItem> getComputerItems() {
        if (computerItems == null) {
            computerItems = new ArrayList<ApiComputerItem>();
        }
        return this.computerItems;
    }

}
