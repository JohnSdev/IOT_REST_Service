/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RESTDemo1;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.BufferedReader;
import java.io.FileReader;
import com.google.gson.reflect.TypeToken;

import java.io.FileWriter;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Xeno
 */
public class DeviceSql {
    //Reads from file, not used in this version
    public List<devices> getAllDevices(){
    List<devices> deviceList = new ArrayList<>();
    String deviceListPath = "c:\\REST\\devices.json";
    

     
    try(BufferedReader br = new  BufferedReader(new FileReader(deviceListPath));){
        Type listType = new TypeToken<ArrayList<devices>>(){}.getType();
        deviceList = new Gson().fromJson(br, listType);
    }
    catch(Exception x){
        x.printStackTrace();
    }
    return deviceList;
    }
    
    //Get values from SQL
    public List<devices> getFromSql(String username, String password, int count){
        List<devices> deviceList = new ArrayList<>();
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/climate", username, password);
            Statement stmt = con.createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM climatedevice order by id desc limit 7");
         
                while (res.next() && count >0){
                   
                    devices d1 = new devices();

                    d1.setId(res.getInt("id"));

                    d1.setTemp(res.getDouble("temp"));
                    d1.setHumidity(res.getInt("humidity"));
                    d1.setLight(res.getInt("light"));
                    d1.setWatt(res.getDouble("watt"));
                    d1.setDatetime(res.getString("timestamp"));
                    deviceList.add(d1);
                    count--;
                }
        }
        catch (Exception ex){
            System.out.println(ex);    
        }
        return deviceList;
    }
    
    //Get values from SQL 7 days back. Stores in weekRepport class, depending on choice input fr ex: Temp
    public List<weekRepport> getFromSqlReport(String username, String password, int count, String choice){
        String selection = choice;
        List<weekRepport> deviceListSql = new ArrayList<>();
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/climate", username, password);
            Statement stmt = con.createStatement();
            String sqlCall = String.format("SELECT %s FROM climatedevice order by id desc limit 7", selection);
            ResultSet res = stmt.executeQuery(sqlCall);
         
                while (res.next() && count >0){
                   
                    weekRepport d1 = new weekRepport();

                    d1.setValue(res.getInt(selection));
                    d1.setType(selection);
                    //d1.setValue(res.getInt("humidity"));
                    //d1.setValue(res.getInt("light"));
                    //d1.setValue(res.getInt("watt"));

                    deviceListSql.add(d1);
                    count--;
                }
        }
        catch (Exception ex){
            System.out.println(ex);    
        }
        return deviceListSql;
    }
    
    //Creates timestamp for SQL
    private static java.sql.Timestamp getCurrentTimeStamp() {

    java.util.Date today = new java.util.Date();
    return new java.sql.Timestamp(today.getTime());

    }
    
    //Insert manual log into SQL
    public int postToSql(String username, String password, SimulatedDevice climate){
        List<devices> deviceList = new ArrayList<>();
        int a=0;
        
        try{
        Class.forName("com.mysql.jdbc.Driver");
        String insertString = String.format("INSERT INTO climatedevice "
                + "(temp, humidity, light, watt, timestamp, manual) VALUES (?, ?, ?, ?, ?, ?)");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/climate", username, password);
            PreparedStatement stmt = con.prepareStatement(insertString);
            stmt.setDouble(1,  climate.getTemp());
            stmt.setInt(2, climate.getHumidity());
            stmt.setInt(3, climate.getLight());
            stmt.setDouble(4, climate.getWatt());
            stmt.setTimestamp(5,getCurrentTimeStamp());
            stmt.setInt(6, 1);
            a = stmt.executeUpdate(); 
        }
        catch (Exception ex){
            System.out.println(ex);    
        }
        return a;
        
    }

    /*
        public int getDeviceId(){
        devices d1 = new devices();
        return d1.getId();
    }*/
     
    //File writer not used in this version.
    public void writeToFile(List<devices> allDev){
        GsonBuilder builder = new GsonBuilder(); 
        builder.setPrettyPrinting(); 
      
        Gson gson = builder.create(); 
        String gsonList = gson.toJson(allDev);    

        try (FileWriter writer = new FileWriter("c:\\REST\\devices.json");){
            writer.write(gsonList);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

}
