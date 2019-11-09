package zip_code_db_web_ui.app.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * フィルターを管理する。
 */
@WebFilter("/*")
public class FilterResource implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    /**
     * 文字エンコーディングとコンテンツタイプを自動設定する。
     *
     * @param request  {@link javax.servlet.ServletRequest}
     * @param response {@link javax.servlet.ServletResponse}
     * @param chain    {@link javax.servlet.FilterChain}
     * @throws IOException      {@link java.io.IOException}
     * @throws ServletException {@link javax.servlet.ServletException}
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        final String encoding = "UTF-8";
        request.setCharacterEncoding(encoding);
        response.setContentType("text/html; charset=" + encoding);
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
