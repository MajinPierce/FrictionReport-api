package report.friction.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenAPIConfig {

    @Value("${friction-report.url.dev}")
    private String devUrl;
    @Value("${friction-report.url.prod}")
    private String prodUrl;

    @Bean
    public OpenAPI openAPI(){
        Server devServer = new Server();
        devServer.setUrl(devUrl);
        devServer.setDescription("Server URL in a local development environment");

        Server prodServer = new Server();
        prodServer.setUrl(prodUrl);
        prodServer.setDescription("Server URL in the production environment");

        Contact contact = new Contact();
        contact.setName("Pierce Beckett");
        contact.setUrl("https://github.com/MajinPierce/");

        License license = new License().name("MPL 2.0").url("https://choosealicense.com/licenses/mpl-2.0/");

        Info info = new Info()
                .title("Friction Report API")
                .version("1.0")
                .contact(contact)
                .description("This API exposes the endpoints needed for friction.report")
                .license(license);

        return new OpenAPI().info(info).servers(List.of(devServer, prodServer));
    }

}
