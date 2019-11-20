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

import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;

/**
 * 住所検索ページのコントローラ
 */
@WebServlet("/search")
public class SearchAddressController extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private final ZipCodeHelper helper;
    private final ZipCodeService service;

    /**
     * @see HttpServlet#HttpServlet()
     * @throws Exception {@link java.lang.Exception}
     */
    public SearchAddressController() throws Exception {
        super();
        helper = new ZipCodeHelper();
        service = helper.getZipCodeService("WebContent/WEB-INF/classes/connection.properties");
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
        final ZipCode form = helper.convertToZipCode(request);
        final ServletContext context = getServletContext();

        if (form.getZipCode().isEmpty() && form.getPrefecture().isEmpty() && form.getCity().isEmpty()
                && form.getArea().isEmpty()) {
            request.setAttribute("error", "検索条件を指定してください。");

            final RequestDispatcher dispatcher = context.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
        }

        List<ZipCode> recordset = new ArrayList<>();

        try {
            recordset = service.find(form);

            if (recordset.size() > 1000) {
                request.setAttribute("error", "該当する住所が 1000 件を超えました。検索範囲を狭めてください。");
                request.setAttribute("form", form);

                final RequestDispatcher dispatcher = context.getRequestDispatcher("/home");
                dispatcher.forward(request, response);
            } else if (recordset.size() == 0) {
                request.setAttribute("error", "該当する住所が見つかりませんでした。");
                request.setAttribute("form", form);

                final RequestDispatcher dispatcher = context.getRequestDispatcher("/home");
                dispatcher.forward(request, response);
            } else {
                request.setAttribute("recordset", recordset);
            }
        } catch (final Exception e) {
            throw new IOException(e);
        }

        final RequestDispatcher dispatcher = context.getRequestDispatcher("/search_result.jsp");
        dispatcher.include(request, response);
    }
}
