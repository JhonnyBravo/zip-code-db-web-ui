package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;

import zip_code_db_web_ui.domain.model.ZipCode;

public interface ZipCodeService {
    /**
     * @param zipcode 検索条件として受け取る {@link ZipCode} を指定する。
     * @return recordset 住所検索を実行し、 {@link List} に収めて返す。
     */
    List<ZipCode> find(ZipCode zipcode);
}
