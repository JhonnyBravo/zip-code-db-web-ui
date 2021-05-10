package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import zip_code_db_web_ui.domain.model.ZipCode;

/**
 * レコード検索用メソッドを管理する。
 */
@Repository
public class ZipCodeRepositoryImpl implements ZipCodeRepository {
  @Autowired
  private NamedParameterJdbcTemplate template;
  private final Predicate<String> isNotEmpty = value -> {
    boolean result = false;

    if (value != null && !value.isEmpty()) {
      result = true;
    }

    return result;
  };

  @Override
  public String getSql(ZipCode model) {
    final List<String> conditions = new ArrayList<>();
    final StringBuilder buffer = new StringBuilder();

    buffer.append("SELECT * FROM t_zip_code");

    if (isNotEmpty.test(model.getZipCode())) {
      buffer.append(" WHERE zip_code = :zip_code");
    } else if (isNotEmpty.test(model.getPrefecture()) || isNotEmpty.test(model.getCity())
        || isNotEmpty.test(model.getArea())) {
      if (isNotEmpty.test(model.getPrefecture())) {
        conditions.add("prefecture = :prefecture");
      }

      if (isNotEmpty.test(model.getCity())) {
        conditions.add("city LIKE :city");
      }

      if (isNotEmpty.test(model.getArea())) {
        conditions.add("area LIKE :area");
      }

      final String expr = String.join(" AND ", conditions);
      buffer.append(" WHERE ");
      buffer.append(expr);
    }

    final String sql = new String(buffer);
    return sql;
  }

  @Override
  public List<ZipCode> find(ZipCode model) {
    final String sql = getSql(model);
    final MapSqlParameterSource params = new MapSqlParameterSource();

    if (isNotEmpty.test(model.getZipCode())) {
      params.addValue("zip_code", model.getZipCode());
    } else if (isNotEmpty.test(model.getPrefecture()) || isNotEmpty.test(model.getCity())
        || isNotEmpty.test(model.getArea())) {
      if (isNotEmpty.test(model.getPrefecture())) {
        params.addValue("prefecture", model.getPrefecture());
      }

      if (isNotEmpty.test(model.getCity())) {
        params.addValue("city", model.getCity());
      }

      if (isNotEmpty.test(model.getArea())) {
        params.addValue("area", model.getArea());
      }
    }

    final RowMapper<ZipCode> mapper = new BeanPropertyRowMapper<>(ZipCode.class);
    final List<ZipCode> recordset = template.query(sql, params, mapper);
    return recordset;
  }
}
