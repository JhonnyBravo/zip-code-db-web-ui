package zip_code_db_web_ui;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import mysql_resource.ConnectionController;
import mysql_resource.ConnectionProperties;
import mysql_resource.StatementController;
import status_resource.Status;

/**
 * @see zip_code_db_web_ui.ZipCodeResource
 */
public class ZipCode extends Status implements ZipCodeResource {
    private ConnectionProperties cp = new ConnectionProperties();
    private ConnectionController cc = new ConnectionController();
    private StatementController sc = new StatementController();

    private Connection connection = null;

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#openConnection(String)
     */
    @Override
    public void openConnection(String path) {
        this.initStatus();
        cp.setPath(path);

        String userName = cp.getUserName();
        this.setCode(cp.getCode());

        if (this.getCode() == 1) {
            return;
        }

        String password = cp.getPassword();

        this.setCode(cp.getCode());

        if (this.getCode() == 1) {
            return;
        }

        String conStr = cp.getConnectionString();
        this.setCode(cp.getCode());

        if (this.getCode() == 1) {
            return;
        }

        cc.setUserName(userName);
        cc.setPassword(password);
        cc.setConnectionString(conStr);

        connection = cc.openConnection();
        this.setCode(cc.getCode());

        if (this.getCode() == 1) {
            return;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#closeConnection()
     */
    @Override
    public void closeConnection() {
        sc.closeStatement();
        cc.closeConnection();
        connection = null;
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findAll()
     */
    @Override
    public ResultSet findAll() {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findByZipCode(java.lang.String)
     */
    @Override
    public ResultSet findByZipCode(String zipCode) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE zip_code=? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, zipCode);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findByPrefecture(java.lang.String)
     */
    @Override
    public ResultSet findByPrefecture(String prefecture) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, prefecture);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findByCityLike(java.lang.String)
     */
    @Override
    public ResultSet findByCityLike(String city) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE city LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, city);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findByAreaLike(java.lang.String)
     */
    @Override
    public ResultSet findByAreaLike(String area) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE area LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, area);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see
     * zip_code_db_web_ui.ZipCodeResource#findByPrefectureAndCityLikeAndAreaLike(
     * java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public ResultSet findByPrefectureAndCityLikeAndAreaLike(String prefecture, String city, String area) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? AND area LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, prefecture);
            ps.setString(2, city);
            ps.setString(3, area);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see
     * zip_code_db_web_ui.ZipCodeResource#findByPrefectureAndCityLike(java.lang.
     * String, java.lang.String)
     */
    @Override
    public ResultSet findByPrefectureAndCityLike(String prefecture, String city) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND city LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, prefecture);
            ps.setString(2, city);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see
     * zip_code_db_web_ui.ZipCodeResource#findByPrefectureAndAreaLike(java.lang.
     * String, java.lang.String)
     */
    @Override
    public ResultSet findByPrefectureAndAreaLike(String prefecture, String area) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE prefecture=? AND area LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, prefecture);
            ps.setString(2, area);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }

    /*
     * @see zip_code_db_web_ui.ZipCodeResource#findByCityLikeAndAreaLike(java.lang.
     * String, java.lang.String)
     */
    @Override
    public ResultSet findByCityLikeAndAreaLike(String city, String area) {
        this.initStatus();

        ResultSet result = null;

        if (this.connection == null) {
            this.errorTerminate("Connection が確立されていません。");
            return result;
        }

        String sql = "SELECT * FROM t_zip_code WHERE city LIKE ? AND area LIKE ? LIMIT 1000;";
        PreparedStatement ps = sc.openStatement(connection, sql);
        this.setCode(sc.getCode());

        if (this.getCode() == 1) {
            this.closeConnection();
            return result;
        }

        try {
            ps.setString(1, city);
            ps.setString(2, area);
            result = ps.executeQuery();
            this.setCode(2);
            return result;
        } catch (SQLException e) {
            this.errorTerminate("エラーが発生しました。 " + e.toString());
            this.closeConnection();
            return result;
        }
    }
}
