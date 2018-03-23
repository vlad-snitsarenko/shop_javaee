
package by.epam.soapclient;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for price complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="price">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="p_id" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="value" type="{http://www.w3.org/2001/XMLSchema}double"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "price", propOrder = {
        "pId",
        "value"
})
public class Price {

    @XmlElement(name = "p_id")
    protected int pId;
    protected double value;

    /**
     * Gets the value of the pId property.
     */
    public int getPId() {
        return pId;
    }

    /**
     * Sets the value of the pId property.
     */
    public void setPId(int value) {
        this.pId = value;
    }

    /**
     * Gets the value of the value property.
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets the value of the value property.
     */
    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Price{" +
                "pId=" + pId +
                ", value=" + value +
                '}';
    }
}
