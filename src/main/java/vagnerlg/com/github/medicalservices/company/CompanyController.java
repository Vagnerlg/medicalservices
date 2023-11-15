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

    @Autowired
    private CompanyService companyService;

    @Autowired
    private WorkerService workerService;

    @GetMapping
    public List<Company> list() {
        return companyService.list();
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable UUID id) {
        return companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @PostMapping
    public Company create(@RequestBody @Valid CompanyDTO company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    public Company edit(@RequestBody @Valid Company company, @PathVariable UUID id) {
        return companyService.update(id, company).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @PostMapping("{id}/worker")
    public Company addWorker(@RequestBody @Valid CompanyAddressDTO companyAddressDTO, @PathVariable UUID id) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));
        Worker worker = workerService.findOne(companyAddressDTO.workerId())
                .orElseThrow(() -> new NotFoundException("Worker", companyAddressDTO.workerId()));
        List<Worker> workers = company.getWorkers();
        workers.add(worker);
        company.setWorkers(workers);

        return companyService.save(company);
    }
}
