package vagnerlg.com.github.medicalservices.schedule;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.utils.entity.BaseEntity;
import vagnerlg.com.github.medicalservices.worker.Worker;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
public class Schedule extends BaseEntity {

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
}
