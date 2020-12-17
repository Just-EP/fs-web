package ml.justep7185.fsweb.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @author yangjiawei
 * @date 2020-12-17 13:45
 **/
@Component
@PropertySource(value = "classpath:application.yml")
@ConfigurationProperties(prefix = "custom.upload")
@Data
public class UploadFile {
    private String fileDirPath;
}
