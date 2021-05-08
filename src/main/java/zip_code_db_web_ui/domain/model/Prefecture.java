package zip_code_db_web_ui.domain.model;

import java.io.Serializable;

/**
 * 都道府県名セレクトボックスの情報を管理するエンティティ。
 */
public class Prefecture implements Serializable {
  private static final long serialVersionUID = 1L;

  private String label;
  private String value;
  private boolean selected;

  /**
   * 都道府県名セレクトボックスに表示するラベルを返す。
   *
   * @return label 都道府県セレクトボックスに表示するラベル
   */
  public String getLabel() {
    return label;
  }

  /**
   * 都道府県名セレクトボックスに表示するラベルを設定する。
   *
   * @param label ラベルとして設定する文字列を指定する。
   */
  public void setLabel(String label) {
    this.label = label;
  }

  /**
   * 都道府県名セレクトボックスに設定するオプション値を返す。
   *
   * @return value 都道府県セレクトボックスに設定するオプション値
   */
  public String getValue() {
    return value;
  }

  /**
   * 都道府県名セレクトボックスに設定するオプション値を設定する。
   *
   * @param value オプション値として設定する文字列を指定する。
   */
  public void setValue(String value) {
    this.value = value;
  }

  /**
   * オプション値が選択済みであるかどうかを真偽値で返す。
   *
   * @return selected オプション値が選択済みであるかどうかを表す真偽値
   */
  public boolean isSelected() {
    return selected;
  }

  /**
   * オプション値が選択済みであるかどうかを設定する。
   *
   * @param selected オプション値の状態を表す真偽値
   *        <ul>
   *        <li>true: オプション値が選択済みであることを表す。</li>
   *        <li>false: オプション値が選択されていないことを表す。</li>
   *        </ul>
   */
  public void setSelected(boolean selected) {
    this.selected = selected;
  }
}
