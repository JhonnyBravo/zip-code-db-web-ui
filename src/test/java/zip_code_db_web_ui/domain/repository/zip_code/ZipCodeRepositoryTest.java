package zip_code_db_web_ui.domain.repository.zip_code;

import static org.junit.Assert.assertThat;

import static org.hamcrest.CoreMatchers.is;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import zip_code_db_web_ui.domain.model.ZipCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZipCodeRepositoryTest {
    @Autowired
    private ZipCodeRepository repository;

    // パラメータを指定しない場合
    @Test
    public void testGetSqlパラメータを指定しない場合() {
        final ZipCode zipcode = new ZipCode();
        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code"));
    }

    @Test
    public void testFindパラメータを指定しない場合() {
        final ZipCode zipcode = new ZipCode();
        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(124568));
    }

    // 都道府県名のみ指定する場合
    @Test
    public void testGetSql都道府県名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture = :prefecture"));
    }

    @Test
    public void testFind都道府県名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(8252));
    }

    // 市区郡名のみ指定する場合
    @Test
    public void testGetSql市区郡名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setCity("札幌市%");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE city LIKE :city"));
    }

    @Test
    public void testFind市区郡名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setCity("札幌市%");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(823));
    }

    // 町域名のみ指定する場合
    @Test
    public void testGetSql町域名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setArea("大通%");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE area LIKE :area"));
    }

    @Test
    public void testFind町域名のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setArea("大通%");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(50));
    }

    // 郵便番号のみ指定する場合
    @Test
    public void testGetSql郵便番号のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0800030");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code = :zip_code"));
    }

    @Test
    public void testFind郵便番号のみ指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0800030");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0800030"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("帯広市"));
        assertThat(record.getArea(), is("大通北"));
    }

    // 都道府県名と市区郡名を指定する場合
    @Test
    public void testGetSql都道府県名と市区郡名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市中央区");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture = :prefecture AND city LIKE :city"));
    }

    @Test
    public void testFind都道府県名と市区郡名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市中央区");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(89));
    }

    // 都道府県名と町域名を指定する場合
    @Test
    public void testGetSql都道府県名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setArea("大通%");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture = :prefecture AND area LIKE :area"));
    }

    @Test
    public void testFind都道府県名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setArea("大通%");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(38));
    }

    // 都道府県名と市区郡名と町域名を指定する場合
    @Test
    public void testGetSql都道府県名と市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通%");

        final String sql = repository.getSql(zipcode);
        assertThat(sql,
                is("SELECT * FROM t_zip_code WHERE prefecture = :prefecture AND city LIKE :city AND area LIKE :area"));
    }

    @Test
    public void testFind都道府県名と市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通%");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(3));
    }

    // 市区郡名と町域名を指定する場合
    @Test
    public void testGetSql市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通東");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE city LIKE :city AND area LIKE :area"));
    }

    @Test
    public void testFind市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通東");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }

    // 郵便番号と都道府県名を指定する場合
    @Test
    public void testGetSql郵便番号と都道府県名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setPrefecture("北海道");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code = :zip_code"));
    }

    @Test
    public void testFind郵便番号と都道府県名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setPrefecture("北海道");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }

    // 郵便番号と市区郡名を指定する場合
    @Test
    public void testGetSql郵便番号と市区郡名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setCity("札幌市中央区");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code = :zip_code"));
    }

    @Test
    public void testFind郵便番号と市区郡名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setCity("札幌市中央区");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }

    // 郵便番号と町域名を指定する場合
    @Test
    public void testGetSql郵便番号と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setArea("大通東");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code = :zip_code"));
    }

    @Test
    public void testFind郵便番号と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setArea("大通東");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }

    // 郵便番号と都道府県名と市区郡名と町域名を指定する場合
    @Test
    public void testGetSql郵便番号と都道府県名と市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通東");

        final String sql = repository.getSql(zipcode);
        assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code = :zip_code"));
    }

    @Test
    public void testFind郵便番号と都道府県名と市区郡名と町域名を指定する場合() {
        final ZipCode zipcode = new ZipCode();
        zipcode.setZipCode("0600041");
        zipcode.setPrefecture("北海道");
        zipcode.setCity("札幌市%");
        zipcode.setArea("大通東");

        final List<ZipCode> recordset = repository.find(zipcode);
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }
}
