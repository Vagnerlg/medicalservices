package vagnerlg.com.github.medicalservices.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.schedule.Schedule;
import vagnerlg.com.github.medicalservices.worker.Worker;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
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
    @JsonIgnoreProperties({"company", "address", "worker"})
    private List<Schedule> schedules;

    @OneToMany(mappedBy = "company")
    @JsonIgnoreProperties({"company"})
    private List<Address> addresses;

    @ManyToMany(mappedBy = "companies")
    @JsonIgnoreProperties({"companies"})
    private List<Worker> workers;

    @OneToOne
    @JoinColumn(name = "file_id", referencedColumnName = "file")
    @JsonIgnore
    private File file;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    public String getImageLink() {
        if(Objects.isNull(file)){
            return null;
        }

        return file.getImageLink();
    }
}
