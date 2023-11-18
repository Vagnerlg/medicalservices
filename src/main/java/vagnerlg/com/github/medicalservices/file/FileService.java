package vagnerlg.com.github.medicalservices.file;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FileService {

    @Autowired
    private FileRepository fileRepository;
    public File save(File file)
    {
        return fileRepository.save(file);
    }

    public Optional<File> findOne(String fileName) {
        return fileRepository.findById(fileName);
    }
}
