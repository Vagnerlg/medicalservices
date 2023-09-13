package vagnerlg.com.github.medicalservices.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import vagnerlg.com.github.medicalservices.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.model.Worker;
import vagnerlg.com.github.medicalservices.repository.WorkerRepository;

import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(WorkerController.class)
public class WorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WorkerController workerController;

    @MockBean
    private WorkerRepository repository;

    @Test
    public void shouldListReturnWorkers() throws Exception {
        when(repository.findAll()).thenReturn(
                Collections.singletonList(new Worker(null, "Vagner", "Advogado", null))
        );

        mockMvc.perform(get("/worker"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().string("[{\"id\":null,\"name\":\"Vagner\",\"occupation\":\"Advogado\",\"companies\":null}]"));
    }

    @Test
    public void shouldFindAndReturnNotFoundId() throws Exception {
        UUID id = UUID.randomUUID();
        when(repository.findById(id)).thenThrow(new NotFoundException("worker", id));

        mockMvc.perform(get("/worker/" + id))
                .andDo(print())
                .andExpect(status().isNotFound())
                .andExpect(content().string("{\"errors\":\"worker " + id + " not found.\"}"));
    }
}
