package com.hzy.minio;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 作者：亥子曜
 * -后之览者，亦将有感于斯文
 */
@Data
@ConfigurationProperties(prefix = "minio")
public class MinioConfigurationProperties {
    private String endpoint;
    private String accessKey;
    private String secretKey;
    private String bucketName;
}
