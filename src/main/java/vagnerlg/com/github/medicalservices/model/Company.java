package vagnerlg.com.github.medicalservices.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vagnerlg.com.github.medicalservices.schedule.domain.entity.Schedule;

import java.util.Set;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @NotBlank
    private String name;

    @OneToMany(mappedBy = "company")
    private Set<Schedule> schedules;

    @OneToMany(mappedBy = "company")
    private Set<Address> addresses;
}
