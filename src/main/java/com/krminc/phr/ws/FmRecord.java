/**
 * Copyright (C) 2012 KRM Associates, Inc. healtheme@krminc.com
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.krminc.phr.ws;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for fmRecord complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="fmRecord">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="IEN" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="IENS" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="parent" type="{http://service.ws.ovid.ccr.medsphere.com/}fmRecord" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "fmRecord", propOrder = {
    "ien",
    "iens",
    "parent"
})
@XmlSeeAlso({
    FMPatient.class
})
public class FmRecord {

    @XmlElement(name = "IEN")
    protected String ien;
    @XmlElement(name = "IENS")
    protected String iens;
    protected FmRecord parent;

    /**
     * Gets the value of the ien property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIEN() {
        return ien;
    }

    /**
     * Sets the value of the ien property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIEN(String value) {
        this.ien = value;
    }

    /**
     * Gets the value of the iens property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIENS() {
        return iens;
    }

    /**
     * Sets the value of the iens property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIENS(String value) {
        this.iens = value;
    }

    /**
     * Gets the value of the parent property.
     * 
     * @return
     *     possible object is
     *     {@link FmRecord }
     *     
     */
    public FmRecord getParent() {
        return parent;
    }

    /**
     * Sets the value of the parent property.
     * 
     * @param value
     *     allowed object is
     *     {@link FmRecord }
     *     
     */
    public void setParent(FmRecord value) {
        this.parent = value;
    }

}
