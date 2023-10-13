package vagnerlg.com.github.medicalservices.controller;

import jakarta.validation.Valid;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.model.Address;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.repository.AddressRepository;
import vagnerlg.com.github.medicalservices.company.CompanyRepository;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Value("${maps.google.key}")
    private String mapsKey;

    @PostMapping("/{id}")
    public Address create(@PathVariable UUID id, @Valid @RequestBody Address address) {
        Company company = companyRepository.findById(id).orElseThrow(() -> new NotFoundException("Company", id));
        address.setCompany(company);
        getLocation(address);

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

    private void getLocation(Address address)
    {
        String response = (new RestTemplate()).getForObject(
                "https://maps.googleapis.com/maps/api/geocode/json?address={address}&key={key}",
                String.class,
                Map.of(
                        "address", address.getStreet() + ", " +
                                address.getNumber() + " - " +
                                address.getDistrict() + ", " +
                                address.getMunicipal() + " - " +
                                address.getState() + ", " +
                                address.getPostalCode() + ", Brazil",
                        "key", mapsKey)
        );

        JSONObject json = new JSONObject(response);
        JSONObject location = (JSONObject) json.query("/results/0/geometry/location");

        if (location == null) {
            return;
        }

        address.setLatitude(location.getFloat("lng"));
        address.setLongitude(location.getFloat("lat"));
    }
}
