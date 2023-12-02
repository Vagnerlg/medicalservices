package vagnerlg.com.github.medicalservices.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.company.request.CompanyRequest;
import vagnerlg.com.github.medicalservices.company.request.CompanyWorkerRequest;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.worker.Worker;
import vagnerlg.com.github.medicalservices.worker.WorkerService;

import java.util.UUID;

@Tag(name="Company", description="Entidade relacionada a empresas e suas localidades")
@RestController
@RequestMapping("/company")
@RequiredArgsConstructor
class CompanyController {

    private static final String NAME = "Company";

    private final CompanyService companyService;
    private final WorkerService workerService;

    @Operation(
        parameters = {
            @Parameter(in = ParameterIn.QUERY, name = "page"),
            @Parameter(in = ParameterIn.QUERY, name = "size")
        })
    @GetMapping
    Page<Company> list(@PageableDefault @Parameter(hidden = true) Pageable pageable) {
        return companyService.list(pageable);
    }

    @GetMapping(value = "/{id}")
    Company get(@PathVariable UUID id) {
        return companyService.findOne(id).orElseThrow(() -> new NotFoundException(NAME, id));
    }

    @PostMapping
    Company create(@RequestBody @Valid CompanyRequest company) {
        return companyService.create(company);
    }

    @PutMapping("/{id}")
    Company update(@RequestBody @Valid CompanyRequest company, @PathVariable UUID id) {
        return companyService.update(id, company).orElseThrow(() -> new NotFoundException(NAME, id));
    }

    @PostMapping("{id}/worker")
    Company addWorker(@RequestBody @Valid CompanyWorkerRequest workerRequest, @PathVariable UUID id) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException(NAME, id));
        Worker worker = workerService.findOne(workerRequest.workerId())
                .orElseThrow(() -> new NotFoundException("Worker", workerRequest.workerId()));
        company.getWorkers().add(worker);

        return companyService.save(company);
    }

    @DeleteMapping("{id}/worker/{workerId}")
    Company removeWorker(@PathVariable("id") UUID id, @PathVariable("workerId") UUID workerId) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException(NAME, id));
        Worker worker = workerService.findOne(workerId)
                .orElseThrow(() -> new NotFoundException("Worker", workerId));
        company.getWorkers().remove(worker);

        return companyService.save(company);
    }
}
