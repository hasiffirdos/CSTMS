package com.wego.cstms.minio;
import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
@ConfigurationProperties(prefix = "minio")
public class MinioProperties {

    private String endpoint;
    private Integer port;
    private String accessKey;
    private String secretKey;
    private boolean secure;
    private String bucketName;
//    The maximum size of the picture
    private long imageSize;
//    Maximum size of other files
    private long fileSize;

    @Bean
    public MinioClient minioClient() {
        return MinioClient.builder()
                .credentials(accessKey, secretKey)
                .endpoint(endpoint,port,secure)
                .build();
    }
}