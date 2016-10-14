package org.labexp.traces;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

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
 * This class is a direct connection to the API server .
 */
public class ApiConnector {
  /**
   * jersey client to connect the server.
   */
  private static Client client;
  /**
   * Server url.
   */
  private static String apiBaseUrl = "http://10.173.1.153";
  /**
   * Format to save date on the server.
   */
  private static final  String DATE_FORMAT = "MM/dd/yyyy HH:mm:ss";
  /**
   * sub-url to post trace start.
   */
  private static final  String POST_START_TRACE = "/buses/v0.1/trace";
  /**
   * server response ok.
   */
  private static final  int OK_RESPONSE = 200;

  /**
   * This main is just for testing.
   */
  public static void main() {
    System.out.println("Hello World!");
    createClient();
    // System.out.print(startTrace("Leo-Test1"));
  }

  /**
   * Starts Jersey client.
   */
  public static void createClient() {
    ClientConfig clientConfig = new DefaultClientConfig();
    clientConfig.getFeatures().put(
        JSONConfiguration.FEATURE_POJO_MAPPING, Boolean.TRUE);
    client = Client.create(clientConfig);
  }
  /**
   * Starts trace on server.
   * @param deviceId A unique identificator for the client device.
   * @return unique trace identificator
   */
  public static String startTrace(final String deviceId) {

    // Creates a webResource with the API url to post start a trace
    WebResource webResource = client.resource(apiBaseUrl + POST_START_TRACE);

    // Building the post content
    String jsonInput = "";
    try {
      jsonInput = new JSONObject()
              .put("deviceId", deviceId)
              .put("timestamp", getDate())
              .toString();
    } catch (JSONException e) {
      e.printStackTrace();
    }
    // POST method
    ClientResponse response = webResource
        .accept("application/json")
        .type("application/json")
        .post(ClientResponse.class, jsonInput);

    // Check response status code
    if (response.getStatus() != OK_RESPONSE) {
      throw new RuntimeException(
          "Failed : HTTP error code : " + response.getStatus());
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

  /**
   * Calculates timeZone and formats date into a string.
   * @return Date with the format of @DATE_FORMAT.
   */
  private static String getDate() {
    DateTime nowUTC = new DateTime(DateTimeZone.UTC);
    DateTimeFormatter dtfOut = DateTimeFormat.forPattern(DATE_FORMAT);
    return dtfOut.print(nowUTC);
  }

}
