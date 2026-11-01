package lab06.validation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// результат валідації: прапор успішності та список повідомлень з іменами полів
public class ValidationResult {
    private final List<String> errors = new ArrayList<>();

    public boolean isValid() {
        return errors.isEmpty();
    }

    public List<String> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public void addError(String fieldName, String message) {
        errors.add(fieldName + ": " + message);
    }
}