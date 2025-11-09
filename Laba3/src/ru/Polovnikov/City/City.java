package ru.Polovnikov.City;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
    private String name;
    private Map<City, Integer> ways;

    public City(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Название города не может быть пустым");
        }
        this.name = name;
        this.ways = new HashMap<>();
    }

    public City(String name, Map<City, Integer> roads) {
        this(name);
        if (roads != null) {
            for (Map.Entry<City, Integer> entry : roads.entrySet()) {
                addWays(entry.getKey(), entry.getValue());
            }
        }
    }

    public String getName() {
        return name;
    }

    public Map<City, Integer> getWays() {
        return new HashMap<>(ways);
    }

    public void addWays(City city, int distance) {
        validateCity(city);
        if (distance <= 0) {
            throw new IllegalArgumentException("Расстояние должно быть положительным");
        }

        // Проверка существования дороги
        if (ways.containsKey(city)) {
            throw new IllegalArgumentException("Дорога между " + name + " и " + city.name + " уже существует");
        }

        // Добавляем дорогу в обе стороны
        this.ways.put(city, distance);
        city.ways.put(this, distance);
    }

    public void removeWay(City city) {
        validateCity(city);

        // Проверка существования дороги
        if (!ways.containsKey(city)) {
            throw new IllegalArgumentException("Дорога между " + name + " и " + city.name + " не существует");
        }

        // Удаляем дорогу в обе стороны
        this.ways.remove(city);
        city.ways.remove(this);
    }

    public boolean hasWayTo(City city) {
        return ways.containsKey(city);
    }

    public Integer getDistanceTo(City city) {
        return ways.get(city);
    }

    private void validateCity(City city) {
        if (city == null) {
            throw new IllegalArgumentException("Город не может быть null");
        }
        if (city == this) {
            throw new IllegalArgumentException("Нельзя создать дорогу из города в себя");
        }
    }

    public static void printRoads(City... cities) {
        for (City city : cities) {
            System.out.print("   " + city.getName() + " → ");
            Map<City, Integer> roads = city.getWays();
            if (roads.isEmpty()) {
                System.out.println("Нет дорог");
            } else {
                boolean first = true;
                for (Map.Entry<City, Integer> road : roads.entrySet()) {
                    if (!first) System.out.print(", ");
                    System.out.print(road.getKey().getName() + " Расстояние:" + road.getValue());
                    first = false;
                }
                System.out.println();
            }
        }
    }

    public boolean sameWays(City other) {
        if (other == null || this.ways.size() != other.ways.size()) {
            return false;
        }

        for (Map.Entry<City, Integer> entry : this.ways.entrySet()) {
            City target = entry.getKey();
            Integer distance = entry.getValue();

            boolean found = false;
            for (Map.Entry<City, Integer> otherEntry : other.ways.entrySet()) {
                if (otherEntry.getKey().getName().equals(target.getName()) &&
                        otherEntry.getValue().equals(distance)) {
                    found = true;
                    break;
                }
            }

            if (!found) return false;
        }

        return true;
    }

    public boolean equalsByWays(Object obj) {
        if (obj == null) return false;
        if (obj instanceof City other) {
            return sameWays(other);
        }
        return false;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return Objects.equals(name, city.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(name);
        if (!ways.isEmpty()) {
            sb.append(" -> ");
            boolean first = true;
            for (Map.Entry<City, Integer> entry : ways.entrySet()) {
                if (!first) sb.append(", ");
                sb.append(entry.getKey().name).append(":").append(entry.getValue());
                first = false;
            }
        }
        return sb.toString();
    }
}
