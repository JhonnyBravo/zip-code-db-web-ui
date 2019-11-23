package zip_code_db_web_ui.app.zip_code;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class ZipCodeValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return ZipCodeForm.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        if (errors.hasFieldErrors("zipCode")) {
            return;
        }

        final ZipCodeForm form = (ZipCodeForm) target;

        if (form.getZipCode().isEmpty() && form.getPrefecture().isEmpty() && form.getCity().isEmpty()
                && form.getArea().isEmpty()) {
            errors.rejectValue("zipCode", "ZipCodeValidator.ZipCodeForm.zipCode", "検索条件を指定してください。");
        }
    }

}
