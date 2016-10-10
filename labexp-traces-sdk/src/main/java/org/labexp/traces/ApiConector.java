package org.labexp.traces;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.config.DefaultClientConfig;

import com.sun.jersey.api.json.JSONConfiguration;
import com.sun.jersey.api.client.config.ClientConfig;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.json.JSONException;
import org.json.JSONObject;




/**
 * Hello world!
 *
 */
public class ApiConector

{
	public static Client client;
	
	private static String apiBaseUrl = "http://10.173.1.153"; 
	private static String dateFormat = "MM/dd/yyyy HH:mm:ss"; 
	
	private static String postStartTrace = "/buses/v0.1/trace";

	
	
	
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        createClient(); 
        System.out.print(startTrace("Leo-Test1")); 
    }
    
    public static void createClient() {
		ClientConfig clientConfig = new DefaultClientConfig();
        clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
        client = Client.create(clientConfig);
    }
    
    public static String startTrace(String deviceId) {
    	
    	//Creates a webResource with the API url to post start a trace
    	WebResource webResource = client.resource(apiBaseUrl+postStartTrace);
    	
    	//Building the post content 
    	String jsonInput = "";
    	try {
			jsonInput = new JSONObject()
					.put("deviceId", deviceId)
					.put("timestamp", getDate() ).toString();
		} catch (JSONException e) {
			e.printStackTrace();
		}
        // POST method
        ClientResponse response = webResource.accept("application/json")
                .type("application/json").post(ClientResponse.class, jsonInput);
        
        // Check response status code
        if (response.getStatus() != 200) {
            throw new RuntimeException("Failed : HTTP error code : "
                    + response.getStatus());
        }
        
        // Reads response
        String output = response.getEntity(String.class);
        String traceId = ""; 
        
        try {
			JSONObject result = new JSONObject(output);
			traceId = result.getString("traceId");
		} catch (JSONException e) {
			e.printStackTrace();
		}
        
        return traceId; 


    }

	private static String getDate() {
		DateTime nowUTC = new DateTime(DateTimeZone.UTC);
        DateTimeFormatter  dtfOut = DateTimeFormat.forPattern(dateFormat );
        return dtfOut.print(nowUTC);
        
	}
    
    
}
