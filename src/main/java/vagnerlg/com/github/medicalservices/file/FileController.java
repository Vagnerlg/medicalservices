package vagnerlg.com.github.medicalservices.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.file.service.FileService;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

    @Autowired
    private FileRepository fileRepository;

    @PostMapping("/upload")
    public File upload(@RequestParam("file") MultipartFile file) {
        try {
            return fileService.upload(Objects.requireNonNull(file.getOriginalFilename()), file.getInputStream());
        } catch (IOException e) {
            throw new NotFoundException("file", UUID.randomUUID());
        }
    }

    @GetMapping()
    public List<File> list() {
        return fileRepository.findAll();
    }
}
