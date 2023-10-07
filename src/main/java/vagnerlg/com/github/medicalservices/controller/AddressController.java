package vagnerlg.com.github.medicalservices.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.model.Address;
import vagnerlg.com.github.medicalservices.model.Company;
import vagnerlg.com.github.medicalservices.repository.AddressRepository;
import vagnerlg.com.github.medicalservices.repository.CompanyRepository;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @PostMapping("/{id}")
    public Address create(@PathVariable UUID id, @Valid @RequestBody Address address) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company", id));
        address.setCompany(company);

        return addressRepository.save(address);
    }

    @GetMapping("/{id}")
    public List<Address> list(@PathVariable UUID id) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company", id));

        return addressRepository.findByCompany(company);
    }

    @GetMapping()
    public List<Address> listAll() {
        return addressRepository.findAll();
    }

}
