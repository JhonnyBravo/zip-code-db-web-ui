package zip_code_db_web_ui.domain.repository.zip_code;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import zip_code_db_cli.domain.model.ZipCode;

/**
 * 住所検索を実行するリポジトリ
 */
@Mapper
public interface ZipCodeRepository {
    /**
     * 住所検索を実行し、結果を List に変換して返す。
     *
     * @param zipcode
     *            パラメータとして渡す ZipCode を指定する。
     * @return recordset 検索結果を List に変換して返す。
     */
    public List<ZipCode> find(@Param("ZipCode") ZipCode zipcode);

    /**
     * @return recordset 都道府県名の一覧を返す。
     */
    public List<String> findPrefectureAll();
}
