package vagnerlg.com.github.medicalservices.company;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.worker.Worker;
import vagnerlg.com.github.medicalservices.worker.WorkerRepository;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/company")
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @Autowired
    private WorkerRepository workerRepository;

    @GetMapping
    public List<Company> list() {
        return companyService.list();
    }

    @GetMapping("/{id}")
    public Company get(@PathVariable UUID id) {
        return companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @PostMapping
    public Company create(@RequestBody @Valid CompanyDTORequest company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    public Company edit(@RequestBody @Valid Company company, @PathVariable UUID id) {
        return companyService.update(id, company).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @PostMapping("{id}/worker")
    public Worker addWorker(@RequestBody @Valid CompanyAddressDTO companyAddressDTO, @PathVariable UUID id) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));
        Worker worker = workerRepository.findById(companyAddressDTO.workerId()).orElseThrow(() -> new NotFoundException("Worker", companyAddressDTO.workerId()));
        Set<Company> companies = worker.getCompanies();
        companies.add(company);
        worker.setCompanies(companies);

        return workerRepository.save(worker);
    }
}
