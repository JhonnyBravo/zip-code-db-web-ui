package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;
import javax.ejb.Stateless;
import javax.inject.Inject;
import zip_code_db_web_ui.domain.model.ZipCode;
import zip_code_db_web_ui.domain.repository.zip_code.ZipCodeRepository;

@Stateless
public class ZipCodeServiceImpl implements ZipCodeService {
  @Inject
  private ZipCodeRepository repository;

  @Override
  public List<ZipCode> find(ZipCode zipcode) throws Exception {
    final List<ZipCode> recordset = repository.find(zipcode);
    return recordset;
  }

  @Override
  public List<String> findPrefectureAll() throws Exception {
    final List<String> recordset = repository.findPrefectureAll();
    return recordset;
  }
}
