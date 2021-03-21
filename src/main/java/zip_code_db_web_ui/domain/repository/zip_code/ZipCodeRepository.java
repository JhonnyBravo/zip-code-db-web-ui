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
     * @param zipCode 検索対象とする郵便番号を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByZipCode(String zipCode);

    /**
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByPrefecture(String prefecture);

    /**
     * @param city 検索対象とする市区町村名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByCityLike(String city);

    /**
     * @param area 検索対象とする町域名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByAreaLike(String area);

    /**
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param city       検索対象とする市区町村名を指定する。
     * @param area       検索対象とする町域名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByPrefectureAndCityLikeAndAreaLike(String prefecture, String city, String area);

    /**
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param city       検索対象とする市区町村名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByPrefectureAndCityLike(String prefecture, String city);

    /**
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param area       検索対象とする町域名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByPrefectureAndAreaLike(String prefecture, String area);

    /**
     * @param city 検索対象とする市区町村名を指定する。
     * @param area 検索対象とする町域名を指定する。
     * @return List&lt;ZipCode&gt; 検索結果を返す。
     */
    public List<ZipCode> findByCityLikeAndAreaLike(String city, String area);
}
