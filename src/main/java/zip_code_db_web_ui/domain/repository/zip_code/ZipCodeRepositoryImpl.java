package zip_code_db_web_ui.domain.repository.zip_code;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import zip_code_db_cli.domain.model.ZipCode;

public class ZipCodeRepositoryImpl implements ZipCodeRepository {
    private final Logger logger;

    public ZipCodeRepositoryImpl() {
        logger = LoggerFactory.getLogger(getClass());
    }

    /**
     * 文字列が null または空文字であるかどうかを確認する。
     *
     * @param value チェック対象とする文字列を指定する。
     * @return result
     *         <ul>
     *         <li>true: 文字列が null または空文字であることを表す。</li>
     *         <li>false: 文字列が null でも空文字でもないことを表す。</li>
     *         </ul>
     */
    private boolean isEmpty(String value) {
        boolean result = false;

        if (value == null || value.isEmpty()) {
            result = true;
        }

        return result;
    }

    @Override
    public String getSql(ZipCode zipcode) {
        final List<String> conditions = new ArrayList<>();
        final StringBuilder buffer = new StringBuilder();

        buffer.append("SELECT * FROM t_zip_code");

        if (!isEmpty(zipcode.getZipCode())) {
            buffer.append(" WHERE zip_code=?");
        } else if (!isEmpty(zipcode.getPrefecture()) || !isEmpty(zipcode.getCity()) || !isEmpty(zipcode.getArea())) {
            if (!isEmpty(zipcode.getPrefecture())) {
                conditions.add("prefecture=?");
            }

            if (!isEmpty(zipcode.getCity())) {
                conditions.add("city LIKE ?");
            }

            if (!isEmpty(zipcode.getArea())) {
                conditions.add("area LIKE ?");
            }

            final String expr = String.join(" AND ", conditions);
            buffer.append(" WHERE ");
            buffer.append(expr);
        }

        logger.info("SQL を生成しています......");
        final String sql = new String(buffer);

        logger.info(sql);
        return sql;
    }

    @Override
    public List<ZipCode> find(Connection connection, ZipCode zipcode) throws Exception {
        final List<ZipCode> recordset = new ArrayList<>();
        ResultSet resultset = null;

        try (PreparedStatement query = connection.prepareStatement(getSql(zipcode))) {
            if (!isEmpty(zipcode.getZipCode())) {
                query.setString(1, zipcode.getZipCode());
            } else if (!isEmpty(zipcode.getPrefecture()) || !isEmpty(zipcode.getCity())
                    || !isEmpty(zipcode.getArea())) {
                final List<String> values = new ArrayList<>();

                if (!isEmpty(zipcode.getPrefecture())) {
                    values.add(zipcode.getPrefecture());
                }

                if (!isEmpty(zipcode.getCity())) {
                    values.add(zipcode.getCity());
                }

                if (!isEmpty(zipcode.getArea())) {
                    values.add(zipcode.getArea());
                }

                int index = 0;

                for (final String value : values) {
                    index++;
                    query.setString(index, value);
                }
            }

            logger.info("レコードを検索しています......");
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

            logger.info(recordset.size() + " 件のレコードを取得しました。");
        } finally {
            if (resultset != null) {
                resultset.close();
            }
        }

        return recordset;
    }
}
