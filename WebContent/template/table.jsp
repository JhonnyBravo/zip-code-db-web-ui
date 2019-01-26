<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.ResultSet"%>
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
            ResultSet result = (ResultSet) request.getAttribute("result");
            int i = 0;

            while (result.next()) {
                ++i;
                out.println("<tr>");
                out.println("<td>" + result.getString("zip_code") + "</td>");
                out.println("<td>" + result.getString("prefecture") + "</td>");
                out.println("<td>" + result.getString("city") + "</td>");
                out.println("<td>" + result.getString("area") + "</td>");
                out.println("</tr>");
            }
        %>
    </tbody>
</table>
<%
    if (i == 1000) {
        out.println("<p>該当する住所が 1000 件を超えました。検索範囲を狭めてください。</p>");
    } else if (i == 0) {
        out.println("<p>該当する住所は見つかりませんでした。</p>");
    } else {
        out.println("<p>" + i + " 件の住所が見つかりました。</p>");
    }
%>
