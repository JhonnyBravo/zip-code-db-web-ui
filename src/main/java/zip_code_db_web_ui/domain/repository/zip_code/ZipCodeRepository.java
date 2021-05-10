package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.List;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * t_zip_code からのレコード取得を管理する。
 */
public interface ZipCodeRepository {
  /**
   * SQL 文を動的に生成する。
   *
   * @param zipcode パラメータとして渡す {@link ZipCode} を指定する。
   * @return query 動的に生成された SQL 文を返す。
   */
  String getSql(ZipCode zipcode);

  /**
   * レコード検索を実行する。
   *
   * @param zipcode パラメータとして渡す {@link ZipCode} オブジェクトを指定する。
   * @return recordset レコードの検索結果を {@link ZipCode} の {@link List} に格納して返す。
   */
  List<ZipCode> find(ZipCode zipcode);

}
