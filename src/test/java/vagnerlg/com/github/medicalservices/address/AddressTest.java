package vagnerlg.com.github.medicalservices.address;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import vagnerlg.com.github.medicalservices.schedule.montage.Time;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void shouldInvalidInstance() {
        Address address = new Address();

        Set<ConstraintViolation<Address>> constraintViolations = validator.validate(address);
        List<String> strings = constraintViolations
                .stream()
                .map((item) -> item.getPropertyPath().toString() + " " + item.getMessage())
                .collect(Collectors.toList());

        for (String erro: strings) {
            System.out.println(erro);
        }
    }
}
