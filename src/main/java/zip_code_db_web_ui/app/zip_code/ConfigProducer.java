package zip_code_db_web_ui.app.zip_code;

import java.io.BufferedInputStream;
import java.util.Properties;
import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

/**
 * config.properties から値を取得する。
 */
@ApplicationScoped
public class ConfigProducer {
  private Properties properties;

  /**
   * 初期化処理を実行する。
   */
  @PostConstruct
  public void init() {
    try (BufferedInputStream steram = new BufferedInputStream(
        this.getClass().getClassLoader().getResourceAsStream("config.properties"))) {
      this.properties = new Properties();
      this.properties.load(steram);
    } catch (final Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * {@link Config} アノテーションに指定されたキーを基に値を取得する。
   *
   * @param ip {@link InjectionPoint}
   * @return value プロパティの値を返す。
   */
  @Produces
  @Config
  public String getConfig(InjectionPoint ip) {
    final String key = ip.getAnnotated().getAnnotation(Config.class).value();
    return properties.getProperty(key);
  }
}
