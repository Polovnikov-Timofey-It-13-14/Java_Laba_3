package ru.Polovnikov.City;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class City {
    private String name;
    private Map<City, Integer> ways;

    public City(String name) {
        this.name = name;
        this.ways = new HashMap<>();
    }

    public City(String name, Map<City, Integer> roads) {
        this(name);
        if (roads != null) {
            for (City targetCity : roads.keySet()) {
                int cost = roads.get(targetCity);
                addWays(targetCity, cost);
            }
        }
    }

    public static City createWithRoads(String name, Object... roadData) {
        City city = new City(name);
        if (roadData.length % 2 != 0) {
            return city; // Нечетное количество аргументов - ошибка
        }

        for (int i = 0; i < roadData.length; i += 2) {
            if (roadData[i] instanceof City targetCity && roadData[i+1] instanceof Integer) {
                int distance = (Integer) roadData[i+1];
                city.addWays(targetCity, distance);
            }
        }
        return city;
    }

    public String getName() {
        return name;
    }

    public Map<City, Integer> getWays() {
        return new HashMap<>(ways);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addWays(City city, int way) {
        if (city == null) {
            throw new RuntimeException("Имя не может быть пустым");
        }
        if (city == this) {
            throw new RuntimeException("Нельзя добавить дорогу из себя к себе");
        }
        if (way <= 0) {
            throw new RuntimeException("Длина дороги не может быть меньше или равной 0");
        }

        if (ways.containsKey(city) || city.ways.containsKey(this)) {
            throw new RuntimeException("Дорога между " + name + " и " + city.name + " уже существует");
        }

        ways.put(city, way);
        city.ways.put(this, way);
    }

    public void removeRoad(City city) {
        if (city == null) {
            throw new RuntimeException("Имя не может быть пустым");
        }

        if (ways.containsKey(city) || city.ways.containsKey(this)) {
            throw new RuntimeException("Дорога между " + name + " и " + city.name + " не найдена");
        }

        ways.remove(city);
        city.ways.remove(this);
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

    @Override
    public boolean equals(Object obj) {
        /*
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;
        return Objects.equals(name, city.name);

         */
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        City city = (City) obj;

        // Сравниваем по наборам путей, а не по именам
        return sameWays(city);
    }

    @Override
    public int hashCode() {
        //return Objects.hash(name);
        int result = 1;
        for (Map.Entry<City, Integer> entry : ways.entrySet()) {
            result = 31 * result + entry.getKey().getName().hashCode();
            result = 31 * result + entry.getValue().hashCode();
        }
        return result;
    }

    public boolean sameWays(City city) {
        /*if (city == null)
            return false;

        int count = 0;

        for (City neighbor : ways.keySet()) {
            for (City otherNeighbor : city.ways.keySet()) {
                if (neighbor.getName().equals(otherNeighbor.getName())) {
                    count++;
                    break;
                }
            }
        }

        return count == ways.size() && count == city.ways.size();

         */
        if (city == null || this.ways.size() != city.ways.size())
            return false;

        // Проверяем все дороги текущего города
        for (Map.Entry<City, Integer> entry : this.ways.entrySet()) {
            City target = entry.getKey();
            Integer distance = entry.getValue();

            // Ищем дорогу к тому же городу с тем же расстоянием
            boolean found = false;
            for (Map.Entry<City, Integer> otherEntry : city.ways.entrySet()) {
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

    @Override
    public String toString() {
        String result = name;
        if (!ways.isEmpty()) {
            result += " -> ";
            boolean first = true;
            for (Map.Entry<City, Integer> road : ways.entrySet()) {
                if (!first) result += ", ";
                result += road.getKey().name + ":" + road.getValue();
                first = false;
            }
        }
        return result;
    }
}