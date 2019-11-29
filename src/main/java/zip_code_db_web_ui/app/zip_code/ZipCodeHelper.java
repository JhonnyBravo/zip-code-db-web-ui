package zip_code_db_web_ui.app.zip_code;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.model.Prefecture;

/**
 * 住所検索ページのヘルパー
 */
public class ZipCodeHelper {
    /**
     * 住所検索ページから送信されたリクエストパラメータを受け取り、 ZipCodeForm に変換して返す。
     *
     * @param request
     *            住所検索ページから送信されたリクエストを受け取る。
     * @return form ZipCodeForm を返す。
     */
    public ZipCodeForm convertToZipCodeForm(HttpServletRequest request) {
        final ZipCodeForm form = new ZipCodeForm();

        form.setZipCode(request.getParameter("zip_code"));
        form.setPrefecture(request.getParameter("prefecture"));
        form.setCity(request.getParameter("city"));
        form.setArea(request.getParameter("area"));

        return form;
    }

    /**
     * 住所検索ページから送信されたリクエストパラメータを受け取り、 ZipCode に変換して返す。
     *
     * @param request
     *            住所検索ページから送信されたリクエストを受け取る。
     * @return zipcode ZipCode を返す。
     */
    public ZipCode convertToZipCode(HttpServletRequest request) {
        final ZipCode zipcode = new ZipCode();

        zipcode.setZipCode(request.getParameter("zip_code"));
        zipcode.setPrefecture(request.getParameter("prefecture"));
        zipcode.setCity(request.getParameter("city"));
        zipcode.setArea(request.getParameter("area"));

        return zipcode;
    }

    /**
     * 都道府県名の一覧をセレクトボックスのオプションリスト用データに変換して返す。
     * 
     * @param recordset
     *            変換対象とする都道府県の一覧を指定する。
     * @param prefecture
     *            セレクトボックス上で現在選択されている都道府県の名前を指定する。
     * @return prefectures 変換されたオプションリストデータを返す。
     */
    public List<Prefecture> convertToPrefectures(List<String> recordset, String prefecture) {
        final List<Prefecture> resultset = new ArrayList<>();

        for (final String record : recordset) {
            final Prefecture item = new Prefecture();
            item.setLabel(record);
            item.setValue(record);

            if (record.equals(prefecture)) {
                item.setSelected(true);
            }

            resultset.add(item);
        }

        return resultset;
    }
}
