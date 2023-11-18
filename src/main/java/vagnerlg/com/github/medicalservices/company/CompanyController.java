package vagnerlg.com.github.medicalservices.company;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.company.dto.CompanyAddressDTO;
import vagnerlg.com.github.medicalservices.company.dto.CompanyDTO;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.worker.Worker;
import vagnerlg.com.github.medicalservices.worker.WorkerService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/company")
class CompanyController {

    private static final String NAME = "Company";

    private final CompanyService companyService;
    private final WorkerService workerService;

    @Autowired
    public CompanyController(CompanyService companyService, WorkerService workerService) {
        this.companyService = companyService;
        this.workerService = workerService;
    }

    @GetMapping
    List<Company> list() {
        return companyService.list();
    }

    @GetMapping("/{id}")
    Company get(@PathVariable UUID id) {
        return companyService.findOne(id).orElseThrow(() -> new NotFoundException(NAME, id));
    }

    @PostMapping
    Company create(@RequestBody @Valid CompanyDTO company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    Company edit(@RequestBody @Valid Company company, @PathVariable UUID id) {
        return companyService.update(id, company).orElseThrow(() -> new NotFoundException(NAME, id));
    }

    @PostMapping("{id}/worker")
    Company addWorker(@RequestBody @Valid CompanyAddressDTO companyAddressDTO, @PathVariable UUID id) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException(NAME, id));
        Worker worker = workerService.findOne(companyAddressDTO.workerId())
                .orElseThrow(() -> new NotFoundException("Worker", companyAddressDTO.workerId()));
        List<Worker> workers = company.getWorkers();
        workers.add(worker);
        company.setWorkers(workers);

        return companyService.save(company);
    }
}
