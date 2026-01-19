package com.hzy.minio;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */
@Configuration
@Data
@EnableConfigurationProperties(MinioConfigurationProperties.class)
public class MinioConfiguration {

    @Autowired
    private MinioConfigurationProperties properties;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder().endpoint(properties.getEndpoint()).credentials(properties.getAccessKey(), properties.getSecretKey()).build();
    }
}
