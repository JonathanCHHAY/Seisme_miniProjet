package com.example.jonathan.seisme_miniprojet;

/**
 * Created by Jonathan on 22/03/16.
 *
 */
public class Earthq {
    private String title;
    private String type;
    private String mag;
    private String place;
    private String intensity;
    private int coord0;
    private int coord1;
    private int coord2;

    public Earthq(String title, String type, String mag, String place, String intensity, int coord0, int coord1, int coord2) {
        this.title = title;
        this.type = type;
        this.mag = mag;
        this.place = place;
        this.intensity = intensity;
        this.coord0 = coord0;
        this.coord1 = coord1;
        this.coord2 = coord2;
    }
}


