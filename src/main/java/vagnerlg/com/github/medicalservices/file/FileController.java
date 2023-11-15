package vagnerlg.com.github.medicalservices.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.file.service.FileService;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

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
        return fileService.upload(Objects.requireNonNull(file.getOriginalFilename()), file)
                .orElseThrow(() -> new NotFoundException("file", UUID.randomUUID()));
    }

    @GetMapping()
    public List<File> list() {
        return fileRepository.findAll();
    }

    @GetMapping(value = "/get/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public @ResponseBody Resource getFile(@PathVariable("fileName") String fileName) {
        return fileService.get(fileName).orElseThrow(
                () -> new NotFoundException("file", UUID.randomUUID()));
    }
}
