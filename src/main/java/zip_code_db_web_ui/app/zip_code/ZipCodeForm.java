package zip_code_db_web_ui.app.zip_code;

import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import java.io.Serializable;

/**
 * 住所検索ページから送信されたフォームデータを格納する為のエンティティ。
 */
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
   * 郵便番号を返す。
   *
   * @return 郵便番号
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * 郵便番号を設定する。
   *
   * @param zipCode 郵便番号として設定する文字列を指定する。
   */
  public void setZipCode(String zipCode) {
    this.zipCode = zipCode;
  }

  /**
   * 都道府県名を返す。
   *
   * @return prefecture 都道府県名
   */
  public String getPrefecture() {
    return prefecture;
  }

  /**
   * 都道府県名を設定する。
   *
   * @param prefecture 都道府県名として設定する文字列を指定する。
   */
  public void setPrefecture(String prefecture) {
    this.prefecture = prefecture;
  }

  /**
   * 市区郡名を返す。
   *
   * @return city 市区郡名
   */
  public String getCity() {
    return city;
  }

  /**
   * 市区郡名を設定する。
   *
   * @param city 市区郡名として設定する文字列を指定する。
   */
  public void setCity(String city) {
    this.city = city;
  }

  /**
   * 町域名を返す。
   *
   * @return area 町域名
   */
  public String getArea() {
    return area;
  }

  /**
   * 町域名を設定する。
   *
   * @param area 町域名として設定する文字列を指定する。
   */
  public void setArea(String area) {
    this.area = area;
  }
}
