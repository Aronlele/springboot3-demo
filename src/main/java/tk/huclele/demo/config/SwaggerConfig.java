package tk.huclele.demo.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * swagger配置
 */
@Configuration
public class SwaggerConfig {

    @Value("${swagger.config.terms-of-service-url}")
    private String termsOfServiceUrl;

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("springboot 3 演示项目")
                        .version("1.0")
                        .description("springboot 3 演示项目")
                        .termsOfService(termsOfServiceUrl));
    }
}
