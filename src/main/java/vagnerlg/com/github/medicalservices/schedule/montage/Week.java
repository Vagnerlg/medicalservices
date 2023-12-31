package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
public class Week {

    @NotEmpty
    private Set<DayOfWeek> daysOfWeek;

    public Set<LocalDate> schedule(LocalDate monthYear) {
        Set<LocalDate> days = new HashSet<>();
        var currentMonth = LocalDate.of(monthYear.getYear(), monthYear.getMonth(), monthYear.getDayOfMonth());
        while(currentMonth.getMonth() == monthYear.getMonth()) {
            if (daysOfWeek == null) {
                days.add(LocalDate.of(currentMonth.getYear(), currentMonth.getMonth(), currentMonth.getDayOfMonth()));
                continue;
            }

            for (DayOfWeek dw : daysOfWeek) {
                if (dw == currentMonth.getDayOfWeek()) {
                    days.add(LocalDate.of(
                            currentMonth.getYear(),
                            currentMonth.getMonth(),
                            currentMonth.getDayOfMonth()));
                }
            }
            currentMonth = currentMonth.plusDays(1);
        }

        return days;
    }
}
