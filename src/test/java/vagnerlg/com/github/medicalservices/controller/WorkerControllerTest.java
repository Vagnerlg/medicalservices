package vagnerlg.com.github.medicalservices.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

//@WebMvcTest(WorkerController.class)
public class WorkerControllerTest {

    @Autowired
    private MockMvc mockMvc;

//    @Autowired
//    private WorkerController workerController;
//
//    @MockBean
//    private WorkerRepository repository;

//    @Test
//    public void shouldListReturnWorkers() throws Exception {
//        Worker worker = new Worker();
//        worker.setName("Vagner");
//        worker.setOccupation("Advogado");
//
//        when(repository.findAll()).thenReturn(
//                Collections.singletonList(worker)
//        );
//
//        mockMvc.perform(get("/worker"))
//                .andDo(print())
//                .andExpect(status().isOk())
//                .andExpect(content().string("[{\"id\":null,\"name\":\"Vagner\",\"occupation\":\"Advogado\",\"companies\":null,\"schedules\":null,\"file\":null,\"createdAt\":null,\"updatedAt\":null}]"));
//    }

//    @Test
//    public void shouldFindAndReturnNotFoundId() throws Exception {
//        UUID id = UUID.randomUUID();
//        when(repository.findById(id)).thenThrow(new NotFoundException("worker", id));
//
//        mockMvc.perform(get("/worker/" + id))
//                .andDo(print())
//                .andExpect(status().isNotFound())
//                .andExpect(content().string("{\"errors\":\"worker " + id + " not found.\"}"));
//    }
}
