package com.example.jonathan.seisme_miniprojet;

/**
 * Created by Jonathan on 22/03/16.
 *
 */

// 3, 5, 7, 9
public class Quake {
    private double mag;
    private String type;
    private String place;
    private String date;
    private double coord0;
    private double coord1;
    private double coord2;

    public Quake(double mag, String type, String place, String date, double coord0, double coord1, double coord2) {
        this.mag = mag;
        this.type = type;
        this.place = place;
        this.date = date;
        this.coord0 = coord0;
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    public String getDate() {
        return date;
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