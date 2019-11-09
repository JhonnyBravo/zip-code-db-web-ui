package zip_code_db_web_ui.domain.repository.zip_code;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import zip_code_db_cli.domain.model.ZipCode;

@RunWith(Enclosed.class)
public class ZipCodeRepositoryTest {
    public static class パラメータを一つ指定する場合 {
        private ZipCodeRepository repository;

        @Before
        public void setUp() throws Exception {
            repository = new ZipCodeRepositoryImpl();
        }

        @Test
        public void パラメータを指定しない場合() {
            final ZipCode zipcode = new ZipCode();
            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code LIMIT 1000;"));
        }

        @Test
        public void 郵便番号を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 都道府県名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture=? LIMIT 1000;"));
        }

        @Test
        public void 市区郡名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("札幌市中央区");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE city LIKE ? LIMIT 1000;"));
        }

        @Test
        public void 町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE area LIKE ? LIMIT 1000;"));
        }
    }

    public static class パラメータを複数指定する場合 {
        private ZipCodeRepository repository;

        @Before
        public void setUp() throws Exception {
            repository = new ZipCodeRepositoryImpl();
        }

        @Test
        public void 市区郡名と町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE city LIKE ? AND area LIKE ? LIMIT 1000;"));
        }

        @Test
        public void 都道府県名と町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture=? AND area LIKE ? LIMIT 1000;"));
        }

        @Test
        public void 都道府県名と市区郡名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? LIMIT 1000;"));
        }

        @Test
        public void 都道府県名_市区郡名_町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql,
                    is("SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? AND area LIKE ? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号と町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号と市区郡名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setCity("札幌市中央区");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号_市区郡名_町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号と都道府県名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setZipCode("0640941");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号_都道府県名_町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名_町域名を指定した場合() {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            final String sql = repository.getSql(zipcode);
            assertThat(sql, is("SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;"));
        }
    }
}
