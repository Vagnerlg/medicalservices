package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

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

    public Set<LocalDateTime> schedule() {
        Set<LocalDate> allDays = week.schedule(monthYear());
        excludeDays(allDays);
        includeDays(allDays);

        return day.schedule(allDays);
    }

    private void includeDays(Set<LocalDate> allDays) {
        Set<LocalDate> includeDays = new HashSet<>();
        if (include != null) {
            for(int number: include) {
                Optional<LocalDate> optionalLocalDate = allDays.stream().findFirst();
                if (optionalLocalDate.isEmpty()) {
                    continue;
                }
                includeDays.add(LocalDate.of(
                        optionalLocalDate.get().getYear(),
                        optionalLocalDate.get().getMonth(), number));
            }
        }
        allDays.addAll(includeDays);
    }

    private void excludeDays(Set<LocalDate> allDays) {
        Set<LocalDate> excludeDays = new HashSet<>();
        for (int number : exclude) {
            for (LocalDate allDay : allDays) {
                if (allDay.getDayOfMonth() == number) {
                    excludeDays.add(allDay);
                }
            }
        }


        var now = LocalDate.now();
        for (LocalDate allDay : allDays) {
            if (now.isAfter(allDay)) {
                excludeDays.add(allDay);
            }
        }

        allDays.removeAll(excludeDays);
    }

    private LocalDate monthYear() {
        var currentDate = LocalDate.now().withDayOfMonth(1);
        var currentYear = LocalDate.of(currentDate.getYear(), month.getValue(), 1);
        if (currentYear.isAfter(currentDate) || currentYear.isEqual(currentDate)) {
            return currentYear;
        }

        return LocalDate.of(currentDate.getYear() + 1, month.getValue(), 1);
    }
}
