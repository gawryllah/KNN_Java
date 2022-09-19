package com.S21106;

public class Wektor {
    private double x;
    private double y;
    private double z;
    private double w;
    private String category;

    public Wektor(double x, double y, double z, double w, String category){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w ;
        this.category = category;
    }

    public Wektor(double x, double y, double z, double w){
        this.x = x;
        this.y = y;
        this.z = z;
        this.w = w ;
        this.category = "noEtykieta";
    }

    @Override
    public String toString() {
        return "Wektor{" +
                "x=" + x +
                ", y=" + y +
                ", z=" + z +
                ", w=" + w +
                ", category='" + category + '\'' +
                '}';
    }

    public double getW() {
        return w;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getZ() {
        return z;
    }

    public String getCategory() {
        return category;
    }
}
