package vagnerlg.com.github.medicalservices.address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @BeforeEach
    void before(){
        Locale.setDefault(new Locale ("pt", "BR"));
    }

    @Test
    void whenCreateAndressWithEmptyDataAndReturnErrors() {
        var message = "não deve ser nulo";
        Map<String, String> expected = Map.of(
            "number", message,
            "street", message,
            "district", message,
            "postalCode", message,
            "municipal", message,
            "state", message
        );

        Map<String, String> result = this.restTemplate.postForObject("/address/{id}", new Address(), Map.class, UUID.randomUUID());

        assertThat(result).isEqualTo(expected);
    }
}