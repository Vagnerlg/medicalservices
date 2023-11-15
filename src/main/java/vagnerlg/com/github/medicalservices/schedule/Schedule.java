package vagnerlg.com.github.medicalservices.schedule;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.company.Company;
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

    @NotNull
    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
