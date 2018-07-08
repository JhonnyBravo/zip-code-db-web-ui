<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
<head>
<!-- Required meta tags -->
<meta charset="UTF-8">
<meta name="viewport"
    content="width=device-width, initial-scale=1, shrink-to-fit=no">

<!-- Bootstrap CSS -->
<link rel="stylesheet"
    href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
    integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
    crossorigin="anonymous">

<title>検索結果 | 郵便番号検索</title>
</head>
<body>
    <header>
        <nav class="navbar navbar-dark bg-dark">
            <a class="navbar-brand" href="index.html">郵便番号検索</a>
        </nav>
    </header>

    <section>
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
                <%@page import="zip_code_db_web_ui.TZipCodeController"
                    import="java.util.List" import="java.util.ArrayList"
                    import="java.sql.PreparedStatement" import="java.sql.ResultSet"
                    import="java.sql.SQLException"%>
                <%
                    //GET リクエストパラメータ取得
                    String zipCode = request.getParameter("zip_code");
                    String prefecture = request.getParameter("prefecture");
                    String city = request.getParameter("city");
                    String area = request.getParameter("area");

                    //DB へ接続する。
                    TZipCodeController tzcc = new TZipCodeController("WebContent/WEB-INF/classes/connection.properties");
                    tzcc.openConnection();

                    if (tzcc.getCode() == 1) {
                        return;
                    }

                    //SQL を生成する。
                    List<String> paramList = new ArrayList<String>();

                    if (!zipCode.equals("")) {
                        tzcc.setZipCode(zipCode);
                        paramList.add(zipCode);
                    }

                    if (!prefecture.equals("")) {
                        tzcc.setPrefecture(prefecture);
                        paramList.add(prefecture);
                    }

                    if (!city.equals("")) {
                        tzcc.setCity(city);
                        paramList.add(city);
                    }

                    if (!area.equals("")) {
                        tzcc.setArea(area);
                        paramList.add(area);
                    }

                    String sql = tzcc.getSql();

                    //Statement を生成する。
                    PreparedStatement ps = tzcc.openStatement(sql);

                    if (tzcc.getCode() == 1) {
                        tzcc.closeConnection();
                        return;
                    }

                    //レコードセットを取得する。
                    ResultSet rs = null;
                    int i = 0;

                    try {
                        for (String param : paramList) {
                            ++i;
                            ps.setString(i, param);
                        }

                        rs = ps.executeQuery();

                        //レコードセットを画面へ出力する。

                        i = 0;

                        while (rs.next()) {
                            ++i;
                            out.println("<tr>");
                            out.println("<td>" + rs.getString("zip_code") + "</td>");
                            out.println("<td>" + rs.getString("prefecture") + "</td>");
                            out.println("<td>" + rs.getString("city") + "</td>");
                            out.println("<td>" + rs.getString("area") + "</td>");
                            out.println("</tr>");
                        }
                    } catch (SQLException e) {
                        System.err.println("エラーが発生しました。 " + e.toString());
                        return;
                    } finally {
                        //DB 接続を切断する。
                        if (rs != null) {
                            rs.close();
                        }

                        tzcc.closeStatement();
                        tzcc.closeConnection();
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
    </section>
    <!-- Optional JavaScript -->
    <!-- jQuery first, then Popper.js, then Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
        integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
        crossorigin="anonymous"></script>
    <script
        src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
        integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
        crossorigin="anonymous"></script>
    <script
        src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
        integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
        crossorigin="anonymous"></script>
</body>
</html>
