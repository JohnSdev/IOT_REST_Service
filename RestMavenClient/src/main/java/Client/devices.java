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
public class devices implements Serializable{
    private static final long SerialVersionUID = 1L;
  
    private int humidity;
    private double temp;
    private int light;
    private int id;
    private double watt;
    private String datetime;
    
    private int setHumidity;
    private int setTemp;
    private int setLight;
    
    
    public devices(){}
    
  
    

   
  

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
     * @return the temp
     */
    public double getTemp() {
        return temp;
    }

    /**
     * @param temp the temp to set
     */
    public void setTemp(double temp) {
        this.temp = temp;
    }

    /**
     * @return the light
     */
    public int getLight() {
        return light;
    }

    /**
     * @param light the light to set
     */
    public void setLight(int light) {
        this.light = light;
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
        this.watt = watt;
    }

    /**
     * @return the datetime
     */
    public String getDatetime() {
        return datetime;
    }

    /**
     * @param datetime the datetime to set
     */
    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }

    /**
     * @return the setHumidity
     */
    public int getSetHumidity() {
        return setHumidity;
    }

    /**
     * @param setHumidity the setHumidity to set
     */
    public void setSetHumidity(int setHumidity) {
        this.setHumidity = setHumidity;
    }

    /**
     * @return the setTemp
     */
    public int getSetTemp() {
        return setTemp;
    }

    /**
     * @param setTemp the setTemp to set
     */
    public void setSetTemp(int setTemp) {
        this.setTemp = setTemp;
    }

    /**
     * @return the setLight
     */
    public int getSetLight() {
        return setLight;
    }

    /**
     * @param setLight the setLight to set
     */
    public void setSetLight(int setLight) {
        this.setLight = setLight;
    }
}

