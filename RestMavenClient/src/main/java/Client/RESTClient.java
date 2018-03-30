/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Client;
import com.google.gson.Gson;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriBuilder;
import java.util.Scanner;


/**
 *
 * @author Xeno
 */
public class RESTClient {
    private static ClientConfig config = new DefaultClientConfig();
  z
   
    private static final com.sun.jersey.api.client.Client client = Client.create(config);
  
   
    

    private static WebResource service = client.resource( 
    UriBuilder.fromUri("http://localhost:8080/RESTDemo1/").build());
    
    
    public static Scanner scanner = new Scanner(System.in);
    
    public static void mainMenu(){
                System.out.println("IOT Climate Control");
        System.out.println("MENU");
        System.out.println("Press [1] To read temperature");
        System.out.println("Press [2] To read humidity");
        System.out.println("Press [3] To read lights status");
        System.out.println("Press [4] To read power outtage for the last 24hrs");
        System.out.println("Press [5] To Set new Temperature");
        System.out.println("Press [6] To set new humidity");
        System.out.println("Press [7] To set light sensitivity");
        System.out.println("Press [8] To get a current climate repport");
        System.out.println("Press [9] To Temp repport for last 7 days");
        System.out.println("Press [10] To Humidity repport for last 7 days");
        System.out.println("Press [11] To Lights repport for last 7 days");
        System.out.println("Press [12] Show Power costs for th last 7 days");
        System.out.println("Press [13] To log all values manualy to the Database");
    }
    
    public static void main(String[] args) throws IOException{
        
        
        
               
         int val = 0 ;
         while( val != 15){
         mainMenu();    
         val = scanner.nextInt();
        switch(val){
            
            case 1:
                //Read Temp
                System.out.println("Reading Temperature");
                currentTemp();
                break;
            case 2:
                
                currentHumidity();
                break;
            case 3:
                currentLight();
                break;
            case 4:
                currentWatt();
                break;
            case 5:
                //Sets new paramaeter
                
                System.out.println("Enter new Temp value:");
                int valueTemp = scanner.nextInt();
                newParam("temp", valueTemp);
                break;
            case 6:
                System.out.println("Enter new Humidity value:");
                int valueHum = scanner.nextInt();
                newParam("temp", valueHum);
                break;
             case 7:
                System.out.println("Enter new Light value:");
                int valueLight = scanner.nextInt();
                newParam("temp", valueLight);
                break;
            case 8:
                //Lista alla kompisar
                currentValues();
                break;
            case 9:
                //Lista alla kompisar
                repportWeek("Temp");
                break;    
            case 10:
                //Lista alla kompisar
                repportWeek("humidity");
                break; 
            case 11:
                //Lista alla kompisar
                repportWeek("light");
                break;  
            case 12:
                //Lista alla kompisar
                repportWeek("watt");
                break;
            case 13:
                //Lista alla kompisar
                manualLog();
                break;            
             default:
        }  
        
        }
    }
    
    public static void viewTemp(){
         // getting one kompis
        String jsonKompis = service.path("rest/iot/device/1/readtemp")
                
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println();
        
        device device = new Gson().fromJson(jsonKompis, device.class);
        System.out.println("Current temp: " + device.getTemp() + "\n");
    }
    
    public static void putFriend(int id, String namn, String type){
         device device = new device(id, namn, type);
         ClientResponse response = service.path("rest/iot/device/add")
                 .accept(MediaType.APPLICATION_JSON).post(ClientResponse.class, device);
         
         System.out.println("Response " + response.getEntity(String.class));
    }
    
    public static void currentTemp(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String currTemp = service.path("rest/iot/getClimate")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        device device = new Gson().fromJson(currTemp, device.class);
     
        System.out.println(device.getTemp());        
    }
    
    public static void currentLight(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String currTemp = service.path("rest/iot/getClimate")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        device device = new Gson().fromJson(currTemp, device.class);
     
        System.out.println(device.getLight());       
    }
    public static void currentHumidity(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String currTemp = service.path("rest/iot/getClimate")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        device device = new Gson().fromJson(currTemp, device.class);
     
        System.out.println(device.getHumidity()); 
    }
    
        public static void currentWatt(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String currTemp = service.path("rest/iot/getClimate")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        device device = new Gson().fromJson(currTemp, device.class);
     
        System.out.println(device.getWatt() ); 
    }
    
    
    public static void currentPowerDay(){
    
    }
    public static void newParam(String param, int value){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String post = String.format("rest/iot/%s/%s", param, value);
        
        String setResponse = service.path(post)
                .accept(MediaType.APPLICATION_JSON).post(String.class);
        System.out.println(setResponse);
    }        
    

    
   
    
    public static void currentValues(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
    
        String listDevices = service.path("rest/iot/getClimate")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(listDevices);
    }
    
    public static void repportHumidity(){
    
    } 
    public static void repportWeek(String choice){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        
        String formatted = String.format("rest/iot/weekRepport/%s", choice);
        List<weekRepport> lista = service.path(formatted)
                .accept(MediaType.APPLICATION_JSON).get(new GenericType<List<weekRepport>>(){});
        List<Double> meanWeek = new ArrayList<>();
        Double mean=0.0;
        for (weekRepport item : lista){
            mean += item.getValue();
            meanWeek.add(item.getValue());
            System.out.println(item.getType() + " " + item.getValue());

        }
        if ( choice.contains("watt")){
           System.out.println("Average " + lista.get(0).getType() + " :" +  mean/meanWeek.size() + "\n\n");
           double count=0;
           for (weekRepport rep: lista){
               count += rep.getValue();
               
           }
           double sum = count/1000;
               System.out.println("Ange aktuell kWh kostnad: ");
               double kost = scanner.nextDouble();
               kost = (kost* sum);
               System.out.println("Elkostnaden för de senaste 7 dygn är " + kost + "Kr");
        }
        else{
        System.out.println("Average " + lista.get(0).getType() + " :" +  mean/meanWeek.size() + "\n\n");
        }
    }
    
    
    public static void manualLog(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String manualLog = service.path("rest/iot/manualLog")
            .accept(MediaType.APPLICATION_JSON).post(String.class);
        System.out.println(manualLog);
    } 
    public static void repportPoerCost(){
    
    } 
        
    public static void listAllFriend(){
        client.addFilter(new HTTPBasicAuthFilter("master", "pazz"));
        String listDevices = service.path("rest/iot/list")
                .accept(MediaType.APPLICATION_JSON).get(String.class);
        System.out.println(listDevices);
    }
        
        
        
    }
    
    
