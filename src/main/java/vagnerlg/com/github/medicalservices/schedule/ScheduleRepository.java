package vagnerlg.com.github.medicalservices.schedule;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
