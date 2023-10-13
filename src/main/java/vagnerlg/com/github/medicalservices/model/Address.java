package vagnerlg.com.github.medicalservices.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import vagnerlg.com.github.medicalservices.company.Company;

import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
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
}
