package com.shekhar.ner;
import java.util.Random;

import org.apache.commons.math3.linear.ArrayRealVector;
import org.apache.commons.math3.linear.RealVector;

public class DotProduct {

    private static int NUMBER_RANGE = 1000;
    private static int SIZE = 1000000;

    public static void main(String[] args) {
        Random random = new Random();

        double[] a = new double[SIZE];
        for (int i=0; i<SIZE; ++i) {
            a[i] = (random.nextInt(NUMBER_RANGE));
        }

        double[] b = new double[SIZE];
        for (int i=0; i<SIZE; ++i) {
            b[i] = (random.nextInt(NUMBER_RANGE));
        }

        RealVector a1 = new ArrayRealVector(a);
        RealVector b1 = new ArrayRealVector(b);


        arrayVersion(a,b);
        vectorVersion(a1, b1);
    }

    private static double vectorVersion(RealVector a1, RealVector b1) {
        long start = System.currentTimeMillis();
        double c = a1.dotProduct(b1);
        long end = System.currentTimeMillis();
        System.out.println("Vector version: " + c);
        System.out.println( (end-start) + " ms."  );
        return c;
    }

    private static double arrayVersion(double[] a, double[] b) {

        long start = System.currentTimeMillis();
        double c = 0.0;
        for (int i=0; i< a.length; ++i)
            c += a[i] * b[i];
        long end = System.currentTimeMillis();
        System.out.println("Array version: " + c);
        System.out.println( (end-start) + " ms."  );
        return c;
    }

}
