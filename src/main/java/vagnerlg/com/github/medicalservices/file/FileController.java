package vagnerlg.com.github.medicalservices.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.file.service.FileService;

import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @PostMapping("/upload")
    public File upload(@RequestParam("file") MultipartFile file) {
        try {
            return fileService.upload(Objects.requireNonNull(file.getOriginalFilename()), file.getInputStream());
        } catch (IOException e) {
            throw new NotFoundException("file", UUID.randomUUID());
        }
    }
}
