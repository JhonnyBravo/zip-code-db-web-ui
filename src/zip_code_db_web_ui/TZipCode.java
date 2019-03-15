package zip_code_db_web_ui;

/**
 * t_zip_code
 */
public class TZipCode {
    private String zipCode;
    private String prefecture;
    private String city;
    private String area;

    /**
     * @return zipCode 郵便番号を返す。
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
     * @return prefecture 都道府県を返す。
     */
    public String getPrefecture() {
        return prefecture;
    }

    /**
     * @param prefecture 都道府県を指定する。
     */
    public void setPrefecture(String prefecture) {
        this.prefecture = prefecture;
    }

    /**
     * @return city 市区町村を返す。
     */
    public String getCity() {
        return city;
    }

    /**
     * @param city 市区町村を指定する。
     */
    public void setCity(String city) {
        this.city = city;
    }

    /**
     * @return area 町域を返す。
     */
    public String getArea() {
        return area;
    }

    /**
     * @param area 町域を指定する。
     */
    public void setArea(String area) {
        this.area = area;
    }
}
