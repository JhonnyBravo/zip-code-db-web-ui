package zip_code_db_web_ui.app.zip_code;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import zip_code_db_web_ui.domain.model.Prefecture;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeService;
import zip_code_db_web_ui.domain.service.zip_code.ZipCodeServiceImpl;

/**
 * 住所検索フォームのコントローラ
 */
@WebServlet("/home")
public class ZipCodeController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  private final ZipCodeHelper helper;
  private final ZipCodeService service;

  /**
   * 初期化処理を実行する。
   *
   * @see HttpServlet#HttpServlet()
   * @throws Exception {@link java.lang.Exception}
   */
  public ZipCodeController() throws Exception {
    super();
    helper = new ZipCodeHelper();
    service = new ZipCodeServiceImpl();
  }

  /**
   * 住所検索フォームを表示する。
   *
   * @param request {@link javax.servlet.http.HttpServletRequest}
   * @param response 住所検索フォームを表示する。
   * @throws ServletException {@link javax.servlet.ServletException}
   * @throws IOException {@link java.io.IOException}
   * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
   */
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    final List<String> recordset = service.findPrefectureAll();
    final ZipCodeForm form = (ZipCodeForm) request.getAttribute("form");

    String prefecture = null;

    if (form != null) {
      prefecture = form.getPrefecture();
    }

    final List<Prefecture> prefectures = helper.convertToPrefectures(recordset, prefecture);
    request.setAttribute("prefectures", prefectures);

    final ServletContext context = getServletContext();
    final RequestDispatcher dispatcher = context.getRequestDispatcher("/index.jsp");
    dispatcher.include(request, response);
  }
}
