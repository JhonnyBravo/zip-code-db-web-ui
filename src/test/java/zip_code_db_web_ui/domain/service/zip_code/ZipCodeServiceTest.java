package zip_code_db_web_ui.domain.service.zip_code;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import zip_code_db_cli.domain.model.ZipCode;

@RunWith(Enclosed.class)
public class ZipCodeServiceTest {
    public static class パラメータを一つ指定する場合 {
        private ZipCodeService zcs;

        @Before
        public void setUp() throws Exception {
            final ContentsAttribute attr = new ContentsAttribute();
            attr.setPath("src/test/resources/connection.properties");

            final ConnectionInfoService cis = new ConnectionInfoServiceImpl();
            zcs = new ZipCodeServiceImpl(cis.getConnectionInfo(attr));
        }

        @Test
        public void パラメータを指定しない場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1000));
        }

        @Test
        public void 郵便番号を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 都道府県名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("新潟県");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1000));
        }

        @Test
        public void 市区郡名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("新潟市東区");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(104));
        }

        @Test
        public void 町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(5));
        }
    }

    public static class パラメータを複数指定する場合 {
        private ZipCodeService zcs;

        @Before
        public void setUp() throws Exception {
            final ContentsAttribute attr = new ContentsAttribute();
            attr.setPath("src/test/resources/connection.properties");

            final ConnectionInfoService cis = new ConnectionInfoServiceImpl();
            zcs = new ZipCodeServiceImpl(cis.getConnectionInfo(attr));
        }

        @Test
        public void 市区郡名と町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setCity("新潟市東区");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 都道府県名と町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("新潟県");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 都道府県名と市区郡名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("新潟県");
            zipcode.setCity("新潟市東区");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(104));
        }

        @Test
        public void 都道府県名_市区郡名_町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("新潟県");
            zipcode.setCity("新潟市東区");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号と町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号と市区郡名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setCity("新潟市東区");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号_市区郡名_町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setCity("新潟市東区");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号と都道府県名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setPrefecture("新潟県");
            zipcode.setZipCode("9500047");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号_都道府県名_町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setPrefecture("新潟県");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setPrefecture("新潟県");
            zipcode.setCity("新潟市東区");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }

        @Test
        public void 郵便番号_都道府県名_市区郡名_町域名を指定した場合() throws Exception {
            final ZipCode zipcode = new ZipCode();
            zipcode.setZipCode("9500047");
            zipcode.setPrefecture("新潟県");
            zipcode.setCity("新潟市東区");
            zipcode.setArea("臨海町");

            final List<ZipCode> recordset = zcs.find(zipcode);
            assertThat(recordset.size(), is(1));

            assertThat(recordset.get(0).getZipCode(), is("9500047"));
            assertThat(recordset.get(0).getPrefecture(), is("新潟県"));
            assertThat(recordset.get(0).getCity(), is("新潟市東区"));
            assertThat(recordset.get(0).getArea(), is("臨海町"));
        }
    }
}
