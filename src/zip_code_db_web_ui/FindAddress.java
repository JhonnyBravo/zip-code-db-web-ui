package zip_code_db_web_ui;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * index.jsp から送信されたパラメータを基に ResultSet を取得して search_result.jsp へ転送する。
 */
@WebServlet("/FindAddress")
public class FindAddress extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private ZipCode table = new ZipCode();

    /**
     * @see HttpServlet#HttpServlet()
     */
    public FindAddress() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     *      response)
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String zip_code = request.getParameter("zip_code");
        String prefecture = request.getParameter("prefecture");
        String city = request.getParameter("city");
        String area = request.getParameter("area");
        ResultSet result = null;

        table.openConnection("WebContent/WEB-INF/classes/connection.properties");

        if (table.getCode() == 1) {
            return;
        }

        if (!prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            result = table.findByPrefectureAndCityLikeAndAreaLike(prefecture, city, area);
        } else if (!prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            result = table.findByPrefectureAndCityLike(prefecture, city);
        } else if (!prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            result = table.findByPrefectureAndAreaLike(prefecture, area);
        } else if (prefecture.isEmpty() && !city.isEmpty() && !area.isEmpty()) {
            result = table.findByCityLikeAndAreaLike(city, area);
        } else if (!prefecture.isEmpty() && city.isEmpty() && area.isEmpty()) {
            result = table.findByPrefecture(prefecture);
        } else if (prefecture.isEmpty() && !city.isEmpty() && area.isEmpty()) {
            result = table.findByCityLike(city);
        } else if (prefecture.isEmpty() && city.isEmpty() && !area.isEmpty()) {
            result = table.findByAreaLike(area);
        } else if (!zip_code.isEmpty()) {
            result = table.findByZipCode(zip_code);
        } else {
            result = table.findAll();
        }

        if (table.getCode() == 1) {
            return;
        }

        request.setAttribute("result", result);
        ServletContext context = getServletContext();
        RequestDispatcher rd = context.getRequestDispatcher("/search_result.jsp");

        rd.include(request, response);

        if (result != null) {
            try {
                result.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        table.closeConnection();
    }
}
