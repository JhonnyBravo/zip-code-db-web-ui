package zip_code_db_web_ui.app.zip_code;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class ConditionsNotNullValidator implements ConstraintValidator<ConditionsNotNull, Object> {
    private String field;
    private String message;

    @Override
    public void initialize(ConditionsNotNull constraintAnnotation) {
        field = constraintAnnotation.field();
        message = constraintAnnotation.message();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        boolean result = true;
        final ZipCodeForm form = (ZipCodeForm) value;

        if (form.getZipCode().isEmpty() && form.getPrefecture().isEmpty() && form.getCity().isEmpty()
                && form.getArea().isEmpty()) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addPropertyNode(field).addConstraintViolation();
            result = false;
        }

        return result;
    }
}
