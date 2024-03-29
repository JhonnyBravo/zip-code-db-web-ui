package zip_code_db_web_ui.app.zip_code;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * リクエストパラメータからモデルオブジェクトを生成する。
 */
@Component
public class ZipCodeHelper {
  /**
   * {@link HttpServletRequest} からリクエストパラメータを受け取って {@link ZipCode} へ変換して返す。
   *
   * @param request 検索フォームから送信された {@link HttpServletRequest} を受け取る。
   * @return zipcode 変換された {@link ZipCode} を返す。
   */
  public ZipCode convertToZipCode(HttpServletRequest request) {
    final ZipCode zipcode = new ZipCode();

    zipcode.setZipCode(request.getParameter("zipCode"));
    zipcode.setPrefecture(request.getParameter("prefecture"));
    zipcode.setCity(request.getParameter("city"));
    zipcode.setArea(request.getParameter("area"));

    return zipcode;
  }
}
