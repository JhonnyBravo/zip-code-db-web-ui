package zip_code_db_web_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * アプリケーションを起動する。
 */
@SpringBootApplication(scanBasePackages = "zip_code_db_web_ui")
public class Main {

    /**
     * @param args 起動パラメータとして使用する引数を受け取る。
     */
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
