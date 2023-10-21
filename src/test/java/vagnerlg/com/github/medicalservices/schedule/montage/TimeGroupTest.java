package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeGroupTest {
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

        Assertions.assertEquals(0, validator.validate(timeGroup).size());
    }

    @Test
    public void shouldInvalidInstance() {
        Time time = new Time();
        time.setHour(-1);
        time.setMinute(-1);

        TimeGroup timeGroup = new TimeGroup();
        timeGroup.setStart(time);
        timeGroup.setEnd(time);

        Assertions.assertEquals(4, validator.validate(timeGroup).size());
    }

}