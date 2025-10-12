package ru.Polovnikov.Power;

import static java.lang.Integer.parseInt;
import static java.lang.Math.pow;

//Вычисление степени
public class Power {
    public static double calculatePower(String xStr, String yStr) {
        int x = Integer.parseInt(xStr);
        int y = Integer.parseInt(yStr);
        return pow(x, y);
    }
}

