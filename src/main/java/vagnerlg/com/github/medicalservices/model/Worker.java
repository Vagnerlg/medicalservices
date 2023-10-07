package vagnerlg.com.github.medicalservices.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vagnerlg.com.github.medicalservices.schedule.domain.entity.Schedule;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Worker {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @NotBlank
    private String occupation;

    @ManyToMany(cascade=CascadeType.PERSIST)
    @JoinTable(
            name = "company_worker",
            joinColumns = {@JoinColumn(name = "worker_id")},
            inverseJoinColumns = {@JoinColumn(name = "company_id")}
    )
    private Set<Company> companies;

    @OneToMany(mappedBy = "worker")
    private Set<Schedule> schedules;
}
