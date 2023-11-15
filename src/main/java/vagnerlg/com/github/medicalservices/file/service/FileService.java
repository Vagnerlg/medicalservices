package vagnerlg.com.github.medicalservices.file.service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.file.File;

import java.util.Optional;

public interface FileService {
    Optional<File> upload(String fileName, MultipartFile inputStream);
    Optional<ByteArrayResource> get(String fileName);
}
