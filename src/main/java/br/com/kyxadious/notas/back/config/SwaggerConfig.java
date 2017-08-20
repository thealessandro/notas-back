package br.com.kyxadious.notas.back.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Notas-Back API")
                .description("Simples documentação da API REST")
                .termsOfServiceUrl("")
                .license("Apache License 2.0")
                .licenseUrl("https://github.com/thealessandro/notas-back-spring-boot/blob/master/LICENSE")
                .version("0.0.1")
                .build();
    }

    @Bean
    public Docket notasApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.basePackage("br.com.kyxadious.notas.back"))
                .paths(PathSelectors.regex("/api/.*"))
                .build()
                .apiInfo(apiInfo());
    }
}
