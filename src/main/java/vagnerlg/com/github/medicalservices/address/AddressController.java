package vagnerlg.com.github.medicalservices.address;

import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.company.CompanyService;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/company")
class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyService companyService;

    @Autowired
    private AddressService addressService;

    @PostMapping("/{id}/address")
    public Address create(@PathVariable UUID id, @Valid @RequestBody Address address) {
        return addressService.create(id, address);
    }

    @GetMapping("/{id}/address")
    public List<Address> list(@PathVariable UUID id) {
        return addressService.findByCompany(id).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @GetMapping("/{id}/address/{addressId}")
    public Address list(@PathVariable UUID id, @PathVariable UUID addressId) {
        return addressService.findOne(id, addressId).orElseThrow(() -> new NotFoundException("Address", addressId));
    }
}
