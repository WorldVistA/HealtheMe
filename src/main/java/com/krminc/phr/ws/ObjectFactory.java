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

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.krminc.phr.ws package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");
    private final static QName _Boolean_QNAME = new QName("http://tempuri.org/", "boolean");
    private final static QName _ArrayOfFMPatient_QNAME = new QName("http://tempuri.org/", "ArrayOfFMPatient");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.krminc.phr.ws
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link CCRServiceLogin }
     * 
     */
    public CCRServiceLogin createCCRServiceLogin() {
        return new CCRServiceLogin();
    }

    /**
     * Create an instance of {@link ArrayOfFMPatient }
     * 
     */
    public ArrayOfFMPatient createArrayOfFMPatient() {
        return new ArrayOfFMPatient();
    }

    /**
     * Create an instance of {@link LookupPatientsByName }
     * 
     */
    public LookupPatientsByName createLookupPatientsByName() {
        return new LookupPatientsByName();
    }

    /**
     * Create an instance of {@link GetAllPatients }
     * 
     */
    public GetAllPatients createGetAllPatients() {
        return new GetAllPatients();
    }

    /**
     * Create an instance of {@link FMPatient }
     * 
     */
    public FMPatient createFMPatient() {
        return new FMPatient();
    }

    /**
     * Create an instance of {@link GetCCRForPatientDFNResponse }
     * 
     */
    public GetCCRForPatientDFNResponse createGetCCRForPatientDFNResponse() {
        return new GetCCRForPatientDFNResponse();
    }

    /**
     * Create an instance of {@link LookupPatientsByIDResponse }
     * 
     */
    public LookupPatientsByIDResponse createLookupPatientsByIDResponse() {
        return new LookupPatientsByIDResponse();
    }

    /**
     * Create an instance of {@link GetCCDForPatientDFNResponse }
     * 
     */
    public GetCCDForPatientDFNResponse createGetCCDForPatientDFNResponse() {
        return new GetCCDForPatientDFNResponse();
    }

    /**
     * Create an instance of {@link GetCCDForPatientDFN }
     * 
     */
    public GetCCDForPatientDFN createGetCCDForPatientDFN() {
        return new GetCCDForPatientDFN();
    }

    /**
     * Create an instance of {@link LookupPatientsByNameResponse }
     * 
     */
    public LookupPatientsByNameResponse createLookupPatientsByNameResponse() {
        return new LookupPatientsByNameResponse();
    }

    /**
     * Create an instance of {@link GetXSLFormattedCCRForPatientDFN }
     * 
     */
    public GetXSLFormattedCCRForPatientDFN createGetXSLFormattedCCRForPatientDFN() {
        return new GetXSLFormattedCCRForPatientDFN();
    }

    /**
     * Create an instance of {@link LookupPatientsByID }
     * 
     */
    public LookupPatientsByID createLookupPatientsByID() {
        return new LookupPatientsByID();
    }

    /**
     * Create an instance of {@link FmRecord }
     * 
     */
    public FmRecord createFmRecord() {
        return new FmRecord();
    }

    /**
     * Create an instance of {@link GetCCRForPatientDFN }
     * 
     */
    public GetCCRForPatientDFN createGetCCRForPatientDFN() {
        return new GetCCRForPatientDFN();
    }

    /**
     * Create an instance of {@link GetAllPatientsResponse }
     * 
     */
    public GetAllPatientsResponse createGetAllPatientsResponse() {
        return new GetAllPatientsResponse();
    }

    /**
     * Create an instance of {@link GetXSLFormattedCCDForPatientDFNResponse }
     * 
     */
    public GetXSLFormattedCCDForPatientDFNResponse createGetXSLFormattedCCDForPatientDFNResponse() {
        return new GetXSLFormattedCCDForPatientDFNResponse();
    }

    /**
     * Create an instance of {@link GetXSLFormattedCCRForPatientDFNResponse }
     * 
     */
    public GetXSLFormattedCCRForPatientDFNResponse createGetXSLFormattedCCRForPatientDFNResponse() {
        return new GetXSLFormattedCCRForPatientDFNResponse();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link GetXSLFormattedCCDForPatientDFN }
     * 
     */
    public GetXSLFormattedCCDForPatientDFN createGetXSLFormattedCCDForPatientDFN() {
        return new GetXSLFormattedCCDForPatientDFN();
    }

    /**
     * Create an instance of {@link CCRServiceLoginResponse }
     * 
     */
    public CCRServiceLoginResponse createCCRServiceLoginResponse() {
        return new CCRServiceLoginResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Boolean }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "boolean")
    public JAXBElement<Boolean> createBoolean(Boolean value) {
        return new JAXBElement<Boolean>(_Boolean_QNAME, Boolean.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfFMPatient }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ArrayOfFMPatient")
    public JAXBElement<ArrayOfFMPatient> createArrayOfFMPatient(ArrayOfFMPatient value) {
        return new JAXBElement<ArrayOfFMPatient>(_ArrayOfFMPatient_QNAME, ArrayOfFMPatient.class, null, value);
    }

}
