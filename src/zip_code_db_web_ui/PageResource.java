package zip_code_db_web_ui;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import property_resource.PropertyResource;
import zip_code_db_cli.ZipCode;

/**
 * index.jsp から送信された Get パラメータを基に住所検索を実行し、 search_result.jsp へ転送する。
 */
@WebServlet("/search")
public class PageResource extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private final Logger logger;

    public PageResource() {
        super();
        logger = LoggerFactory.getLogger(this.getClass());
    }

    /**
     * index.jsp から送信された Get パラメータを基に住所検索を実行し、 search_result.jsp へ転送する。
     *
     * @param request  index.jsp から送信された Get パラメータ
     * @param response 検索結果を反映した search_result.jsp
     * @throws ServletException {@link javax.servlet.ServletException}
     * @throws IOException      {@link java.io.IOException}
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final ZipCode params = new ZipCode();
        params.setZipCode(request.getParameter("zip_code"));
        params.setPrefecture(request.getParameter("prefecture"));
        params.setCity(request.getParameter("city"));
        params.setArea(request.getParameter("area"));

        try {
            final PropertyResource pr = new PropertyResource("WebContent/WEB-INF/classes/connection.properties");
            final Map<String, String> properties = pr.getContent();

            final DaoResource dr = new DaoResource(properties);
            final List<ZipCode> recordset = dr.find(params);

            request.setAttribute("recordset", recordset);
        } catch (final Exception e) {
            logger.warn("エラーが発生しました。", e);
        }

        final ServletContext context = getServletContext();
        final RequestDispatcher dispatcher = context.getRequestDispatcher("/search_result.jsp");
        dispatcher.include(request, response);
    }
}
