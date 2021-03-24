package zip_code_db_web_ui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication(scanBasePackages = "zip_code_db_web_ui")
public class ZipCodeDbWebUiApplication extends SpringBootServletInitializer {

    public static void main(String[] args) {
        SpringApplication.run(ZipCodeDbWebUiApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(ZipCodeDbWebUiApplication.class);
    }
}
