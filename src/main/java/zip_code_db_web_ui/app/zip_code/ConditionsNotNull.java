package zip_code_db_web_ui.app.zip_code;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * {@link ZipCodeForm} を対象に相関チェックを実行する。郵便番号、都道府県名、市区郡名、町域名いずれも入力されていない場合にエラーとする。
 */
@Documented
@Retention(RUNTIME)
@Target(TYPE)
@Constraint(validatedBy = {ConditionsNotNullValidator.class})
public @interface ConditionsNotNull {
  String message() default "{zip_code_db_web_ui.app.zip_code.ConditionsNotNull}";

  Class<?>[] groups() default {};

  Class<? extends Payload>[] payload() default {};
}
