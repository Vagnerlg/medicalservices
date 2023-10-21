package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

class WeekTest {
    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldInvalidNullInstance() {
        Week week = new Week();

        Assertions.assertEquals(1, validator.validate(week).size());
    }

    @Test
    public void shouldInvalidInstance() {
        Week week = new Week();
        week.setDaysOfWeek(Set.of());

        Assertions.assertEquals(1, validator.validate(week).size());
    }

    @Test
    public void shouldScheduleWeek() {
        Week week = new Week();
        week.setDaysOfWeek(Set.of(DayOfWeek.SUNDAY));
        LocalDate plusMonths = LocalDate.now().plusMonths(1L);

        Set<LocalDate> schedule = week.schedule(plusMonths);

        Assertions.assertFalse(schedule.isEmpty());
    }
}