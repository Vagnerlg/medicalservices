package vagnerlg.com.github.medicalservices.file;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import vagnerlg.com.github.medicalservices.file.service.FileService;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.Objects;
import java.util.UUID;

@Tag(name = "File", description = "Upload de imagens para varias entidades")
@RestController
@RequestMapping("/file")
@RequiredArgsConstructor
class FileController {

    private final FileService fileService;

    @PostMapping(value = "/upload", produces = MediaType.APPLICATION_JSON_VALUE)
    File upload(@RequestParam("file") MultipartFile file) {
        return fileService.upload(Objects.requireNonNull(file.getOriginalFilename()), file)
                .orElseThrow(() -> new NotFoundException("file", UUID.randomUUID()));
    }

    @GetMapping(value = "/get/{fileName}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    Resource getFile(@PathVariable("fileName") String fileName) {
        return fileService.get(fileName).orElseThrow(
                () -> new NotFoundException("file", UUID.randomUUID()));
    }
}
