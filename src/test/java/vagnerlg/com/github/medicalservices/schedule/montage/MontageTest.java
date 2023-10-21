package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vagnerlg.com.github.medicalservices.schedule.montage.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class MontageTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldInvalidInstance() {
        Montage montage = new Montage();

        Assertions.assertEquals(3, validator.validate(montage).size());
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

        Montage montage = new Montage();
        montage.setMonths(Set.of(month));
        montage.setWorkerId(UUID.randomUUID());
        montage.setCompanyId(UUID.randomUUID());

        Assertions.assertEquals(0, validator.validate(montage).size());
    }

    @Test
    public void testShouldCreateListDay() {
        Time start = new Time();
        start.setHour(10);
        start.setMinute(0);

        Time end = new Time();
        end.setHour(12);
        end.setMinute(0);

        TimeGroup timeGroup = new TimeGroup();
        timeGroup.setStart(start);
        timeGroup.setEnd(end);

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

        Montage montage = new Montage();
        montage.setMonths(Set.of(month));
        montage.setWorkerId(UUID.randomUUID());
        montage.setCompanyId(UUID.randomUUID());

        List<LocalDateTime> schedule = montage.schedule();

        Assertions.assertFalse(schedule.isEmpty());
    }
}