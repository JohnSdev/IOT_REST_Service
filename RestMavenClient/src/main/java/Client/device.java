/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement(name="device")




/**
 *
 * @author Xeno
 */
public class device implements Serializable{
    private static final long SerialVersionUID = 1L;
    private String name;
    private String type;
    private String temp;
    private String hum;
    private String light;
    private double watt;
    private int humidity;
    
    private int id;
    
    
    
    public device(){}
    
    public device(int id, String name, String type){
        this.id=id;
        this.name=name;
        this.type=type;
        
        
    }
    

   
    

        

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

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

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    @XmlElement
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the temp
     */
    public String getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(String temp) {
        this.temp = temp;
    }

    /**
     * @return the hum
     */
    public String getHum() {
        return hum;
    }

    /**
     * @param hum the hum to set
     */
    public void setHum(String hum) {
        this.hum = hum;
    }

    /**
     * @return the light
     */
    public String getLight() {
        return light;
    }

    /**
     * @param light the light to set
     */
    public void setLight(String light) {
        this.light = light;
    }

    /**
     * @return the humidity
     */
    public int getHumidity() {
        return humidity;
    }

    /**
     * @param humidity the humidity to set
     */
    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }

    /**
     * @return the watt
     */
    public double getWatt() {
        return watt;
    }

    /**
     * @param watt the watt to set
     */
    public void setWatt(double watt) {
        this.setWatt(watt);
    }


}

