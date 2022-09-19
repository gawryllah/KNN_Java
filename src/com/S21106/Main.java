package com.S21106;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {

    static int goodCount = 0;

    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.println("Podaje nazwe test set");

        String testPath = "";
        String traingPath = "";
        int k;

        testPath = scanner.nextLine();
        System.out.println("Podaj traing path");
        traingPath = scanner.nextLine();
        System.out.println("podaj k");
        k = Integer.parseInt(scanner.nextLine());

        List<List<String>> test = csvReader(testPath);
        List<List<String>> train = csvReader(traingPath);
        System.out.println(test);
        List<Wektor> doTestow = wektors(test);
        List<Wektor> doTestowania = wektors(train);

        //System.out.println(obiekty);
        List<List<Double>> inRes = new ArrayList<>();

        for (int i = 0; i < train.size(); i++) {

            System.out.println("Wektor " + i + ":");
            inRes = info(doTestowania.get(i), doTestow, k, i);
            System.out.println();
        }
        System.out.println("Accuracy: " + (float) goodCount / doTestow.size());
    }


    static List<List<String>> csvReader(String fileName) {
        List<List<String>> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                lines.add(Arrays.asList(values));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < lines.size(); i++) {
            for (int j = 0; j < lines.get(i).size(); j++) {
                lines.get(i).set(j, lines.get(i).get(j).replace("\"", ""));
            }
        }
        return lines;
    }

    static List<String> createCategory(List<List<String>> csv) {
        List<String> category = new ArrayList<>();

        if (csv.size() > 0) {
            category.add(csv.get(0).get(4));
            int j = 0;
            for (int i = 1; i < csv.size(); i++) {

                if (!csv.get(i).get(4).equals(category.get(j))) {

                    category.add(csv.get(i).get(4));
                    j++;
                }
            }
        }
        return category;

    }

    static List<Wektor> wektors(List<List<String>> iris) {

        List<Wektor> obiekty = new ArrayList<>();

        for (int i = 0; i < iris.size(); i++) {
            obiekty.add(new Wektor(Double.parseDouble(iris.get(i).get(0)), Double.parseDouble(iris.get(i).get(1)), Double.parseDouble(iris.get(i).get(2)), Double.parseDouble(iris.get(i).get(3)), iris.get(i).get(4)));
        }

        return obiekty;
    }

    static double getEukledes(Wektor testowany, Wektor train) {


        double result = Math.sqrt((Math.pow((testowany.getX() - train.getX()), 2) + Math.pow((testowany.getY() - train.getY()), 2) + Math.pow((testowany.getZ() - train.getZ()), 2) + Math.pow((testowany.getW() - train.getW()), 2)));

        return result;
    }

    static List<List<Double>> info(Wektor wektor, List<Wektor> testowe, int k, int nrWek) {

        System.out.println("Expected: " + wektor.getCategory());
        List<List<Double>> info = new ArrayList<>();

        List<Double> index = new ArrayList<>();
        List<Double> wynik = new ArrayList<>();
        List<EuklidesRes> connected = new ArrayList<>();

        String reuslut = "";

        int virginicaCount = 0;
        int setosaCoiunt = 0;
        int versiColorCount = 0;

        Map<Double, Double> relacja = new TreeMap<>();

        int j = 0;
        for (int i = 0; i < testowe.size(); i++) {


            index.add((double) i);
            wynik.add(getEukledes(wektor, testowe.get(i)));

            relacja.put((double) i, getEukledes(wektor, testowe.get(i)));

            connected.add(new EuklidesRes(i, getEukledes(wektor, testowe.get(i))));


        }


        for (int i = 0; i < connected.size(); i++) {
            if (i + 1 < connected.size()) {
                if (connected.get(i).getWyink() > connected.get(i + 1).getWyink()) {
                    Collections.swap(connected, i, i + 1);
                }
            }
        }


        for (int i = 0; i < k; i++) {

            if (testowe.get(connected.get(i).getIndexTestwoego()).getCategory().equals("Iris-setosa")) {

                setosaCoiunt++;

            } else if (testowe.get(connected.get(i).getIndexTestwoego()).getCategory().equals("Iris-versicolor")) {

                versiColorCount++;

            } else if (testowe.get(connected.get(i).getIndexTestwoego()).getCategory().equals("Iris-virginica")) {
                virginicaCount++;
            }

        }

        if (setosaCoiunt > versiColorCount && setosaCoiunt > virginicaCount) {
            reuslut = "Iris-setosa";
        } else if (versiColorCount > setosaCoiunt && versiColorCount > virginicaCount) {
            reuslut = "Iris-versicolor";

        } else if (virginicaCount > setosaCoiunt && virginicaCount > versiColorCount) {
            reuslut = "Iris-virginica";
        } else {
            reuslut = "nie ma";
        }

        if (reuslut.equals(wektor.getCategory())){
            goodCount++;
        }


        System.out.println("Wynik: " + reuslut);

        info.add(index);
        info.add(wynik);


        return info;
    }

}
