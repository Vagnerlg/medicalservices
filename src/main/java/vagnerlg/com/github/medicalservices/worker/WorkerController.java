package vagnerlg.com.github.medicalservices.worker;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/worker")
class WorkerController {

    private final WorkerRepository repository;

    @Autowired
    public WorkerController(WorkerRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public List<Worker> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    Worker get(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Worker", id));
    }

    @PostMapping
    Worker create(@RequestBody @Valid Worker worker) {
        return repository.save(worker);
    }

    @PutMapping("/{id}")
    Worker edit(@RequestBody @Valid Worker worker, @PathVariable UUID id) {
        return repository.findById(id)
            .map((Worker workerEdit) -> {
                workerEdit.setName(worker.getName());
                workerEdit.setOccupation(worker.getOccupation());
                return repository.save(workerEdit);
            }).orElseThrow(() -> new NotFoundException("Worker", id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
