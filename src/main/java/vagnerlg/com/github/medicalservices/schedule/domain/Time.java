package vagnerlg.com.github.medicalservices.schedule.domain;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Time {

        @Min(0)
        @Max(23)
        @NotNull
        private Integer hour;

        @Min(0)
        @Max(59)
        @NotNull
        private Integer minute;
}
