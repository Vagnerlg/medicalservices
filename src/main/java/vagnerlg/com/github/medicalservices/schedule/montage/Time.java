package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
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
