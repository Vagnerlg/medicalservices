package vagnerlg.com.github.medicalservices.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.file.File;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.UUID;

@Service
public class LocalFileService implements FileService {

    @Autowired
    private vagnerlg.com.github.medicalservices.file.FileService fileService;

    @Value("${fileSystem.local.dir}")
    private String basePathDir;

    @Value("${fileSystem.local.url}")
    private String baseUrl;

    public Optional<File> upload(String fileName, MultipartFile multipartFile){
        String newFileName = getNewFileName(fileName);
        Path basePath = Paths.get(basePathDir);
        if(!Files.exists(basePath)) {
            try {
                Files.createDirectories(basePath);
            } catch (IOException e) {
                return Optional.empty();
            }
        }

        Path destinationFile = basePath.resolve(Paths.get(getNewFileName(fileName)))
                .normalize().toAbsolutePath();

        try {
            Files.copy(multipartFile.getInputStream(), destinationFile,
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            return Optional.empty();
        }

        File fileEntity = File.builder()
            .drive("local")
            .path(basePathDir)
            .baseUrl(baseUrl)
            .file(newFileName)
            .build();

        return Optional.of(fileService.save(fileEntity));
    }

    @Override
    public Optional<ByteArrayResource> get(String fileName) {
        Path path = Paths.get(basePathDir + fileName);
        try {
            return Optional.of(new ByteArrayResource(Files.readAllBytes(path)));
        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private static String getNewFileName(String fileName) {
        String extension = fileName.substring(fileName.indexOf('.') - 1);

        return UUID.randomUUID() + extension;
    }
}
