/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTDemo1;

import static java.lang.Boolean.TRUE;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import static javax.ws.rs.HttpMethod.POST;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/iot")


/**
 *
 * @author Xeno
 * main class for RESP API
 */
public class iot{
    //Creates simulated climate control to get actual reading from sensors. Stores desired valuem ans settings from userinput aswell
    private static SimulatedDevice SimDev = new SimulatedDevice(20, 34, 555);
    private static DeviceSql devicelist = new DeviceSql();
    private static List<devices> allDev = devicelist.getAllDevices();
    private static List<devices> allDevSql = devicelist.getFromSql("Recon", "Test", 7);

    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
        public List<devices> getAllDevices(){
        return allDev;
    }
        
    @GET
    @Path("/listSql")
    @Produces(MediaType.APPLICATION_JSON)
        public List<devices> getAllDevicesSql(){
        return allDevSql;
    }
    
    
    @GET
    @Path("/weekRepport/{choice}")
    @Produces(MediaType.APPLICATION_JSON)
        public List<weekRepport> getTempRepport(@PathParam("choice") String choice){
        List<weekRepport> temp = devicelist.getFromSqlReport("Recon", "Test", 7, choice); 
        if (temp.isEmpty()){
            weekRepport r1 = new weekRepport();
            r1.setType("No SQL Connectiopn");
            temp.add(r1);
            return temp;
        }

        return temp;
    }  
    
        //Gets actual readings from simulated device
    @GET
    @Path("/getClimate")
    @Produces(MediaType.APPLICATION_JSON)
        public SimulatedDevice getClimateDevice(){
        return SimDev;
    }    
    @GET
    @Path("/currentTemp")
    @Produces(MediaType.APPLICATION_JSON)
        public SimulatedDevice getClimateTemp(){
        return SimDev;
    }    
        
    //For future IOT USe    
    @GET
    @Path("/device/{id}")
    @Produces(MediaType.APPLICATION_JSON)
        public devices getDevices(@PathParam("id") int id){
        devices response = new devices();
        for (devices d: devicelist.getAllDevices()){
            if (d.getId() == id){
                response = d;
            }
            
        }
        return response;
        
      
    }
    //For future IOT USe    
    @GET
    @Path("/device/{id}/delete")
    @Produces(MediaType.APPLICATION_JSON)
        public Response deleteDevices(@PathParam("id") int id){
        Response res = new Response("Device deleted", id , Boolean.FALSE);

        int index = -1;
        for (int i = 0; i < allDev.size(); i++){
            if (allDev.get(i).getId() == id){
                index = i;
            }
        }
        if (index != -1){
            allDev.remove(index);
            
            res.setStatus(Boolean.TRUE);
        }
        return res;
    
}
    //To set new desired climate values on the climate device    
    @POST
    @Path("/{param}/{value}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDevice(@PathParam("param") String param, @PathParam("value") int value){
       Response res2 = new Response("New Temperature set at: {value}", value, Boolean.FALSE);

        if (param.contains("temp")){ //Set temp
            SimDev.setSetTemp(value);
            Response res = new Response("New Temperature set: ", value, Boolean.FALSE);
            res.setStatus(Boolean.TRUE); 
            //devicelist.writeToFile(allDev);
            return res;
        }
        else if (param.contains("humidity")){ //Set temp
            SimDev.setSetHumidity(value);
            Response res = new Response("New Humidity set: ", value, Boolean.FALSE);
            res.setStatus(Boolean.TRUE); 
            //devicelist.writeToFile(allDev);
            return res;
        }            
        else if (param.contains("light")){ //Set temp
            SimDev.setSetLight(value);
            Response res = new Response("New Light set: ", value, Boolean.FALSE);
            res.setStatus(Boolean.TRUE); 
            //devicelist.writeToFile(allDev);
            return res;
        }
        else return res2;

    }
    
    //To log actual readings from the device into the SQL databse
    @POST
    @Path("/manualLog")
    @Produces(MediaType.APPLICATION_JSON)
    public Response manualLog(){
    Response res = new Response("New Values written to Sql: ", 0, Boolean.FALSE);
    int sqlResult=devicelist.postToSql("Recon", "Test", SimDev); 
    if ( sqlResult != 0){
        res.setStatus(Boolean.TRUE); 
    }
    res.setStatus(Boolean.FALSE); 
    return res;
    }
}