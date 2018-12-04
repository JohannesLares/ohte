/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fi.johanneslares.yliopistobot;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LocationService {
    static HttpURLConnection con;
    
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
            return object.get("bbox").getAsJsonArray().get(1) + " " + object.get("bbox").getAsJsonArray().get(0);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return "";
    }
    
    
    
    private static String buildLocation(String location) {
        String[] words = location.split(" ");
        String returnSt = "";
        for (String word : words) {
            returnSt += word;
        }
        return returnSt;
    }
}
