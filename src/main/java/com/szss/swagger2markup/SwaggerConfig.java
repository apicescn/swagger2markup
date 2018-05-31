/*
 *
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 *
 */
package com.szss.swagger2markup;

import static java.util.Arrays.asList;
import static springfox.documentation.builders.PathSelectors.ant;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.base.Predicates;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.Contact;
import springfox.documentation.service.GrantType;
import springfox.documentation.service.ImplicitGrant;
import springfox.documentation.service.LoginEndpoint;
import springfox.documentation.service.OAuth;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket restApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).securitySchemes(asList(
            new OAuth("petstore_auth",
                asList(new AuthorizationScope("write_pets", "modify pets in your account"),
                    new AuthorizationScope("read_pets", "read your pets")),
                Arrays.<GrantType>asList(
                    new ImplicitGrant(new LoginEndpoint("http://petstore.swagger.io/api/oauth/dialog"), "tokenName"))),
            new ApiKey("api_key", "api_key", "header"))).select()
            .paths(Predicates.and(ant("/**"), Predicates.not(ant("/error")), Predicates.not(ant("/management/**")),
                Predicates.not(ant("/management*"))))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title("Swagger Petstore").description("Petstore API Description")
            .contact(new Contact("TestName", "http:/test-url.com", "test@test")).license("Apache 2.0")
            .licenseUrl("http://www.apache.org/licenses/LICENSE-2.0.html").version("1.0.0").build();
    }
}
