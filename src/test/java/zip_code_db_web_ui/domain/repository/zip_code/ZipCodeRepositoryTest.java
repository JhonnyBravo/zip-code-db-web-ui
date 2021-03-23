package zip_code_db_web_ui.domain.repository.zip_code;

import static org.hamcrest.CoreMatchers.is;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import zip_code_db_cli.domain.model.ZipCode;

@RunWith(Enclosed.class)
public class ZipCodeRepositoryTest {
    public static class パラメータを指定しない場合 {
        private static SqlSessionFactory factory;
        private ZipCodeRepository repository;

        @BeforeClass
        public static void setUp() throws Exception {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Test
        public void testFind() {
            final ZipCode zipcode = new ZipCode();
            List<ZipCode> recordset = new ArrayList<>();

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(124568));
        }

        @Test
        public void testFindPrefectureAll() {
            List<String> recordset = new ArrayList<>();

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.findPrefectureAll();
            }

            assertThat(recordset.size(), is(47));
        }
    }

    public static class パラメータを一つ指定する場合 {
        private static SqlSessionFactory factory;
        private ZipCodeRepository repository;

        @BeforeClass
        public static void setUp() throws Exception {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Test
        public void 郵便番号を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 都道府県名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(8252));
        }

        @Test
        public void 市区郡名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("札幌市中央区");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(89));
        }

        @Test
        public void 町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(33));
        }
    }

    public static class パラメータを複数指定する場合 {
        private static SqlSessionFactory factory;
        private ZipCodeRepository repository;

        @BeforeClass
        public static void setUp() throws Exception {
            factory = new SqlSessionFactoryBuilder().build(Resources.getResourceAsStream("mybatis-config.xml"));
        }

        @Test
        public void 市区郡名と町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 都道府県名と町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(4));
        }

        @Test
        public void 都道府県名と市区郡名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(89));
        }

        @Test
        public void 都道府県名_市区郡名_町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号と町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号と市区郡名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setCity("札幌市中央区");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号_市区郡名_町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号と都道府県名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("北海道");
            zipcode.setZipCode("0640941");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号_都道府県名_町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名_町域名を指定した場合() {
            List<ZipCode> recordset = new ArrayList<>();

            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("0640941");
            zipcode.setPrefecture("北海道");
            zipcode.setCity("札幌市中央区");
            zipcode.setArea("旭ケ丘");

            try (SqlSession session = factory.openSession()) {
                repository = session.getMapper(ZipCodeRepository.class);
                recordset = repository.find(zipcode);
            }

            assertThat(recordset.size(), is(1));
        }
    }
}
