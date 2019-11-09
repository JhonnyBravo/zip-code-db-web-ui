package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;

import java_itamae_connection.domain.service.connection.ConnectionService;
import zip_code_db_cli.domain.model.ZipCode;

/**
 * 住所検索を実行するサービス
 */
public abstract class ZipCodeService extends ConnectionService {
    /**
     * 住所検索を実行し、結果を List に変換して返す。
     * 
     * @param zipcode パラメータとして渡す ZipCode を指定する。
     * @return recordset 検索結果を List に変換して返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public abstract List<ZipCode> find(ZipCode zipcode) throws Exception;
}
