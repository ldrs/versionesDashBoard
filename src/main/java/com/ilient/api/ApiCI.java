
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
 * <p>Java class for apiCI complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="apiCI">
 *   &lt;complexContent>
 *     &lt;extension base="{http://api.ilient.com/}apiSysaidObject">
 *       &lt;sequence>
 *         &lt;element name="acceptDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ciSubType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="ciType" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="company" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custDateField1" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField10" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField11" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField12" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField13" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField14" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField15" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField16" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField17" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField18" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField19" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField2" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField20" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField21" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField22" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField23" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField24" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField25" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField26" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField27" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField28" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField29" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField3" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField30" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField31" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField32" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField33" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField34" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField35" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField36" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField37" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField38" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField39" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField4" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField40" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField41" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField42" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField43" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField44" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField45" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField46" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField47" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField48" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField49" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField5" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField50" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField6" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField7" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField8" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custDateField9" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="custIntField1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField10" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField11" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField12" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField13" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField14" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField15" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField16" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField17" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField18" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField19" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField20" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField21" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField22" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField23" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField24" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField25" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField26" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField27" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField28" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField29" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField30" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField31" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField32" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField33" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField34" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField35" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField36" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField37" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField38" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField39" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField40" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField41" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField42" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField43" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField44" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField45" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField46" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField47" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField48" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField49" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField5" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField50" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField6" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField7" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField8" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custIntField9" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField1" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField10" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField11" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField12" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField13" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField14" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField15" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField16" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField17" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField18" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField19" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField2" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField20" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField21" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField22" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField23" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField24" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField25" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField26" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField27" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField28" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField29" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField3" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField30" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField31" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField32" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField33" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField34" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField35" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField36" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField37" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField38" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField39" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField4" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField40" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField41" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField42" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField43" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField44" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField45" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField46" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField47" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField48" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField49" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField5" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField50" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField6" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField7" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField8" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custListField9" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="custLongTextField1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField21" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField22" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField23" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField24" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField25" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField26" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField27" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField28" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField29" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField30" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField31" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField32" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField33" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField34" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField35" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField36" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField37" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField38" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField39" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField40" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField41" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField42" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField43" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField44" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField45" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField46" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField47" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField48" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField49" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField50" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custLongTextField9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField10" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField11" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField12" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField13" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField14" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField15" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField16" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField17" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField18" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField19" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField20" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField21" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField22" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField23" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField24" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField25" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField26" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField27" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField28" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField29" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField3" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField30" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField31" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField32" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField33" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField34" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField35" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField36" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField37" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField38" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField39" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField40" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField41" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField42" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField43" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField44" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField45" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField46" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField47" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField48" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField49" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField50" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField6" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField7" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField8" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="custTextField9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
 *         &lt;element name="historyVersion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="location" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="notes" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="owner" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ownerGroup" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="priority" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="status" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="subCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="supplier" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="supplyDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="thirdLevelCategory" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="users" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "apiCI", propOrder = {
    "acceptDate",
    "category",
    "ciName",
    "ciSubType",
    "ciType",
    "company",
    "custDateField1",
    "custDateField10",
    "custDateField11",
    "custDateField12",
    "custDateField13",
    "custDateField14",
    "custDateField15",
    "custDateField16",
    "custDateField17",
    "custDateField18",
    "custDateField19",
    "custDateField2",
    "custDateField20",
    "custDateField21",
    "custDateField22",
    "custDateField23",
    "custDateField24",
    "custDateField25",
    "custDateField26",
    "custDateField27",
    "custDateField28",
    "custDateField29",
    "custDateField3",
    "custDateField30",
    "custDateField31",
    "custDateField32",
    "custDateField33",
    "custDateField34",
    "custDateField35",
    "custDateField36",
    "custDateField37",
    "custDateField38",
    "custDateField39",
    "custDateField4",
    "custDateField40",
    "custDateField41",
    "custDateField42",
    "custDateField43",
    "custDateField44",
    "custDateField45",
    "custDateField46",
    "custDateField47",
    "custDateField48",
    "custDateField49",
    "custDateField5",
    "custDateField50",
    "custDateField6",
    "custDateField7",
    "custDateField8",
    "custDateField9",
    "custIntField1",
    "custIntField10",
    "custIntField11",
    "custIntField12",
    "custIntField13",
    "custIntField14",
    "custIntField15",
    "custIntField16",
    "custIntField17",
    "custIntField18",
    "custIntField19",
    "custIntField2",
    "custIntField20",
    "custIntField21",
    "custIntField22",
    "custIntField23",
    "custIntField24",
    "custIntField25",
    "custIntField26",
    "custIntField27",
    "custIntField28",
    "custIntField29",
    "custIntField3",
    "custIntField30",
    "custIntField31",
    "custIntField32",
    "custIntField33",
    "custIntField34",
    "custIntField35",
    "custIntField36",
    "custIntField37",
    "custIntField38",
    "custIntField39",
    "custIntField4",
    "custIntField40",
    "custIntField41",
    "custIntField42",
    "custIntField43",
    "custIntField44",
    "custIntField45",
    "custIntField46",
    "custIntField47",
    "custIntField48",
    "custIntField49",
    "custIntField5",
    "custIntField50",
    "custIntField6",
    "custIntField7",
    "custIntField8",
    "custIntField9",
    "custListField1",
    "custListField10",
    "custListField11",
    "custListField12",
    "custListField13",
    "custListField14",
    "custListField15",
    "custListField16",
    "custListField17",
    "custListField18",
    "custListField19",
    "custListField2",
    "custListField20",
    "custListField21",
    "custListField22",
    "custListField23",
    "custListField24",
    "custListField25",
    "custListField26",
    "custListField27",
    "custListField28",
    "custListField29",
    "custListField3",
    "custListField30",
    "custListField31",
    "custListField32",
    "custListField33",
    "custListField34",
    "custListField35",
    "custListField36",
    "custListField37",
    "custListField38",
    "custListField39",
    "custListField4",
    "custListField40",
    "custListField41",
    "custListField42",
    "custListField43",
    "custListField44",
    "custListField45",
    "custListField46",
    "custListField47",
    "custListField48",
    "custListField49",
    "custListField5",
    "custListField50",
    "custListField6",
    "custListField7",
    "custListField8",
    "custListField9",
    "custLongTextField1",
    "custLongTextField10",
    "custLongTextField11",
    "custLongTextField12",
    "custLongTextField13",
    "custLongTextField14",
    "custLongTextField15",
    "custLongTextField16",
    "custLongTextField17",
    "custLongTextField18",
    "custLongTextField19",
    "custLongTextField2",
    "custLongTextField20",
    "custLongTextField21",
    "custLongTextField22",
    "custLongTextField23",
    "custLongTextField24",
    "custLongTextField25",
    "custLongTextField26",
    "custLongTextField27",
    "custLongTextField28",
    "custLongTextField29",
    "custLongTextField3",
    "custLongTextField30",
    "custLongTextField31",
    "custLongTextField32",
    "custLongTextField33",
    "custLongTextField34",
    "custLongTextField35",
    "custLongTextField36",
    "custLongTextField37",
    "custLongTextField38",
    "custLongTextField39",
    "custLongTextField4",
    "custLongTextField40",
    "custLongTextField41",
    "custLongTextField42",
    "custLongTextField43",
    "custLongTextField44",
    "custLongTextField45",
    "custLongTextField46",
    "custLongTextField47",
    "custLongTextField48",
    "custLongTextField49",
    "custLongTextField5",
    "custLongTextField50",
    "custLongTextField6",
    "custLongTextField7",
    "custLongTextField8",
    "custLongTextField9",
    "custTextField1",
    "custTextField10",
    "custTextField11",
    "custTextField12",
    "custTextField13",
    "custTextField14",
    "custTextField15",
    "custTextField16",
    "custTextField17",
    "custTextField18",
    "custTextField19",
    "custTextField2",
    "custTextField20",
    "custTextField21",
    "custTextField22",
    "custTextField23",
    "custTextField24",
    "custTextField25",
    "custTextField26",
    "custTextField27",
    "custTextField28",
    "custTextField29",
    "custTextField3",
    "custTextField30",
    "custTextField31",
    "custTextField32",
    "custTextField33",
    "custTextField34",
    "custTextField35",
    "custTextField36",
    "custTextField37",
    "custTextField38",
    "custTextField39",
    "custTextField4",
    "custTextField40",
    "custTextField41",
    "custTextField42",
    "custTextField43",
    "custTextField44",
    "custTextField45",
    "custTextField46",
    "custTextField47",
    "custTextField48",
    "custTextField49",
    "custTextField5",
    "custTextField50",
    "custTextField6",
    "custTextField7",
    "custTextField8",
    "custTextField9",
    "customDateFields",
    "customFields",
    "historyVersion",
    "id",
    "location",
    "notes",
    "owner",
    "ownerGroup",
    "priority",
    "serialNumber",
    "status",
    "subCategory",
    "supplier",
    "supplyDate",
    "thirdLevelCategory",
    "users"
})
public class ApiCI
    extends ApiSysaidObject
{

    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar acceptDate;
    protected String category;
    protected String ciName;
    protected int ciSubType;
    protected int ciType;
    protected int company;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField1;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField10;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField11;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField12;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField13;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField14;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField15;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField16;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField17;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField18;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField19;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField2;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField20;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField21;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField22;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField23;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField24;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField25;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField26;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField27;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField28;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField29;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField3;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField30;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField31;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField32;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField33;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField34;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField35;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField36;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField37;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField38;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField39;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField4;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField40;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField41;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField42;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField43;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField44;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField45;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField46;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField47;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField48;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField49;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField5;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField50;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField6;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField7;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField8;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar custDateField9;
    protected int custIntField1;
    protected int custIntField10;
    protected int custIntField11;
    protected int custIntField12;
    protected int custIntField13;
    protected int custIntField14;
    protected int custIntField15;
    protected int custIntField16;
    protected int custIntField17;
    protected int custIntField18;
    protected int custIntField19;
    protected int custIntField2;
    protected int custIntField20;
    protected int custIntField21;
    protected int custIntField22;
    protected int custIntField23;
    protected int custIntField24;
    protected int custIntField25;
    protected int custIntField26;
    protected int custIntField27;
    protected int custIntField28;
    protected int custIntField29;
    protected int custIntField3;
    protected int custIntField30;
    protected int custIntField31;
    protected int custIntField32;
    protected int custIntField33;
    protected int custIntField34;
    protected int custIntField35;
    protected int custIntField36;
    protected int custIntField37;
    protected int custIntField38;
    protected int custIntField39;
    protected int custIntField4;
    protected int custIntField40;
    protected int custIntField41;
    protected int custIntField42;
    protected int custIntField43;
    protected int custIntField44;
    protected int custIntField45;
    protected int custIntField46;
    protected int custIntField47;
    protected int custIntField48;
    protected int custIntField49;
    protected int custIntField5;
    protected int custIntField50;
    protected int custIntField6;
    protected int custIntField7;
    protected int custIntField8;
    protected int custIntField9;
    protected int custListField1;
    protected int custListField10;
    protected int custListField11;
    protected int custListField12;
    protected int custListField13;
    protected int custListField14;
    protected int custListField15;
    protected int custListField16;
    protected int custListField17;
    protected int custListField18;
    protected int custListField19;
    protected int custListField2;
    protected int custListField20;
    protected int custListField21;
    protected int custListField22;
    protected int custListField23;
    protected int custListField24;
    protected int custListField25;
    protected int custListField26;
    protected int custListField27;
    protected int custListField28;
    protected int custListField29;
    protected int custListField3;
    protected int custListField30;
    protected int custListField31;
    protected int custListField32;
    protected int custListField33;
    protected int custListField34;
    protected int custListField35;
    protected int custListField36;
    protected int custListField37;
    protected int custListField38;
    protected int custListField39;
    protected int custListField4;
    protected int custListField40;
    protected int custListField41;
    protected int custListField42;
    protected int custListField43;
    protected int custListField44;
    protected int custListField45;
    protected int custListField46;
    protected int custListField47;
    protected int custListField48;
    protected int custListField49;
    protected int custListField5;
    protected int custListField50;
    protected int custListField6;
    protected int custListField7;
    protected int custListField8;
    protected int custListField9;
    protected String custLongTextField1;
    protected String custLongTextField10;
    protected String custLongTextField11;
    protected String custLongTextField12;
    protected String custLongTextField13;
    protected String custLongTextField14;
    protected String custLongTextField15;
    protected String custLongTextField16;
    protected String custLongTextField17;
    protected String custLongTextField18;
    protected String custLongTextField19;
    protected String custLongTextField2;
    protected String custLongTextField20;
    protected String custLongTextField21;
    protected String custLongTextField22;
    protected String custLongTextField23;
    protected String custLongTextField24;
    protected String custLongTextField25;
    protected String custLongTextField26;
    protected String custLongTextField27;
    protected String custLongTextField28;
    protected String custLongTextField29;
    protected String custLongTextField3;
    protected String custLongTextField30;
    protected String custLongTextField31;
    protected String custLongTextField32;
    protected String custLongTextField33;
    protected String custLongTextField34;
    protected String custLongTextField35;
    protected String custLongTextField36;
    protected String custLongTextField37;
    protected String custLongTextField38;
    protected String custLongTextField39;
    protected String custLongTextField4;
    protected String custLongTextField40;
    protected String custLongTextField41;
    protected String custLongTextField42;
    protected String custLongTextField43;
    protected String custLongTextField44;
    protected String custLongTextField45;
    protected String custLongTextField46;
    protected String custLongTextField47;
    protected String custLongTextField48;
    protected String custLongTextField49;
    protected String custLongTextField5;
    protected String custLongTextField50;
    protected String custLongTextField6;
    protected String custLongTextField7;
    protected String custLongTextField8;
    protected String custLongTextField9;
    protected String custTextField1;
    protected String custTextField10;
    protected String custTextField11;
    protected String custTextField12;
    protected String custTextField13;
    protected String custTextField14;
    protected String custTextField15;
    protected String custTextField16;
    protected String custTextField17;
    protected String custTextField18;
    protected String custTextField19;
    protected String custTextField2;
    protected String custTextField20;
    protected String custTextField21;
    protected String custTextField22;
    protected String custTextField23;
    protected String custTextField24;
    protected String custTextField25;
    protected String custTextField26;
    protected String custTextField27;
    protected String custTextField28;
    protected String custTextField29;
    protected String custTextField3;
    protected String custTextField30;
    protected String custTextField31;
    protected String custTextField32;
    protected String custTextField33;
    protected String custTextField34;
    protected String custTextField35;
    protected String custTextField36;
    protected String custTextField37;
    protected String custTextField38;
    protected String custTextField39;
    protected String custTextField4;
    protected String custTextField40;
    protected String custTextField41;
    protected String custTextField42;
    protected String custTextField43;
    protected String custTextField44;
    protected String custTextField45;
    protected String custTextField46;
    protected String custTextField47;
    protected String custTextField48;
    protected String custTextField49;
    protected String custTextField5;
    protected String custTextField50;
    protected String custTextField6;
    protected String custTextField7;
    protected String custTextField8;
    protected String custTextField9;
    @XmlElement(required = true)
    protected ApiCI.CustomDateFields customDateFields;
    @XmlElement(required = true)
    protected ApiCI.CustomFields customFields;
    protected int historyVersion;
    protected int id;
    protected int location;
    protected String notes;
    protected String owner;
    protected String ownerGroup;
    protected int priority;
    protected String serialNumber;
    protected int status;
    protected String subCategory;
    protected int supplier;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar supplyDate;
    protected String thirdLevelCategory;
    protected String users;

    /**
     * Gets the value of the acceptDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAcceptDate() {
        return acceptDate;
    }

    /**
     * Sets the value of the acceptDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAcceptDate(XMLGregorianCalendar value) {
        this.acceptDate = value;
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
     * Gets the value of the ciName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCiName() {
        return ciName;
    }

    /**
     * Sets the value of the ciName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCiName(String value) {
        this.ciName = value;
    }

    /**
     * Gets the value of the ciSubType property.
     * 
     */
    public int getCiSubType() {
        return ciSubType;
    }

    /**
     * Sets the value of the ciSubType property.
     * 
     */
    public void setCiSubType(int value) {
        this.ciSubType = value;
    }

    /**
     * Gets the value of the ciType property.
     * 
     */
    public int getCiType() {
        return ciType;
    }

    /**
     * Sets the value of the ciType property.
     * 
     */
    public void setCiType(int value) {
        this.ciType = value;
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
     * Gets the value of the custDateField1 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField1() {
        return custDateField1;
    }

    /**
     * Sets the value of the custDateField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField1(XMLGregorianCalendar value) {
        this.custDateField1 = value;
    }

    /**
     * Gets the value of the custDateField10 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField10() {
        return custDateField10;
    }

    /**
     * Sets the value of the custDateField10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField10(XMLGregorianCalendar value) {
        this.custDateField10 = value;
    }

    /**
     * Gets the value of the custDateField11 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField11() {
        return custDateField11;
    }

    /**
     * Sets the value of the custDateField11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField11(XMLGregorianCalendar value) {
        this.custDateField11 = value;
    }

    /**
     * Gets the value of the custDateField12 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField12() {
        return custDateField12;
    }

    /**
     * Sets the value of the custDateField12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField12(XMLGregorianCalendar value) {
        this.custDateField12 = value;
    }

    /**
     * Gets the value of the custDateField13 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField13() {
        return custDateField13;
    }

    /**
     * Sets the value of the custDateField13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField13(XMLGregorianCalendar value) {
        this.custDateField13 = value;
    }

    /**
     * Gets the value of the custDateField14 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField14() {
        return custDateField14;
    }

    /**
     * Sets the value of the custDateField14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField14(XMLGregorianCalendar value) {
        this.custDateField14 = value;
    }

    /**
     * Gets the value of the custDateField15 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField15() {
        return custDateField15;
    }

    /**
     * Sets the value of the custDateField15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField15(XMLGregorianCalendar value) {
        this.custDateField15 = value;
    }

    /**
     * Gets the value of the custDateField16 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField16() {
        return custDateField16;
    }

    /**
     * Sets the value of the custDateField16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField16(XMLGregorianCalendar value) {
        this.custDateField16 = value;
    }

    /**
     * Gets the value of the custDateField17 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField17() {
        return custDateField17;
    }

    /**
     * Sets the value of the custDateField17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField17(XMLGregorianCalendar value) {
        this.custDateField17 = value;
    }

    /**
     * Gets the value of the custDateField18 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField18() {
        return custDateField18;
    }

    /**
     * Sets the value of the custDateField18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField18(XMLGregorianCalendar value) {
        this.custDateField18 = value;
    }

    /**
     * Gets the value of the custDateField19 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField19() {
        return custDateField19;
    }

    /**
     * Sets the value of the custDateField19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField19(XMLGregorianCalendar value) {
        this.custDateField19 = value;
    }

    /**
     * Gets the value of the custDateField2 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField2() {
        return custDateField2;
    }

    /**
     * Sets the value of the custDateField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField2(XMLGregorianCalendar value) {
        this.custDateField2 = value;
    }

    /**
     * Gets the value of the custDateField20 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField20() {
        return custDateField20;
    }

    /**
     * Sets the value of the custDateField20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField20(XMLGregorianCalendar value) {
        this.custDateField20 = value;
    }

    /**
     * Gets the value of the custDateField21 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField21() {
        return custDateField21;
    }

    /**
     * Sets the value of the custDateField21 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField21(XMLGregorianCalendar value) {
        this.custDateField21 = value;
    }

    /**
     * Gets the value of the custDateField22 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField22() {
        return custDateField22;
    }

    /**
     * Sets the value of the custDateField22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField22(XMLGregorianCalendar value) {
        this.custDateField22 = value;
    }

    /**
     * Gets the value of the custDateField23 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField23() {
        return custDateField23;
    }

    /**
     * Sets the value of the custDateField23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField23(XMLGregorianCalendar value) {
        this.custDateField23 = value;
    }

    /**
     * Gets the value of the custDateField24 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField24() {
        return custDateField24;
    }

    /**
     * Sets the value of the custDateField24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField24(XMLGregorianCalendar value) {
        this.custDateField24 = value;
    }

    /**
     * Gets the value of the custDateField25 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField25() {
        return custDateField25;
    }

    /**
     * Sets the value of the custDateField25 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField25(XMLGregorianCalendar value) {
        this.custDateField25 = value;
    }

    /**
     * Gets the value of the custDateField26 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField26() {
        return custDateField26;
    }

    /**
     * Sets the value of the custDateField26 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField26(XMLGregorianCalendar value) {
        this.custDateField26 = value;
    }

    /**
     * Gets the value of the custDateField27 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField27() {
        return custDateField27;
    }

    /**
     * Sets the value of the custDateField27 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField27(XMLGregorianCalendar value) {
        this.custDateField27 = value;
    }

    /**
     * Gets the value of the custDateField28 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField28() {
        return custDateField28;
    }

    /**
     * Sets the value of the custDateField28 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField28(XMLGregorianCalendar value) {
        this.custDateField28 = value;
    }

    /**
     * Gets the value of the custDateField29 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField29() {
        return custDateField29;
    }

    /**
     * Sets the value of the custDateField29 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField29(XMLGregorianCalendar value) {
        this.custDateField29 = value;
    }

    /**
     * Gets the value of the custDateField3 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField3() {
        return custDateField3;
    }

    /**
     * Sets the value of the custDateField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField3(XMLGregorianCalendar value) {
        this.custDateField3 = value;
    }

    /**
     * Gets the value of the custDateField30 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField30() {
        return custDateField30;
    }

    /**
     * Sets the value of the custDateField30 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField30(XMLGregorianCalendar value) {
        this.custDateField30 = value;
    }

    /**
     * Gets the value of the custDateField31 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField31() {
        return custDateField31;
    }

    /**
     * Sets the value of the custDateField31 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField31(XMLGregorianCalendar value) {
        this.custDateField31 = value;
    }

    /**
     * Gets the value of the custDateField32 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField32() {
        return custDateField32;
    }

    /**
     * Sets the value of the custDateField32 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField32(XMLGregorianCalendar value) {
        this.custDateField32 = value;
    }

    /**
     * Gets the value of the custDateField33 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField33() {
        return custDateField33;
    }

    /**
     * Sets the value of the custDateField33 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField33(XMLGregorianCalendar value) {
        this.custDateField33 = value;
    }

    /**
     * Gets the value of the custDateField34 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField34() {
        return custDateField34;
    }

    /**
     * Sets the value of the custDateField34 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField34(XMLGregorianCalendar value) {
        this.custDateField34 = value;
    }

    /**
     * Gets the value of the custDateField35 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField35() {
        return custDateField35;
    }

    /**
     * Sets the value of the custDateField35 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField35(XMLGregorianCalendar value) {
        this.custDateField35 = value;
    }

    /**
     * Gets the value of the custDateField36 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField36() {
        return custDateField36;
    }

    /**
     * Sets the value of the custDateField36 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField36(XMLGregorianCalendar value) {
        this.custDateField36 = value;
    }

    /**
     * Gets the value of the custDateField37 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField37() {
        return custDateField37;
    }

    /**
     * Sets the value of the custDateField37 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField37(XMLGregorianCalendar value) {
        this.custDateField37 = value;
    }

    /**
     * Gets the value of the custDateField38 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField38() {
        return custDateField38;
    }

    /**
     * Sets the value of the custDateField38 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField38(XMLGregorianCalendar value) {
        this.custDateField38 = value;
    }

    /**
     * Gets the value of the custDateField39 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField39() {
        return custDateField39;
    }

    /**
     * Sets the value of the custDateField39 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField39(XMLGregorianCalendar value) {
        this.custDateField39 = value;
    }

    /**
     * Gets the value of the custDateField4 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField4() {
        return custDateField4;
    }

    /**
     * Sets the value of the custDateField4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField4(XMLGregorianCalendar value) {
        this.custDateField4 = value;
    }

    /**
     * Gets the value of the custDateField40 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField40() {
        return custDateField40;
    }

    /**
     * Sets the value of the custDateField40 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField40(XMLGregorianCalendar value) {
        this.custDateField40 = value;
    }

    /**
     * Gets the value of the custDateField41 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField41() {
        return custDateField41;
    }

    /**
     * Sets the value of the custDateField41 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField41(XMLGregorianCalendar value) {
        this.custDateField41 = value;
    }

    /**
     * Gets the value of the custDateField42 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField42() {
        return custDateField42;
    }

    /**
     * Sets the value of the custDateField42 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField42(XMLGregorianCalendar value) {
        this.custDateField42 = value;
    }

    /**
     * Gets the value of the custDateField43 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField43() {
        return custDateField43;
    }

    /**
     * Sets the value of the custDateField43 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField43(XMLGregorianCalendar value) {
        this.custDateField43 = value;
    }

    /**
     * Gets the value of the custDateField44 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField44() {
        return custDateField44;
    }

    /**
     * Sets the value of the custDateField44 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField44(XMLGregorianCalendar value) {
        this.custDateField44 = value;
    }

    /**
     * Gets the value of the custDateField45 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField45() {
        return custDateField45;
    }

    /**
     * Sets the value of the custDateField45 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField45(XMLGregorianCalendar value) {
        this.custDateField45 = value;
    }

    /**
     * Gets the value of the custDateField46 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField46() {
        return custDateField46;
    }

    /**
     * Sets the value of the custDateField46 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField46(XMLGregorianCalendar value) {
        this.custDateField46 = value;
    }

    /**
     * Gets the value of the custDateField47 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField47() {
        return custDateField47;
    }

    /**
     * Sets the value of the custDateField47 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField47(XMLGregorianCalendar value) {
        this.custDateField47 = value;
    }

    /**
     * Gets the value of the custDateField48 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField48() {
        return custDateField48;
    }

    /**
     * Sets the value of the custDateField48 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField48(XMLGregorianCalendar value) {
        this.custDateField48 = value;
    }

    /**
     * Gets the value of the custDateField49 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField49() {
        return custDateField49;
    }

    /**
     * Sets the value of the custDateField49 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField49(XMLGregorianCalendar value) {
        this.custDateField49 = value;
    }

    /**
     * Gets the value of the custDateField5 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField5() {
        return custDateField5;
    }

    /**
     * Sets the value of the custDateField5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField5(XMLGregorianCalendar value) {
        this.custDateField5 = value;
    }

    /**
     * Gets the value of the custDateField50 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField50() {
        return custDateField50;
    }

    /**
     * Sets the value of the custDateField50 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField50(XMLGregorianCalendar value) {
        this.custDateField50 = value;
    }

    /**
     * Gets the value of the custDateField6 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField6() {
        return custDateField6;
    }

    /**
     * Sets the value of the custDateField6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField6(XMLGregorianCalendar value) {
        this.custDateField6 = value;
    }

    /**
     * Gets the value of the custDateField7 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField7() {
        return custDateField7;
    }

    /**
     * Sets the value of the custDateField7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField7(XMLGregorianCalendar value) {
        this.custDateField7 = value;
    }

    /**
     * Gets the value of the custDateField8 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField8() {
        return custDateField8;
    }

    /**
     * Sets the value of the custDateField8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField8(XMLGregorianCalendar value) {
        this.custDateField8 = value;
    }

    /**
     * Gets the value of the custDateField9 property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getCustDateField9() {
        return custDateField9;
    }

    /**
     * Sets the value of the custDateField9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setCustDateField9(XMLGregorianCalendar value) {
        this.custDateField9 = value;
    }

    /**
     * Gets the value of the custIntField1 property.
     * 
     */
    public int getCustIntField1() {
        return custIntField1;
    }

    /**
     * Sets the value of the custIntField1 property.
     * 
     */
    public void setCustIntField1(int value) {
        this.custIntField1 = value;
    }

    /**
     * Gets the value of the custIntField10 property.
     * 
     */
    public int getCustIntField10() {
        return custIntField10;
    }

    /**
     * Sets the value of the custIntField10 property.
     * 
     */
    public void setCustIntField10(int value) {
        this.custIntField10 = value;
    }

    /**
     * Gets the value of the custIntField11 property.
     * 
     */
    public int getCustIntField11() {
        return custIntField11;
    }

    /**
     * Sets the value of the custIntField11 property.
     * 
     */
    public void setCustIntField11(int value) {
        this.custIntField11 = value;
    }

    /**
     * Gets the value of the custIntField12 property.
     * 
     */
    public int getCustIntField12() {
        return custIntField12;
    }

    /**
     * Sets the value of the custIntField12 property.
     * 
     */
    public void setCustIntField12(int value) {
        this.custIntField12 = value;
    }

    /**
     * Gets the value of the custIntField13 property.
     * 
     */
    public int getCustIntField13() {
        return custIntField13;
    }

    /**
     * Sets the value of the custIntField13 property.
     * 
     */
    public void setCustIntField13(int value) {
        this.custIntField13 = value;
    }

    /**
     * Gets the value of the custIntField14 property.
     * 
     */
    public int getCustIntField14() {
        return custIntField14;
    }

    /**
     * Sets the value of the custIntField14 property.
     * 
     */
    public void setCustIntField14(int value) {
        this.custIntField14 = value;
    }

    /**
     * Gets the value of the custIntField15 property.
     * 
     */
    public int getCustIntField15() {
        return custIntField15;
    }

    /**
     * Sets the value of the custIntField15 property.
     * 
     */
    public void setCustIntField15(int value) {
        this.custIntField15 = value;
    }

    /**
     * Gets the value of the custIntField16 property.
     * 
     */
    public int getCustIntField16() {
        return custIntField16;
    }

    /**
     * Sets the value of the custIntField16 property.
     * 
     */
    public void setCustIntField16(int value) {
        this.custIntField16 = value;
    }

    /**
     * Gets the value of the custIntField17 property.
     * 
     */
    public int getCustIntField17() {
        return custIntField17;
    }

    /**
     * Sets the value of the custIntField17 property.
     * 
     */
    public void setCustIntField17(int value) {
        this.custIntField17 = value;
    }

    /**
     * Gets the value of the custIntField18 property.
     * 
     */
    public int getCustIntField18() {
        return custIntField18;
    }

    /**
     * Sets the value of the custIntField18 property.
     * 
     */
    public void setCustIntField18(int value) {
        this.custIntField18 = value;
    }

    /**
     * Gets the value of the custIntField19 property.
     * 
     */
    public int getCustIntField19() {
        return custIntField19;
    }

    /**
     * Sets the value of the custIntField19 property.
     * 
     */
    public void setCustIntField19(int value) {
        this.custIntField19 = value;
    }

    /**
     * Gets the value of the custIntField2 property.
     * 
     */
    public int getCustIntField2() {
        return custIntField2;
    }

    /**
     * Sets the value of the custIntField2 property.
     * 
     */
    public void setCustIntField2(int value) {
        this.custIntField2 = value;
    }

    /**
     * Gets the value of the custIntField20 property.
     * 
     */
    public int getCustIntField20() {
        return custIntField20;
    }

    /**
     * Sets the value of the custIntField20 property.
     * 
     */
    public void setCustIntField20(int value) {
        this.custIntField20 = value;
    }

    /**
     * Gets the value of the custIntField21 property.
     * 
     */
    public int getCustIntField21() {
        return custIntField21;
    }

    /**
     * Sets the value of the custIntField21 property.
     * 
     */
    public void setCustIntField21(int value) {
        this.custIntField21 = value;
    }

    /**
     * Gets the value of the custIntField22 property.
     * 
     */
    public int getCustIntField22() {
        return custIntField22;
    }

    /**
     * Sets the value of the custIntField22 property.
     * 
     */
    public void setCustIntField22(int value) {
        this.custIntField22 = value;
    }

    /**
     * Gets the value of the custIntField23 property.
     * 
     */
    public int getCustIntField23() {
        return custIntField23;
    }

    /**
     * Sets the value of the custIntField23 property.
     * 
     */
    public void setCustIntField23(int value) {
        this.custIntField23 = value;
    }

    /**
     * Gets the value of the custIntField24 property.
     * 
     */
    public int getCustIntField24() {
        return custIntField24;
    }

    /**
     * Sets the value of the custIntField24 property.
     * 
     */
    public void setCustIntField24(int value) {
        this.custIntField24 = value;
    }

    /**
     * Gets the value of the custIntField25 property.
     * 
     */
    public int getCustIntField25() {
        return custIntField25;
    }

    /**
     * Sets the value of the custIntField25 property.
     * 
     */
    public void setCustIntField25(int value) {
        this.custIntField25 = value;
    }

    /**
     * Gets the value of the custIntField26 property.
     * 
     */
    public int getCustIntField26() {
        return custIntField26;
    }

    /**
     * Sets the value of the custIntField26 property.
     * 
     */
    public void setCustIntField26(int value) {
        this.custIntField26 = value;
    }

    /**
     * Gets the value of the custIntField27 property.
     * 
     */
    public int getCustIntField27() {
        return custIntField27;
    }

    /**
     * Sets the value of the custIntField27 property.
     * 
     */
    public void setCustIntField27(int value) {
        this.custIntField27 = value;
    }

    /**
     * Gets the value of the custIntField28 property.
     * 
     */
    public int getCustIntField28() {
        return custIntField28;
    }

    /**
     * Sets the value of the custIntField28 property.
     * 
     */
    public void setCustIntField28(int value) {
        this.custIntField28 = value;
    }

    /**
     * Gets the value of the custIntField29 property.
     * 
     */
    public int getCustIntField29() {
        return custIntField29;
    }

    /**
     * Sets the value of the custIntField29 property.
     * 
     */
    public void setCustIntField29(int value) {
        this.custIntField29 = value;
    }

    /**
     * Gets the value of the custIntField3 property.
     * 
     */
    public int getCustIntField3() {
        return custIntField3;
    }

    /**
     * Sets the value of the custIntField3 property.
     * 
     */
    public void setCustIntField3(int value) {
        this.custIntField3 = value;
    }

    /**
     * Gets the value of the custIntField30 property.
     * 
     */
    public int getCustIntField30() {
        return custIntField30;
    }

    /**
     * Sets the value of the custIntField30 property.
     * 
     */
    public void setCustIntField30(int value) {
        this.custIntField30 = value;
    }

    /**
     * Gets the value of the custIntField31 property.
     * 
     */
    public int getCustIntField31() {
        return custIntField31;
    }

    /**
     * Sets the value of the custIntField31 property.
     * 
     */
    public void setCustIntField31(int value) {
        this.custIntField31 = value;
    }

    /**
     * Gets the value of the custIntField32 property.
     * 
     */
    public int getCustIntField32() {
        return custIntField32;
    }

    /**
     * Sets the value of the custIntField32 property.
     * 
     */
    public void setCustIntField32(int value) {
        this.custIntField32 = value;
    }

    /**
     * Gets the value of the custIntField33 property.
     * 
     */
    public int getCustIntField33() {
        return custIntField33;
    }

    /**
     * Sets the value of the custIntField33 property.
     * 
     */
    public void setCustIntField33(int value) {
        this.custIntField33 = value;
    }

    /**
     * Gets the value of the custIntField34 property.
     * 
     */
    public int getCustIntField34() {
        return custIntField34;
    }

    /**
     * Sets the value of the custIntField34 property.
     * 
     */
    public void setCustIntField34(int value) {
        this.custIntField34 = value;
    }

    /**
     * Gets the value of the custIntField35 property.
     * 
     */
    public int getCustIntField35() {
        return custIntField35;
    }

    /**
     * Sets the value of the custIntField35 property.
     * 
     */
    public void setCustIntField35(int value) {
        this.custIntField35 = value;
    }

    /**
     * Gets the value of the custIntField36 property.
     * 
     */
    public int getCustIntField36() {
        return custIntField36;
    }

    /**
     * Sets the value of the custIntField36 property.
     * 
     */
    public void setCustIntField36(int value) {
        this.custIntField36 = value;
    }

    /**
     * Gets the value of the custIntField37 property.
     * 
     */
    public int getCustIntField37() {
        return custIntField37;
    }

    /**
     * Sets the value of the custIntField37 property.
     * 
     */
    public void setCustIntField37(int value) {
        this.custIntField37 = value;
    }

    /**
     * Gets the value of the custIntField38 property.
     * 
     */
    public int getCustIntField38() {
        return custIntField38;
    }

    /**
     * Sets the value of the custIntField38 property.
     * 
     */
    public void setCustIntField38(int value) {
        this.custIntField38 = value;
    }

    /**
     * Gets the value of the custIntField39 property.
     * 
     */
    public int getCustIntField39() {
        return custIntField39;
    }

    /**
     * Sets the value of the custIntField39 property.
     * 
     */
    public void setCustIntField39(int value) {
        this.custIntField39 = value;
    }

    /**
     * Gets the value of the custIntField4 property.
     * 
     */
    public int getCustIntField4() {
        return custIntField4;
    }

    /**
     * Sets the value of the custIntField4 property.
     * 
     */
    public void setCustIntField4(int value) {
        this.custIntField4 = value;
    }

    /**
     * Gets the value of the custIntField40 property.
     * 
     */
    public int getCustIntField40() {
        return custIntField40;
    }

    /**
     * Sets the value of the custIntField40 property.
     * 
     */
    public void setCustIntField40(int value) {
        this.custIntField40 = value;
    }

    /**
     * Gets the value of the custIntField41 property.
     * 
     */
    public int getCustIntField41() {
        return custIntField41;
    }

    /**
     * Sets the value of the custIntField41 property.
     * 
     */
    public void setCustIntField41(int value) {
        this.custIntField41 = value;
    }

    /**
     * Gets the value of the custIntField42 property.
     * 
     */
    public int getCustIntField42() {
        return custIntField42;
    }

    /**
     * Sets the value of the custIntField42 property.
     * 
     */
    public void setCustIntField42(int value) {
        this.custIntField42 = value;
    }

    /**
     * Gets the value of the custIntField43 property.
     * 
     */
    public int getCustIntField43() {
        return custIntField43;
    }

    /**
     * Sets the value of the custIntField43 property.
     * 
     */
    public void setCustIntField43(int value) {
        this.custIntField43 = value;
    }

    /**
     * Gets the value of the custIntField44 property.
     * 
     */
    public int getCustIntField44() {
        return custIntField44;
    }

    /**
     * Sets the value of the custIntField44 property.
     * 
     */
    public void setCustIntField44(int value) {
        this.custIntField44 = value;
    }

    /**
     * Gets the value of the custIntField45 property.
     * 
     */
    public int getCustIntField45() {
        return custIntField45;
    }

    /**
     * Sets the value of the custIntField45 property.
     * 
     */
    public void setCustIntField45(int value) {
        this.custIntField45 = value;
    }

    /**
     * Gets the value of the custIntField46 property.
     * 
     */
    public int getCustIntField46() {
        return custIntField46;
    }

    /**
     * Sets the value of the custIntField46 property.
     * 
     */
    public void setCustIntField46(int value) {
        this.custIntField46 = value;
    }

    /**
     * Gets the value of the custIntField47 property.
     * 
     */
    public int getCustIntField47() {
        return custIntField47;
    }

    /**
     * Sets the value of the custIntField47 property.
     * 
     */
    public void setCustIntField47(int value) {
        this.custIntField47 = value;
    }

    /**
     * Gets the value of the custIntField48 property.
     * 
     */
    public int getCustIntField48() {
        return custIntField48;
    }

    /**
     * Sets the value of the custIntField48 property.
     * 
     */
    public void setCustIntField48(int value) {
        this.custIntField48 = value;
    }

    /**
     * Gets the value of the custIntField49 property.
     * 
     */
    public int getCustIntField49() {
        return custIntField49;
    }

    /**
     * Sets the value of the custIntField49 property.
     * 
     */
    public void setCustIntField49(int value) {
        this.custIntField49 = value;
    }

    /**
     * Gets the value of the custIntField5 property.
     * 
     */
    public int getCustIntField5() {
        return custIntField5;
    }

    /**
     * Sets the value of the custIntField5 property.
     * 
     */
    public void setCustIntField5(int value) {
        this.custIntField5 = value;
    }

    /**
     * Gets the value of the custIntField50 property.
     * 
     */
    public int getCustIntField50() {
        return custIntField50;
    }

    /**
     * Sets the value of the custIntField50 property.
     * 
     */
    public void setCustIntField50(int value) {
        this.custIntField50 = value;
    }

    /**
     * Gets the value of the custIntField6 property.
     * 
     */
    public int getCustIntField6() {
        return custIntField6;
    }

    /**
     * Sets the value of the custIntField6 property.
     * 
     */
    public void setCustIntField6(int value) {
        this.custIntField6 = value;
    }

    /**
     * Gets the value of the custIntField7 property.
     * 
     */
    public int getCustIntField7() {
        return custIntField7;
    }

    /**
     * Sets the value of the custIntField7 property.
     * 
     */
    public void setCustIntField7(int value) {
        this.custIntField7 = value;
    }

    /**
     * Gets the value of the custIntField8 property.
     * 
     */
    public int getCustIntField8() {
        return custIntField8;
    }

    /**
     * Sets the value of the custIntField8 property.
     * 
     */
    public void setCustIntField8(int value) {
        this.custIntField8 = value;
    }

    /**
     * Gets the value of the custIntField9 property.
     * 
     */
    public int getCustIntField9() {
        return custIntField9;
    }

    /**
     * Sets the value of the custIntField9 property.
     * 
     */
    public void setCustIntField9(int value) {
        this.custIntField9 = value;
    }

    /**
     * Gets the value of the custListField1 property.
     * 
     */
    public int getCustListField1() {
        return custListField1;
    }

    /**
     * Sets the value of the custListField1 property.
     * 
     */
    public void setCustListField1(int value) {
        this.custListField1 = value;
    }

    /**
     * Gets the value of the custListField10 property.
     * 
     */
    public int getCustListField10() {
        return custListField10;
    }

    /**
     * Sets the value of the custListField10 property.
     * 
     */
    public void setCustListField10(int value) {
        this.custListField10 = value;
    }

    /**
     * Gets the value of the custListField11 property.
     * 
     */
    public int getCustListField11() {
        return custListField11;
    }

    /**
     * Sets the value of the custListField11 property.
     * 
     */
    public void setCustListField11(int value) {
        this.custListField11 = value;
    }

    /**
     * Gets the value of the custListField12 property.
     * 
     */
    public int getCustListField12() {
        return custListField12;
    }

    /**
     * Sets the value of the custListField12 property.
     * 
     */
    public void setCustListField12(int value) {
        this.custListField12 = value;
    }

    /**
     * Gets the value of the custListField13 property.
     * 
     */
    public int getCustListField13() {
        return custListField13;
    }

    /**
     * Sets the value of the custListField13 property.
     * 
     */
    public void setCustListField13(int value) {
        this.custListField13 = value;
    }

    /**
     * Gets the value of the custListField14 property.
     * 
     */
    public int getCustListField14() {
        return custListField14;
    }

    /**
     * Sets the value of the custListField14 property.
     * 
     */
    public void setCustListField14(int value) {
        this.custListField14 = value;
    }

    /**
     * Gets the value of the custListField15 property.
     * 
     */
    public int getCustListField15() {
        return custListField15;
    }

    /**
     * Sets the value of the custListField15 property.
     * 
     */
    public void setCustListField15(int value) {
        this.custListField15 = value;
    }

    /**
     * Gets the value of the custListField16 property.
     * 
     */
    public int getCustListField16() {
        return custListField16;
    }

    /**
     * Sets the value of the custListField16 property.
     * 
     */
    public void setCustListField16(int value) {
        this.custListField16 = value;
    }

    /**
     * Gets the value of the custListField17 property.
     * 
     */
    public int getCustListField17() {
        return custListField17;
    }

    /**
     * Sets the value of the custListField17 property.
     * 
     */
    public void setCustListField17(int value) {
        this.custListField17 = value;
    }

    /**
     * Gets the value of the custListField18 property.
     * 
     */
    public int getCustListField18() {
        return custListField18;
    }

    /**
     * Sets the value of the custListField18 property.
     * 
     */
    public void setCustListField18(int value) {
        this.custListField18 = value;
    }

    /**
     * Gets the value of the custListField19 property.
     * 
     */
    public int getCustListField19() {
        return custListField19;
    }

    /**
     * Sets the value of the custListField19 property.
     * 
     */
    public void setCustListField19(int value) {
        this.custListField19 = value;
    }

    /**
     * Gets the value of the custListField2 property.
     * 
     */
    public int getCustListField2() {
        return custListField2;
    }

    /**
     * Sets the value of the custListField2 property.
     * 
     */
    public void setCustListField2(int value) {
        this.custListField2 = value;
    }

    /**
     * Gets the value of the custListField20 property.
     * 
     */
    public int getCustListField20() {
        return custListField20;
    }

    /**
     * Sets the value of the custListField20 property.
     * 
     */
    public void setCustListField20(int value) {
        this.custListField20 = value;
    }

    /**
     * Gets the value of the custListField21 property.
     * 
     */
    public int getCustListField21() {
        return custListField21;
    }

    /**
     * Sets the value of the custListField21 property.
     * 
     */
    public void setCustListField21(int value) {
        this.custListField21 = value;
    }

    /**
     * Gets the value of the custListField22 property.
     * 
     */
    public int getCustListField22() {
        return custListField22;
    }

    /**
     * Sets the value of the custListField22 property.
     * 
     */
    public void setCustListField22(int value) {
        this.custListField22 = value;
    }

    /**
     * Gets the value of the custListField23 property.
     * 
     */
    public int getCustListField23() {
        return custListField23;
    }

    /**
     * Sets the value of the custListField23 property.
     * 
     */
    public void setCustListField23(int value) {
        this.custListField23 = value;
    }

    /**
     * Gets the value of the custListField24 property.
     * 
     */
    public int getCustListField24() {
        return custListField24;
    }

    /**
     * Sets the value of the custListField24 property.
     * 
     */
    public void setCustListField24(int value) {
        this.custListField24 = value;
    }

    /**
     * Gets the value of the custListField25 property.
     * 
     */
    public int getCustListField25() {
        return custListField25;
    }

    /**
     * Sets the value of the custListField25 property.
     * 
     */
    public void setCustListField25(int value) {
        this.custListField25 = value;
    }

    /**
     * Gets the value of the custListField26 property.
     * 
     */
    public int getCustListField26() {
        return custListField26;
    }

    /**
     * Sets the value of the custListField26 property.
     * 
     */
    public void setCustListField26(int value) {
        this.custListField26 = value;
    }

    /**
     * Gets the value of the custListField27 property.
     * 
     */
    public int getCustListField27() {
        return custListField27;
    }

    /**
     * Sets the value of the custListField27 property.
     * 
     */
    public void setCustListField27(int value) {
        this.custListField27 = value;
    }

    /**
     * Gets the value of the custListField28 property.
     * 
     */
    public int getCustListField28() {
        return custListField28;
    }

    /**
     * Sets the value of the custListField28 property.
     * 
     */
    public void setCustListField28(int value) {
        this.custListField28 = value;
    }

    /**
     * Gets the value of the custListField29 property.
     * 
     */
    public int getCustListField29() {
        return custListField29;
    }

    /**
     * Sets the value of the custListField29 property.
     * 
     */
    public void setCustListField29(int value) {
        this.custListField29 = value;
    }

    /**
     * Gets the value of the custListField3 property.
     * 
     */
    public int getCustListField3() {
        return custListField3;
    }

    /**
     * Sets the value of the custListField3 property.
     * 
     */
    public void setCustListField3(int value) {
        this.custListField3 = value;
    }

    /**
     * Gets the value of the custListField30 property.
     * 
     */
    public int getCustListField30() {
        return custListField30;
    }

    /**
     * Sets the value of the custListField30 property.
     * 
     */
    public void setCustListField30(int value) {
        this.custListField30 = value;
    }

    /**
     * Gets the value of the custListField31 property.
     * 
     */
    public int getCustListField31() {
        return custListField31;
    }

    /**
     * Sets the value of the custListField31 property.
     * 
     */
    public void setCustListField31(int value) {
        this.custListField31 = value;
    }

    /**
     * Gets the value of the custListField32 property.
     * 
     */
    public int getCustListField32() {
        return custListField32;
    }

    /**
     * Sets the value of the custListField32 property.
     * 
     */
    public void setCustListField32(int value) {
        this.custListField32 = value;
    }

    /**
     * Gets the value of the custListField33 property.
     * 
     */
    public int getCustListField33() {
        return custListField33;
    }

    /**
     * Sets the value of the custListField33 property.
     * 
     */
    public void setCustListField33(int value) {
        this.custListField33 = value;
    }

    /**
     * Gets the value of the custListField34 property.
     * 
     */
    public int getCustListField34() {
        return custListField34;
    }

    /**
     * Sets the value of the custListField34 property.
     * 
     */
    public void setCustListField34(int value) {
        this.custListField34 = value;
    }

    /**
     * Gets the value of the custListField35 property.
     * 
     */
    public int getCustListField35() {
        return custListField35;
    }

    /**
     * Sets the value of the custListField35 property.
     * 
     */
    public void setCustListField35(int value) {
        this.custListField35 = value;
    }

    /**
     * Gets the value of the custListField36 property.
     * 
     */
    public int getCustListField36() {
        return custListField36;
    }

    /**
     * Sets the value of the custListField36 property.
     * 
     */
    public void setCustListField36(int value) {
        this.custListField36 = value;
    }

    /**
     * Gets the value of the custListField37 property.
     * 
     */
    public int getCustListField37() {
        return custListField37;
    }

    /**
     * Sets the value of the custListField37 property.
     * 
     */
    public void setCustListField37(int value) {
        this.custListField37 = value;
    }

    /**
     * Gets the value of the custListField38 property.
     * 
     */
    public int getCustListField38() {
        return custListField38;
    }

    /**
     * Sets the value of the custListField38 property.
     * 
     */
    public void setCustListField38(int value) {
        this.custListField38 = value;
    }

    /**
     * Gets the value of the custListField39 property.
     * 
     */
    public int getCustListField39() {
        return custListField39;
    }

    /**
     * Sets the value of the custListField39 property.
     * 
     */
    public void setCustListField39(int value) {
        this.custListField39 = value;
    }

    /**
     * Gets the value of the custListField4 property.
     * 
     */
    public int getCustListField4() {
        return custListField4;
    }

    /**
     * Sets the value of the custListField4 property.
     * 
     */
    public void setCustListField4(int value) {
        this.custListField4 = value;
    }

    /**
     * Gets the value of the custListField40 property.
     * 
     */
    public int getCustListField40() {
        return custListField40;
    }

    /**
     * Sets the value of the custListField40 property.
     * 
     */
    public void setCustListField40(int value) {
        this.custListField40 = value;
    }

    /**
     * Gets the value of the custListField41 property.
     * 
     */
    public int getCustListField41() {
        return custListField41;
    }

    /**
     * Sets the value of the custListField41 property.
     * 
     */
    public void setCustListField41(int value) {
        this.custListField41 = value;
    }

    /**
     * Gets the value of the custListField42 property.
     * 
     */
    public int getCustListField42() {
        return custListField42;
    }

    /**
     * Sets the value of the custListField42 property.
     * 
     */
    public void setCustListField42(int value) {
        this.custListField42 = value;
    }

    /**
     * Gets the value of the custListField43 property.
     * 
     */
    public int getCustListField43() {
        return custListField43;
    }

    /**
     * Sets the value of the custListField43 property.
     * 
     */
    public void setCustListField43(int value) {
        this.custListField43 = value;
    }

    /**
     * Gets the value of the custListField44 property.
     * 
     */
    public int getCustListField44() {
        return custListField44;
    }

    /**
     * Sets the value of the custListField44 property.
     * 
     */
    public void setCustListField44(int value) {
        this.custListField44 = value;
    }

    /**
     * Gets the value of the custListField45 property.
     * 
     */
    public int getCustListField45() {
        return custListField45;
    }

    /**
     * Sets the value of the custListField45 property.
     * 
     */
    public void setCustListField45(int value) {
        this.custListField45 = value;
    }

    /**
     * Gets the value of the custListField46 property.
     * 
     */
    public int getCustListField46() {
        return custListField46;
    }

    /**
     * Sets the value of the custListField46 property.
     * 
     */
    public void setCustListField46(int value) {
        this.custListField46 = value;
    }

    /**
     * Gets the value of the custListField47 property.
     * 
     */
    public int getCustListField47() {
        return custListField47;
    }

    /**
     * Sets the value of the custListField47 property.
     * 
     */
    public void setCustListField47(int value) {
        this.custListField47 = value;
    }

    /**
     * Gets the value of the custListField48 property.
     * 
     */
    public int getCustListField48() {
        return custListField48;
    }

    /**
     * Sets the value of the custListField48 property.
     * 
     */
    public void setCustListField48(int value) {
        this.custListField48 = value;
    }

    /**
     * Gets the value of the custListField49 property.
     * 
     */
    public int getCustListField49() {
        return custListField49;
    }

    /**
     * Sets the value of the custListField49 property.
     * 
     */
    public void setCustListField49(int value) {
        this.custListField49 = value;
    }

    /**
     * Gets the value of the custListField5 property.
     * 
     */
    public int getCustListField5() {
        return custListField5;
    }

    /**
     * Sets the value of the custListField5 property.
     * 
     */
    public void setCustListField5(int value) {
        this.custListField5 = value;
    }

    /**
     * Gets the value of the custListField50 property.
     * 
     */
    public int getCustListField50() {
        return custListField50;
    }

    /**
     * Sets the value of the custListField50 property.
     * 
     */
    public void setCustListField50(int value) {
        this.custListField50 = value;
    }

    /**
     * Gets the value of the custListField6 property.
     * 
     */
    public int getCustListField6() {
        return custListField6;
    }

    /**
     * Sets the value of the custListField6 property.
     * 
     */
    public void setCustListField6(int value) {
        this.custListField6 = value;
    }

    /**
     * Gets the value of the custListField7 property.
     * 
     */
    public int getCustListField7() {
        return custListField7;
    }

    /**
     * Sets the value of the custListField7 property.
     * 
     */
    public void setCustListField7(int value) {
        this.custListField7 = value;
    }

    /**
     * Gets the value of the custListField8 property.
     * 
     */
    public int getCustListField8() {
        return custListField8;
    }

    /**
     * Sets the value of the custListField8 property.
     * 
     */
    public void setCustListField8(int value) {
        this.custListField8 = value;
    }

    /**
     * Gets the value of the custListField9 property.
     * 
     */
    public int getCustListField9() {
        return custListField9;
    }

    /**
     * Sets the value of the custListField9 property.
     * 
     */
    public void setCustListField9(int value) {
        this.custListField9 = value;
    }

    /**
     * Gets the value of the custLongTextField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField1() {
        return custLongTextField1;
    }

    /**
     * Sets the value of the custLongTextField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField1(String value) {
        this.custLongTextField1 = value;
    }

    /**
     * Gets the value of the custLongTextField10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField10() {
        return custLongTextField10;
    }

    /**
     * Sets the value of the custLongTextField10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField10(String value) {
        this.custLongTextField10 = value;
    }

    /**
     * Gets the value of the custLongTextField11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField11() {
        return custLongTextField11;
    }

    /**
     * Sets the value of the custLongTextField11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField11(String value) {
        this.custLongTextField11 = value;
    }

    /**
     * Gets the value of the custLongTextField12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField12() {
        return custLongTextField12;
    }

    /**
     * Sets the value of the custLongTextField12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField12(String value) {
        this.custLongTextField12 = value;
    }

    /**
     * Gets the value of the custLongTextField13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField13() {
        return custLongTextField13;
    }

    /**
     * Sets the value of the custLongTextField13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField13(String value) {
        this.custLongTextField13 = value;
    }

    /**
     * Gets the value of the custLongTextField14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField14() {
        return custLongTextField14;
    }

    /**
     * Sets the value of the custLongTextField14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField14(String value) {
        this.custLongTextField14 = value;
    }

    /**
     * Gets the value of the custLongTextField15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField15() {
        return custLongTextField15;
    }

    /**
     * Sets the value of the custLongTextField15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField15(String value) {
        this.custLongTextField15 = value;
    }

    /**
     * Gets the value of the custLongTextField16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField16() {
        return custLongTextField16;
    }

    /**
     * Sets the value of the custLongTextField16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField16(String value) {
        this.custLongTextField16 = value;
    }

    /**
     * Gets the value of the custLongTextField17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField17() {
        return custLongTextField17;
    }

    /**
     * Sets the value of the custLongTextField17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField17(String value) {
        this.custLongTextField17 = value;
    }

    /**
     * Gets the value of the custLongTextField18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField18() {
        return custLongTextField18;
    }

    /**
     * Sets the value of the custLongTextField18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField18(String value) {
        this.custLongTextField18 = value;
    }

    /**
     * Gets the value of the custLongTextField19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField19() {
        return custLongTextField19;
    }

    /**
     * Sets the value of the custLongTextField19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField19(String value) {
        this.custLongTextField19 = value;
    }

    /**
     * Gets the value of the custLongTextField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField2() {
        return custLongTextField2;
    }

    /**
     * Sets the value of the custLongTextField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField2(String value) {
        this.custLongTextField2 = value;
    }

    /**
     * Gets the value of the custLongTextField20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField20() {
        return custLongTextField20;
    }

    /**
     * Sets the value of the custLongTextField20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField20(String value) {
        this.custLongTextField20 = value;
    }

    /**
     * Gets the value of the custLongTextField21 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField21() {
        return custLongTextField21;
    }

    /**
     * Sets the value of the custLongTextField21 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField21(String value) {
        this.custLongTextField21 = value;
    }

    /**
     * Gets the value of the custLongTextField22 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField22() {
        return custLongTextField22;
    }

    /**
     * Sets the value of the custLongTextField22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField22(String value) {
        this.custLongTextField22 = value;
    }

    /**
     * Gets the value of the custLongTextField23 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField23() {
        return custLongTextField23;
    }

    /**
     * Sets the value of the custLongTextField23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField23(String value) {
        this.custLongTextField23 = value;
    }

    /**
     * Gets the value of the custLongTextField24 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField24() {
        return custLongTextField24;
    }

    /**
     * Sets the value of the custLongTextField24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField24(String value) {
        this.custLongTextField24 = value;
    }

    /**
     * Gets the value of the custLongTextField25 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField25() {
        return custLongTextField25;
    }

    /**
     * Sets the value of the custLongTextField25 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField25(String value) {
        this.custLongTextField25 = value;
    }

    /**
     * Gets the value of the custLongTextField26 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField26() {
        return custLongTextField26;
    }

    /**
     * Sets the value of the custLongTextField26 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField26(String value) {
        this.custLongTextField26 = value;
    }

    /**
     * Gets the value of the custLongTextField27 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField27() {
        return custLongTextField27;
    }

    /**
     * Sets the value of the custLongTextField27 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField27(String value) {
        this.custLongTextField27 = value;
    }

    /**
     * Gets the value of the custLongTextField28 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField28() {
        return custLongTextField28;
    }

    /**
     * Sets the value of the custLongTextField28 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField28(String value) {
        this.custLongTextField28 = value;
    }

    /**
     * Gets the value of the custLongTextField29 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField29() {
        return custLongTextField29;
    }

    /**
     * Sets the value of the custLongTextField29 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField29(String value) {
        this.custLongTextField29 = value;
    }

    /**
     * Gets the value of the custLongTextField3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField3() {
        return custLongTextField3;
    }

    /**
     * Sets the value of the custLongTextField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField3(String value) {
        this.custLongTextField3 = value;
    }

    /**
     * Gets the value of the custLongTextField30 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField30() {
        return custLongTextField30;
    }

    /**
     * Sets the value of the custLongTextField30 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField30(String value) {
        this.custLongTextField30 = value;
    }

    /**
     * Gets the value of the custLongTextField31 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField31() {
        return custLongTextField31;
    }

    /**
     * Sets the value of the custLongTextField31 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField31(String value) {
        this.custLongTextField31 = value;
    }

    /**
     * Gets the value of the custLongTextField32 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField32() {
        return custLongTextField32;
    }

    /**
     * Sets the value of the custLongTextField32 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField32(String value) {
        this.custLongTextField32 = value;
    }

    /**
     * Gets the value of the custLongTextField33 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField33() {
        return custLongTextField33;
    }

    /**
     * Sets the value of the custLongTextField33 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField33(String value) {
        this.custLongTextField33 = value;
    }

    /**
     * Gets the value of the custLongTextField34 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField34() {
        return custLongTextField34;
    }

    /**
     * Sets the value of the custLongTextField34 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField34(String value) {
        this.custLongTextField34 = value;
    }

    /**
     * Gets the value of the custLongTextField35 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField35() {
        return custLongTextField35;
    }

    /**
     * Sets the value of the custLongTextField35 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField35(String value) {
        this.custLongTextField35 = value;
    }

    /**
     * Gets the value of the custLongTextField36 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField36() {
        return custLongTextField36;
    }

    /**
     * Sets the value of the custLongTextField36 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField36(String value) {
        this.custLongTextField36 = value;
    }

    /**
     * Gets the value of the custLongTextField37 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField37() {
        return custLongTextField37;
    }

    /**
     * Sets the value of the custLongTextField37 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField37(String value) {
        this.custLongTextField37 = value;
    }

    /**
     * Gets the value of the custLongTextField38 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField38() {
        return custLongTextField38;
    }

    /**
     * Sets the value of the custLongTextField38 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField38(String value) {
        this.custLongTextField38 = value;
    }

    /**
     * Gets the value of the custLongTextField39 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField39() {
        return custLongTextField39;
    }

    /**
     * Sets the value of the custLongTextField39 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField39(String value) {
        this.custLongTextField39 = value;
    }

    /**
     * Gets the value of the custLongTextField4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField4() {
        return custLongTextField4;
    }

    /**
     * Sets the value of the custLongTextField4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField4(String value) {
        this.custLongTextField4 = value;
    }

    /**
     * Gets the value of the custLongTextField40 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField40() {
        return custLongTextField40;
    }

    /**
     * Sets the value of the custLongTextField40 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField40(String value) {
        this.custLongTextField40 = value;
    }

    /**
     * Gets the value of the custLongTextField41 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField41() {
        return custLongTextField41;
    }

    /**
     * Sets the value of the custLongTextField41 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField41(String value) {
        this.custLongTextField41 = value;
    }

    /**
     * Gets the value of the custLongTextField42 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField42() {
        return custLongTextField42;
    }

    /**
     * Sets the value of the custLongTextField42 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField42(String value) {
        this.custLongTextField42 = value;
    }

    /**
     * Gets the value of the custLongTextField43 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField43() {
        return custLongTextField43;
    }

    /**
     * Sets the value of the custLongTextField43 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField43(String value) {
        this.custLongTextField43 = value;
    }

    /**
     * Gets the value of the custLongTextField44 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField44() {
        return custLongTextField44;
    }

    /**
     * Sets the value of the custLongTextField44 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField44(String value) {
        this.custLongTextField44 = value;
    }

    /**
     * Gets the value of the custLongTextField45 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField45() {
        return custLongTextField45;
    }

    /**
     * Sets the value of the custLongTextField45 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField45(String value) {
        this.custLongTextField45 = value;
    }

    /**
     * Gets the value of the custLongTextField46 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField46() {
        return custLongTextField46;
    }

    /**
     * Sets the value of the custLongTextField46 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField46(String value) {
        this.custLongTextField46 = value;
    }

    /**
     * Gets the value of the custLongTextField47 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField47() {
        return custLongTextField47;
    }

    /**
     * Sets the value of the custLongTextField47 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField47(String value) {
        this.custLongTextField47 = value;
    }

    /**
     * Gets the value of the custLongTextField48 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField48() {
        return custLongTextField48;
    }

    /**
     * Sets the value of the custLongTextField48 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField48(String value) {
        this.custLongTextField48 = value;
    }

    /**
     * Gets the value of the custLongTextField49 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField49() {
        return custLongTextField49;
    }

    /**
     * Sets the value of the custLongTextField49 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField49(String value) {
        this.custLongTextField49 = value;
    }

    /**
     * Gets the value of the custLongTextField5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField5() {
        return custLongTextField5;
    }

    /**
     * Sets the value of the custLongTextField5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField5(String value) {
        this.custLongTextField5 = value;
    }

    /**
     * Gets the value of the custLongTextField50 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField50() {
        return custLongTextField50;
    }

    /**
     * Sets the value of the custLongTextField50 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField50(String value) {
        this.custLongTextField50 = value;
    }

    /**
     * Gets the value of the custLongTextField6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField6() {
        return custLongTextField6;
    }

    /**
     * Sets the value of the custLongTextField6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField6(String value) {
        this.custLongTextField6 = value;
    }

    /**
     * Gets the value of the custLongTextField7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField7() {
        return custLongTextField7;
    }

    /**
     * Sets the value of the custLongTextField7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField7(String value) {
        this.custLongTextField7 = value;
    }

    /**
     * Gets the value of the custLongTextField8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField8() {
        return custLongTextField8;
    }

    /**
     * Sets the value of the custLongTextField8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField8(String value) {
        this.custLongTextField8 = value;
    }

    /**
     * Gets the value of the custLongTextField9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustLongTextField9() {
        return custLongTextField9;
    }

    /**
     * Sets the value of the custLongTextField9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustLongTextField9(String value) {
        this.custLongTextField9 = value;
    }

    /**
     * Gets the value of the custTextField1 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField1() {
        return custTextField1;
    }

    /**
     * Sets the value of the custTextField1 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField1(String value) {
        this.custTextField1 = value;
    }

    /**
     * Gets the value of the custTextField10 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField10() {
        return custTextField10;
    }

    /**
     * Sets the value of the custTextField10 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField10(String value) {
        this.custTextField10 = value;
    }

    /**
     * Gets the value of the custTextField11 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField11() {
        return custTextField11;
    }

    /**
     * Sets the value of the custTextField11 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField11(String value) {
        this.custTextField11 = value;
    }

    /**
     * Gets the value of the custTextField12 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField12() {
        return custTextField12;
    }

    /**
     * Sets the value of the custTextField12 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField12(String value) {
        this.custTextField12 = value;
    }

    /**
     * Gets the value of the custTextField13 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField13() {
        return custTextField13;
    }

    /**
     * Sets the value of the custTextField13 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField13(String value) {
        this.custTextField13 = value;
    }

    /**
     * Gets the value of the custTextField14 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField14() {
        return custTextField14;
    }

    /**
     * Sets the value of the custTextField14 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField14(String value) {
        this.custTextField14 = value;
    }

    /**
     * Gets the value of the custTextField15 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField15() {
        return custTextField15;
    }

    /**
     * Sets the value of the custTextField15 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField15(String value) {
        this.custTextField15 = value;
    }

    /**
     * Gets the value of the custTextField16 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField16() {
        return custTextField16;
    }

    /**
     * Sets the value of the custTextField16 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField16(String value) {
        this.custTextField16 = value;
    }

    /**
     * Gets the value of the custTextField17 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField17() {
        return custTextField17;
    }

    /**
     * Sets the value of the custTextField17 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField17(String value) {
        this.custTextField17 = value;
    }

    /**
     * Gets the value of the custTextField18 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField18() {
        return custTextField18;
    }

    /**
     * Sets the value of the custTextField18 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField18(String value) {
        this.custTextField18 = value;
    }

    /**
     * Gets the value of the custTextField19 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField19() {
        return custTextField19;
    }

    /**
     * Sets the value of the custTextField19 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField19(String value) {
        this.custTextField19 = value;
    }

    /**
     * Gets the value of the custTextField2 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField2() {
        return custTextField2;
    }

    /**
     * Sets the value of the custTextField2 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField2(String value) {
        this.custTextField2 = value;
    }

    /**
     * Gets the value of the custTextField20 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField20() {
        return custTextField20;
    }

    /**
     * Sets the value of the custTextField20 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField20(String value) {
        this.custTextField20 = value;
    }

    /**
     * Gets the value of the custTextField21 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField21() {
        return custTextField21;
    }

    /**
     * Sets the value of the custTextField21 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField21(String value) {
        this.custTextField21 = value;
    }

    /**
     * Gets the value of the custTextField22 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField22() {
        return custTextField22;
    }

    /**
     * Sets the value of the custTextField22 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField22(String value) {
        this.custTextField22 = value;
    }

    /**
     * Gets the value of the custTextField23 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField23() {
        return custTextField23;
    }

    /**
     * Sets the value of the custTextField23 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField23(String value) {
        this.custTextField23 = value;
    }

    /**
     * Gets the value of the custTextField24 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField24() {
        return custTextField24;
    }

    /**
     * Sets the value of the custTextField24 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField24(String value) {
        this.custTextField24 = value;
    }

    /**
     * Gets the value of the custTextField25 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField25() {
        return custTextField25;
    }

    /**
     * Sets the value of the custTextField25 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField25(String value) {
        this.custTextField25 = value;
    }

    /**
     * Gets the value of the custTextField26 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField26() {
        return custTextField26;
    }

    /**
     * Sets the value of the custTextField26 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField26(String value) {
        this.custTextField26 = value;
    }

    /**
     * Gets the value of the custTextField27 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField27() {
        return custTextField27;
    }

    /**
     * Sets the value of the custTextField27 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField27(String value) {
        this.custTextField27 = value;
    }

    /**
     * Gets the value of the custTextField28 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField28() {
        return custTextField28;
    }

    /**
     * Sets the value of the custTextField28 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField28(String value) {
        this.custTextField28 = value;
    }

    /**
     * Gets the value of the custTextField29 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField29() {
        return custTextField29;
    }

    /**
     * Sets the value of the custTextField29 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField29(String value) {
        this.custTextField29 = value;
    }

    /**
     * Gets the value of the custTextField3 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField3() {
        return custTextField3;
    }

    /**
     * Sets the value of the custTextField3 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField3(String value) {
        this.custTextField3 = value;
    }

    /**
     * Gets the value of the custTextField30 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField30() {
        return custTextField30;
    }

    /**
     * Sets the value of the custTextField30 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField30(String value) {
        this.custTextField30 = value;
    }

    /**
     * Gets the value of the custTextField31 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField31() {
        return custTextField31;
    }

    /**
     * Sets the value of the custTextField31 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField31(String value) {
        this.custTextField31 = value;
    }

    /**
     * Gets the value of the custTextField32 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField32() {
        return custTextField32;
    }

    /**
     * Sets the value of the custTextField32 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField32(String value) {
        this.custTextField32 = value;
    }

    /**
     * Gets the value of the custTextField33 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField33() {
        return custTextField33;
    }

    /**
     * Sets the value of the custTextField33 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField33(String value) {
        this.custTextField33 = value;
    }

    /**
     * Gets the value of the custTextField34 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField34() {
        return custTextField34;
    }

    /**
     * Sets the value of the custTextField34 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField34(String value) {
        this.custTextField34 = value;
    }

    /**
     * Gets the value of the custTextField35 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField35() {
        return custTextField35;
    }

    /**
     * Sets the value of the custTextField35 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField35(String value) {
        this.custTextField35 = value;
    }

    /**
     * Gets the value of the custTextField36 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField36() {
        return custTextField36;
    }

    /**
     * Sets the value of the custTextField36 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField36(String value) {
        this.custTextField36 = value;
    }

    /**
     * Gets the value of the custTextField37 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField37() {
        return custTextField37;
    }

    /**
     * Sets the value of the custTextField37 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField37(String value) {
        this.custTextField37 = value;
    }

    /**
     * Gets the value of the custTextField38 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField38() {
        return custTextField38;
    }

    /**
     * Sets the value of the custTextField38 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField38(String value) {
        this.custTextField38 = value;
    }

    /**
     * Gets the value of the custTextField39 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField39() {
        return custTextField39;
    }

    /**
     * Sets the value of the custTextField39 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField39(String value) {
        this.custTextField39 = value;
    }

    /**
     * Gets the value of the custTextField4 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField4() {
        return custTextField4;
    }

    /**
     * Sets the value of the custTextField4 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField4(String value) {
        this.custTextField4 = value;
    }

    /**
     * Gets the value of the custTextField40 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField40() {
        return custTextField40;
    }

    /**
     * Sets the value of the custTextField40 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField40(String value) {
        this.custTextField40 = value;
    }

    /**
     * Gets the value of the custTextField41 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField41() {
        return custTextField41;
    }

    /**
     * Sets the value of the custTextField41 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField41(String value) {
        this.custTextField41 = value;
    }

    /**
     * Gets the value of the custTextField42 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField42() {
        return custTextField42;
    }

    /**
     * Sets the value of the custTextField42 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField42(String value) {
        this.custTextField42 = value;
    }

    /**
     * Gets the value of the custTextField43 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField43() {
        return custTextField43;
    }

    /**
     * Sets the value of the custTextField43 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField43(String value) {
        this.custTextField43 = value;
    }

    /**
     * Gets the value of the custTextField44 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField44() {
        return custTextField44;
    }

    /**
     * Sets the value of the custTextField44 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField44(String value) {
        this.custTextField44 = value;
    }

    /**
     * Gets the value of the custTextField45 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField45() {
        return custTextField45;
    }

    /**
     * Sets the value of the custTextField45 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField45(String value) {
        this.custTextField45 = value;
    }

    /**
     * Gets the value of the custTextField46 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField46() {
        return custTextField46;
    }

    /**
     * Sets the value of the custTextField46 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField46(String value) {
        this.custTextField46 = value;
    }

    /**
     * Gets the value of the custTextField47 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField47() {
        return custTextField47;
    }

    /**
     * Sets the value of the custTextField47 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField47(String value) {
        this.custTextField47 = value;
    }

    /**
     * Gets the value of the custTextField48 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField48() {
        return custTextField48;
    }

    /**
     * Sets the value of the custTextField48 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField48(String value) {
        this.custTextField48 = value;
    }

    /**
     * Gets the value of the custTextField49 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField49() {
        return custTextField49;
    }

    /**
     * Sets the value of the custTextField49 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField49(String value) {
        this.custTextField49 = value;
    }

    /**
     * Gets the value of the custTextField5 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField5() {
        return custTextField5;
    }

    /**
     * Sets the value of the custTextField5 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField5(String value) {
        this.custTextField5 = value;
    }

    /**
     * Gets the value of the custTextField50 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField50() {
        return custTextField50;
    }

    /**
     * Sets the value of the custTextField50 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField50(String value) {
        this.custTextField50 = value;
    }

    /**
     * Gets the value of the custTextField6 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField6() {
        return custTextField6;
    }

    /**
     * Sets the value of the custTextField6 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField6(String value) {
        this.custTextField6 = value;
    }

    /**
     * Gets the value of the custTextField7 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField7() {
        return custTextField7;
    }

    /**
     * Sets the value of the custTextField7 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField7(String value) {
        this.custTextField7 = value;
    }

    /**
     * Gets the value of the custTextField8 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField8() {
        return custTextField8;
    }

    /**
     * Sets the value of the custTextField8 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField8(String value) {
        this.custTextField8 = value;
    }

    /**
     * Gets the value of the custTextField9 property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCustTextField9() {
        return custTextField9;
    }

    /**
     * Sets the value of the custTextField9 property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCustTextField9(String value) {
        this.custTextField9 = value;
    }

    /**
     * Gets the value of the customDateFields property.
     * 
     * @return
     *     possible object is
     *     {@link ApiCI.CustomDateFields }
     *     
     */
    public ApiCI.CustomDateFields getCustomDateFields() {
        return customDateFields;
    }

    /**
     * Sets the value of the customDateFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiCI.CustomDateFields }
     *     
     */
    public void setCustomDateFields(ApiCI.CustomDateFields value) {
        this.customDateFields = value;
    }

    /**
     * Gets the value of the customFields property.
     * 
     * @return
     *     possible object is
     *     {@link ApiCI.CustomFields }
     *     
     */
    public ApiCI.CustomFields getCustomFields() {
        return customFields;
    }

    /**
     * Sets the value of the customFields property.
     * 
     * @param value
     *     allowed object is
     *     {@link ApiCI.CustomFields }
     *     
     */
    public void setCustomFields(ApiCI.CustomFields value) {
        this.customFields = value;
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
     * Gets the value of the owner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Sets the value of the owner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwner(String value) {
        this.owner = value;
    }

    /**
     * Gets the value of the ownerGroup property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerGroup() {
        return ownerGroup;
    }

    /**
     * Sets the value of the ownerGroup property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerGroup(String value) {
        this.ownerGroup = value;
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
     * Gets the value of the serialNumber property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSerialNumber() {
        return serialNumber;
    }

    /**
     * Sets the value of the serialNumber property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSerialNumber(String value) {
        this.serialNumber = value;
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
     * Gets the value of the supplier property.
     * 
     */
    public int getSupplier() {
        return supplier;
    }

    /**
     * Sets the value of the supplier property.
     * 
     */
    public void setSupplier(int value) {
        this.supplier = value;
    }

    /**
     * Gets the value of the supplyDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getSupplyDate() {
        return supplyDate;
    }

    /**
     * Sets the value of the supplyDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setSupplyDate(XMLGregorianCalendar value) {
        this.supplyDate = value;
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
     * Gets the value of the users property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsers() {
        return users;
    }

    /**
     * Sets the value of the users property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsers(String value) {
        this.users = value;
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

        protected List<ApiCI.CustomDateFields.Entry> entry;

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
         * {@link ApiCI.CustomDateFields.Entry }
         * 
         * 
         */
        public List<ApiCI.CustomDateFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiCI.CustomDateFields.Entry>();
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

        protected List<ApiCI.CustomFields.Entry> entry;

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
         * {@link ApiCI.CustomFields.Entry }
         * 
         * 
         */
        public List<ApiCI.CustomFields.Entry> getEntry() {
            if (entry == null) {
                entry = new ArrayList<ApiCI.CustomFields.Entry>();
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
