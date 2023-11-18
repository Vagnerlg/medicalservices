package vagnerlg.com.github.medicalservices.worker;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.utils.entity.BaseEntity;
import vagnerlg.com.github.medicalservices.schedule.Schedule;

import java.util.List;
import java.util.Set;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Worker extends BaseEntity {

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
    @JsonIgnoreProperties({"schedules", "workers"})
    private Set<Company> companies;

    @OneToMany(mappedBy = "worker")
    @JsonIgnoreProperties({"company", "address", "worker"})
    private List<Schedule> schedules;

    @OneToOne()
    @JoinColumn(name = "file_id", referencedColumnName = "file")
    private File file;
}
