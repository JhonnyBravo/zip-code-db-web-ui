package zip_code_db_web_ui.app.zip_code;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Retention(RUNTIME)
@Target({ TYPE, ANNOTATION_TYPE })
@Constraint(validatedBy = { ConditionsNotNullValidator.class })
public @interface ConditionsNotNull {
    String message() default "{zip_code_db_web_ui.app.zip_code.SearchCondition}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    String field();

    @Documented
    @Retention(RUNTIME)
    @Target({ TYPE, ANNOTATION_TYPE })
    @interface List {
        ConditionsNotNull[] value();
    }
}
