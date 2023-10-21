package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class DayTest {

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

        Assertions.assertEquals(0, validator.validate(day).size());
    }

    @Test
    public void shouldInvalidInstance() {
        Day day = new Day();
        day.setTimeGroups(List.of());

        Assertions.assertEquals(2, validator.validate(day).size());
    }

    @Test
    public void shouldScheduleNullDateTime() {
        Time time = new Time();
        time.setHour(0);
        time.setMinute(0);

        TimeGroup timeGroup = new TimeGroup();
        timeGroup.setStart(time);
        timeGroup.setEnd(time);

        Day day = new Day();
        day.setInterval(30);
        day.setTimeGroups(List.of(timeGroup));

        Set<LocalDateTime> result = day.schedule(null);

        Assertions.assertEquals(0, result.size());
    }

    @Test
    public void shouldScheduleTwoDateTime() {
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

        Set<LocalDateTime> result = day.schedule(Set.of(LocalDate.now().plusDays(1L), LocalDate.now().plusDays(2L)));

        Assertions.assertEquals(12, result.size());
    }
}