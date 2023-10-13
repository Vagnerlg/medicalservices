package vagnerlg.com.github.medicalservices.address;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import vagnerlg.com.github.medicalservices.company.Company;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

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
    private Company company;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}
