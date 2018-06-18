package zip_code_db_web_ui;

/**
 * SQL 文を組み立てる。
 */
public class SqlBean {
    private String zipCode = null;
    private String prefecture = null;
    private String city = null;
    private String area = null;

    /**
    * @return zipCode 検索対象とする郵便番号を返す。
    */
    public String getZipCode() {
        return zipCode;
    }

    /**
    * @param zipCode 検索対象とする郵便番号を指定する。
    */
    public void setZipCode(String zipCode) {
        this.zipCode = " zip_code='" + zipCode + "'";
    }

    /**
    * @return prefecture 検索対象とする都道府県を返す。
    */
    public String getPrefecture() {
        return prefecture;
    }

    /**
    * @param prefecture 検索対象とする都道府県を指定する。
    */
    public void setPrefecture(String prefecture) {
        this.prefecture = " prefecture='" + prefecture + "'";
    }

    /**
    * @return city 検索対象とする市区町村を返す。
    */
    public String getCity() {
        return city;
    }

    /**
    * @param city 検索対象とする市区町村を指定する。
    */
    public void setCity(String city) {
        this.city = " city LIKE '%" + city + "%'";
    }

    /**
    * @return area 検索対象とする町域を返す。
    */
    public String getArea() {
        return area;
    }

    /**
    * @param area 検索対象とする町域を指定する。
    */
    public void setArea(String area) {
        this.area = " area LIKE '%" + area + "%'";
    }

    /**
    * @return sql SQL 文を返す。
    */
    public String getSql() {
        String sql = "SELECT * FROM t_zip_code";

        if (this.zipCode != null || this.prefecture != null || this.city != null || this.area != null) {
            sql = sql + " WHERE";

            if (this.zipCode != null) {
                sql = sql + this.zipCode;

                if (this.prefecture != null || this.city != null || this.area != null) {
                    sql = sql + " AND";
                }
            }

            if (this.prefecture != null) {
                sql = sql + this.prefecture;

                if (this.city == null && this.area == null) {
                    sql = sql + " LIMIT 1000";
                } else if (this.city != null || this.area != null) {
                    sql = sql + " AND";
                }
            }

            if (this.city != null) {
                sql = sql + this.city;

                if (this.area != null) {
                    sql = sql + " AND";
                }
            }

            if (this.area != null) {
                sql = sql + this.area;
            }
        } else {
            sql = sql + " LIMIT 1000";
        }

        return sql + ";";
    }
}
