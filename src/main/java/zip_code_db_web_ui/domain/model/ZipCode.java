package zip_code_db_web_ui.domain.model;

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
@Table(name = "t_zip_code")
public class ZipCode {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column
  @NotNull
  private Long id;

  @Column(name = "zip_code", length = 7, nullable = false)
  private String zipCode;

  @Column(name = "prefecture", length = 10, nullable = false)
  private String prefecture;

  @Column(name = "city", length = 20, nullable = false)
  private String city;

  @Column(name = "area", length = 100, nullable = true)
  private String area;

  /**
   * レコード ID を返す。
   *
   * @return id レコード ID
   */
  public Long getId() {
    return id;
  }

  /**
   * レコード ID を設定する。
   *
   * @param id レコード ID として設定する数値を指定する。
   */
  public void setId(Long id) {
    this.id = id;
  }

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
   * @param zipCode 郵便番号として設定する 7 桁の数列を指定する。
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
