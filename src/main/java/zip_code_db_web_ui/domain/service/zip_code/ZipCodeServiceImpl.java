package zip_code_db_web_ui.domain.service.zip_code;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import zip_code_db_web_ui.domain.model.ZipCode;
import zip_code_db_web_ui.domain.repository.zip_code.ZipCodeRepository;

@Service
public class ZipCodeServiceImpl implements ZipCodeService {
    @Autowired
    private ZipCodeRepository repository;

    @Override
    public List<ZipCode> find(ZipCode zipcode) {
        final Predicate<String> isNotEmpty = value -> {
            boolean result = false;

            if (value != null && !value.isEmpty()) {
                result = true;
            }

            return result;
        };

        List<ZipCode> recordset = new ArrayList<>();

        if (isNotEmpty.test(zipcode.getZipCode())) {
            // 郵便番号が指定されている場合
            recordset = repository.findByZipCode(zipcode.getZipCode());
        } else if (isNotEmpty.test(zipcode.getPrefecture()) && !isNotEmpty.test(zipcode.getCity())
                && !isNotEmpty.test(zipcode.getArea())) {
            // 都道府県名のみ指定されている場合
            recordset = repository.findByPrefecture(zipcode.getPrefecture());
        } else if (!isNotEmpty.test(zipcode.getPrefecture()) && isNotEmpty.test(zipcode.getCity())
                && !isNotEmpty.test(zipcode.getArea())) {
            // 市区郡名のみ指定されている場合
            recordset = repository.findByCityLike(zipcode.getCity());
        } else if (!isNotEmpty.test(zipcode.getPrefecture()) && !isNotEmpty.test(zipcode.getCity())
                && isNotEmpty.test(zipcode.getArea())) {
            // 町域名のみ指定されている場合
            recordset = repository.findByAreaLike(zipcode.getArea());
        } else if (isNotEmpty.test(zipcode.getPrefecture()) && isNotEmpty.test(zipcode.getCity())
                && !isNotEmpty.test(zipcode.getArea())) {
            // 都道府県名と市区郡名が指定されている場合
            recordset = repository.findByPrefectureAndCityLike(zipcode.getPrefecture(), zipcode.getCity());
        } else if (isNotEmpty.test(zipcode.getPrefecture()) && !isNotEmpty.test(zipcode.getCity())
                && isNotEmpty.test(zipcode.getArea())) {
            // 都道府県名と町域名が指定されている場合
            recordset = repository.findByPrefectureAndAreaLike(zipcode.getPrefecture(), zipcode.getArea());
        } else if (isNotEmpty.test(zipcode.getPrefecture()) && isNotEmpty.test(zipcode.getCity())
                && isNotEmpty.test(zipcode.getArea())) {
            // 都道府県名・市区郡名・町域名が指定されている場合
            recordset = repository.findByPrefectureAndCityLikeAndAreaLike(zipcode.getPrefecture(), zipcode.getCity(),
                    zipcode.getArea());
        } else if (!isNotEmpty.test(zipcode.getPrefecture()) && isNotEmpty.test(zipcode.getCity())
                && isNotEmpty.test(zipcode.getArea())) {
            // 市区郡名と町域名が指定されている場合
            recordset = repository.findByCityLikeAndAreaLike(zipcode.getCity(), zipcode.getArea());
        }

        return recordset;
    }

}
