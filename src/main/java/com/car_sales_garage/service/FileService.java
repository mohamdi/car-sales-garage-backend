package com.car_sales_garage.service;

import jakarta.annotation.PostConstruct;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;

@Slf4j
@Service
public class FileService {

    @Value("${uploads.path}")
    private String uploadPath;

    @SneakyThrows
    @PostConstruct
    private void checkFilePath() {
        Path path = Paths.get(uploadPath);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    public String saveAndReturnPath(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new IllegalArgumentException("File is required");
        }
        String fileName = generateFileName(file);
        Path filepath = Paths.get(uploadPath, fileName);
        try (OutputStream os = Files.newOutputStream(filepath)) {
            os.write(file.getBytes());
        } catch (IOException e) {
            log.error("Error saving file", e);
        }

        return fileName;
    }

    private String generateFileName(MultipartFile file) {
        return Instant.now().toString() + "_" + file.getOriginalFilename();
    }
}
