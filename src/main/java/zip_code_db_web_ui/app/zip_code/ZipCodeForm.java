package zip_code_db_web_ui.app.zip_code;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * 住所検索フォーム
 */
public class ZipCodeForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Size(min = 0, max = 7)
    @Pattern(regexp = "[0-9]{7}|")
    private String zipCode;

    @Size(min = 0, max = 10)
    private String prefecture;

    @Size(min = 0, max = 20)
    private String city;

    @Size(min = 0, max = 100)
    private String area;

    /**
     * @return zipCode 郵便番号を返す。
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode 郵便番号として設定する 7 桁の数列。
     */
    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    /**
     * @return prefecture 都道府県名を返す。
     */
    public String getPrefecture() {
        return prefecture;
    }

    /**
     * @param prefecture 都道府県名として設定する文字列を指定する。
     */
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    /**
     * @return city 市区町村名を返す。
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 市区町村名として設定する文字列を指定する。
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return area 町域名を返す。
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area 町域名として設定する文字列を指定する。
     */
    public void setArea(String area) {
        this.area = area;
    }
}
