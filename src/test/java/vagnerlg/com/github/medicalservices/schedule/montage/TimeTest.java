package vagnerlg.com.github.medicalservices.schedule.montage;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

class TimeTest {

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

        Assertions.assertEquals(0, validator.validate(time).size());
    }

    @Test
    public void shouldMinValueErrorsInstance() {
        Time time = new Time();
        time.setHour(-1);
        time.setMinute(-1);

        Set<ConstraintViolation<Time>> constraintViolations = validator.validate(time);
        List<ConstraintViolation<Time>> result = constraintViolations.stream().toList();

        Assertions.assertEquals(2, constraintViolations.size());
    }

    @Test
    public void shouldMaxValueErrorsInstance() {
        Time time = new Time();
        time.setHour(24);
        time.setMinute(61);

        Set<ConstraintViolation<Time>> constraintViolations = validator.validate(time);
        List<ConstraintViolation<Time>> result = constraintViolations.stream().toList();

        Assertions.assertEquals(2, constraintViolations.size());
    }
}