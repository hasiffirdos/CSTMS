package com.wego.cstms.service;

import com.wego.cstms.minio.MinioService;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

@Service
public class FilesStorageService {

    private final Path root = Paths.get("uploads");
    private final MinioService minioService;

    public FilesStorageService(MinioService minioService) {
        this.minioService = minioService;
    }

    public void init(){
        try {
            Files.createDirectory(root);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize folder for upload!");
        }
    }

    public void save(MultipartFile file,Integer courseId){
        try {

            String stpath = this.root.toString()+"/"+courseId.toString();
            Path path = Paths.get(this.root.toString(),courseId.toString());
            File directory = new File(path.toString());
            if (!directory.exists()){
                directory.mkdirs();
            }
            File files = new File(stpath,file.getOriginalFilename());
            Files.copy(file.getInputStream(), files.toPath());
        } catch (Exception e) {
            throw new RuntimeException("Could not store the file. Error: " + e.getMessage());
        }
    }

    public Resource load(String filename){
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteFile(String filename){
            Path file = root.resolve(filename);
            FileSystemUtils.deleteRecursively(file.toFile());
    }
    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public Stream<Path> loadAll(){
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Could not load the files!");
        }
    }
}
