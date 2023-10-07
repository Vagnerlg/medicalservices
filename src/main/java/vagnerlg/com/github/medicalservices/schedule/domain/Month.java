package vagnerlg.com.github.medicalservices.schedule.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Month {

    @NotNull
    private java.time.Month month;

    @NotNull
    @Valid
    private Week week;

    @NotNull
    @Valid
    private Day day;

    private Set<Integer> include;

    private Set<Integer> exclude;

    public Set<LocalDateTime> schedule()
    {
        Set<LocalDate> allDays = week.schedule(monthYear());
        excludeDays(allDays);
        includeDays(allDays);

        return day.schedule(allDays);
    }

    private void includeDays(Set<LocalDate> allDays) {
        Set<LocalDate> includeDays = new HashSet<>();
        if (include != null) {
            for(int number: include) {
                includeDays.add(LocalDate.of(allDays.stream().findFirst().get().getYear(), allDays.stream().findFirst().get().getMonth(), number));
            }
        }
        allDays.addAll(includeDays);
    }

    private void excludeDays(Set<LocalDate> allDays) {
        Set<LocalDate> excludeDays = new HashSet<>();
        if (exclude != null) {
            for (int number : exclude) {
                for (LocalDate day : allDays) {
                    if (day.getDayOfMonth() == number) {
                        excludeDays.add(day);
                    }
                }
            }
        }

        LocalDate now = LocalDate.now();
        for (LocalDate day : allDays) {
            if (now.isAfter(day)) {
                excludeDays.add(day);
            }
        }

        allDays.removeAll(excludeDays);
    }

    private LocalDate monthYear() {
        LocalDate currentDate = LocalDate.now().withDayOfMonth(1);
        LocalDate currentYear = LocalDate.of(currentDate.getYear(), month.getValue(), 1);
        if (currentYear.isAfter(currentDate) || currentYear.isEqual(currentDate)) {
            return currentYear;
        }

        return LocalDate.of(currentDate.getYear() + 1, month.getValue(), 1);
    }
}
