package zip_code_db_web_ui.domain.repository.prefectures;

import java.sql.Connection;
import java.util.List;

import zip_code_db_web_ui.domain.model.Prefecture;

public interface PrefecturesRepository {
    /**
     * @param connection DB 接続に使用する Connection を指定する。
     * @param prefecture 現在選択されている都道府県を指定する。
     * @return prefectures 都道府県セレクトボックスの表示に使用するリストを返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public List<Prefecture> getPrefectures(Connection connection, String prefecture) throws Exception;
}
