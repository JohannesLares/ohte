/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;


/**
 * Provides location service, so you can get coordinates from address.
 * @author jlares
 */
public class LocationService {
    static HttpURLConnection con;
    
    /**
     * 
     * @param location address to get coordinates
     * @return Coordinates as String xx.xxxx,yy.yyyy
     */
    public static String getCoordinates(String location) {
        try {
            URL url = new URL("https://api.digitransit.fi/geocoding/v1/search?text=" + buildLocation(location) + "&size=1");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String inputLine;
            StringBuffer content = new StringBuffer();
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            JsonObject object = new Gson().fromJson(content.toString(), JsonObject.class);
            return object.get("bbox").getAsJsonArray().get(1) + "," + object.get("bbox").getAsJsonArray().get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    
    
    private static String buildLocation(String location) {
        String[] words = location.split(" ");
        String returnSt = "";
        int i = 0;
        for (String word : words) {
            if (i != 0) {
                returnSt += "%20";
            }
            returnSt += word;
            i++;
        }
        System.out.println(returnSt);
        return returnSt;
    }
}
