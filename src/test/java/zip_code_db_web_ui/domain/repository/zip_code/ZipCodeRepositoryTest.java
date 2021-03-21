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

    @Test
    public void testFindByZipCode() {
        final List<ZipCode> recordset = repository.findByZipCode("0800030");
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0800030"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("帯広市"));
        assertThat(record.getArea(), is("大通北"));
    }

    @Test
    public void testFindByPrefecture() {
        final List<ZipCode> recordset = repository.findByPrefecture("北海道");
        assertThat(recordset.size(), is(8252));
    }

    @Test
    public void testFindByCityLike() {
        final List<ZipCode> recordset = repository.findByCityLike("札幌市%");
        assertThat(recordset.size(), is(823));
    }

    @Test
    public void testFindByAreaLike() {
        final List<ZipCode> recordset = repository.findByAreaLike("大通%");
        assertThat(recordset.size(), is(50));
    }

    @Test
    public void testFindByPrefectureAndCityLikeAndAreaLike() {
        final List<ZipCode> recordset = repository.findByPrefectureAndCityLikeAndAreaLike("北海道", "札幌市%", "大通%");
        assertThat(recordset.size(), is(3));
    }

    @Test
    public void testFindByPrefectureAndCityLike() {
        final List<ZipCode> recordset = repository.findByPrefectureAndCityLike("北海道", "札幌市中央区");
        assertThat(recordset.size(), is(89));
    }

    @Test
    public void testFindByPrefectureAndAreaLike() {
        final List<ZipCode> recordset = repository.findByPrefectureAndAreaLike("北海道", "大通%");
        assertThat(recordset.size(), is(38));
    }

    @Test
    public void testFindByCityLikeAndAreaLike() {
        final List<ZipCode> recordset = repository.findByCityLikeAndAreaLike("札幌市%", "大通東");
        assertThat(recordset.size(), is(1));

        final ZipCode record = recordset.get(0);
        assertThat(record.getZipCode(), is("0600041"));
        assertThat(record.getPrefecture(), is("北海道"));
        assertThat(record.getCity(), is("札幌市中央区"));
        assertThat(record.getArea(), is("大通東"));
    }

}
