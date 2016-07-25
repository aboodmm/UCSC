package xyz.slugspot.slugspot.Place;

import android.util.DisplayMetrics;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

/**
 * Created by jaredjensen on 7/14/16.
 * Contains tools for manipulating places
 */
public class PlaceTools {

    /**
     * Zooms a given map out to fit the given place list
     * @param places
     * @param map
     */
    public static void fitMapToPoints(ArrayList<Place> places, GoogleMap map) {
        fitMapToPoints(places, map, 0);
    }

    /**
     * Zooms a given map out to fit the given place list
     * @param places a list of places
     * @param map a map to zoom
     * @param padding amount of padding to give
     */
    public static void fitMapToPoints(ArrayList<Place> places, GoogleMap map, int padding) {
        if(places.isEmpty())
            return;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Place place : places) {
            builder.include(place.coordinates);
        }
        LatLngBounds bounds = builder.build();

        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, padding));
    }

    /**
     * Zooms out the map to fit a list of places.
     * This is necessary when initiating the map the first time.
     * @param places list of places
     * @param map the map to update
     * @param width width of the phone screen
     * @param height height of the phne screen
     * @param padding padding (in pixels)
     */
    public static void fitMapToPoints(ArrayList<Place> places, GoogleMap map, int width, int height, int padding) {
        if(places.isEmpty())
            return;
        LatLngBounds.Builder builder = new LatLngBounds.Builder();
        for (Place place : places) {
            builder.include(place.coordinates);
        }
        LatLngBounds bounds = builder.build();
        map.moveCamera(CameraUpdateFactory.newLatLngBounds(bounds, width, height, padding));
    }

    /**
     * Zooms out the map to fit a list of places.
     * This is necessary then initiating the map the first time.
     * @param places
     * @param map
     * @param display
     */
    public static void fitMapToPoints(ArrayList<Place> places, GoogleMap map, DisplayMetrics display) {
        fitMapToPoints(places, map, display.widthPixels, display.heightPixels, Math.max(display.widthPixels, display.heightPixels)/10);
    }

    /**
     * Displays a list of places on a map
     * @param places a list of places
     * @param map a map to display on
     */
    public static void displayOnMap(ArrayList<Place> places, GoogleMap map) {
        map.clear();
        for (Place place : places) {
            map.addMarker(new MarkerOptions().position(place.coordinates).title(place.name).snippet(place.description));
        }
    }

    public static void displayOnMap(Place place, GoogleMap map) {
        map.clear();
        map.addMarker(new MarkerOptions().position(place.coordinates).title(place.name).snippet(place.description));
    }
}