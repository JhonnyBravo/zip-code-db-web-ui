package zip_code_db_web_ui.domain.service.zip_code;

import java.util.List;

import zip_code_db_cli.domain.model.ZipCode;

/**
 * 住所検索を実行するサービス
 */
public interface ZipCodeService {
    /**
     * 住所検索を実行し、結果を List に変換して返す。
     *
     * @param zipcode
     *            パラメータとして渡す ZipCode を指定する。
     * @return recordset 検索結果を List に変換して返す。
     */
    public List<ZipCode> find(ZipCode zipcode);

    /**
     * @return recordset 都道府県名の一覧を返す。
     */
    public List<String> findPrefectureAll();
}
