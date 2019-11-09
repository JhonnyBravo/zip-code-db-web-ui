package zip_code_db_web_ui.app.zip_code;

import javax.servlet.http.HttpServletRequest;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeServiceImpl;

/**
 * 住所検索ページのヘルパー
 */
public class ZipCodeHelper {
    private final ConnectionInfoService cis;

    public ZipCodeHelper() {
        cis = new ConnectionInfoServiceImpl();
    }

    /**
     * @param path DB の接続情報を記述した設定ファイルのパスを指定する。
     * @return connectionInfo ConnectionInfo を返す。
     * @throws Exception {@link java.lang.Exception}
     */
    private ConnectionInfo getConnectionInfo(String path) throws Exception {
        final ContentsAttribute attr = new ContentsAttribute();
        attr.setPath(path);

        final ConnectionInfo connectionInfo = cis.getConnectionInfo(attr);
        return connectionInfo;
    }

    /**
     * @param path DB の接続情報を記述した設定ファイルのパスを指定する。
     * @return zipCodeService ZipCodeService を返す。
     * @throws Exception {@link java.lang.Exception}
     */
    public ZipCodeService getZipCodeService(String path) throws Exception {
        final ZipCodeService zcs = new ZipCodeServiceImpl(getConnectionInfo(path));
        return zcs;
    }

    /**
     * 住所検索ページから送信されたリクエストパラメータを受け取り、 ZipCode に変換して返す。
     *
     * @param request 住所検索ページから送信されたリクエストを受け取る。
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
}
