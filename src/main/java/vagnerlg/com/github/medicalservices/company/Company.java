package vagnerlg.com.github.medicalservices.company;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.model.Address;
import vagnerlg.com.github.medicalservices.schedule.domain.entity.Schedule;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
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

    @OneToOne()
    @JoinColumn(name = "file_id", referencedColumnName = "file")
    private File file;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
