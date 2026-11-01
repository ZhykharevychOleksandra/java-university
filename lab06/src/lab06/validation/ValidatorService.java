package lab06.validation;

import lab06.annotations.NumericFormat;
import lab06.annotations.Range;
import lab06.annotations.SalaryByExperience;
import java.lang.reflect.Field;

public class ValidatorService implements Validator {
    @Override
    public ValidationResult validate(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        ValidationResult result = new ValidationResult();
        Class<?> type = entity.getClass();

        // 1. валідація рівня класу
        SalaryByExperience classRule = type.getAnnotation(SalaryByExperience.class);
        if (classRule != null) {
            try {
                Field experienceField = type.getDeclaredField(classRule.experienceField());
                Field salaryField = type.getDeclaredField(classRule.salaryField());
                experienceField.setAccessible(true);
                salaryField.setAccessible(true);
                String error = ValidationRules.checkSalaryByExperience(
                        experienceField.get(entity), salaryField.get(entity), classRule);
                if (error != null) {
                    result.addError(type.getSimpleName() + "(" + classRule.experienceField()
                            + ", " + classRule.salaryField() + ")", error);
                }
            } catch (ReflectiveOperationException e) {
                result.addError(type.getSimpleName(), "помилка рефлексії: " + e);
            }
        }

        // 2. валідація полів (публічні й закриті)
        for (Field field : type.getDeclaredFields()) {
            Range range = field.getAnnotation(Range.class);
            NumericFormat format = field.getAnnotation(NumericFormat.class);
            if (range == null && format == null) {
                continue;
            }
            try {
                field.setAccessible(true);
                Object value = field.get(entity);
                if (range != null) {
                    String error = ValidationRules.checkRange(value, range);
                    if (error != null) {
                        result.addError(field.getName(), error);
                    }
                }
                if (format != null) {
                    String error = ValidationRules.checkNumericFormat(value, format);
                    if (error != null) {
                        result.addError(field.getName(), error);
                    }
                }
            } catch (IllegalAccessException e) {
                result.addError(field.getName(), "немає доступу до поля: " + e);
            }
        }
        return result;
    }
}
