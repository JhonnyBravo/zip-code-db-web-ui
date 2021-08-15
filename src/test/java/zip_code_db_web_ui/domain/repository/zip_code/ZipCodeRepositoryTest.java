package zip_code_db_web_ui.domain.repository.zip_code;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import java.util.List;
import javax.inject.Inject;
import org.jboss.weld.junit4.WeldInitiator;
import org.junit.Rule;
import org.junit.Test;
import zip_code_db_web_ui.domain.model.ZipCode;

public class ZipCodeRepositoryTest {
  @Inject
  private ZipCodeRepository repository;

  @Rule
  public WeldInitiator weld = WeldInitiator.from(ZipCodeRepositoryImpl.class).inject(this).build();

  // パラメータ指定なしの場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test1_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    final String expSql = "SELECT e FROM ZipCode e";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * {@link ZipCodeRepository#find(ZipCode)} 実行時にテーブルに登録されている全レコードを取得できること。
   */
  @Test
  public void test1_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(124568));
  }

  // 都道府県名のみ指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test2_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.prefecture=:prefecture";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 都道府県名をキーにレコードの検索ができること。
   */
  @Test
  public void test2_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(8252));
    assertThat(recordset.get(0).getPrefecture(), is(zipcode.getPrefecture()));
  }

  // 市区郡名のみ指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test3_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setCity("札幌市%");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.city LIKE :city";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 市区郡名をキーにレコードの検索ができること。
   */
  @Test
  public void test3_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setCity("札幌市%");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(823));
    assertThat(recordset.get(0).getCity().substring(0, 3), is("札幌市"));
  }

  // 町域名のみ指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test4_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setArea("大通%");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.area LIKE :area";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 町域名をキーにレコードの検索ができること。
   */
  @Test
  public void test4_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setArea("大通%");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(50));
    assertThat(recordset.get(0).getArea().substring(0, 2), is("大通"));
  }

  // 郵便番号のみ指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test5_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0800030");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.zipCode=:zip_code";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 郵便番号をキーにレコードの検索ができること。
   */
  @Test
  public void test5_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0800030");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0800030"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("帯広市"));
    assertThat(recordset.get(0).getArea(), is("大通北"));
  }

  // 都道府県名と市区郡名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test6_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市中央区");

    final String expSql =
        "SELECT e FROM ZipCode e WHERE e.prefecture=:prefecture AND e.city LIKE :city";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 都道府県名と市区郡名をキーにレコードの検索ができること。
   */
  @Test
  public void test6_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市中央区");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(89));
    assertThat(recordset.get(0).getPrefecture(), is(zipcode.getPrefecture()));
    assertThat(recordset.get(0).getCity(), is(zipcode.getCity()));
  }

  // 都道府県名と町域名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test7_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setArea("大通%");

    final String expSql =
        "SELECT e FROM ZipCode e WHERE e.prefecture=:prefecture AND e.area LIKE :area";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 都道府県名と町域名をキーにレコードの検索ができること。
   */
  @Test
  public void test7_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setArea("大通%");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(38));
    assertThat(recordset.get(0).getPrefecture(), is(zipcode.getPrefecture()));
    assertThat(recordset.get(0).getArea().substring(0, 2), is("大通"));
  }

  // 都道府県名・市区郡名・町域名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test8_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通%");

    final String expSql =
        "SELECT e FROM ZipCode e WHERE e.prefecture=:prefecture AND e.city LIKE :city AND e.area LIKE :area";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 都道府県名・市区郡名・町域名をキーにレコードの検索ができること。
   */
  @Test
  public void test8_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通%");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(3));
    assertThat(recordset.get(0).getPrefecture(), is(zipcode.getPrefecture()));
    assertThat(recordset.get(0).getCity().substring(0, 3), is("札幌市"));
    assertThat(recordset.get(0).getArea().substring(0, 2), is("大通"));
  }

  // 市区郡名と町域名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test9_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通東");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.city LIKE :city AND e.area LIKE :area";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 市区郡名と町域名をキーにレコードの検索ができること。
   */
  @Test
  public void test9_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通東");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0600041"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("札幌市中央区"));
    assertThat(recordset.get(0).getArea(), is("大通東"));
  }

  // 郵便番号と都道府県名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test10_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setPrefecture("北海道");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.zipCode=:zip_code";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 郵便番号をキーにレコードの検索ができること。
   */
  @Test
  public void test10_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setPrefecture("北海道");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0600041"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("札幌市中央区"));
    assertThat(recordset.get(0).getArea(), is("大通東"));
  }

  // 郵便番号と市区郡名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test11_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setCity("札幌市中央区");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.zipCode=:zip_code";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 郵便番号をキーにレコードの検索ができること。
   */
  @Test
  public void test11_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setCity("札幌市中央区");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0600041"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("札幌市中央区"));
    assertThat(recordset.get(0).getArea(), is("大通東"));
  }

  // 郵便番号と町域名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test12_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setArea("大通東");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.zipCode=:zip_code";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 郵便番号をキーにレコードの検索ができること。
   */
  @Test
  public void test12_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setArea("大通東");
    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0600041"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("札幌市中央区"));
    assertThat(recordset.get(0).getArea(), is("大通東"));
  }

  // 郵便番号・都道府県名・市区郡名・町域名を指定する場合
  /**
   * {@link ZipCodeRepository#getSql(ZipCode)} 実行時に SQL が想定通りに生成されること。
   */
  @Test
  public void test13_1() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通東");

    final String expSql = "SELECT e FROM ZipCode e WHERE e.zipCode=:zip_code";
    final String actSql = repository.getSql(zipcode);

    assertThat(actSql, is(expSql));
  }

  /**
   * 郵便番号をキーにレコードの検索ができること。
   */
  @Test
  public void test13_2() throws Exception {
    final ZipCode zipcode = new ZipCode();
    zipcode.setZipCode("0600041");
    zipcode.setPrefecture("北海道");
    zipcode.setCity("札幌市%");
    zipcode.setArea("大通東");

    final List<ZipCode> recordset = repository.find(zipcode);

    assertThat(recordset.size(), is(1));
    assertThat(recordset.get(0).getZipCode(), is("0600041"));
    assertThat(recordset.get(0).getPrefecture(), is("北海道"));
    assertThat(recordset.get(0).getCity(), is("札幌市中央区"));
    assertThat(recordset.get(0).getArea(), is("大通東"));
  }

  /**
   * {@link ZipCodeRepository#findPrefectureAll()} 実行時に都道府県名の一覧を取得できること。
   */
  @Test
  public void test14() throws Exception {
    final List<String> recordset = repository.findPrefectureAll();
    assertThat(recordset.size(), is(47));

    recordset.forEach(record -> {
      System.out.println(record);
    });
  }
}
