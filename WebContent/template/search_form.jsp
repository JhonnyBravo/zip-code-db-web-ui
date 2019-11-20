<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
    import="java.util.List, zip_code_db_cli.domain.model.ZipCode,zip_code_db_web_ui.domain.model.Prefecture"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form method="get" action="search">
    <div class="form-row">
        <div class="col">
            <input type="text" class="form-control" placeholder="郵便番号"
                name="zip_code" value="${form.zipCode}">
        </div>
        <div class="col">
            <select class="form-control" name="prefecture">
                <option></option>
                <c:forEach var="prefecture" items="${prefectures}">
                    <c:choose>
                        <c:when test="${prefecture.selected}">
                            <option selected value="${prefecture.value}">${prefecture.label}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${prefecture.value}">${prefecture.label}</option>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
            </select>
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="市区町村"
                name="city" value="${form.city}">
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="町域"
                name="area" value="${form.area}">
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">検索</button>
        </div>
    </div>
</form>
<c:if test="${error!=null}">
    <p>${error}</p>
</c:if>
