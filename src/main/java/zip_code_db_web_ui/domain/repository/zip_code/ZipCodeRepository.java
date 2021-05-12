package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * レコード検索用メソッドを管理する。
 */
@Repository
public interface ZipCodeRepository extends JpaRepository<ZipCode, Long> {
  /**
   * 郵便番号をキーにレコードを検索する。
   *
   * @param zipCode 検索対象とする郵便番号を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByZipCode(String zipCode);

  /**
   * 都道府県名をキーにレコードを検索する。
   *
   * @param prefecture 検索対象とする都道府県名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByPrefecture(String prefecture);

  /**
   * 市区郡名をキーにレコードを検索する。
   *
   * @param city 検索対象とする市区郡名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByCityLike(String city);

  /**
   * 町域名をキーにレコードを検索する。
   *
   * @param area 検索対象とする町域名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByAreaLike(String area);

  /**
   * 都道府県名・市区郡名・町域名をキーにレコードを検索する。
   *
   * @param prefecture 検索対象とする都道府県名を指定する。
   * @param city 検索対象とする市区郡名を指定する。
   * @param area 検索対象とする町域名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByPrefectureAndCityLikeAndAreaLike(String prefecture, String city, String area);

  /**
   * 都道府県名と市区郡名をキーにレコードを検索する。
   *
   * @param prefecture 検索対象とする都道府県名を指定する。
   * @param city 検索対象とする市区郡名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByPrefectureAndCityLike(String prefecture, String city);

  /**
   * 都道府県名と町域名をキーにレコードを検索する。
   *
   * @param prefecture 検索対象とする都道府県名を指定する。
   * @param area 検索対象とする町域名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByPrefectureAndAreaLike(String prefecture, String area);

  /**
   * 市区郡名と町域名をキーにレコードを検索する。
   *
   * @param city 検索対象とする市区郡名を指定する。
   * @param area 検索対象とする町域名を指定する。
   * @return recordset {@link ZipCode} の {@link List} を返す。
   */
  List<ZipCode> findByCityLikeAndAreaLike(String city, String area);
}
