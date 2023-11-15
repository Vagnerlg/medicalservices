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
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CompanyService companyService;

    @Value("${maps.google.key}")
    private String mapsKey;

    @Value("${maps.google.enabled}")
    private boolean enabled;

    @PostMapping("/{id}/address")
    public Address create(@PathVariable UUID id, @Valid @RequestBody Address address) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));
        address.setCompany(company);
        getLocation(address);

        return addressRepository.save(address);
    }

    @GetMapping("/{id}/address")
    public List<Address> list(@PathVariable UUID id) {
        Company company = companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));

        return addressRepository.findByCompany(company);
    }

    @GetMapping("/{id}/address/{addressId}")
    public Address list(@PathVariable UUID id, @PathVariable UUID addressId) {
        companyService.findOne(id).orElseThrow(() -> new NotFoundException("Company", id));

        return addressRepository.findById(addressId).orElseThrow(() -> new NotFoundException("Address", addressId));
    }

    private void getLocation(Address address) {
        if (!enabled) {
            return;
        }

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

        var json = new JSONObject(response);
        JSONObject location = (JSONObject) json.query("/results/0/geometry/location");

        if (location == null) {
            return;
        }

        address.setLatitude(location.getFloat("lng"));
        address.setLongitude(location.getFloat("lat"));
    }
}
