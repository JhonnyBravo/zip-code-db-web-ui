<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<form method="get" action="search">
    <div class="form-row">
        <div class="col">
            <input type="text" class="form-control" placeholder="郵便番号" name="zip_code">
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="都道府県" name="prefecture">
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="市区町村" name="city">
        </div>
        <div class="col">
            <input type="text" class="form-control" placeholder="町域" name="area">
        </div>
        <div class="col">
            <button type="submit" class="btn btn-primary">検索</button>
        </div>
    </div>
</form>
