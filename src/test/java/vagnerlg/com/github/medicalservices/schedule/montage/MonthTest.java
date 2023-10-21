package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

class MonthTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldValidInstance() {
        Time time = new Time();
        time.setHour(0);
        time.setMinute(0);

        TimeGroup timeGroup = new TimeGroup();
        timeGroup.setStart(time);
        timeGroup.setEnd(time);

        Day day = new Day();
        day.setInterval(30);
        day.setTimeGroups(List.of(timeGroup));

        Week week = new Week();
        week.setDaysOfWeek(Set.of(DayOfWeek.SUNDAY));

        Month month = new Month();
        month.setMonth(java.time.Month.APRIL);
        month.setWeek(new Week());
        month.setDay(day);
        month.setWeek(week);

        Assertions.assertEquals(0, validator.validate(month).size());
    }

    @Test
    public void shouldInvalidInstance() {
        Month month = new Month();

        Assertions.assertEquals(3, validator.validate(month).size());
    }

    @Test
    public void shouldScheduleReturnListDateTime() {
        Time start = new Time();
        start.setHour(12);
        start.setMinute(0);

        Time end = new Time();
        end.setHour(15);
        end.setMinute(0);

        TimeGroup timeGroup = new TimeGroup();
        timeGroup.setStart(start);
        timeGroup.setEnd(end);

        Day day = new Day();
        day.setInterval(30);
        day.setTimeGroups(List.of(timeGroup));

        Week week = new Week();
        week.setDaysOfWeek(Set.of(DayOfWeek.SUNDAY, DayOfWeek.FRIDAY));

        Month month = new Month();
        month.setMonth(java.time.Month.APRIL);
        month.setDay(day);
        month.setWeek(week);
        month.setInclude(Set.of(1));
        month.setExclude(Set.of(2));

        Set<LocalDateTime> schedule = month.schedule();

        Assertions.assertFalse(schedule.isEmpty());
    }
}