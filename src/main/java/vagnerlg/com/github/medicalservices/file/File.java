package vagnerlg.com.github.medicalservices.file;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @NotBlank
    private String drive;

    @NotBlank
    private String path;

    @NotBlank
    @Id
    private String file;
}
