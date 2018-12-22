package fi.johanneslares.yliopistobot.services;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import fi.johanneslares.yliopistobot.Itinerary;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Class provides itinerary service to Yliopistobot.
 * 
 * @author jlares
 */
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
        "        trip {\n" +
        "          tripHeadsign\n" +
        "          routeShortName\n" +
        "        }" +
        "      }\n" +
        "    }\n" +
        "  }";
    private static String requestUrl = "https://api.digitransit.fi/routing/v1/routers/hsl/index/graphql";
    
    /**
     * @param start Start location
     * @param end End location
     * @param time time, when should arrive to end location
     * @param name name of the lesson, where are going
     * @return Message with itinerary suggestion
     */
    
    public static Itinerary getItinerary(String start, String end, String time, String name) {
        Itinerary result = null;
        time += ":00";
        String date = getDateAsString();
        System.out.println(date + " " + time);
        try {
            String query = itineraryRequestStart + "\"" + start + "\",\n" + "toPlace: \"" + end + "\",\ndate: \"" + date + "\",\ntime: \"" + time + "\",\n arriveBy: true,\n)" + itineraryRequestEnd + "\n}"; 
            JsonObject object = sendRequest(query);
            result = handleItineraryPlans(object, time, name);
        } catch (Exception ex) {
            Logger.getLogger(ItineraryService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
    
    private static JsonObject sendRequest(String query) throws Exception {
        URL url = new URL(requestUrl);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setDoOutput(true);
        con.setRequestProperty("Content-Type", "application/graphql");
        DataOutputStream out = new DataOutputStream(con.getOutputStream());
        out.writeBytes(query);
        out.flush();
        out.close();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer content = new StringBuffer();
        while ((inputLine = in.readLine()) != null) {
            content.append(inputLine);
        }
        in.close();
        return new Gson().fromJson(content.toString(), JsonObject.class);
    }
    
    private static Itinerary handleItineraryPlans(JsonObject obj, String time, String name) {
        String ret = "Reitti luennolle " + name + " kello " + time + "\n";
        long gotime = 0L;
        if (obj.get("data").getAsJsonObject().get("plan").getAsJsonObject().get("itineraries").getAsJsonArray().size() > 0) {
            JsonObject firstItinerary = obj.get("data").getAsJsonObject().get("plan").getAsJsonObject().get("itineraries").getAsJsonArray().get(0).getAsJsonObject();
            JsonArray legs = firstItinerary.get("legs").getAsJsonArray();
            gotime = legs.get(0).getAsJsonObject().get("startTime").getAsLong();
            for (JsonElement leg : legs) {
                JsonObject object = leg.getAsJsonObject();
                String headsign = "";
                if (!object.get("trip").isJsonNull()) {
                    headsign = " " + object.get("trip").getAsJsonObject().get("routeShortName").getAsString() + " kohti " + object.get("trip").getAsJsonObject().get("tripHeadsign").getAsString();
                }
                ret += "\nMene " + getMethodOfTransportation(object.get("mode").getAsString()) + headsign + " paikasta " + object.get("from").getAsJsonObject().get("name").getAsString() + " paikkaan " + object.get("to").getAsJsonObject().get("name").getAsString() + " kello " + getTimeAsString(object.get("startTime").getAsLong()) + "\n";
            }
        } else {
            ret += "Ei reittiä";
        }
        return new Itinerary(ret, gotime);
    }
    
    
    /**
     * @param method method of transportation
     * @return method of transportation in Finnish
     */
    public static String getMethodOfTransportation(String method) {
        Map<String, String> methods = new HashMap<>();
        methods.put("WALK", "kävellen");
        methods.put("BUS", "busilla");
        methods.put("SUBWAY", "metrolla");
        methods.put("RAIL", "junalla");
        methods.put("TRAM", "raitiovaunulla");
        methods.put("FERRY", "lautalla");
        return methods.get(method);
    }
    
    private static String getTimeAsString(long time) {
        Date date = new Date(time);
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
        return sdf.format(date);
    }
    
    private static String getDateAsString() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(date);
    }
}