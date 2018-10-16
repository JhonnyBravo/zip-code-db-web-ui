package zip_code_db_web_ui;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 * 検索用住所データを保持するテーブル。
 */
@Entity
@Table(name = "ZipCode")
public class ZipCode {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column
    @NotNull
    private Long id;

    @Column(length = 7, nullable = false)
    private String zipCode;

    @Column(length = 10, nullable = false)
    private String prefecture;

    @Column(length = 20, nullable = false)
    private String city;

    @Column(length = 100, nullable = true)
    private String area;

    /**
     * @return id レコード ID を返す。
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id レコード ID として設定する数値を指定する。
     */
    public void setId(Long id) {
        this.id = id;
    }

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
