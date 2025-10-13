package ru.Polovnikov.Main;

public interface Calculate {
    double decimal();
}

    //Сумма через интерфейс
    public static double sumNumbers(Calculate... values) {
        double sum = 0;
        for (Calculate value : values) {
            sum += value.decimal();
        }
        return sum;
    }

