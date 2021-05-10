package zip_code_db_web_ui.app.zip_code;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

/**
 * {@link ZipCodeForm} を対象に相関チェックを実行する。
 */
@Component
public class ZipCodeValidator implements Validator {

  @Override
  public boolean supports(Class<?> clazz) {
    return ZipCodeForm.class.isAssignableFrom(clazz);
  }

  /**
   * 郵便番号、都道府県名、市区郡名、町域名いずれも指定されていない場合にエラーを発生させる。
   */
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
