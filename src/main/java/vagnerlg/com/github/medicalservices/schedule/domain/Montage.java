package vagnerlg.com.github.medicalservices.schedule.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Montage {

    @NotEmpty
    @Valid
    private Set<Month> months;

    @NotNull
    private UUID workerId;

    @NotNull
    private UUID companyId;

    public List<LocalDateTime> schedule()
    {
        Set<LocalDateTime> monthYear = new HashSet<>();
        for (Month month: months) {
            monthYear.addAll(month.schedule());
        }

        return monthYear.stream().sorted().toList();
    }
}