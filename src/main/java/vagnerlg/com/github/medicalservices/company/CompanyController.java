package vagnerlg.com.github.medicalservices.company;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyRepository repository;

    @GetMapping
    public List<Company> list() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable UUID id) {
        return repository.findById(id).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @PostMapping
    public Company create(@RequestBody @Valid Company company) {
        return repository.save(company);
    }

    @PutMapping("/{id}")
    public Company edit(@RequestBody @Valid Company company, @PathVariable UUID id) {
        return repository.findById(id).map(c -> {
            c.setName(c.getName());
            return  repository.save(c);
        }).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public void delete(@PathVariable UUID id) {
        repository.deleteById(id);
    }
}
