package lab06.validation;

import lab06.annotations.NumericFormat;
import lab06.annotations.Range;
import lab06.annotations.SalaryByExperience;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

final class ClassValidationMetadata {

    // метадані одного поля, на якому є хоча б одна анотація валідації
    static final class FieldRule {
        final Field field;
        final Range range; // може бути null
        final NumericFormat numeric; // може бути null

        FieldRule(Field field, Range range, NumericFormat numeric) {
            this.field = field;
            this.range = range;
            this.numeric = numeric;
        }
    }

    final String className;
    final SalaryByExperience classRule; // може бути null
    final Field experienceField; // null, якщо classRule == null
    final Field salaryField; // null, якщо classRule == null
    final List<FieldRule> fieldRules;

    private ClassValidationMetadata(String className, SalaryByExperience classRule,
                                    Field experienceField, Field salaryField, List<FieldRule> fieldRules) {
        this.className = className;
        this.classRule = classRule;
        this.experienceField = experienceField;
        this.salaryField = salaryField;
        this.fieldRules = fieldRules;
    }

    // єдине місце, де виконуються "дорогі" виклики рефлексії
    static ClassValidationMetadata build(Class<?> type) {
        SalaryByExperience classRule = type.getAnnotation(SalaryByExperience.class);
        Field experienceField = null;
        Field salaryField = null;
        if (classRule != null) {
            try {
                experienceField = type.getDeclaredField(classRule.experienceField());
                salaryField = type.getDeclaredField(classRule.salaryField());
                experienceField.setAccessible(true);
                salaryField.setAccessible(true);
            } catch (NoSuchFieldException e) {
                throw new IllegalStateException(
                        "Некоректна анотація @SalaryByExperience на " + type.getName() + ": " + e.getMessage(), e);
            }
        }

        List<FieldRule> rules = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            Range range = field.getAnnotation(Range.class);
            NumericFormat numeric = field.getAnnotation(NumericFormat.class);
            if (range != null || numeric != null) {
                field.setAccessible(true);
                rules.add(new FieldRule(field, range, numeric));
            }
        }
        return new ClassValidationMetadata(type.getSimpleName(), classRule, experienceField, salaryField,
                Collections.unmodifiableList(rules));
    }
}

