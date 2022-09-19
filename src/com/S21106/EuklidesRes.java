package com.S21106;

public class EuklidesRes {
    private int indexTestwoego;
    private double wyink;

    public EuklidesRes(int indexTestwoego, double wyink) {
        this.indexTestwoego = indexTestwoego;
        this.wyink = wyink;
    }

    public double getWyink() {
        return wyink;
    }

    public int getIndexTestwoego() {
        return indexTestwoego;
    }

    @Override
    public String toString() {
        return "EuklidesRes{" +
                "indexTestwoego=" + indexTestwoego +
                ", wyink=" + wyink +
                '}';
    }
}
