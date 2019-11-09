package zip_code_db_web_ui.domain.service.zip_code;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;

import java_itamae_connection.domain.model.ConnectionInfo;
import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.repository.zip_code.ZipCodeRepository;
import zip_code_db_web_ui.domain.repository.zip_code.ZipCodeRepositoryImpl;

public class ZipCodeServiceImpl extends ZipCodeService {
    private final ConnectionInfo cnInfo;
    private final ZipCodeRepository zcr;

    /**
     * @param cnInfo DB の接続情報を納めた ConnectionInfo を指定する。
     */
    public ZipCodeServiceImpl(ConnectionInfo cnInfo) {
        this.cnInfo = cnInfo;
        zcr = new ZipCodeRepositoryImpl();
    }

    @Override
    public List<ZipCode> find(ZipCode zipcode) throws Exception {
        List<ZipCode> recordset = new ArrayList<>();

        try (Connection connection = getConnection(cnInfo)) {
            recordset = zcr.find(connection, zipcode);
        }

        return recordset;
    }

}
