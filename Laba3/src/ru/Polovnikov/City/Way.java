package ru.Polovnikov.City;

import java.util.HashMap;
import java.util.Map;

public class Way {
    private City start;
    private City finish;

    //Конструктор
    public Way(City start, City finish) {
        if (start == null || finish == null) {
            throw new RuntimeException("Ошибка, нет точки начала или конца");
        }

        this.start = start;
        this.finish = finish;
    }

    //Геттеры
    public City getStart() {
        return start;
    }

    public City getFinish() {
        return finish;
    }

    //Сеттеры
    public void setStart(City start) {
        if (start == null) {
            throw new RuntimeException("Ошибка, нет точки старта");
        }
        this.start = start;
    }

    public void setFinish(City finish) {
        if (finish == null) {
            throw new RuntimeException("Ошибка, нет точки конца");
        }
        this.finish = finish;
    }

    //Поиск пути
    public City[] findWay() {
        if (start.equals(finish)) {
            return new City[]{start};
        }

        Map<City, City> from = new HashMap<>();
        Map<City, Boolean> current = new HashMap<>();
        Map<City, Boolean> visited = new HashMap<>();

        from.put(start, null);
        current.put(start, true);
        visited.put(start, true);

        boolean found = false;

        while (!found && !current.isEmpty()) {
            Map<City, Boolean> next = new HashMap<>();
            for (City cur : current.keySet()) {
                for (City neighbor : cur.getWays().keySet()) {
                    if (!visited.containsKey(neighbor)) {
                        visited.put(neighbor, true);
                        from.put(neighbor, cur);
                        next.put(neighbor, true);

                        if (neighbor.equals(finish)) {
                            found = true;
                            break;
                        }
                    }
                }
                if (found)
                    break;
            }
            current = next;
        }
        if (found) {
            return build(from);
        }
        return new City[0];
    }

    //Построение пути от начала до конца
    public City[] build(Map<City, City> from) {
        int length = 0;
        City current = finish;
        while (current != null) {
            length++;
            current = from.get(current);
        }

        City[] path = new City[length];
        current = finish;
        for (int i = length - 1; i >= 0; i--) {
            path[i] = current;
            current = from.get(current);
        }

        return path;
    }

    //Вывод
    @Override
    public String toString() {
        City[] route = findWay();
        if (route.length == 0)
            return "Маршрут не найден";

        String result = "";
        for (int i = 0; i < route.length; i++) {
            if (i > 0) result += " -> ";
            result += route[i].getName();
        }
        return result;
    }
}

