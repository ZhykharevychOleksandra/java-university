package lab06.validation;

// спільний інтерфейс для ValidatorService та CachedValidationService
public interface Validator {
    ValidationResult validate(Object entity);
}
