package vagnerlg.com.github.medicalservices.address;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.company.CompanyService;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class AddressService {

    private final CompanyService companyService;
    private final AddressRepository addressRepository;

    @Value("${maps.google.key}")
    private String mapsKey;

    @Value("${maps.google.enabled}")
    private boolean enabled;

    @Autowired
    AddressService(
            CompanyService companyService,
            AddressRepository addressRepository
    ) {
        this.companyService = companyService;
        this.addressRepository = addressRepository;
    }

    public Address create(UUID companyId, Address address) {
        Company company = companyService.findOne(companyId)
                .orElseThrow(() -> new NotFoundException("Company", companyId));
        address.setCompany(company);
        getLocation(address);

        return addressRepository.save(address);
    }

    public Optional<List<Address>> findByCompany(UUID companyId) {
        Optional<Company> company = companyService.findOne(companyId);
        if (company.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(addressRepository.findByCompany(company.get()));
    }

    public Optional<Address> findOne(UUID companyId, UUID id) {

        Optional<Company> optionalCompany = companyService.findOne(companyId);
        if(optionalCompany.isEmpty()) {
            return Optional.empty();
        }

        return addressRepository.findByIdAndCompany(id, optionalCompany.get());
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
