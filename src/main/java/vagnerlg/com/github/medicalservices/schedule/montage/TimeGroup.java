package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class TimeGroup {

        @NotNull
        @Valid
        private Time start;

        @NotNull
        @Valid
        private Time end;
}
