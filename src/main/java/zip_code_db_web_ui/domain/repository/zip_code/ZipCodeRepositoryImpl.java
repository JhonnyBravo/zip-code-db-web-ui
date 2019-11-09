package zip_code_db_web_ui.domain.repository.zip_code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import zip_code_db_cli.domain.model.ZipCode;

public class ZipCodeRepositoryImpl implements ZipCodeRepository {
    @Override
    public String getSql(ZipCode zipcode) {
        final List<String> conditions = new ArrayList<>();
        final StringBuilder buffer = new StringBuilder();

        buffer.append("SELECT * FROM t_zip_code");

        if (!zipcode.getZipCode().isEmpty()) {
            buffer.append(" WHERE zip_code=?");
        } else if (!zipcode.getPrefecture().isEmpty() || !zipcode.getCity().isEmpty() || !zipcode.getArea().isEmpty()) {
            if (!zipcode.getPrefecture().isEmpty()) {
                conditions.add("prefecture=?");
            }

            if (!zipcode.getCity().isEmpty()) {
                conditions.add("city LIKE ?");
            }

            if (!zipcode.getArea().isEmpty()) {
                conditions.add("area LIKE ?");
            }

            final String expr = String.join(" AND ", conditions);
            buffer.append(" WHERE ");
            buffer.append(expr);
        }

        buffer.append(" LIMIT 1000;");

        final String sql = new String(buffer);
        return sql;
    }

    @Override
    public List<ZipCode> find(Connection connection, ZipCode zipcode) throws Exception {
        final List<ZipCode> recordset = new ArrayList<>();
        ResultSet resultset = null;

        try (PreparedStatement query = connection.prepareStatement(getSql(zipcode))) {
            if (!zipcode.getZipCode().isEmpty()) {
                query.setString(1, zipcode.getZipCode());
            } else if (!zipcode.getPrefecture().isEmpty() || !zipcode.getCity().isEmpty()
                    || !zipcode.getArea().isEmpty()) {
                final List<String> values = new ArrayList<>();

                if (!zipcode.getPrefecture().isEmpty()) {
                    values.add(zipcode.getPrefecture());
                }

                if (!zipcode.getCity().isEmpty()) {
                    values.add(zipcode.getCity());
                }

                if (!zipcode.getArea().isEmpty()) {
                    values.add(zipcode.getArea());
                }

                int index = 0;

                for (final String value : values) {
                    index++;
                    query.setString(index, value);
                }
            }

            resultset = query.executeQuery();

            while (resultset.next()) {
                final ZipCode record = new ZipCode();

                record.setId(resultset.getLong("id"));
                record.setJisCode(resultset.getString("jis_code"));
                record.setZipCode(resultset.getString("zip_code"));

                record.setPrefecturePhonetic(resultset.getString("prefecture_phonetic"));
                record.setCityPhonetic(resultset.getString("city_phonetic"));
                record.setAreaPhonetic(resultset.getString("area_phonetic"));

                record.setPrefecture(resultset.getString("prefecture"));
                record.setCity(resultset.getString("city"));
                record.setArea(resultset.getString("area"));

                record.setUpdateFlag(resultset.getInt("update_flag"));
                record.setReasonFlag(resultset.getInt("reason_flag"));

                recordset.add(record);
            }
        } finally {
            if (resultset != null) {
                resultset.close();
            }
        }

        return recordset;
    }
}
