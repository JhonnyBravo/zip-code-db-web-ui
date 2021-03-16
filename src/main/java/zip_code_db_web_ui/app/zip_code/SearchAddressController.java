package zip_code_db_web_ui.app.zip_code;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import zip_code_db_cli.domain.model.ZipCode;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeServiceImpl;

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
        service = new ZipCodeServiceImpl();
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
        final ZipCodeForm form = helper.convertToZipCodeForm(request);
        request.setAttribute("form", form);

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<ZipCodeForm>> validResult = validator.validate(form);

        final ServletContext context = getServletContext();

        if (validResult.size() > 0) {
            validResult.stream().forEach(e -> {
                final String path = e.getPropertyPath().toString() + "Error";
                final String message = e.getMessage();

                request.setAttribute(path, message);
            });

            final RequestDispatcher dispatcher = context.getRequestDispatcher("/home");
            dispatcher.forward(request, response);
            return;
        }

        List<ZipCode> recordset = new ArrayList<>();
        final ZipCode zipcode = helper.convertToZipCode(request);

        String error = null;

        try {
            recordset = service.find(zipcode);
            request.setAttribute("recordset", recordset);

            if (recordset.size() > 1000) {
                error = "該当する住所が 1000 件を超えました。検索範囲を狭めてください。";
            } else if (recordset.size() == 0) {
                error = "該当する住所が見つかりませんでした。";
            }

            if (error != null) {
                final RequestDispatcher dispatcher = context.getRequestDispatcher("/home");
                request.setAttribute("error", error);
                dispatcher.forward(request, response);
                return;
            }
        } catch (final Exception e) {
            throw new IOException(e);
        }

        final RequestDispatcher dispatcher = context.getRequestDispatcher("/search_result.jsp");
        dispatcher.include(request, response);
    }
}
