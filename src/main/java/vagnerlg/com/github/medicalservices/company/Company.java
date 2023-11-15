package vagnerlg.com.github.medicalservices.company;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.file.File;
import vagnerlg.com.github.medicalservices.global.entity.BaseEntity;
import vagnerlg.com.github.medicalservices.schedule.Schedule;
import vagnerlg.com.github.medicalservices.worker.Worker;

import java.util.List;
import java.util.Objects;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Company extends BaseEntity {

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

    public String getImageLink() {
        if(Objects.isNull(file)){
            return null;
        }

        return file.getImageLink();
    }
}
