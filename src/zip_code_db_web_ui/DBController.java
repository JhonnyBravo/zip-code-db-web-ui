package zip_code_db_web_ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context_resource.ConnectionResource;
import context_resource.StatementResource;
import status_resource.Status;

/**
 * DB からレコードセットを取得する。
 */
public class DBController extends Status {
    private ConnectionResource cr;
    private StatementResource sr;

    /**
     * @param configPath DB の接続情報が記載された設定ファイルのパスを指定する。
     */
    public DBController(String configPath) {
        this.cr = new ConnectionResource(configPath);
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findAll() {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param zipCode 検索キーとして使用する郵便番号を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByZipCode(String zipCode) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, zipCode);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param prefecture 検索キーとして使用する都道府県を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByPrefecture(String prefecture) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, prefecture);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param city 検索キーとして使用する市区町村を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByCityLike(String city) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE city LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, city);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param area 検索キーとして使用する町域を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByAreaLike(String area) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE area LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, area);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param prefecture 検索キーとして使用する都道府県を指定する。
     * @param city       検索キーとして使用する市区町村を指定する。
     * @param area       検索キーとして使用する町域を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByPrefectureAndCityLikeAndAreaLike(String prefecture, String city, String area) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? AND area LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, prefecture);
            statement.setString(2, city);
            statement.setString(3, area);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param prefecture 検索キーとして使用する都道府県を指定する。
     * @param city       検索キーとして使用する市区町村を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByPrefectureAndCityLike(String prefecture, String city) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, prefecture);
            statement.setString(2, city);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param prefecture 検索キーとして使用する都道府県を指定する。
     * @param area       検索キーとして使用する町域を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByPrefectureAndAreaLike(String prefecture, String area) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND area LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, prefecture);
            statement.setString(2, area);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }

    /**
     * t_zip_code から最大 1000 件のレコードを取得する。
     * 
     * @param city 検索キーとして使用する市区町村を指定する。
     * @param area 検索キーとして使用する町域を指定する。
     * @return List&lt;TZipCode&gt;
     */
    public List<TZipCode> findByCityLikeAndAreaLike(String city, String area) {
        List<TZipCode> result = new ArrayList<TZipCode>();
        this.initStatus();

        cr.openContext();
        this.setCode(cr.getCode());

        if (this.getCode() == 1) {
            return result;
        }

        Connection connection = (Connection) cr.getContext();
        String sql = "SELECT * FROM t_zip_code WHERE city LIKE ? AND area LIKE ? LIMIT 1000;";
        this.sr = new StatementResource(connection, sql);

        sr.openContext();
        this.setCode(sr.getCode());

        if (this.getCode() == 1) {
            cr.closeContext();
            return result;
        }

        PreparedStatement statement = (PreparedStatement) sr.getContext();
        ResultSet resultset = null;

        try {
            statement.setString(1, city);
            statement.setString(2, area);
            resultset = statement.executeQuery();

            while (resultset.next()) {
                TZipCode table = new TZipCode();

                table.setZipCode(resultset.getString("zip_code"));
                table.setPrefecture(resultset.getString("prefecture"));
                table.setCity(resultset.getString("city"));
                table.setArea(resultset.getString("area"));

                result.add(table);
            }
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e);
        } finally {
            sr.closeContext();
            cr.closeContext();
        }

        if (result.size() == 0) {
            this.initStatus();
        } else {
            this.setCode(2);
        }

        return result;
    }
}
