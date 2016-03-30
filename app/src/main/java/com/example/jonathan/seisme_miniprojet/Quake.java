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
    private double x0;
    private double x1;
    private double x2;
    String url;

    public Quake(double mag, String type, String place, String date, double x0, double x1, double x2, String url) {
        this.mag = mag;
        this.type = type;
        this.place = place;
        this.date = date;
        this.x0 = x0;
        this.x1 = x1;
        this.x2 = x2;
        this.url = url;
    }

    public String getUrl() {
        return url;
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

    public double getX0() {
        return x0;
    }

    public double getX1() {
        return x1;
    }

    public double getX2() {
        return x2;
    }
}