package vagnerlg.com.github.medicalservices.file;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Entity
@Data
@Setter(AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@Builder
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
        return baseUrl + file;
    }
}
