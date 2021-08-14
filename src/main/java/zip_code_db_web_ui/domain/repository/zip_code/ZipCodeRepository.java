package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.List;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * レコード検索用メソッドを管理する。
 */
public interface ZipCodeRepository {
  /**
   * SQL 文を動的に生成する。
   *
   * @param zipcode 検索対象とする住所情報を収めた {@link ZipCode} を指定する。
   * @return sql 検索に使用する SQL 文を返す。
   */
  String getSql(ZipCode zipcode) throws Exception;

  /**
   * 条件に該当するレコードを検索する。
   *
   * @param zipcode 検索対象とする住所情報を収めた {@link ZipCode} を指定する。
   * @return recordset 条件に該当するレコードを取得する。
   */
  List<ZipCode> find(ZipCode zipcode) throws Exception;
}
