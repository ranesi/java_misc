package com.rich;

import com.google.maps.GeoApiContext;
import com.google.maps.model.ElevationResult;
import com.google.maps.model.GeocodingResult;
import com.google.maps.model.LatLng;
import com.google.maps.ElevationApi;
import com.google.maps.GeocodingApi;
import java.util.Scanner;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;

public class Main {

    private static Scanner strScan = new Scanner(System.in);
    private static Scanner numScan = new Scanner(System.in);

    public static void main(String[] args) {
        //Find the elevation of a user specified location
        try {
            //GET PLACE NAME!!
            System.out.println("ENTER PLACE NAME!");
            String place = strScan.nextLine(); //OK PLACE LIVES HERE NOW
            //GOOGLE KNOWS WHO WE ARE
            GeoApiContext context = new GeoApiContext().setApiKey(getKey());
            //GET A BUNCH OF PLACES!
            GeocodingResult[] request = GeocodingApi.geocode(context, place).await();
            if (request.length != 0) {
                //THIS IS WHERE THE INDEX LIVES
                int index = getIndex(request);
                //WHERE IS IT!!
                LatLng locationLatLng = request[index].geometry.location;
                //THIS IS WHERE!
                ElevationResult[] results = ElevationApi.getByPoints(context, locationLatLng).await();
                //IT IS THE FIRST RESULT OF THIS ARRAY!
                ElevationResult elevation = results[0];
                //RESULTS!
                System.out.println(String.format("Elevation of %s is %.2fkm above sea level.",
                        request[index].formattedAddress, elevation.elevation / 1000));
            } else {
                System.out.println("YOUR PLACE ISN'T A PLACE!");
            }
        } catch (Exception e) {
            System.out.println("Something has gone wrong!");
        }
        //BYE SCANNERS
        strScan.close();
        numScan.close();
    }

    private static int getIndex(GeocodingResult[] g) {
        //Prompt with user with a maximum of 5 locations, retrieve the index
        int bound;
        if (g.length > 5) {
            bound = 5;
        } else {
            bound = g.length;
        }
        for (int x = 0; x < bound; x++) {
                System.out.println(x + ": " + g[x].formattedAddress);
        }
        System.out.println("ENTER LOCATION NUMBER!");
        return numScan.nextInt();
    }

    private static String getKey() {
        //Get the Google API key from the key file
        String key = null, path = "key.txt";

        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            key = br.readLine();
            br.close();
        } catch (IOException e) {
            System.out.println("SOMETHING TERRIBLE HAS HAPPENED!");
            System.exit(1);
        }
        return key;
    }
}
