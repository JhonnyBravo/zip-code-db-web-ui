package zip_code_db_web_ui.domain.service.prefectures;

import java.util.List;

import java_itamae_connection.domain.service.connection.ConnectionService;
import zip_code_db_web_ui.domain.model.Prefecture;

public abstract class PrefecturesService extends ConnectionService {
    /**
     * @param prefecture 現在選択されている都道府県を指定する。
     * @return prefectures 都道府県セレクトボックスに使用するリストを返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public abstract List<Prefecture> getPrefectures(String prefecture) throws Exception;
}
