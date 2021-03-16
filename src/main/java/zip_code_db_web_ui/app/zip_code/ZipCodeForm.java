package zip_code_db_web_ui.app.zip_code;

import java.io.Serializable;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@ConditionsNotNull(field = "conditions")
public class ZipCodeForm implements Serializable {
    private static final long serialVersionUID = 1L;

    @Pattern(regexp = "[0-9]{7}|")
    private String zipCode;
    @Size(min = 0, max = 10)
    private String prefecture;
    @Size(min = 0, max = 100)
    private String city;
    @Size(min = 0, max = 100)
    private String area;

    /**
     * @return 郵便番号を返す。
     */
    public String getZipCode() {
        return zipCode;
    }

    /**
     * @param zipCode 郵便番号を指定する。
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
     * @param prefecture 都道府県名を指定する。
     */
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    /**
     * @return city 市区郡名を返す。
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 市区郡名を指定する。
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
     * @param area 町域名を指定する。
     */
    public void setArea(String area) {
        this.area = area;
    }
}
