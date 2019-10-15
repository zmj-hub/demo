package com.zmj.demo.config;

import com.google.common.base.Predicate;
import io.swagger.annotations.ApiOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration

@ConfigurationProperties(prefix ="swagger")

public class Swagger2 {



    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("")
                .description("")
                .termsOfServiceUrl("")
                .version("1.0")
                .build();
    }

//    private boolean disable;
//
//    @bean
//
//    public Docket createRestApi() {
//
//        Predicate selector = PathSelectors.any();
//
//        if (disable) {
//
//            selector = PathSelectors.none();
//
//        }
//
//        return new Docket(DocumentationType.SWAGGER_2)
//
//                .apiInfo(apiInfo())
//
//                .select()
//
//                .apis(RequestHandlerSelectors.basePackage("com.coss"))
//
//                .paths(selector)
//
//                .build();
//
//    }
//
//    private ApiInfo apiInfo() {
//
//        return new ApiInfoBuilder()
//
//                .title("工具接口")
//
//                .version("1.0.0.")
//
//                .build();
//
//    }
//
//    public void setDisable(boolean disable) {
//
//        this.disable = disable;
//
//    }

}
