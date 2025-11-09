package ru.Polovnikov.Valid;

import java.util.Set;

public class Valid {

    // Проверяет, что строка состоит только из натуральных чисел
    public boolean isNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Проверяет, что строка является числом (целым или дробным, отрицательным или положительным)
    public boolean isAnyNumber(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        boolean hasDecimalPoint = false;
        boolean hasDigit = false;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);

            if (i == 0 && c == '-') {
                continue;
            }
            else if (c == '.' && !hasDecimalPoint) {
                hasDecimalPoint = true;
            }
            else if (c >= '0' && c <= '9') {
                hasDigit = true;
            }
            else {
                return false;
            }
        }

        return hasDigit;
    }

    // Проверяет, что строка является целым числом (отрицательным, нулем или положительным)
    public boolean isInteger(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        int startIndex = 0;
        if (str.charAt(0) == '-') {
            if (str.length() == 1){
                return false;
            }
            startIndex = 1;
        }

        // Проверка на ведущий ноль (кроме числа 0)
        if (str.charAt(startIndex) == '0' && str.length() > startIndex + 1) {
            return false;
        }

        for (int i = startIndex; i < str.length(); i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }

    // Проверяет, что строка является положительным целым числом
    public boolean isPositiveInteger(String str) {
        return isInteger(str) && (str.charAt(0) != '-');
    }

    private static final Set<Character> RUSSIAN_CONSONANTS = Set.of(
            'б', 'в', 'г', 'д', 'ж', 'з', 'й', 'к', 'л', 'м',
            'н', 'п', 'р', 'с', 'т', 'ф', 'х', 'ц', 'ч', 'ш', 'щ',
            'Б', 'В', 'Г', 'Д', 'Ж', 'З', 'Й', 'К', 'Л', 'М',
            'Н', 'П', 'Р', 'С', 'Т', 'Ф', 'Х', 'Ц', 'Ч', 'Ш', 'Щ'
    );

    public boolean isRussianConsonant(char c) {
        return RUSSIAN_CONSONANTS.contains(c);
    }

    public boolean isRussianConsonant(String str) {
        return str != null && str.length() == 1 && isRussianConsonant(str.charAt(0));
    }
}
