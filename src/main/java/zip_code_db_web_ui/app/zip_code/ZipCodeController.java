package zip_code_db_web_ui.app.zip_code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java_itamae_connection.domain.model.ConnectionInfo;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoService;
import java_itamae_connection.domain.service.connection_info.ConnectionInfoServiceImpl;
import java_itamae_contents.domain.model.ContentsAttribute;
import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeServiceImpl;

/**
 * 住所検索ページのコントローラ
 */
@WebServlet("/search")
public class ZipCodeController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ConnectionInfoService cis;
    private ZipCodeService zcs;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public ZipCodeController() {
        super();
        cis = new ConnectionInfoServiceImpl();
    }

    /**
     * 住所検索を実行する
     *
     * @param request  下記のリクエストパラメータを受け取る。
     *                 <ul>
     *                 <li>zip_code: 検索対象とする郵便番号を指定する。</li>
     *                 <li>prefecture: 検索対象とする都道府県名を指定する。</li>
     *                 <li>city: 検索対象とする市区郡名を指定する。</li>
     *                 <li>area: 検索対象とする町域名を指定する。</li>
     *                 </ul>
     * @param response 検索結果を表示ページへ転送する。
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.IOException}
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final ZipCode form = new ZipCode();

        form.setZipCode(request.getParameter("zip_code"));
        form.setPrefecture(request.getParameter("prefecture"));
        form.setCity(request.getParameter("city"));
        form.setArea(request.getParameter("area"));

        final ContentsAttribute attr = new ContentsAttribute();
        attr.setPath("WebContent/WEB-INF/classes/connection.properties");

        ConnectionInfo cnInfo = new ConnectionInfo();
        List<ZipCode> recordset = new ArrayList<>();

        try {
            cnInfo = cis.getConnectionInfo(attr);
            zcs = new ZipCodeServiceImpl(cnInfo);
            recordset = zcs.find(form);
            request.setAttribute("recordset", recordset);
        } catch (final Exception e) {
            throw new IOException(e);
        }

        final ServletContext context = getServletContext();
        final RequestDispatcher dispatcher = context.getRequestDispatcher("/search_result.jsp");
        dispatcher.include(request, response);
    }
}
