package zip_code_db_web_ui.domain.repository.prefectures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zip_code_db_web_ui.domain.model.Prefecture;

public class PrefecturesRepositoryImpl implements PrefecturesRepository {

    @Override
    public List<Prefecture> getPrefectures(Connection connection, String prefecture) throws Exception {
        final List<Prefecture> prefectures = new ArrayList<>();
        ResultSet resultset = null;

        try (PreparedStatement query = connection
                .prepareStatement("SELECT DISTINCT prefecture FROM t_zip_code ORDER BY prefecture_phonetic;")) {
            resultset = query.executeQuery();

            while (resultset.next()) {
                final Prefecture p = new Prefecture();

                p.setLabel(resultset.getString("prefecture"));
                p.setValue(resultset.getString("prefecture"));

                if (resultset.getString("prefecture").equals(prefecture)) {
                    p.setSelected(true);
                }

                prefectures.add(p);
            }
        } finally {
            if (resultset != null) {
                resultset.close();
            }
        }

        return prefectures;
    }
}
