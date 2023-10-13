package vagnerlg.com.github.medicalservices.schedule.domain.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vagnerlg.com.github.medicalservices.model.Company;
import vagnerlg.com.github.medicalservices.worker.Worker;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotNull
    private LocalDateTime start;

    @NotNull
    @Column(name = "endDate")
    private LocalDateTime end;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "worker_id")
    private Worker worker;

    @NotNull
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
}
