package com.aos.api.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.Contact;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI aosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("AOS 최소필드 API 연계 플랫폼")
                        .version("1.0.0")
                        .description("""
                                보험개발원 AOS 최소필드 API 연계 플랫폼.
                                제공된 제안서의 Event / Vehicle / Part / Shop 최소 항목을 기준으로
                                수신(POST), 조회(GET), 외부 API 송신(GET/POST) 기능을 제공합니다.
                                """)
                        .contact(new Contact().name("AOS API 개발팀")));
    }
}
