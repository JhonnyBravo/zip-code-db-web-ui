package zip_code_db_web_ui.app.zip_code;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

/**
 * {@link ZipCodeForm} を対象に相関チェックを実行する。
 */
public class ConditionsNotNullValidator implements ConstraintValidator<ConditionsNotNull, Object> {
  private String field;
  private String message;

  @Override
  public void initialize(ConditionsNotNull constraintAnnotation) {
    field = constraintAnnotation.field();
    message = constraintAnnotation.message();
  }

  /**
   * {@link ZipCodeForm} を対象に相関チェックを実行し、結果を真偽値で返す。
   *
   * @param value {@link ZipCodeForm} を指定する。
   * @param context {@link ConstraintValidatorContext} を指定する。
   * @return result 郵便番号・都道府県名・市区郡名・町域名いずれも指定されていない場合に false を返す。
   */
  @Override
  public boolean isValid(Object value, ConstraintValidatorContext context) {
    boolean result = true;
    final ZipCodeForm form = (ZipCodeForm) value;

    if (form.getZipCode().isEmpty() && form.getPrefecture().isEmpty() && form.getCity().isEmpty()
        && form.getArea().isEmpty()) {
      context.disableDefaultConstraintViolation();
      context.buildConstraintViolationWithTemplate(message).addPropertyNode(field)
          .addConstraintViolation();
      result = false;
    }

    return result;
  }
}
