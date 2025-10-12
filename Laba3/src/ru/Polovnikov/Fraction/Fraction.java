package ru.Polovnikov.Fraction;

import ru.Polovnikov.Main.Calculate;

public final class Fraction extends Number implements Calculate, Cloneable {
    private final int numerator;
    private final int denominator;

    //Конструкторы
    public Fraction(int numerator, int denominator) {
        if (denominator == 0) {
            throw new RuntimeException("Знаменатель 0");
        }

        if (denominator < 0) {
            numerator = -numerator;
            denominator = -denominator;
        }

        this.numerator = numerator;
        this.denominator = denominator;
    }

    public Fraction(int number) {
        this(number, 1);
    }

    public Fraction(double den) {
        this((int)(den * 10), 10);
    }

    // Геттеры
    public int getNumerator() {
        return numerator;
    }

    public int getDenominator() {
        return denominator;
    }

    //Вывод в вещественной форме
    public double decimal() {
        return (double) numerator / denominator;
    }

    //Методы сложения (С дробью и с числом)
    public Fraction sum(Fraction x) {
        int newNum = this.numerator * x.denominator + x.numerator * this.denominator;
        int newDen = this.denominator * x.denominator;
        return new Fraction(newNum, newDen);
    }

    public Fraction sum(double num) {
        return sum(new Fraction(num));
    }

    //Методы вычитания (С дробью и с числом)
    public Fraction minus(Fraction x) {
        int newNum = this.numerator * x.denominator - x.numerator * this.denominator;
        int newDen = this.denominator * x.denominator;
        return new Fraction(newNum, newDen);
    }

    public Fraction minus(int num) {
        return minus(new Fraction(num));
    }

    //Методы умножения (С дробью и с числом)
    public Fraction multiply(Fraction x) {
        int newNum = this.numerator * x.numerator;
        int newDen = this.denominator * x.denominator;
        return new Fraction(newNum, newDen);
    }

    public Fraction multiply(int num) {
        return multiply(new Fraction(num));
    }

    //Методы деления (С дробью и с числом)
    public Fraction divide(Fraction x) {
        if (x.denominator == 0) {
            throw new RuntimeException("Знаменатель 0");
        }
        int newNum = this.numerator * x.denominator;
        int newDen = this.denominator * x.numerator;
        return new Fraction(newNum, newDen);
    }

    public Fraction divide(int num) {
        if (num == 0){
            throw new RuntimeException("Знаменатель 0");
        }
        return divide(new Fraction(num));
    }

    //Сумма через интерфейс
    public static double sumNumbers(Calculate... values) {
        double sum = 0;
        for (Calculate value : values) {
            sum += value.decimal();
        }
        return sum;
    }

    //Клонирование
    @Override
    public Fraction clone() {
        try {
            return (Fraction) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException("Клонирование не поддерживается", e);
        }
    }

    //Вывод
    @Override
    public String toString() {
        if (denominator == 1)
            return String.valueOf(numerator);

        return numerator + "/" + denominator;
    }

    //Методы "Number" для вывода разных типов данных
    @Override
    public int intValue() {
        return numerator / denominator;
    }

    @Override
    public long longValue() {
        return (long) numerator / denominator;
    }

    @Override
    public float floatValue() {
        return (float) numerator / denominator;
    }

    @Override
    public double doubleValue() {
        return (double) numerator / denominator;
    }

    //Сравнение
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Fraction fraction = (Fraction) obj;
        return numerator == fraction.numerator && denominator == fraction.denominator;
    }

    @Override
    public int hashCode() {
        return 31 * numerator + denominator;
    }

}
