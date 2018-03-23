
package by.epam.soapclient;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the example package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetPriceByID_QNAME = new QName("http://example/", "getPriceByID");
    private final static QName _GetPriceByIDResponse_QNAME = new QName("http://example/", "getPriceByIDResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: example
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetPriceByIDResponse }
     */
    public GetPriceByIDResponse createGetPriceByIDResponse() {
        return new GetPriceByIDResponse();
    }

    /**
     * Create an instance of {@link GetPriceByID }
     */
    public GetPriceByID createGetPriceByID() {
        return new GetPriceByID();
    }

    /**
     * Create an instance of {@link Price }
     */
    public Price createPrice() {
        return new Price();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPriceByID }{@code >}}
     */
    @XmlElementDecl(namespace = "http://example/", name = "getPriceByID")
    public JAXBElement<GetPriceByID> createGetPriceByID(GetPriceByID value) {
        return new JAXBElement<GetPriceByID>(_GetPriceByID_QNAME, GetPriceByID.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetPriceByIDResponse }{@code >}}
     */
    @XmlElementDecl(namespace = "http://example/", name = "getPriceByIDResponse")
    public JAXBElement<GetPriceByIDResponse> createGetPriceByIDResponse(GetPriceByIDResponse value) {
        return new JAXBElement<GetPriceByIDResponse>(_GetPriceByIDResponse_QNAME, GetPriceByIDResponse.class, null, value);
    }

}
