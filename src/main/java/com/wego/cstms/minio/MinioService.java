package com.wego.cstms.minio;
import io.minio.messages.Bucket;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;
import java.io.InputStream;
import java.util.List;

public interface MinioService {

    boolean bucketExists(String bucketName);
    void makeBucket(String bucketName);
    List<String> listBucketName();
    List<Bucket> listBuckets();
    boolean removeBucket(String bucketName);
    List<String> listObjectNames(String bucketName);
    String putObject( MultipartFile multipartFile, String folderName, String bucketName,String fileType);
    Resource downloadObject(String objectName);
    boolean removeObject(String bucketName, String objectName);
    boolean removeListObject(String bucketName, List<String> objectNameList);
    String getObjectUrl(String bucketName,String objectName);
}
