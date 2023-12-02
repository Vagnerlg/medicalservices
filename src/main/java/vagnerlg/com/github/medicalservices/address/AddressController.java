package vagnerlg.com.github.medicalservices.address;

import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;

import java.util.List;
import java.util.UUID;

@Tag(name="Company")
@RestController
@RequestMapping("/company")
class AddressController {

    private final AddressService addressService;

    @Autowired
    AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/{id}/address")
    Address create(@PathVariable UUID id, @Valid @RequestBody Address address) {
        return addressService.create(id, address);
    }

    @GetMapping("/{id}/address")
    List<Address> list(@PathVariable UUID id) {
        return addressService.findByCompany(id).orElseThrow(() -> new NotFoundException("Company", id));
    }

    @GetMapping("/{id}/address/{addressId}")
    Address list(@PathVariable UUID id, @PathVariable UUID addressId) {
        return addressService.findOne(id, addressId).orElseThrow(() -> new NotFoundException("Address", addressId));
    }
}
