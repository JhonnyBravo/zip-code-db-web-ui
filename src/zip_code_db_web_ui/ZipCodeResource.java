package zip_code_db_web_ui;

import java.sql.ResultSet;

/**
 * t_zip_code から ResultSet を取得する。
 */
public interface ZipCodeResource {
    /**
     * DB への接続を確立する。
     * 
     * @param path DB の接続情報を記載した設定ファイルのパスを指定する。
     */
    public void openConnection(String path);

    /**
     * DB への接続を切断する。
     */
    public void closeConnection();

    /**
     * DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @return ResultSet
     */
    public ResultSet findAll();

    /**
     * 郵便番号をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param zipCode 検索対象とする郵便番号を指定する。
     * @return ResultSet
     */
    public ResultSet findByZipCode(String zipCode);

    /**
     * 都道府県名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @return ResultSet
     */
    public ResultSet findByPrefecture(String prefecture);

    /**
     * 市区町村名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param city 検索対象とする市区町村名を指定する。
     * @return ResultSet
     */
    public ResultSet findByCityLike(String city);

    /**
     * 町域名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param area 検索対象とする町域名を指定する。
     * @return ResultSet
     */
    public ResultSet findByAreaLike(String area);

    /**
     * 都道府県名・市区町村・町域名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param city       検索対象とする市区町村を指定する。
     * @param area       検索対象とする町域名を指定する。
     * @return ResultSet
     */
    public ResultSet findByPrefectureAndCityLikeAndAreaLike(String prefecture, String city, String area);

    /**
     * 都道府県名・市区町村をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param city       検索対象とする市区町村を指定する。
     * @return ResultSet
     */
    public ResultSet findByPrefectureAndCityLike(String prefecture, String city);

    /**
     * 都道府県名・町域名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param prefecture 検索対象とする都道府県名を指定する。
     * @param area       検索対象とする町域名を指定する。
     * @return ResultSet
     */
    public ResultSet findByPrefectureAndAreaLike(String prefecture, String area);

    /**
     * 市区町村・町域名をキーに DB に登録されているレコードを最大 1000 件取得する。
     * 
     * @param city 検索対象とする市区町村を指定する。
     * @param area 検索対象とする町域名を指定する。
     * @return ResultSet
     */
    public ResultSet findByCityLikeAndAreaLike(String city, String area);
}
