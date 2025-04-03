package com.techelevator.service;


import com.techelevator.model.Neo;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class NEOService {

    private RestTemplate template = new RestTemplate();

    private final String API_URL = "https://api.nasa.gov/neo/rest/v1/feed";

    @Value("${NASA_API_KEY}")
    private String API_KEY;

    public List<Neo> getNEOData(String startDate, String endDate) {
        String url = API_URL +
                "?start_date=" + startDate +
                "&end_date=" + endDate +
                "&api_key=" + API_KEY;
//        String response = template.getForObject(url, String.class).toString();
//        System.out.println(response);
//        NeoFeedResponse response =
//                template.getForObject(url, NeoFeedResponse.class);

        List<Neo> neoList = new ArrayList<>();
        try {
            ResponseEntity<String> responseEntity = template.getForEntity(url, String.class);
            String response = responseEntity.getBody();
            // convert the string response to json
            JSONObject json = new JSONObject(response);

            // look through the json and find the object {} that has the key near_earth_objects
            JSONObject nearEarthObjects = json.getJSONObject("near_earth_objects");
            // JSON object returns key:value pairs to us, iterate (or loop) through the key
            Iterator<String> keys = nearEarthObjects.keys(); // Correct way to get keys
            while (keys.hasNext()) {
                String date = keys.next(); // grab the date
                JSONArray neos = nearEarthObjects.getJSONArray(date); // array of neo objects

                for (int i = 0; i < neos.length(); i++) {
                    JSONObject neo = neos.getJSONObject(i);
                    String id = neo.getString("id");
                    String name = neo.getString("name");
                    double estimatedDiameterMax = neo.getJSONObject("estimated_diameter").getJSONObject("kilometers").getDouble("estimated_diameter_max");
                    boolean isPotentiallyHazardous = neo.getBoolean("is_potentially_hazardous_asteroid");

                    Neo newNeo = new Neo();
                    newNeo.setId(id);
                    newNeo.setName(name);
                    newNeo.setPotentiallyHazardousAsteroid(isPotentiallyHazardous);
                    newNeo.setEstimatedDiameterMaxKm(estimatedDiameterMax);

                    JSONArray closeApproaches = neo.getJSONArray("close_approach_data");
                    if (closeApproaches.length() != 0) {
                        JSONObject closeApproach = closeApproaches.getJSONObject(0);
                        String closeApproachDateStr = closeApproach.getString("close_approach_date_full");
                        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MMM-dd HH:mm");
                        LocalDateTime closeApproachDateTime = LocalDateTime.parse(closeApproachDateStr, formatter);
                        double missDistanceKm = Double.parseDouble(closeApproach.getJSONObject("miss_distance").getString("kilometers"));
                        double velocityKmPerSec = Double.parseDouble(closeApproach.getJSONObject("relative_velocity").getString("kilometers_per_second"));

                        newNeo.setCloseApproachDate(closeApproachDateTime);
                        newNeo.setMissDistanceKm(missDistanceKm);
                        newNeo.setVelocityKmPerSec(velocityKmPerSec);
                    }
                    neoList.add(newNeo);
                }
            }

        } catch (Exception e) {
            System.err.println("Error: API request failed: " + e.getMessage());
        }
        return neoList;

    }
}
