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

    private static final int INTERVAL_MIN  = 30;

    private final WorkerService workerService;
    private final AddressService addressService;
    private final ScheduleRepository scheduleRepository;

    @Autowired
    ScheduleService(
            WorkerService workerService,
            AddressService addressService,
            ScheduleRepository scheduleRepository) {
        this.workerService = workerService;
        this.addressService = addressService;
        this.scheduleRepository = scheduleRepository;
    }

    public List<Schedule> createByMontage(Montage montage) {
        Worker worker = workerService.findOne(montage.getWorkerId())
                .orElseThrow(() -> new NotFoundException("worker_id", montage.getWorkerId()));

        Address address = addressService.findOne(montage.getCompanyId(), montage.getAddressId())
                .orElseThrow(() -> new NotFoundException("address_id", montage.getAddressId()));

        List<Schedule> schedules = new ArrayList<>();
        for (LocalDateTime date : montage.schedule()) {
            schedules.add(Schedule.builder()
                .start(date)
                .end(date.plusMinutes(INTERVAL_MIN))
                .company(address.getCompany())
                .address(address)
                .worker(worker)
                .build()
            );
        }

        return scheduleRepository.saveAll(schedules);
    }
}
