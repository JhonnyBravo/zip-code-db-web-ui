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
   * 郵便番号を返す。
   *
   * @return zipCode 郵便番号
   */
  public String getZipCode() {
    return zipCode;
  }

  /**
   * 郵便番号を設定する。
   *
   * @param zipCode 郵便番号として設定する 7 桁の数列。
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
   * @param prefecture 都道府県名として設定する文字列
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
   * @param city 市区郡名として設定する文字列
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
   * @param area 町域名として設定する文字列
   */
  public void setArea(String area) {
    this.area = area;
  }
}
