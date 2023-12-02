package vagnerlg.com.github.medicalservices.company;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.company.request.CompanyRequest;
import vagnerlg.com.github.medicalservices.file.FileService;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repository;
    private final FileService fileService;

    public Page<Company> list(Pageable pageable) {
        return repository.findAll(pageable);
    }

    public Optional<Company> findOne(UUID id) {
        return repository.findById(id);
    }

    public Company create(CompanyRequest companyRequest) {
        var company = new Company();
        company.setName(companyRequest.name());
        if (companyRequest.fileName() != null) {
            fileService.findOne(companyRequest.fileName()).ifPresent(company::setFile);
        }

        return repository.save(company);
    }

    public Optional<Company> update(UUID id, CompanyRequest company) {
        return findOne(id).map((Company companyEdit) -> {
            companyEdit.setName(company.name());
            fileService.findOne(company.fileName()).ifPresent(companyEdit::setFile);
            return  repository.save(companyEdit);
        });
    }

    public Company save(Company company) {
        return repository.save(company);
    }
}
