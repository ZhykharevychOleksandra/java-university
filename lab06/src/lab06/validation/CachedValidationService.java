package lab06.validation;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CachedValidationService implements Validator {

    private final Map<Class<?>, ClassValidationMetadata> cache = new ConcurrentHashMap<>();

    @Override
    public ValidationResult validate(Object entity) {
        if (entity == null) {
            throw new IllegalArgumentException("entity must not be null");
        }
        ClassValidationMetadata meta = cache.computeIfAbsent(entity.getClass(), ClassValidationMetadata::build);
        ValidationResult result = new ValidationResult();

        try {
            if (meta.classRule != null) {
                String error = ValidationRules.checkSalaryByExperience(
                        meta.experienceField.get(entity), meta.salaryField.get(entity), meta.classRule);
                if (error != null) {
                    result.addError(meta.className + "(" + meta.classRule.experienceField()
                            + ", " + meta.classRule.salaryField() + ")", error);
                }
            }
            for (ClassValidationMetadata.FieldRule rule : meta.fieldRules) {
                Object value = rule.field.get(entity);
                if (rule.range != null) {
                    String error = ValidationRules.checkRange(value, rule.range);
                    if (error != null) {
                        result.addError(rule.field.getName(), error);
                    }
                }
                if (rule.numeric != null) {
                    String error = ValidationRules.checkNumericFormat(value, rule.numeric);
                    if (error != null) {
                        result.addError(rule.field.getName(), error);
                    }
                }
            }
        } catch (IllegalAccessException e) {
            result.addError(meta.className, "немає доступу до поля: " + e);
        }
        return result;
    }

    // кількість типів у кеші (для демонстрації)
    public int cacheSize() {
        return cache.size();
    }
}