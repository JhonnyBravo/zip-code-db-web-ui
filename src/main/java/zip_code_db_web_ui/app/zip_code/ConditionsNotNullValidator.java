package zip_code_db_web_ui.app.zip_code;

import java.util.function.Predicate;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * {@link ZipCodeForm} を対象に相関チェックを実行する。
 */
public class ConditionsNotNullValidator
    implements ConstraintValidator<ConditionsNotNull, ZipCodeForm> {
  @Override
  public void initialize(ConditionsNotNull conditionsNotNull) {}

  private final Predicate<String> isNotNull = value -> {
    boolean result = false;

    if (value != null && !value.isEmpty()) {
      result = true;
    }

    return result;
  };

  /**
   * {@link ZipCodeForm} を対象に相関チェックを実行する。
   *
   * @param form {@link ZipCodeForm} を指定する。
   * @param context {@link ConstraintValidatorContext} を指定する。
   * @return 郵便番号、都道府県名、市区郡名、町域名いずれも指定されていない場合に false を返す。
   */
  @Override
  public boolean isValid(ZipCodeForm form, ConstraintValidatorContext context) {
    boolean result = true;

    if (!isNotNull.test(form.getZipCode()) && !isNotNull.test(form.getPrefecture())
        && !isNotNull.test(form.getCity()) && !isNotNull.test(form.getArea())) {
      result = false;
    }

    return result;
  }
}
