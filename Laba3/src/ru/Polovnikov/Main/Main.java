package ru.Polovnikov.Main;

import java.util.Scanner;

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
            System.out.println("10 - Выход");
            number = scanner.nextLine();

            if (!valid.isNumber(number)) {
                System.out.println("Ошибка: введите число от 1 до 10");
                continue;
            }

            int TaskNum = Integer.parseInt(number);
            if (TaskNum < 1 || TaskNum > 10) {
                System.out.println("Вводимый номер должен быть от 1 до 10");
                continue;
            }

            switch (TaskNum) {
                case 1:
                    System.out.println("1 Задание");
                    System.out.print("Сколько дробей создать? ");
                    String countStr1 = scanner.nextLine();

                    if (!valid.isPositiveInteger(countStr1)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int countFractions = Integer.parseInt(countStr1);

                    Fraction[] fractions = new Fraction[countFractions];
                    for (int i = 0; i < countFractions; i++) {
                        System.out.println("Дробь " + (i + 1) + ":");

                        System.out.print("Введите числитель: ");
                        String numStr = scanner.nextLine();
                        if (!valid.isInteger(numStr)) {
                            System.out.println("Ошибка: числитель должен быть целым числом");
                            i--;
                            continue;
                        }

                        System.out.print("Введите знаменатель: ");
                        String denStr = scanner.nextLine();
                        if (!valid.isInteger(denStr)) {
                            System.out.println("Ошибка: знаменатель должен быть целым числом");
                            i--;
                            continue;
                        }

                        int denominator = Integer.parseInt(denStr);
                        if (denominator == 0) {
                            System.out.println("Ошибка: знаменатель не может быть нулем");
                            i--;
                            continue;
                        }

                        try {
                            fractions[i] = new Fraction(Integer.parseInt(numStr), denominator);
                        } catch (Exception e) {
                            System.out.println("Ошибка создания дроби: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Созданные дроби:");
                    for (int i = 0; i < fractions.length; i++) {
                        System.out.println((i + 1) + ". " + fractions[i]);
                    }
                    break;

                case 2:
                    System.out.println("2 Задание");
                    System.out.print("Сколько городов создать? ");
                    String cityCountStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(cityCountStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int cityCount = Integer.parseInt(cityCountStr);

                    if (cityCount < 2) {
                        System.out.println("Ошибка: для работы с городами нужно минимум 2 города");
                        break;
                    }

                    City[] cities = new City[cityCount];
                    for (int i = 0; i < cityCount; i++) {
                        System.out.print("Введите название города " + (i + 1) + ": ");
                        String cityName = scanner.nextLine();

                        if (cityName == null || cityName.trim().isEmpty()) {
                            System.out.println("Ошибка: название города не может быть пустым");
                            i--;
                            continue;
                        }

                        try {
                            cities[i] = new City(cityName);
                        } catch (Exception e) {
                            System.out.println("Ошибка создания города: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.print("Сколько дорог добавить? ");
                    String roadCountStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(roadCountStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int roadCount = Integer.parseInt(roadCountStr);

                    for (int i = 0; i < roadCount; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        System.out.print("Введите номер первого города (1-" + cityCount + "): ");
                        String city1Str = scanner.nextLine();
                        if (!valid.isPositiveInteger(city1Str)) {
                            System.out.println("Ошибка: номер города должен быть целым числом");
                            i--;
                            continue;
                        }
                        int city1Index = Integer.parseInt(city1Str) - 1;

                        if (city1Index < 0 || city1Index >= cityCount) {
                            System.out.println("Ошибка: номер города должен быть от 1 до " + cityCount);
                            i--;
                            continue;
                        }

                        System.out.print("Введите номер второго города (1-" + cityCount + "): ");
                        String city2Str = scanner.nextLine();
                        if (!valid.isPositiveInteger(city2Str)) {
                            System.out.println("Ошибка: номер города должен быть целым числом");
                            i--;
                            continue;
                        }
                        int city2Index = Integer.parseInt(city2Str) - 1;

                        if (city2Index < 0 || city2Index >= cityCount) {
                            System.out.println("Ошибка: номер города должен быть от 1 до " + cityCount);
                            i--;
                            continue;
                        }

                        if (city1Index == city2Index) {
                            System.out.println("Ошибка: нельзя создать дорогу из города в себя");
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        String distStr = scanner.nextLine();
                        if (!valid.isPositiveInteger(distStr)) {
                            System.out.println("Ошибка: расстояние должно быть положительным целым числом");
                            i--;
                            continue;
                        }
                        int distance = Integer.parseInt(distStr);

                        try {
                            cities[city1Index].addWays(cities[city2Index], distance);
                            System.out.println("Дорога добавлена успешно!");
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Результат:");
                    City.printRoads(cities);
                    break;

                case 3:
                    System.out.println("3 Задание");
                    City A = new City("A");
                    City B = new City("B");
                    City C = new City("C");
                    City D = new City("D");
                    City E = new City("E");
                    City F = new City("F");

                    try {
                        A.addWays(B, 5);
                        A.addWays(D, 6);
                        A.addWays(F, 1);
                        B.addWays(C, 3);
                        C.addWays(D, 4);
                        D.addWays(E, 2);
                        E.addWays(F, 2);
                    } catch (Exception e) {
                        System.out.println("Ошибка создания карты: " + e.getMessage());
                        break;
                    }

                    System.out.println("Карта городов:");
                    City.printRoads(A, B, C, D, E, F);

                    System.out.print("Введите номер начального города (A=1, B=2, C=3, D=4, E=5, F=6): ");
                    String startStr = scanner.nextLine();
                    if (!valid.isPositiveInteger(startStr)) {
                        System.out.println("Ошибка: номер города должен быть целым числом");
                        break;
                    }
                    int startIndex = Integer.parseInt(startStr) - 1;

                    System.out.print("Введите номер конечного города (A=1, B=2, C=3, D=4, E=5, F=6): ");
                    String endStr = scanner.nextLine();
                    if (!valid.isPositiveInteger(endStr)) {
                        System.out.println("Ошибка: номер города должен быть целым числом");
                        break;
                    }
                    int endIndex = Integer.parseInt(endStr) - 1;

                    City[] allCities = {A, B, C, D, E, F};

                    if (startIndex < 0 || startIndex >= allCities.length || endIndex < 0 || endIndex >= allCities.length) {
                        System.out.println("Ошибка: номер города должен быть от 1 до " + allCities.length);
                        break;
                    }

                    try {
                        Way route = new Way(allCities[startIndex], allCities[endIndex]);
                        System.out.println("Маршрут: " + route.toString());
                    } catch (Exception e) {
                        System.out.println("Ошибка создания маршрута: " + e.getMessage());
                    }
                    break;

                case 4:
                    System.out.println("4 Задание");
                    System.out.print("Введите числитель: ");
                    String numStr4 = scanner.nextLine();
                    if (!valid.isInteger(numStr4)) {
                        System.out.println("Ошибка: числитель должен быть целым числом");
                        break;
                    }

                    System.out.print("Введите знаменатель: ");
                    String denStr4 = scanner.nextLine();
                    if (!valid.isInteger(denStr4)) {
                        System.out.println("Ошибка: знаменатель должен быть целым числом");
                        break;
                    }

                    int num = Integer.parseInt(numStr4);
                    int den = Integer.parseInt(denStr4);

                    if (den == 0) {
                        System.out.println("Ошибка: знаменатель не может быть нулем");
                        break;
                    }

                    try {
                        Fraction fraction = new Fraction(num, den);
                        System.out.println("Создана дробь: " + fraction);
                        System.out.println("Десятичное значение: " + fraction.decimal());
                    } catch (Exception e) {
                        System.out.println("Ошибка создания дроби: " + e.getMessage());
                    }
                    break;

                case 5:
                    System.out.println("5 Задание");
                    System.out.print("Введите числитель: ");
                    String numStr5 = scanner.nextLine();
                    if (!valid.isInteger(numStr5)) {
                        System.out.println("Ошибка: числитель должен быть целым числом");
                        break;
                    }

                    System.out.print("Введите знаменатель: ");
                    String denStr5 = scanner.nextLine();
                    if (!valid.isInteger(denStr5)) {
                        System.out.println("Ошибка: знаменатель должен быть целым числом");
                        break;
                    }

                    int num5 = Integer.parseInt(numStr5);
                    int den5 = Integer.parseInt(denStr5);

                    if (den5 == 0) {
                        System.out.println("Ошибка: знаменатель не может быть нулем");
                        break;
                    }

                    try {
                        Fraction f5 = new Fraction(num5, den5);
                        Number numberf = f5;

                        System.out.println("Дробь: " + f5);
                        System.out.println("Реализация методов Number:");
                        System.out.println("intValue(): " + f5.intValue());
                        System.out.println("longValue(): " + f5.longValue());
                        System.out.println("floatValue(): " + f5.floatValue());
                        System.out.println("doubleValue(): " + f5.doubleValue());
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 6:
                    System.out.println("6 Задание");
                    System.out.print("Сколько чисел сложить? ");
                    String sumCountStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(sumCountStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int sumCount = Integer.parseInt(sumCountStr);

                    if (sumCount < 1) {
                        System.out.println("Ошибка: нужно сложить хотя бы одно число");
                        break;
                    }

                    Fraction[] sumValues = new Fraction[sumCount];
                    for (int i = 0; i < sumCount; i++) {
                        System.out.println("Число " + (i + 1) + ":");
                        System.out.print("Введите значение: ");
                        String input = scanner.nextLine();

                        try {
                            if (input.contains("/")) {
                                String[] parts = input.split("/");
                                if (parts.length != 2) {
                                    System.out.println("Ошибка: неверный формат дроби");
                                    i--;
                                    continue;
                                }
                                if (!valid.isInteger(parts[0]) || !valid.isInteger(parts[1])) {
                                    System.out.println("Ошибка: числитель и знаменатель должны быть целыми числами");
                                    i--;
                                    continue;
                                }
                                int numerator = Integer.parseInt(parts[0]);
                                int denominator = Integer.parseInt(parts[1]);
                                if (denominator == 0) {
                                    System.out.println("Ошибка: знаменатель не может быть нулем");
                                    i--;
                                    continue;
                                }
                                sumValues[i] = new Fraction(numerator, denominator);
                            } else if (input.contains(".")) {
                                if (!valid.isAnyNumber(input)) {
                                    System.out.println("Ошибка: введите корректное число");
                                    i--;
                                    continue;
                                }
                                double value = Double.parseDouble(input);
                                sumValues[i] = new Fraction(value);
                            } else {
                                if (!valid.isInteger(input)) {
                                    System.out.println("Ошибка: введите целое число");
                                    i--;
                                    continue;
                                }
                                int value = Integer.parseInt(input);
                                sumValues[i] = new Fraction(value);
                            }
                        } catch (Exception e) {
                            System.out.println("Ошибка создания числа: " + e.getMessage());
                            i--;
                        }
                    }

                    try {
                        double result = Fraction.sumNumbers(sumValues);
                        System.out.print("Сумма: ");
                        for (int i = 0; i < sumValues.length; i++) {
                            System.out.print(sumValues[i]);
                            if (i < sumValues.length - 1) {
                                System.out.print(" + ");
                            }
                        }
                        System.out.println(" = " + result);
                    } catch (Exception e) {
                        System.out.println("Ошибка вычисления суммы: " + e.getMessage());
                    }
                    break;

                case 7:
                    System.out.println("7 Задание");
                    System.out.print("Сколько городов создать для сравнения? ");
                    String compareCountStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(compareCountStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int compareCount = Integer.parseInt(compareCountStr);

                    if (compareCount < 2) {
                        System.out.println("Для сравнения нужно минимум 2 города!");
                        break;
                    }

                    City[] compareCities = new City[compareCount];
                    for (int i = 0; i < compareCount; i++) {
                        System.out.print("Введите название города " + (i + 1) + ": ");
                        String name = scanner.nextLine();

                        if (name == null || name.trim().isEmpty()) {
                            System.out.println("Ошибка: название города не может быть пустым");
                            i--;
                            continue;
                        }

                        compareCities[i] = new City(name);
                    }

                    System.out.println("Созданные города:");
                    for (int i = 0; i < compareCities.length; i++) {
                        System.out.println((i + 1) + ". " + compareCities[i].getName());
                    }

                    System.out.println("Добавление дорог к городу " + compareCities[0].getName() + " ---");
                    System.out.print("Сколько дорог добавить? ");
                    String firstRoadsStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(firstRoadsStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int firstRoads = Integer.parseInt(firstRoadsStr);

                    for (int i = 0; i < firstRoads; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        StringBuilder availableCities = new StringBuilder();
                        for (int j = 1; j < compareCount; j++) {
                            if (!compareCities[0].hasWayTo(compareCities[j])) {
                                if (!availableCities.isEmpty()) availableCities.append(", ");
                                availableCities.append(j + 1);
                            }
                        }

                        if (availableCities.isEmpty()) {
                            System.out.println("Нет доступных городов для подключения!");
                            break;
                        }

                        System.out.print("К какому городу подключить? (введите номер " + availableCities + "): ");
                        String targetStr = scanner.nextLine();

                        if (!valid.isPositiveInteger(targetStr)) {
                            System.out.println("Ошибка: номер города должен быть целым числом");
                            i--;
                            continue;
                        }
                        int targetIndex = Integer.parseInt(targetStr) - 1;

                        if (targetIndex <= 0 || targetIndex >= compareCount || compareCities[0].hasWayTo(compareCities[targetIndex])) {
                            System.out.println("Ошибка: можно подключать только к городам " + availableCities);
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        String distStr = scanner.nextLine();
                        if (!valid.isPositiveInteger(distStr)) {
                            System.out.println("Ошибка: расстояние должно быть положительным целым числом");
                            i--;
                            continue;
                        }
                        int dist = Integer.parseInt(distStr);

                        try {
                            compareCities[0].addWays(compareCities[targetIndex], dist);
                            System.out.println("Дорога к " + compareCities[targetIndex].getName() + " добавлена");
                        } catch (Exception e) {
                            System.out.println("Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Добавление дорог к городу " + compareCities[1].getName());
                    System.out.print("Сколько дорог добавить? ");
                    String secondRoadsStr = scanner.nextLine();

                    if (!valid.isPositiveInteger(secondRoadsStr)) {
                        System.out.println("Ошибка: количество должно быть положительным целым числом");
                        break;
                    }
                    int secondRoads = Integer.parseInt(secondRoadsStr);

                    for (int i = 0; i < secondRoads; i++) {
                        System.out.println("Дорога " + (i + 1) + ":");

                        StringBuilder availableCities = new StringBuilder();
                        for (int j = 0; j < compareCount; j++) {
                            if (j != 1 && !compareCities[1].hasWayTo(compareCities[j])) {
                                if (!availableCities.isEmpty()) availableCities.append(", ");
                                availableCities.append(j + 1);
                            }
                        }

                        if (availableCities.isEmpty()) {
                            System.out.println("Нет доступных городов для подключения!");
                            break;
                        }

                        System.out.print("К какому городу подключить? (введите номер " + availableCities + "): ");
                        String targetStr = scanner.nextLine();

                        if (!valid.isPositiveInteger(targetStr)) {
                            System.out.println("Ошибка: номер города должен быть целым числом");
                            i--;
                            continue;
                        }
                        int targetIndex = Integer.parseInt(targetStr) - 1;

                        if (targetIndex < 0 || targetIndex >= compareCount || targetIndex == 1 || compareCities[1].hasWayTo(compareCities[targetIndex])) {
                            System.out.println("Ошибка: можно подключать только к городам " + availableCities);
                            i--;
                            continue;
                        }

                        System.out.print("Введите расстояние: ");
                        String distStr = scanner.nextLine();
                        if (!valid.isPositiveInteger(distStr)) {
                            System.out.println("Ошибка: расстояние должно быть положительным целым числом");
                            i--;
                            continue;
                        }
                        int dist = Integer.parseInt(distStr);

                        try {
                            compareCities[1].addWays(compareCities[targetIndex], dist);
                            System.out.println("✓ Дорога к " + compareCities[targetIndex].getName() + " добавлена");
                        } catch (Exception e) {
                            System.out.println("✗ Ошибка: " + e.getMessage());
                            i--;
                        }
                    }

                    System.out.println("Результат");
                    City.printRoads(compareCities);

                    System.out.println("Сравнение");
                    System.out.println(compareCities[0].getName() + " vs " + compareCities[1].getName() + ":");
                    System.out.println("• sameWays (по путям): " + compareCities[0].sameWays(compareCities[1]));
                    System.out.println("• equalsByWays (универсальное): " + compareCities[0].equalsByWays(compareCities[1]));
                    System.out.println("• equals (по имени): " + compareCities[0].equals(compareCities[1]));
                    break;

                case 8:
                    System.out.println("8 Задание");
                    String xStr = "";
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
                        double result8 = Power.calculatePower(xStr, yStr);
                        System.out.println("Результат: " + xStr + " ^ " + yStr + " = " + result8);
                    } catch (Exception e) {
                        System.out.println("Ошибка при вычислении: " + e.getMessage());
                    }
                    break;

                case 9:
                    System.out.println("9 Задание");
                    System.out.print("Введите числитель: ");
                    String numStr9 = scanner.nextLine();
                    if (!valid.isInteger(numStr9)) {
                        System.out.println("Ошибка: числитель должен быть целым числом");
                        break;
                    }

                    System.out.print("Введите знаменатель: ");
                    String denStr9 = scanner.nextLine();
                    if (!valid.isInteger(denStr9)) {
                        System.out.println("Ошибка: знаменатель должен быть целым числом");
                        break;
                    }

                    int num9 = Integer.parseInt(numStr9);
                    int den9 = Integer.parseInt(denStr9);

                    if (den9 == 0) {
                        System.out.println("Ошибка: знаменатель не может быть нулем");
                        break;
                    }

                    try {
                        Fraction orig = new Fraction(num9, den9);
                        Fraction clone = orig.clone();

                        System.out.println("Оригинал: " + orig);
                        System.out.println("Клон: " + clone);
                        System.out.println("Оригинал == Клон: " + (orig == clone));
                        System.out.println("Оригинал.equals(Клон): " + orig.equals(clone));

                        System.out.print("Введите дробь для сложения с клоном: ");
                        String addInput = scanner.nextLine();
                        Fraction addFraction;

                        if (addInput.contains("/")) {
                            String[] parts = addInput.split("/");
                            if (parts.length != 2 || !valid.isInteger(parts[0]) || !valid.isInteger(parts[1])) {
                                System.out.println("Ошибка: неверный формат дроби");
                                break;
                            }
                            int addNum = Integer.parseInt(parts[0]);
                            int addDen = Integer.parseInt(parts[1]);
                            if (addDen == 0) {
                                System.out.println("Ошибка: знаменатель не может быть нулем");
                                break;
                            }
                            addFraction = new Fraction(addNum, addDen);
                        } else {
                            if (!valid.isInteger(addInput)) {
                                System.out.println("Ошибка: введите целое число");
                                break;
                            }
                            addFraction = new Fraction(Integer.parseInt(addInput));
                        }

                        Fraction modifiedClone = clone.sum(addFraction);
                        System.out.println("Клон после изменения: " + modifiedClone);
                        System.out.println("Оригинал после изменения: " + orig);
                    } catch (Exception e) {
                        System.out.println("Ошибка: " + e.getMessage());
                    }
                    break;

                case 10:
                    System.out.println("Выход");
                    System.exit(0);
            }
        }
    }
}
