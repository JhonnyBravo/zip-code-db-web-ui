<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page
    import="java.util.List, zip_code_db_cli.domain.model.ZipCode,zip_code_db_web_ui.domain.model.Prefecture"%>
<%
    ZipCode form = (ZipCode) request.getAttribute("form");
    String zipcode="";
    String city = "";
    String area = "";

    if (form != null) {
        zipcode = form.getZipCode();
        city = form.getCity();
        area = form.getArea();
    }
%>
<form method="get" action="search">
    <div class="form-row">
        <div class="col">
            <input type="text" class="form-control" placeholder="郵便番号" name="zip_code" value=<%=zipcode%>>
        </div>
        <div class="col">
            <select class="form-control" name="prefecture">
                <option></option>
                <%
                    List<Prefecture> prefectures = (List<Prefecture>) request.getAttribute("prefectures");

                    for (Prefecture prefecture : prefectures) {
                        if (prefecture.isSelected()) {
                            out.print("<option selected value='" + prefecture.getValue() + "'>");
                        } else {
                            out.print("<option value='" + prefecture.getValue() + "'>");
                        }

                        out.print(prefecture.getLabel());
                        out.println("</option>");
                    }
                %>
            </select>
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="市区町村" name="city" value=<%=city%>>
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="町域" name="area" value=<%=area%>>
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">検索</button>
        </div>
    </div>
</form>
<%
    String error = (String) request.getAttribute("error");

    if (error != null) {
        out.println("<p>" + error + "</p>");
    }
%>
