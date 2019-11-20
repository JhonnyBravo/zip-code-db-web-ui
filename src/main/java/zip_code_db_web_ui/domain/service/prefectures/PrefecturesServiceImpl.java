package zip_code_db_web_ui.domain.service.prefectures;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java_itamae_connection.domain.model.ConnectionInfo;
import zip_code_db_web_ui.domain.model.Prefecture;
import zip_code_db_web_ui.domain.repository.prefectures.PrefecturesRepository;
import zip_code_db_web_ui.domain.repository.prefectures.PrefecturesRepositoryImpl;

public class PrefecturesServiceImpl extends PrefecturesService {
    private final ConnectionInfo cnInfo;
    private final PrefecturesRepository pr;

    /**
     * @param cnInfo DB の接続情報を納めた ConnectionInfo を指定する。
     */
    public PrefecturesServiceImpl(ConnectionInfo cnInfo) {
        this.cnInfo = cnInfo;
        pr = new PrefecturesRepositoryImpl();
    }

    @Override
    public List<Prefecture> getPrefectures(String prefecture) throws Exception {
        List<Prefecture> prefectures = new ArrayList<>();

        try (Connection connection = getConnection(cnInfo)) {
            prefectures = pr.getPrefectures(connection, prefecture);
        }

        return prefectures;
    }
}
