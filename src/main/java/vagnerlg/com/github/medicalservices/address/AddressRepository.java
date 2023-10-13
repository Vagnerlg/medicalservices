package vagnerlg.com.github.medicalservices.address;

import org.springframework.data.jpa.repository.JpaRepository;
import vagnerlg.com.github.medicalservices.company.Company;

import java.util.List;
import java.util.UUID;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    public List<Address> findByCompany(Company company);
}
