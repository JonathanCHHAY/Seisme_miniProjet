package com.example.jonathan.seisme_miniprojet;

/**
 * Created by Jonathan on 22/03/16.
 *
 */
public class Earthq {
    private String type;
    private String mag;
    private String place;
    private String intensity;
    private int color;
    private double coord0;
    private double coord1;
    private double coord2;

    public Earthq(String type, String mag, String place, String intensity, int color, double coord0, double coord1, double coord2) {
        this.type = type;
        this.mag = mag;
        this.place = place;
        this.intensity = intensity;
        this.color = color;
        this.coord0 = coord0;
        this.coord1 = coord1;
        this.coord2 = coord2;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMag() {
        return mag;
    }

    public void setMag(String mag) {
        this.mag = mag;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getIntensity() {
        return intensity;
    }

    public void setIntensity(String intensity) {
        this.intensity = intensity;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public double getCoord0() {
        return coord0;
    }

    public void setCoord0(double coord0) {
        this.coord0 = coord0;
    }

    public double getCoord1() {
        return coord1;
    }

    public void setCoord1(double coord1) {
        this.coord1 = coord1;
    }

    public double getCoord2() {
        return coord2;
    }

    public void setCoord2(double coord2) {
        this.coord2 = coord2;
    }
}