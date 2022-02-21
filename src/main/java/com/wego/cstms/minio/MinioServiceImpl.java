package com.wego.cstms.minio;

import io.minio.MinioClient;
import io.minio.messages.Bucket;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


import java.io.InputStream;
import java.util.List;
import java.util.UUID;


@Service
public class MinioServiceImpl implements MinioService {

    private final MinioUtil minioUtil;
    private final MinioProperties minioProperties;

    public MinioServiceImpl(MinioUtil minioUtil, MinioClient minioClient, MinioProperties minioProperties) {
        this.minioUtil = minioUtil;
        this.minioProperties = minioProperties;
    }

    @Override
    public boolean bucketExists(String bucketName) {
        return minioUtil.bucketExists(bucketName);
    }


    @Override
    public void makeBucket(String bucketName) {
        minioUtil.makeBucket(bucketName);
    }

    @Override
    public List<String> listBucketName() {
        return minioUtil.listBucketNames();
    }

    @Override
    public List<Bucket> listBuckets() {
        return minioUtil.listBuckets();
    }

    @Override
    public boolean removeBucket(String bucketName) {
        return minioUtil.removeBucket(bucketName);
    }


    @Override
    public List<String> listObjectNames(String bucketName) {
        return minioUtil.listObjectNames(bucketName);
    }


    @Override
    public String putObject(MultipartFile file, String folderName, String bucketName, String fileType) {
        try {
            bucketName = minioProperties.getBucketName();
            if (!this.bucketExists(bucketName)) {
                this.makeBucket(bucketName);
            }
            String fileName = file.getOriginalFilename();

            String objectName = folderName + "/" + fileName;
            minioUtil.putObject(bucketName, file, objectName, fileType);
            return minioProperties.getEndpoint() + "/" + bucketName + "/" + objectName;
        } catch (Exception e) {
            e.printStackTrace();
            return " Upload failed ";
        }
    }

    @Override
    public Resource downloadObject(String objectName) {
        String bucketName = minioProperties.getBucketName();
        return new InputStreamResource(minioUtil.getObject(bucketName, objectName));
    }

    @Override
    public boolean removeObject(String bucketName, String objectName) {
        return minioUtil.removeObject(bucketName, objectName);
    }

    @Override
    public boolean removeListObject(String bucketName, List<String> objectNameList) {
        return minioUtil.removeObject(bucketName, objectNameList);
    }

    @Override
    public String getObjectUrl(String bucketName, String objectName) {
        return minioUtil.getObjectUrl(bucketName, objectName);
    }
}