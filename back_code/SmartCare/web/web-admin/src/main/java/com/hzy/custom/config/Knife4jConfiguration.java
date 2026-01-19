package com.hzy.custom.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfiguration {

    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(
                new Info()
                        .title("SmartCare")
                        .version("1.0")
                        .description("后台管理系统API"));
    }
    
    @Bean
    public GroupedOpenApi caregiverAPI() {

        return GroupedOpenApi.builder().group("监护人管理").
                pathsToMatch(
                        "/caregiver/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi roomAPI() {

        return GroupedOpenApi.builder().group("房间管理").
                pathsToMatch(
                        "/room/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi patientAPI() {

        return GroupedOpenApi.builder().group("病人管理").
                pathsToMatch(
                        "/patient/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi userAPI() {

        return GroupedOpenApi.builder().group("用户管理").
                pathsToMatch(
                        "/user/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi relationAPI() {

        return GroupedOpenApi.builder().group("关系管理").
                pathsToMatch(
                        "/relation/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi paramAPI() {

        return GroupedOpenApi.builder().group("数据元管理").
                pathsToMatch(
                        "/param/**"
                ).
                build();
    }

    @Bean
    public GroupedOpenApi eventAPI() {

        return GroupedOpenApi.builder().group("事件管理").
                pathsToMatch(
                        "/event/**"
                ).
                build();
    }



}