package vagnerlg.com.github.medicalservices.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.address.AddressService;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.schedule.montage.Montage;
import vagnerlg.com.github.medicalservices.worker.Worker;
import vagnerlg.com.github.medicalservices.worker.WorkerService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private WorkerService workerService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> createByMontage(Montage montage) {
        Worker worker = workerService.findOne(montage.getWorkerId())
                .orElseThrow(() -> new NotFoundException("worker_id", montage.getWorkerId()));

        Address address = addressService.findOne(montage.getCompanyId(), montage.getAddressId())
                .orElseThrow(() -> new NotFoundException("address_id", montage.getAddressId()));

        List<Schedule> schedules = new ArrayList<>();
        for (LocalDateTime date : montage.schedule()) {
            schedules.add(new Schedule(
                    null,
                    date,
                    date.plusMinutes(30),
                    worker,
                    address.getCompany(),
                    address,
                    null
            ));
        }

        return scheduleRepository.saveAll(schedules);
    }
}
