package vagnerlg.com.github.medicalservices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.model.Worker;
import vagnerlg.com.github.medicalservices.repository.WorkerRepository;

import java.util.Collections;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WorkerControllerTest {


    @Value(value = "${local.server.port}")
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private WorkerRepository repository;

    @Test
    public void shouldListReturnWorkers() {
        when(repository.findAll()).thenReturn(
                Collections.singletonList(new Worker(null, "Vagner", "Advogado", null))
        );

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/worker", String.class))
                .contains("[{\"id\":null,\"name\":\"Vagner\",\"occupation\":\"Advogado\",\"companies\":null}]");
    }

    @Test
    public void shouldFindAndReturnNotFoundId() {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenThrow(new NotFoundException("worker", id));

        assertThat(restTemplate.getForObject("http://localhost:" + port + "/worker/" + id, String.class))
                .contains("{\"errors\":\"worker " + id + " not found.\"}");
    }
}
