package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * t_zip_code からのレコード取得を管理する。
 */
public interface ZipCodeService {
  /**
   * 住所検索を実行する。
   *
   * @param zipcode 検索条件として受け取る {@link ZipCode} を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> find(ZipCode zipcode);
}
