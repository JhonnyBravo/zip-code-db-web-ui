package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import zip_code_db_web_ui.app.zip_code.Config;
import zip_code_db_web_ui.domain.model.ZipCode;

@Stateless
public class ZipCodeRepositoryImpl implements ZipCodeRepository {
  @Inject
  @Config("unitName")
  private String unitName;
  private EntityManagerFactory factory;
  private EntityManager manager;

  @PostConstruct
  private void init() {
    this.factory = Persistence.createEntityManagerFactory(unitName);
    this.manager = factory.createEntityManager();
  }

  @PreDestroy
  private void destroy() {
    this.manager.close();
    this.factory.close();
  }

  private final Predicate<String> isNotEmpty = value -> {
    boolean result = false;

    if (value != null && !value.isEmpty()) {
      result = true;
    }

    return result;
  };

  @Override
  public String getSql(ZipCode zipcode) throws Exception {
    final List<String> conditions = new ArrayList<>();
    final StringBuilder buffer = new StringBuilder();

    buffer.append("SELECT e FROM ZipCode e");

    if (isNotEmpty.test(zipcode.getZipCode())) {
      buffer.append(" WHERE e.zipCode=:zip_code");
    } else if (isNotEmpty.test(zipcode.getPrefecture()) || isNotEmpty.test(zipcode.getCity())
        || isNotEmpty.test(zipcode.getArea())) {
      if (isNotEmpty.test(zipcode.getPrefecture())) {
        conditions.add("e.prefecture=:prefecture");
      }

      if (isNotEmpty.test(zipcode.getCity())) {
        conditions.add("e.city LIKE :city");
      }

      if (isNotEmpty.test(zipcode.getArea())) {
        conditions.add("e.area LIKE :area");
      }

      final String expr = String.join(" AND ", conditions);

      buffer.append(" WHERE ");
      buffer.append(expr);
    }

    final String sql = new String(buffer);
    return sql;
  }

  @Override
  public List<ZipCode> find(ZipCode zipcode) throws Exception {
    final String sql = this.getSql(zipcode);
    final TypedQuery<ZipCode> query = manager.createQuery(sql, ZipCode.class);

    if (isNotEmpty.test(zipcode.getZipCode())) {
      query.setParameter("zip_code", zipcode.getZipCode());
    } else if (isNotEmpty.test(zipcode.getPrefecture()) || isNotEmpty.test(zipcode.getCity())
        || isNotEmpty.test(zipcode.getArea())) {
      if (isNotEmpty.test(zipcode.getPrefecture())) {
        query.setParameter("prefecture", zipcode.getPrefecture());
      }

      if (isNotEmpty.test(zipcode.getCity())) {
        query.setParameter("city", zipcode.getCity());
      }

      if (isNotEmpty.test(zipcode.getArea())) {
        query.setParameter("area", zipcode.getArea());
      }
    }

    final List<ZipCode> recordset = query.getResultList();
    return recordset;
  }

  @Override
  public List<String> findPrefectureAll() throws Exception {
    final String sql = "SELECT prefectures.prefecture FROM ("
        + "SELECT DISTINCT prefecture, prefecture_phonetic FROM t_zip_code ORDER BY prefecture_phonetic"
        + ") AS prefectures";
    final Query query = manager.createNativeQuery(sql);
    final List<String> recordset = query.getResultList();
    return recordset;
  }
}
