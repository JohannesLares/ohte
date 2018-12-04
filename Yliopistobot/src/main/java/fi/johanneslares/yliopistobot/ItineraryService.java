package fi.johanneslares.yliopistobot;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ItineraryService {
    private static String itineraryRequestStart = "{\n" +
"  plan(\n" +
"    fromPlace: ";
    private static String itineraryRequestEnd = " {\n" +
"    itineraries{\n" +
"      walkDistance,\n" +
"      duration,\n" +
"      legs {\n" +
"        mode\n" +
"        startTime\n" +
"        endTime\n" +
"        from {\n" +
"          name\n" +
"          stop {\n" +
"            code\n" +
"            name\n" +
"          }\n" +
"        },\n" +
"        to {\n" +
"          name\n" +
"        },\n" +
"        distance\n" +
"        legGeometry {\n" +
"          length\n" +
"        }\n" +
"      }\n" +
"    }\n" +
"  }";
    private static String requestUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    
    public static List<ItineraryLeg> getItinerary(String start, String end, String date){
        try {
            System.out.println("start " + start + " end " + end);
            String query = itineraryRequestStart + "\"" + start + "\",\n" + "toPlace: \"" + end + "\",\n)" + itineraryRequestEnd + "\n}"; 
            System.out.println(query);
            sendRequest(query);
        } catch (Exception ex) {
            Logger.getLogger(ItineraryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return new ArrayList<ItineraryLeg>();
    }
    
    private static void sendRequest(String query) throws Exception{
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/graphql");
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(query);
        out.flush();
        out.close();
        System.out.println(con.getResponseCode() + " HSL response code");
        BufferedReader in = new BufferedReader(
            new InputStreamReader(con.getInputStream()));
          String inputLine;
          StringBuffer content = new StringBuffer();
          while ((inputLine = in.readLine()) != null) {
              content.append(inputLine);
          }
          in.close();
          System.out.println(content.toString());
    }
}