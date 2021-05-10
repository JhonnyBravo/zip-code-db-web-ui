package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * t_zip_code からのレコード取得を管理する。
 */
public interface ZipCodeService {
  /**
   * 検索条件に合致するレコードを取得する。
   *
   * @param zipcode 検索パラメータとして受け取る {@link ZipCode} を指定する。
   * @return recordset 住所検索を実行し、結果を {@link ZipCode} の {@link List} に納めて返す。
   */
  List<ZipCode> find(ZipCode zipcode);
}
