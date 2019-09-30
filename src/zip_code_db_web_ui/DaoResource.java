package zip_code_db_web_ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sql_resource.ConnectionResource;
import zip_code_db_cli.ZipCode;

public class DaoResource extends ConnectionResource {
    private final Logger logger;

    /**
     * @param property DB の接続情報を納めた Map オブジェクトを指定する。
     */
    public DaoResource(Map<String, String> property) {
        super(property);
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * @param value 判定対象とする文字列を指定する。
     * @return result
     *         <ul>
     *         <li>true: value が空文字または null であることを表す。</li>
     *         <li>true: value が空文字ではなく null でもないことを表す。</li>
     *         </ul>
     */
    protected boolean isEmpty(String value) {
        boolean result = false;

        if (value.equals("") || value == null) {
            result = true;
        }

        return result;
    }

    /**
     * SQL を動的に組み立てる。
     * 
     * @param model 検索対象とするレコードの情報を納めたモデルオブジェクトを指定する。
     * @return sql 生成された SQL を返す。
     */
    private String getSql(ZipCode model) {
        logger.info("SQL を生成しています......");

        final List<String> conditions = new ArrayList<String>();
        final StringBuilder buffer = new StringBuilder();

        buffer.append("SELECT * FROM t_zip_code");

        if (!isEmpty(model.getZipCode())) {
            buffer.append(" WHERE zip_code=?");
        } else if (!isEmpty(model.getPrefecture()) || !isEmpty(model.getCity()) || !isEmpty(model.getArea())) {
            if (!isEmpty(model.getPrefecture())) {
                conditions.add("prefecture=?");
            }

            if (!isEmpty(model.getCity())) {
                conditions.add("city LIKE ?");
            }

            if (!isEmpty(model.getArea())) {
                conditions.add("area LIKE ?");
            }

            final String expr = String.join(" AND ", conditions);
            buffer.append(" WHERE ");
            buffer.append(expr);
        }

        buffer.append(" LIMIT 1000;");

        final String sql = new String(buffer);
        logger.info(sql);
        return sql;
    }

    /**
     * レコードを検索する。
     * 
     * @param 検索対象とするレコードの情報を納めたモデルオブジェクトを指定する。
     * @return recordset 検索条件に合致するレコードを List 形式で返す。
     */
    public List<ZipCode> find(ZipCode model) throws SQLException {
        logger.info("DB 接続を試行しています......");
        final Connection cn = getConnection();

        ResultSet rs = null;
        final List<ZipCode> recordset = new ArrayList<ZipCode>();

        try (PreparedStatement query = cn.prepareStatement(getSql(model))) {
            logger.info("レコードを検索しています......");

            if (!isEmpty(model.getZipCode())) {
                query.setString(1, model.getZipCode());
            } else if (!isEmpty(model.getPrefecture()) || !isEmpty(model.getCity()) || !isEmpty(model.getArea())) {
                final List<String> values = new ArrayList<String>();

                if (!isEmpty(model.getPrefecture())) {
                    values.add(model.getPrefecture());
                }

                if (!isEmpty(model.getCity())) {
                    values.add(model.getCity());
                }

                if (!isEmpty(model.getArea())) {
                    values.add(model.getArea());
                }

                int index = 0;

                for (final String value : values) {
                    index++;
                    query.setString(index, value);
                }
            }

            rs = query.executeQuery();

            while (rs.next()) {
                final ZipCode record = new ZipCode();

                record.setId(rs.getLong("id"));
                record.setJisCode(rs.getString("jis_code"));
                record.setZipCode(rs.getString("zip_code"));
                record.setPrefecturePhonetic(rs.getString("prefecture_phonetic"));
                record.setCityPhonetic(rs.getString("city_phonetic"));
                record.setAreaPhonetic(rs.getString("area_phonetic"));
                record.setPrefecture(rs.getString("prefecture"));
                record.setCity(rs.getString("city"));
                record.setArea(rs.getString("area"));
                record.setUpdateFlag(rs.getInt("update_flag"));
                record.setReasonFlag(rs.getInt("reason_flag"));

                recordset.add(record);
            }
        } finally {
            if (rs != null) {
                rs.close();
            }

            cn.close();
        }

        logger.info(recordset.size() + " 件のレコードを取得しました。");
        return recordset;
    }
}
