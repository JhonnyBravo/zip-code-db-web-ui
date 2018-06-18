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
                <%@page import="java.io.FileInputStream"%>
                <%@page import="java.io.FileNotFoundException"%>
                <%@page import="java.io.IOException"%>
                <%@page import="java.io.InputStream"%>
                <%@page import="java.sql.ResultSet"%>
                <%@page import="java.sql.SQLException"%>
                <%@page import="java.util.Properties"%>
                <%@page import="mysql_resource.ConnectionBean"%>
                <%@page import="mysql_resource.MySqlAction"%>
                <%@page import="zip_code_db_web_ui.SqlBean"%>
                <%
                    //接続情報の取得
                    Properties p = new Properties();
                    InputStream configInput = null;

                    try {
                        configInput = new FileInputStream("WebContent/WEB-INF/classes/connection.properties");
                        p.load(configInput);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    ConnectionBean cb = new ConnectionBean();
                    cb.setDbName(p.getProperty("dbName"));
                    cb.setPassword(p.getProperty("password"));
                    cb.setUserName(p.getProperty("userName"));

                    //GET リクエストパラメータ取得
                    String zipCode = request.getParameter("zip_code");
                    String prefecture = request.getParameter("prefecture");
                    String city = request.getParameter("city");
                    String area = request.getParameter("area");

                    //DB 接続
                    MySqlAction msa = new MySqlAction(cb.getConnectionString(), cb.getUserName(), cb.getPassword());
                    msa.openConnection();

                    //SQL の組み立て
                    SqlBean sqlb = new SqlBean();

                    if (!zipCode.equals("")) {
                        sqlb.setZipCode(zipCode);
                    }

                    if (!prefecture.equals("")) {
                        sqlb.setPrefecture(prefecture);
                    }

                    if (!city.equals("")) {
                        sqlb.setCity(city);
                    }

                    if (!area.equals("")) {
                        sqlb.setArea(area);
                    }

                    String sql = sqlb.getSql();

                    //レコードセットの取得
                    ResultSet rs = msa.getRecordset(sql,
                            ResultSet.TYPE_FORWARD_ONLY,
                            ResultSet.CONCUR_READ_ONLY);

                    //レコードセットを画面へ出力
                    while (rs.next()) {
                        out.println("<tr>");
                        out.println("<td>" + rs.getString("zip_code") + "</td>");
                        out.println("<td>" + rs.getString("prefecture") + "</td>");
                        out.println("<td>" + rs.getString("city") + "</td>");
                        out.println("<td>" + rs.getString("area") + "</td>");
                        out.println("</tr>");
                    }

                    //DB 切断
                    msa.closeConnection();
                %>
            </tbody>
        </table>
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
