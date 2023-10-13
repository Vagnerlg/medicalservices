package vagnerlg.com.github.medicalservices.worker;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.schedule.Schedule;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.UUID;

@Entity
@Data
public class Worker{

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

    @OneToOne()
    @JoinColumn(name = "file_id", referencedColumnName = "file")
    private File file;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
