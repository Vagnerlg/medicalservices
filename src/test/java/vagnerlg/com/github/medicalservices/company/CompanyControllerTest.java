package vagnerlg.com.github.medicalservices.company;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import vagnerlg.com.github.medicalservices.company.request.CompanyRequest;
import vagnerlg.com.github.medicalservices.presentation.http.response.errors.Error;
import vagnerlg.com.github.medicalservices.presentation.http.response.errors.Errors;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CompanyControllerTest {

    private final TestRestTemplate restTemplate;

    @Autowired
    public CompanyControllerTest(TestRestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @BeforeEach
    void before(){
        Locale.setDefault(new Locale ("pt", "BR"));
    }

    @Test
    void testShouldCreateCompanyWhenDataEmpty() {
        ResponseEntity<Errors> response = restTemplate.postForEntity("/company", new CompanyRequest(null, null), Errors.class);
        Errors expected = new Errors(List.of(new Error("name", "não deve ser nulo")));

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals(expected, response.getBody());
    }

    @Test
    void testShouldCreateCompanyWhenRequiredData() {
        String name = "Captain Picard";
        ResponseEntity<Company> response = restTemplate.postForEntity("/company", new CompanyRequest(name, null), Company.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody().getId());
        assertEquals(name, response.getBody().getName());
    }

    @Test
    void testShouldListCompany() {
        String name = "Captain Picard";
        restTemplate.postForEntity("/company", new CompanyRequest(name, null), Company.class);

        ResponseEntity<String> response = restTemplate.getForEntity("/company", String.class);
        String body = response.getBody();

        assertTrue(body.contains(name));
        assertTrue(body.contains("content"));
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
