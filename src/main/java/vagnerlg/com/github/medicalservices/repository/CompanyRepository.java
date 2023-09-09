package vagnerlg.com.github.medicalservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vagnerlg.com.github.medicalservices.model.Company;

import java.util.UUID;

public interface CompanyRepository extends JpaRepository<Company, UUID> {
}
