package vagnerlg.com.github.medicalservices.worker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class WorkerService {

    @Autowired
    private WorkerRepository workerRepository;

    public Optional<Worker> findOne(UUID id) {
        return workerRepository.findById(id);
    }
}
