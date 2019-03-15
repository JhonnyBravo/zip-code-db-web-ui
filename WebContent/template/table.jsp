<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.Iterator, zip_code_db_web_ui.TZipCode"%>
<table class="table">
    <thead>
        <tr>
            <th scope="col">郵便番号</th>
            <th scope="col">都道府県</th>
            <th scope="col">市区町村</th>
            <th scope="col">町域</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<TZipCode>result= (List<TZipCode>) request.getAttribute("result");
            Iterator<TZipCode> iterator=result.iterator();

            while (iterator.hasNext()) {
                TZipCode table=iterator.next();
                
                out.println("<tr>");
                out.println("<td>" + table.getZipCode() + "</td>");
                out.println("<td>" + table.getPrefecture() + "</td>");
                out.println("<td>" + table.getCity() + "</td>");
                out.println("<td>" + table.getArea() + "</td>");
                out.println("</tr>");
            }
        %>
    </tbody>
</table>
<%
    if (result.size() == 1000) {
        out.println("<p>該当する住所が 1000 件を超えました。検索範囲を狭めてください。</p>");
    } else if (result.size() == 0) {
        out.println("<p>該当する住所は見つかりませんでした。</p>");
    } else {
        out.println("<p>" + result.size() + " 件の住所が見つかりました。</p>");
    }
%>
