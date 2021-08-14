package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * レコード検索用メソッドを管理する。
 */
public interface ZipCodeService {
  /**
   * 条件に該当するレコードを検索する。
   *
   * @param zipcode 検索対象とする住所情報を収めた {@link ZipCode} を指定する。
   * @return recordset 条件に該当するレコードを取得する。
   */
  List<ZipCode> find(ZipCode zipcode) throws Exception;
}
