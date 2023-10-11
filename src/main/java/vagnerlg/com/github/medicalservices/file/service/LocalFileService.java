package vagnerlg.com.github.medicalservices.file.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.file.FileRepository;
import vagnerlg.com.github.medicalservices.file.service.FileService;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class LocalFileService implements FileService {

    @Autowired
    private FileRepository fileRepository;

    @Value("${fileSystem.local.dir}")
    private String basePathDir;

    public File upload(String fileName, InputStream inputStream) throws IOException {
        String newFileName = getNewFileName(fileName);

        Path basePath = Paths.get(basePathDir);
        if(!Files.exists(basePath)) {
            Files.createDirectories(basePath);
        }

        Path destinationFile = basePath.resolve(Paths.get(newFileName))
                .normalize().toAbsolutePath();

        Files.copy(inputStream, destinationFile,
                StandardCopyOption.REPLACE_EXISTING);

        File fileEntity = new File(
                "local",
                basePathDir,
                newFileName
        );

        return fileRepository.save(fileEntity);
    }

    private static String getNewFileName(String fileName) {
        String extension = fileName.substring(fileName.indexOf('.') - 1);

        return UUID.randomUUID() + extension;
    }
}
