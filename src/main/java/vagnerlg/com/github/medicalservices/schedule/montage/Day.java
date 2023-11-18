package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Data
public class Day {

    @NotEmpty
    @Valid
    private List<TimeGroup> timeGroups;

    @NotNull
    private Integer interval;

    public Set<LocalDateTime> schedule(Set<LocalDate> days) {
        if (Objects.isNull(days)) {
            days = Set.of();
        }

        Set<LocalDateTime> scheduleMark = new HashSet<>();
        LocalDateTime startTime = null;
        LocalDateTime endTime = null;
        for (LocalDate day: days) {
            for (TimeGroup timeGroup: timeGroups) {
                startTime = LocalDateTime.of(
                        day.getYear(),
                        day.getMonth(),
                        day.getDayOfMonth(),
                        timeGroup.getStart().getHour(),
                        timeGroup.getStart().getMinute());

                endTime = LocalDateTime.of(
                        day.getYear(),
                        day.getMonth(),
                        day.getDayOfMonth(),
                        timeGroup.getEnd().getHour(),
                        timeGroup.getEnd().getMinute());
                while(startTime.isBefore(endTime)) {
                    scheduleMark.add(startTime);
                    startTime = startTime.plusMinutes(interval);
                }
            }
        }

        return scheduleMark;
    }
}
