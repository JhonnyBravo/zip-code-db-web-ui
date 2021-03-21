package zip_code_db_web_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "zip_code_db_web_ui")
public class ZipCodeDbWebUiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ZipCodeDbWebUiApplication.class, args);
    }
}
