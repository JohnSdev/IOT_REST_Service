/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTDemo1;

import java.io.Serializable;

/**
 *
 * @author Xeno
 */
public class SimulatedDevice implements Serializable{
    private static final long SerialVersionUID = 1L;
  
    private int humidity;
    private double temp;
    private int light;
    private int id;
    private double watt=5.5;
    private String datetime;
    
    private int setHumidity;
    private int setTemp;
    private int setLight;
    
    


    public SimulatedDevice(double temp, int humidity, int light){
        this.temp=temp;
        this.humidity=humidity;
        this.light=light;
        
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
