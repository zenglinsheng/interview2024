package interview.configurationProperties08;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "test")
@Data
public class TestProperties {

    private String name;

    private String age;

}
