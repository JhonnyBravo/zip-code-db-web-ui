package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;
import zip_code_db_cli.domain.model.ZipCode;

/**
 * 住所検索を実行するサービス
 */
public interface ZipCodeService {
  /**
   * 住所検索を実行し、結果を {@link ZipCode} の {@link List} に変換して返す。
   *
   * @param zipcode 検索条件として渡す {@link ZipCode} を指定する。
   * @return recordset 検索結果を収めた {@link ZipCode} の {@link List} に変換して返す。
   */
  List<ZipCode> find(ZipCode zipcode);

  /**
   * 都道府県名の一覧を返す。
   *
   * @return recordset 都道府県名の一覧
   */
  List<String> findPrefectureAll();
}
