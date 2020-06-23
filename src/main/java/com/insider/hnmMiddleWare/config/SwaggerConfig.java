package com.insider.hnmMiddleWare.config;

import com.google.common.base.Predicates;
import javax.servlet.ServletContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.paths.RelativePathProvider;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Autowired
    ServletContext servletContext;

    @Value("${swagger.base.url:localhost:8080}")
    String baseUrl;

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).host(baseUrl)
                .pathProvider(new RelativePathProvider(servletContext) {
                    @Override
                    public String getApplicationBasePath() {
                        return "/";
                    }
                })
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.insider.hnmMiddleWare.controllers"))
                .paths(Predicates.not(PathSelectors.regex("/error")))
                .build()
                .apiInfo(apiInfo());
    }

    private ApiInfo apiInfo() {
        String description = "Hacker News Middleware Service API";
        return new ApiInfoBuilder()
                .title("HN Middleware REST API")
                .description(description)
                .version("0.0.1")
                .build();
    }

}
