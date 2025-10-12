package ru.Polovnikov.Main;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

import ru.Polovnikov.City.City;
import ru.Polovnikov.City.Way;
import ru.Polovnikov.Fraction.Fraction;
import ru.Polovnikov.Valid.Valid;
import ru.Polovnikov.Power.Power;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Valid valid = new Valid();

        System.out.println("Привет, это 3 лаба по Java");
        String number;
        while (true) {
            System.out.println("Введи номер задания(1-9):");
            number = scanner.nextLine();

            if(!valid.isNumber(number)) {
                System.out.println("Ошибка");
            } else if (!number.equals("1") && !number.equals("2") && !number.equals("3") &&
                    !number.equals("4") && !number.equals("5") && !number.equals("6") &&
                    !number.equals("7") && !number.equals("8") && !number.equals("9")) {
                System.out.println("Такого задания нет");
            }

            switch(number) {
                case "1":
                    System.out.println("1 Задание");
                    Fraction f1 = new Fraction(1, 2);
                    Fraction f2 = new Fraction(-3, 7);
                    Fraction f3 = new Fraction(1, -5);

                    System.out.println("Заданы дроби: ");
                    System.out.println("1. " + f1);
                    System.out.println("2. " + f2);
                    System.out.println("3. " + f3);

                    System.out.println("1 дробь: 1/2 без изменений");
                    System.out.println("2 дробь: -3/7 без изменений");
                    System.out.println("3 дробь: 1/-5 преобразовывается");
                    break;

                case "2":
                    City city1 = new City("Город1");
                    City city2 = new City("Город2");
                    City city3 = new City("Город3");

                    city1.addWays(city2, 5);
                    System.out.println("Добавлена дорога из " + city1 + " в " + city2);
                    city1.addWays(city3, 7);
                    System.out.println("Добавлена дорога из " + city1 + " в " + city3);
                    City.printRoads(city1, city2, city3);

                    try {
                        city1.addWays(city1, 3);
                        System.out.println("Нельзя добавить дорогу в себя");
                    } catch (RuntimeException k) {
                        System.out.println("Ошибка при добавлении дороги в себя: " + k.getMessage());
                    }
                    City.printRoads(city1, city2, city3);

                    try {
                        city1.addWays(city2, 4);
                        System.out.println("Дорога не добавлена, ведь она дублируется");
                    } catch (RuntimeException k) {
                        System.out.println("Ошибка: " + k.getMessage());
                    }
                    City.printRoads(city1, city2, city3);

                    System.out.println("Создание города по данным дорогам");
                    Map<City, Integer> initialRoads = new HashMap<>();
                    initialRoads.put(city1, 2);
                    initialRoads.put(city3, 8);
                    City city4 = new City("Город 4", initialRoads);
                    City.printRoads(city4);

                    System.out.println("Удаление дороги из 1 в 2");
                    try {
                        city1.removeRoad(city2);
                        System.out.println("Дорога успешно удалена");
                    } catch (RuntimeException k) {
                        System.out.println("Ошибка при удалении дороги: " + k.getMessage());
                    }
                    City.printRoads(city1, city2, city3, city4);
                    break;

                case "3":
                    System.out.println("3 Задание");
                    City A = new City("A");
                    City B = new City("B");
                    City C = new City("C");
                    City D = new City("D");
                    City E = new City("E");
                    City F = new City("F");
                    System.out.println("Карта: ");

                    A.addWays(B, 5);
                    A.addWays(D, 6);
                    A.addWays(F, 1);

                    B.addWays(C, 3);

                    C.addWays(D, 4);
                    
                    D.addWays(E, 2);

                    E.addWays(F, 2);

                    City.printRoads(A, B, C, D, E, F);

                    Way route = new Way(F, D);
                    /*
                    route.setStart(B);
                    route.setFinish(C);
                     */

                    City[] routeArray = route.findWay();

                    System.out.println("Маршрут через toString(): " + route.toString());
                    System.out.print("Маршрут как массив: ");
                    for (int i = 0; i < routeArray.length; i++) {
                        System.out.print(routeArray[i].getName());
                        if (i < routeArray.length - 1) {
                            System.out.print(" -> ");
                        }
                    }
                    System.out.println(" ");
                    break;

                case "4":
                    System.out.println("4 Задание");
                    Fraction fraction = new Fraction(1, 3);
                    System.out.println("Создана дробь " + fraction);
                    System.out.println("Ну и все...");
                    System.out.println(" ");
                    break;

                case "5":
                    System.out.println("5 Задание");
                    Fraction f = new Fraction(3, 4);
                    Number numberf = f;
                    System.out.println("Number ссылка на Fraction: " + numberf.doubleValue());
                    System.out.println("Реализация методов Number: ");
                    System.out.println("intValue(): " + f.intValue());
                    System.out.println("longValue(): " + f.longValue());
                    System.out.println("floatValue(): " + f.floatValue());
                    System.out.println("doubleValue(): " + f.doubleValue());
                    break;

                case "6":
                    /*
                    Fraction sum1 = new Fraction(2, 1);
                    Fraction sum2 = new Fraction(3, 5);
                    Fraction sum3 = new Fraction(2.3);
                    Fraction resultSum1 = sum1.sum(sum2).sum(sum3);
                    System.out.println("Ответ 1 примера: " + resultSum1);

                    Fraction sum4 = new Fraction(3.6);
                    Fraction sum5 = new Fraction(49,12);
                    Fraction sum6 = new Fraction(3);
                    Fraction sum7 = new Fraction(3,2);
                    Fraction resultSum2 = sum4.sum(sum5).sum(sum6).sum(sum7);
                    System.out.println("Ответ 1 примера: " + resultSum2);

                    Fraction sum8 = new Fraction(1,3);
                    Fraction sum9 = new Fraction(1);
                    Fraction resultSum3 = sum8.sum(sum9);
                    System.out.println("Ответ 1 примера: " + resultSum3);
                    break;

                     */
                    System.out.println("6 Задание");
                    Fraction[] values1 = {
                            new Fraction(2),
                            new Fraction(3, 5),
                            new Fraction(2.3)
                    };
                    double result1 = Fraction.sumNumbers(values1);
                    System.out.println("2 + 3/5 + 2.3 = " + result1);

                    Fraction[] values2 = {
                            new Fraction(3.6),
                            new Fraction(49, 12),
                            new Fraction(3),
                            new Fraction(3, 2)
                    };
                    double result2 = Fraction.sumNumbers(values2);
                    System.out.println("3.6 + 49/12 + 3 + 3/2 = " + result2);

                    Fraction[] values3 = {
                            new Fraction(1, 3),
                            new Fraction(1)
                    };
                    double result3 = Fraction.sumNumbers(values3);
                    System.out.println("1/3 + 1 = " + result3);
                    break;

                case "7":
                    System.out.println("7 Задание");
                    City city5 = new City("Город5");
                    City city6 = new City("Город6");
                    City city7 = new City("Город7");
                    City city8 = new City("Город8");
                    System.out.println("Города: ");
                    City.printRoads(city5, city6, city7, city8);

                    city5.addWays(city6, 3);
                    city5.addWays(city7, 5 );

                    city8.addWays(city6, 3);
                    city8.addWays(city7, 5);

                    System.out.println("После добавления: ");
                    City.printRoads(city5, city6, city7, city8);

                    System.out.println("Сравнение city5 и city8:");
                    System.out.println("Города имеют одинаковые дороги: " + city5.sameWays(city8));
                    System.out.println("Города равны: " + city5.equals(city8));

                    System.out.println("city5: " + city5);
                    System.out.println("city8: " + city8);
                    break;

                case "8":
                    System.out.println("8 Задание");
                    String xStr = " ";
                    while (true) {
                        System.out.print("Введите число X: ");
                        xStr = scanner.nextLine();
                        if (valid.isNumber(xStr)) {
                            break;
                        }
                        System.out.println("Ошибка: введите целое положительное число!");
                    }

                    String yStr = "";
                    while (true) {
                        System.out.print("Введите степень Y: ");
                        yStr = scanner.nextLine();
                        if (valid.isNumber(yStr)) {
                            break;
                        }
                        System.out.println("Ошибка: введите целое положительное число!");
                    }

                    try {
                        double result = Power.calculatePower(xStr, yStr);
                        System.out.println("Результат: " + xStr + " ^ " + yStr + " = " + result);
                    } catch (Exception e) {
                        System.out.println("Ошибка при вычислении: " + e.getMessage());
                    }
                    break;

                case "9":
                    System.out.println("9 Задание");
                    Fraction orig = new Fraction(3, 4);
                    Fraction clone = orig.clone();

                    System.out.println("Меняем клон (Увеличиваем на 5/4");
                    Fraction sumClone = clone.sum(new Fraction(5, 4));
                    System.out.println("Клон после изменения: " + sumClone);
                    System.out.println("Оригинал после изменения: " + orig);
                    System.out.println("Клон увеличился, а оригинал нет");
            }
        }
    }
}
