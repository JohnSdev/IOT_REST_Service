/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTDemo1;

import java.io.Serializable;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="weekRepport")
/**
 *
 * @author Xeno
 */
public class weekRepport  implements Serializable{
    private double value;
    private String type;
    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    
    public weekRepport(){}

    /**
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * @param value the value to set
     */
    public void setValue(double value) {
        this.value = value;
    }
    
}
