package vagnerlg.com.github.medicalservices.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.model.Worker;
import vagnerlg.com.github.medicalservices.repository.WorkerRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/worker")
public class WorkerController {

    @Autowired
    private WorkerRepository repository;

    @GetMapping
    public List<Worker> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Worker get(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Worker", id));
    }

    @PostMapping
    public Worker create(@RequestBody @Valid Worker worker) {
        return repository.save(worker);
    }

    @PutMapping("/{id}")
    public Worker edit(@RequestBody Worker worker, @PathVariable UUID id) {
        return repository.findById(id)
            .map(w -> {
                w.setName(worker.getName());
                w.setOccupation(worker.getOccupation());
                return repository.save(w);
            }).orElseThrow(() -> new NotFoundException("Worker", id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
