package zip_code_db_web_ui;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * レコード検索用メソッドを管理する。
 */
@Repository
public class ZipCodeRepository {
    @Autowired
    private NamedParameterJdbcTemplate template;

    /**
     * @param model パラメータとして渡す ZipCode オブジェクトを指定する。
     * @return 動的に生成された SQL 文を返す。
     */
    private String getSql(ZipCode model) {
        final List<String> conditions = new ArrayList<String>();
        final StringBuilder buffer = new StringBuilder();

        buffer.append("SELECT * FROM t_zip_code");

        if (!model.getZipCode().isEmpty()) {
            buffer.append(" WHERE zip_code = :zip_code");
        } else if (!model.getPrefecture().isEmpty() || !model.getCity().isEmpty() || !model.getArea().isEmpty()) {
            if (!model.getPrefecture().isEmpty()) {
                conditions.add("prefecture = :prefecture");
            }

            if (!model.getCity().isEmpty()) {
                conditions.add("city LIKE :city");
            }

            if (!model.getArea().isEmpty()) {
                conditions.add("area LIKE :area");
            }

            final String expr = String.join(" AND ", conditions);
            buffer.append(" WHERE ");
            buffer.append(expr);
        }

        buffer.append(" LIMIT 1000;");

        final String sql = new String(buffer);
        return sql;
    }

    /**
     * レコード検索を実行する。
     * 
     * @param model パラメータとして渡す ZipCode オブジェクトを指定する。
     * @return recordset レコードの検索結果をリストに格納して返す。
     */
    public List<ZipCode> find(ZipCode model) {
        final String sql = getSql(model);
        final MapSqlParameterSource params = new MapSqlParameterSource();

        if (!model.getZipCode().isEmpty()) {
            params.addValue("zip_code", model.getZipCode());
        } else if (!model.getPrefecture().isEmpty() || !model.getCity().isEmpty() || !model.getArea().isEmpty()) {
            if (!model.getPrefecture().isEmpty()) {
                params.addValue("prefecture", model.getPrefecture());
            }

            if (!model.getCity().isEmpty()) {
                params.addValue("city", model.getCity());
            }

            if (!model.getArea().isEmpty()) {
                params.addValue("area", model.getArea());
            }
        }

        final RowMapper<ZipCode> mapper = new BeanPropertyRowMapper<ZipCode>(ZipCode.class);
        final List<ZipCode> recordset = template.query(sql, params, mapper);
        return recordset;
    }
}
