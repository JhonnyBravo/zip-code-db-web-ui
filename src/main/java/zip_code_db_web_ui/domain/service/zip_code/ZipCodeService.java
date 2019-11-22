package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;

import zip_code_db_web_ui.domain.model.ZipCode;

public interface ZipCodeService {
    /**
     * @param zipcode 検索パラメータとして受け取る ZipCode を指定する。
     * @return recordset 住所検索を実行し、結果を List に納めて返す。
     */
    public List<ZipCode> find(ZipCode zipcode);
}
