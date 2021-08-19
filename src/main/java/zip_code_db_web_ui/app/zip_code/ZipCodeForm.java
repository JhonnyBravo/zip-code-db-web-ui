package zip_code_db_web_ui.app.zip_code;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import zip_code_db_web_ui.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;

/**
 * 郵便番号検索ページのコントローラ
 */
@Named
@RequestScoped
@ConditionsNotNull(groups = ZipCodeFormValidator.class)
public class ZipCodeForm implements Serializable {
  private static final long serialVersionUID = 1L;

  @Inject
  private ZipCodeService service;

  @Pattern(regexp = "[0-9]{7}|", groups = ZipCodeFormValidator.class)
  private String zipCode;
  @Size(min = 0, max = 10, groups = ZipCodeFormValidator.class)
  private String prefecture;
  @Size(min = 0, max = 100, groups = ZipCodeFormValidator.class)
  private String city;
  @Size(min = 0, max = 100, groups = ZipCodeFormValidator.class)
  private String area;

  private SelectItem[] prefectures;
  private List<ZipCode> searchResult;
  private String error;

  /**
   * 初期化処理を実行する。
   */
  @PostConstruct
  private void init() {
    List<String> recordset = new ArrayList<>();
    final List<SelectItem> items = new ArrayList<>();
    items.add(new SelectItem(""));

    try {
      recordset = service.findPrefectureAll();

      recordset.forEach(record -> {
        items.add(new SelectItem(record));
      });
    } catch (final Exception e) {
      e.printStackTrace();
    } finally {
      prefectures = items.toArray(new SelectItem[items.size()]);
    }
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

  /**
   * 都道府県名の一覧を返す。
   *
   * @return prefectures 都道府県名の一覧。
   */
  public SelectItem[] getPrefectures() {
    return prefectures;
  }

  /**
   * 住所検索の結果を返す。
   *
   * @return searchResult 検索結果を {@link ZipCode} の {@link List} に収めて返す。
   */
  public List<ZipCode> getSearchResult() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode(this.getZipCode());
    zipcode.setPrefecture(this.getPrefecture());
    zipcode.setCity(this.getCity());
    zipcode.setArea(this.getArea());

    this.searchResult = service.find(zipcode);
    return searchResult;
  }

  /**
   * 以下に該当する場合にエラーメッセージを返す。
   * <ul>
   * <li>検索結果が 0 件の場合</li>
   * <li>検索結果が 1000 件を超える場合</li>
   * </ul>
   *
   * @return error エラーメッセージを返す。
   */
  public String getError() {
    return error;
  }

  /**
   * ページ遷移を管理する。
   *
   * @return page
   *         <ul>
   *         <li>検索結果が 0 件の場合: index.xhtml へ戻す。</li>
   *         <li>検索結果が 1000 件を超える場合: index.xhtml へ戻す。</li>
   *         <li>上記に該当しない場合: search_result.xhtml へ遷移する。</li>
   *         </ul>
   */
  public String next() throws Exception {
    final List<ZipCode> result = this.getSearchResult();

    if (result.size() > 1000) {
      this.error = "該当する住所が 1000 件を超えました。検索範囲を狭めてください。";
      return "index.xhtml";
    } else if (result.size() == 0) {
      this.error = "該当する住所が見つかりませんでした。";
      return "index.xhtml";
    } else {
      return "search_result.xhtml";
    }
  }
}
