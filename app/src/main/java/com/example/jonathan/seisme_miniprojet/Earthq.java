package com.example.jonathan.seisme_miniprojet;

/**
 * Created by Jonathan on 22/03/16.
 *
 */

// 3, 5, 7, 9
public class Earthq {
    private double mag;
    private String type;
    private String place;
    private double coord0;
    private double coord1;
    private double coord2;

    public Earthq(double mag, String type, String place) {
        this.mag = mag;
        this.type = type;
        this.place = place;
    }

    public double getMag() {
        return mag;
    }

    public String getType() {
        return type;
    }

    public String getPlace() {
        return place;
    }

    public double getCoord0() {
        return coord0;
    }

    public double getCoord1() {
        return coord1;
    }

    public double getCoord2() {
        return coord2;
    }
}