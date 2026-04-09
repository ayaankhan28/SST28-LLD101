import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Checkpoint C: Single responsibility = validate a ParsedInput and return error messages.
// No console IO, no persistence. Fully unit-testable in isolation.
public class StudentValidator {

    // Configurable program list (Stretch goal: no need to edit the workflow to change this).
    private final List<String> allowedPrograms;

    public StudentValidator() {
        this.allowedPrograms = Arrays.asList("CSE", "AI", "SWE");
    }

    public StudentValidator(List<String> allowedPrograms) {
        this.allowedPrograms = allowedPrograms;
    }

    // Returns an empty list when valid; never throws.
    public List<String> validate(ParsedInput input) {
        List<String> errors = new ArrayList<>();
        if (input.name.isBlank())
            errors.add("name is required");
        if (input.email.isBlank() || !input.email.contains("@"))
            errors.add("email is invalid");
        if (input.phone.isBlank() || !input.phone.chars().allMatch(Character::isDigit))
            errors.add("phone is invalid");
        if (!allowedPrograms.contains(input.program))
            errors.add("program is invalid");
        return errors;
    }
}
