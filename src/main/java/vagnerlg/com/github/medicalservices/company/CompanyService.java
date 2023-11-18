package vagnerlg.com.github.medicalservices.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.company.dto.CompanyDTO;
import vagnerlg.com.github.medicalservices.file.FileService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    private CompanyRepository repository;
    private FileService fileService;

    @Autowired
    public CompanyService(CompanyRepository repository, FileService fileService) {
        this.repository = repository;
        this.fileService = fileService;
    }

    public List<Company> list() {
        return repository.findAll();
    }

    public Optional<Company> findOne(UUID id) {
        return repository.findById(id);
    }

    public Company create(CompanyDTO companyDTORequest) {
        var company = new Company();
        company.setName(companyDTORequest.name());
        fileService.findOne(companyDTORequest.file()).ifPresent(company::setFile);

        return repository.save(company);
    }

    public Optional<Company> update(UUID id, Company company) {
        return repository.findById(id).map((Company companyEdit) -> {
            companyEdit.setName(company.getName());
            return  repository.save(companyEdit);
        });
    }

    public Company save(Company company) {
        return repository.save(company);
    }
}
