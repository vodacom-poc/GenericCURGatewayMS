//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.05.09 at 02:41:00 PM IST 
//


package com.vodacom.GenericCURGatewayMS.domain;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for CURAttribute complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CURAttribute"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="KeyName" type="{http://www.w3.org/2001/XMLSchema}string" form="qualified"/&gt;
 *         &lt;element name="KeyValue" type="{http://www.w3.org/2001/XMLSchema}string" maxOccurs="unbounded" form="qualified"/&gt;
 *         &lt;element name="ModOperation" type="{http://www.vodacom.co.za/}modOperation" minOccurs="0" form="qualified"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CURAttribute", namespace = "java:net.voda.curgw.io", propOrder = {
    "keyName",
    "keyValue",
    "modOperation"
})
public class CURAttribute {

    @XmlElement(name = "KeyName", namespace = "java:net.voda.curgw.io", required = true)
    protected String keyName;
    @XmlElement(name = "KeyValue", namespace = "java:net.voda.curgw.io", required = true)
    protected List<String> keyValue;
    @XmlElement(name = "ModOperation", namespace = "java:net.voda.curgw.io")
    @XmlSchemaType(name = "string")
    protected ModOperation modOperation;

    /**
     * Gets the value of the keyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getKeyName() {
        return keyName;
    }

    /**
     * Sets the value of the keyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setKeyName(String value) {
        this.keyName = value;
    }

    /**
     * Gets the value of the keyValue property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the keyValue property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getKeyValue().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link String }
     * 
     * 
     */
    public List<String> getKeyValue() {
        if (keyValue == null) {
            keyValue = new ArrayList<String>();
        }
        return this.keyValue;
    }

    /**
     * Gets the value of the modOperation property.
     * 
     * @return
     *     possible object is
     *     {@link ModOperation }
     *     
     */
    public ModOperation getModOperation() {
        return modOperation;
    }

    /**
     * Sets the value of the modOperation property.
     * 
     * @param value
     *     allowed object is
     *     {@link ModOperation }
     *     
     */
    public void setModOperation(ModOperation value) {
        this.modOperation = value;
    }

}