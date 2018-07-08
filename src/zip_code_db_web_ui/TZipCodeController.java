package zip_code_db_web_ui;

import java.util.ArrayList;
import java.util.List;

import mysql_resource.MySqlController;

/**
 * t_zip_code テーブルからのデータ取得を管理する。
 */
public class TZipCodeController extends MySqlController {
    private String zipCode = null;
    private String prefecture = null;
    private String city = null;
    private String area = null;

    /**
    * @param path DB 接続に使用する属性値を記述したプロパティファイルのパスを指定する。
    */
    public TZipCodeController(String path) {
        super(path);
    }

    /**
    * @return zipCode 検索対象とする郵便番号を返す。
    */
    public String getZipCode() {
        return zipCode;
    }

    /**
    * @param zipCode 検索対象とする郵便番号を指定する。
    */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
    * @return prefecture 検索対象とする都道府県名を返す。
    */
    public String getPrefecture() {
        return prefecture;
    }

    /**
    * @param prefecture 検索対象とする都道府県名を指定する。
    */
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    /**
    * @return city 検索対象とする市区町村名を返す。
    */
    public String getCity() {
        return city;
    }

    /**
    * @param city 検索対象とする市区町村名を指定する。
    */
    public void setCity(String city) {
        this.city = city;
    }

    /**
    * @return area 検索対象とする町域名を返す。
    */
    public String getArea() {
        return area;
    }

    /**
    * @param area 検索対象とする町域名を指定する。
    */
    public void setArea(String area) {
        this.area = area;
    }

    /**
    * PreparedStatement へ渡す SQL を生成する。
    * @return sql PreparedStatement へ渡す SQL を返す。
    */
    public String getSql() {
        String sql = "SELECT * FROM t_zip_code";

        List<String> paramList = new ArrayList<String>();

        if (this.zipCode != null) {
            paramList.add("zip_code=?");
        }

        if (this.prefecture != null) {
            paramList.add("prefecture=?");
        }

        if (this.city != null) {
            paramList.add("city LIKE ?");
        }

        if (this.area != null) {
            paramList.add("area LIKE ?");
        }

        String paramString = null;

        if (paramList.size() > 0) {
            paramString = String.join(" AND ", paramList);
            sql = sql + " WHERE " + paramString;
        }

        return sql + " LIMIT 1000;";
    }

}
