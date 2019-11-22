package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;

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
        final List<ZipCode> recordset = repository.find(zipcode);
        return recordset;
    }

}
