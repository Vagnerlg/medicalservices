package vagnerlg.com.github.medicalservices.schedule.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeGroup {

        @NotNull
        @Valid
        private Time start;

        @NotNull
        @Valid
        private Time end;
}
