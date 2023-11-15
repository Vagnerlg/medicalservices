package vagnerlg.com.github.medicalservices.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Objects;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File {

    @NotBlank
    private String drive;

    @NotBlank
    @JsonIgnore
    private String path;

    @NotBlank
    @Id
    private String file;

    @NotBlank
    private String baseUrl;

    public String getImageLink() {
        return getBaseUrl() + getFile();
    }
}
