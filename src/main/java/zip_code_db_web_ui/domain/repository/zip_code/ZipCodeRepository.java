package zip_code_db_web_ui.domain.repository.zip_code;

import java.sql.Connection;
import java.util.List;

import zip_code_db_cli.domain.model.ZipCode;

/**
 * 住所検索を実行するリポジトリ
 */
public interface ZipCodeRepository {
    /**
     * SQL ステートメントを生成して返す。
     *
     * @param zipcode パラメータとして渡す ZipCode を指定する。
     * @return sql 生成された SQL ステートメントを返す。
     */
    public String getSql(ZipCode zipcode);

    /**
     * 住所検索を実行し、結果を List に変換して返す。
     *
     * @param connection DB 接続に使用する Connection を指定する。
     * @param zipcode    パラメータとして渡す ZipCode を指定する。
     * @return recordset 検索結果を List に変換して返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public List<ZipCode> find(Connection connection, ZipCode zipcode) throws Exception;
}
