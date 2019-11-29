package zip_code_db_web_ui.domain.service.zip_code;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.repository.zip_code.ZipCodeRepository;

public class ZipCodeServiceImpl implements ZipCodeService {
    private ZipCodeRepository repository;
    private final SqlSessionFactory factory;

    public ZipCodeServiceImpl() throws Exception {
        factory = new SqlSessionFactoryBuilder()
                .build(Resources.getResourceAsStream("mybatis-config.xml"));
    }

    @Override
    public List<ZipCode> find(ZipCode zipcode) {
        List<ZipCode> recordset = new ArrayList<>();

        try (SqlSession session = factory.openSession()) {
            repository = session.getMapper(ZipCodeRepository.class);
            recordset = repository.find(zipcode);
        }

        return recordset;
    }

    @Override
    public List<String> findPrefectureAll() {
        List<String> recordset = new ArrayList<>();

        try (SqlSession session = factory.openSession()) {
            repository = session.getMapper(ZipCodeRepository.class);
            recordset = repository.findPrefectureAll();
        }

        return recordset;
    }
}
