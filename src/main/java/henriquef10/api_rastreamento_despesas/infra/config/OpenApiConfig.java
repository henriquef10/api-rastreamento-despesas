package henriquef10.api_rastreamento_despesas.infra.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Rastreamento de Despesas")
                        .version("v1.0")
                        .description("API para rastreamento de despesas")
                        .termsOfService("https://github.com/henriquef10/api-rastreamento-despesas")
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://github.com/henriquef10/api-rastreamento-despesas")
                        )
                );
    }

}
