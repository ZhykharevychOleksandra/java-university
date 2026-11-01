package lab06.validation;

import lab06.annotations.NumericFormat;
import lab06.annotations.Range;
import lab06.annotations.SalaryByExperience;

final class ValidationRules {

    private ValidationRules() {
    }

    static String checkRange(Object value, Range range) {
        if (!(value instanceof Number)) {
            return custom(range.message(), "очікується числове значення, отримано: " + value);
        }
        double v = ((Number) value).doubleValue();
        if (v < range.min() || v > range.max()) {
            return custom(range.message(),
                    "значення " + value + " поза діапазоном [" + range.min() + "; " + range.max() + "]");
        }
        return null;
    }

    static String checkNumericFormat(Object value, NumericFormat format) {
        if (!(value instanceof String)) {
            return custom(format.message(), "рядок не задано (null)");
        }
        String s = (String) value;
        boolean ok = s.length() == format.length();
        for (int i = 0; ok && i < s.length(); i++) {
            char c = s.charAt(i);
            ok = c >= '0' && c <= '9';
        }
        if (!ok) {
            return custom(format.message(),
                    "очікується рівно " + format.length() + " цифр, отримано '" + s + "'");
        }
        return null;
    }

    static String checkSalaryByExperience(Object experience, Object salary, SalaryByExperience rule) {
        if (!(experience instanceof Number) || !(salary instanceof Number)) {
            return custom(rule.message(), "поля стажу та окладу мають бути числовими");
        }
        int years = ((Number) experience).intValue();
        double pay = ((Number) salary).doubleValue();
        if (years > rule.minYears() && pay < rule.minSalary()) {
            return custom(rule.message(),
                    "стаж " + years + " р. (понад " + rule.minYears() + ") вимагає оклад не нижче "
                            + rule.minSalary() + ", а поточний оклад " + pay);
        }
        return null;
    }

    private static String custom(String userMessage, String defaultMessage) {
        return userMessage == null || userMessage.isEmpty() ? defaultMessage : userMessage;
    }
}
