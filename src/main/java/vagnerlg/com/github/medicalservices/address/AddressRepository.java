package vagnerlg.com.github.medicalservices.address;

import org.springframework.data.jpa.repository.JpaRepository;
import vagnerlg.com.github.medicalservices.company.Company;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

interface AddressRepository extends JpaRepository<Address, UUID> {
    List<Address> findByCompany(Company company);

    Optional<Address> findByIdAndCompany(UUID id, Company company);
}
