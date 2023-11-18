package vagnerlg.com.github.medicalservices.address;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.company.CompanyRepository;

import java.util.Locale;
import java.util.Map;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AddressControllerTest {
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CompanyRepository companyRepository;

    @BeforeEach
    void before(){
        Locale.setDefault(new Locale ("pt", "BR"));
    }

    @Test
    void testWhenCreateAddressWithEmptyDataAndReturnErrors() {
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

    @Test
    void whenCreateAddressAndReturnSuccess() {
        Company company = new Company();
        company.setName("Company");

        Company saveCompany = companyRepository.saveAndFlush(company);

        Address address = new Address();
        address.setStreet("Av Paulista");
        address.setPostalCode("000000000");
        address.setNumber("123");
        address.setDistrict("Vila Mariana");
        address.setState("SP");
        address.setMunicipal("São Paulo");

        Address response = restTemplate.postForObject("/address/{id}", address, Address.class, saveCompany.getId());

        assertThat(response.getId()).isNotNull();
    }
}