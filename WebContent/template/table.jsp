<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.List, zip_code_db_cli.domain.model.ZipCode"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

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
        <c:forEach var="record" items="${recordset}">
            <tr>
                <td>${record.zipCode}</td>
                <td>${record.prefecture}</td>
                <td>${record.city}</td>
                <td>${record.area}</td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<p>${fn:length(recordset)} 件の住所が見つかりました。</p>
