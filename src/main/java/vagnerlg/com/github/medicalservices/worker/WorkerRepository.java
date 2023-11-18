package vagnerlg.com.github.medicalservices.worker;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface WorkerRepository extends JpaRepository<Worker, UUID> {
}
