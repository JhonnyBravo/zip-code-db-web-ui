package zip_code_db_web_ui.domain.model;

import java.io.Serializable;

public class Prefecture implements Serializable {
    private static final long serialVersionUID = 1L;

    private String label;
    private String value;
    private boolean selected;

    /**
     * @return label 都道府県セレクトボックスに表示するラベルを取得する。
     */
    public String getLabel() {
        return label;
    }

    /**
     * @param label 都道府県セレクトボックスに表示するラベルを指定する。
     */
    public void setLabel(String label) {
        this.label = label;
    }

    /**
     * @return value 都道府県セレクトボックスに設定するオプション値を取得する。
     */
    public String getValue() {
        return value;
    }

    /**
     * @param value 都道府県セレクトボックスに設定するオプション値を指定する。
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * @return selected オプション値が選択済みであるかどうかを真偽値で返す。
     */
    public boolean isSelected() {
        return selected;
    }

    /**
     * @param selected オプション値が選択済みであるかどうかを真偽値で指定する。
     *                 <ul>
     *                 <li>true: オプション値が選択済みであることを表す。</li>
     *                 <li>false: オプション値が選択されていないことを表す。</li>
     *                 </ul>
     */
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
