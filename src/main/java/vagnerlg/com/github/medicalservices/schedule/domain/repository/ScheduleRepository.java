package vagnerlg.com.github.medicalservices.schedule.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import vagnerlg.com.github.medicalservices.schedule.domain.entity.Schedule;

import java.util.UUID;

public interface ScheduleRepository extends JpaRepository<Schedule, UUID> {
}
