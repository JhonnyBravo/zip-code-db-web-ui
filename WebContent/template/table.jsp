<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, zip_code_db_cli.domain.model.ZipCode"%>
<table class="table">
    <thead>
        <tr>
            <th>郵便番号</th>
            <th>都道府県</th>
            <th>市区町村</th>
            <th>町域</th>
        </tr>
    </thead>
    <tbody>
        <%
            List<ZipCode> recordset = (List<ZipCode>) request.getAttribute("recordset");

            for (ZipCode record : recordset) {
                out.println("<tr>");
                out.println("<td>" + record.getZipCode() + "</td>");
                out.println("<td>" + record.getPrefecture() + "</td>");
                out.println("<td>" + record.getCity() + "</td>");
                out.println("<td>" + record.getArea() + "</td>");
                out.println("</tr>");
            }
        %>
    </tbody>
</table>
<%
    if (recordset.size() == 1000) {
        out.println("<p>該当する住所が 1000 件を超えました。検索範囲を狭めてください。</p>");
    } else if (recordset.size() > 0) {
        out.println("<p>" + recordset.size() + " 件の住所が見つかりました。</p>");
    } else {
        out.println("<p>該当する住所が見つかりませんでした。</p>");
    }
%>
