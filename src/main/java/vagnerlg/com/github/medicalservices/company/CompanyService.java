package vagnerlg.com.github.medicalservices.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.company.dto.CompanyDTO;
import vagnerlg.com.github.medicalservices.file.FileRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository repository;

    @Autowired
    private FileRepository fileRepository;

    public List<Company> list() {
        return repository.findAll();
    }

    public Optional<Company> findOne(UUID id) {
        return repository.findById(id);
    }

    public Company create(CompanyDTO companyDTORequest) {
        Company company = new Company();
        company.setName(companyDTORequest.name());
        fileRepository.findById(companyDTORequest.file()).ifPresent(company::setFile);

        return repository.save(company);
    }

    public Optional<Company> update(UUID id, Company company) {
        return repository.findById(id).map(c -> {
            c.setName(company.getName());
            return  repository.save(c);
        });
    }

    public Company save(Company company) {
        return repository.save(company);
    }
}
