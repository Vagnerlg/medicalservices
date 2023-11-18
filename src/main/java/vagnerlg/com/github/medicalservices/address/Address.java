package vagnerlg.com.github.medicalservices.address;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.utils.entity.BaseEntity;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Address extends BaseEntity {

    @NotNull
    private String street;

    @NotNull
    private String number;

    private String complement;

    @NotNull
    private String municipal;

    @NotNull
    private String state;

    @NotNull
    private String district;

    @NotNull
    private String postalCode;

    private Float latitude;

    private Float longitude;

    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonIgnoreProperties({"schedules","addresses","workers"})
    private Company company;
}
