package vagnerlg.com.github.medicalservices.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vagnerlg.com.github.medicalservices.model.Worker;

import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
}
