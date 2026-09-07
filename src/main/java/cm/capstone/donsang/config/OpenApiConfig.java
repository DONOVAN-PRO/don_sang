package cm.capstone.donsang.config;
import io.swagger.v3.oas.models.OpenAPI; import io.swagger.v3.oas.models.info.Info; import org.springframework.context.annotation.Bean; import org.springframework.context.annotation.Configuration;
@Configuration public class OpenApiConfig { @Bean public OpenAPI donSangOpenAPI(){return new OpenAPI().info(new Info().title("Réseau Don de Sang API").version("1.0.0").description("API complète du réseau de don de sang — authentification, donneurs, établissements et coordinateur."));} }
