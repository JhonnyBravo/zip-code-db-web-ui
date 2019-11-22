package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.List;

import zip_code_db_web_ui.domain.model.ZipCode;

public interface ZipCodeRepository {
    /**
     * @param zipcode パラメータとして渡す ZipCode オブジェクトを指定する。
     * @return query 動的に生成された SQL 文を返す。
     */
    public String getSql(ZipCode zipcode);

    /**
     * レコード検索を実行する。
     *
     * @param zipcode パラメータとして渡す ZipCode オブジェクトを指定する。
     * @return recordset レコードの検索結果をリストに格納して返す。
     */
    public List<ZipCode> find(ZipCode zipcode);

}