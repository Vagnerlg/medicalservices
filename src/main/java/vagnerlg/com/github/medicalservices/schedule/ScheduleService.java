package vagnerlg.com.github.medicalservices.schedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vagnerlg.com.github.medicalservices.address.Address;
import vagnerlg.com.github.medicalservices.address.AddressRepository;
import vagnerlg.com.github.medicalservices.presentation.http.response.exception.NotFoundException;
import vagnerlg.com.github.medicalservices.company.Company;
import vagnerlg.com.github.medicalservices.worker.Worker;
import vagnerlg.com.github.medicalservices.company.CompanyRepository;
import vagnerlg.com.github.medicalservices.worker.WorkerRepository;
import vagnerlg.com.github.medicalservices.schedule.montage.Montage;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ScheduleService {

    @Autowired
    private WorkerRepository workerRepository;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private ScheduleRepository scheduleRepository;

    public List<Schedule> createByMontage(Montage montage) {
        Worker worker = workerRepository.findById(montage.getWorkerId())
                .orElseThrow(() -> new NotFoundException("worker_id", montage.getWorkerId()));

        Company company = companyRepository.findById(montage.getCompanyId())
                .orElseThrow(() -> new NotFoundException("company_id", montage.getCompanyId()));

        Address address = addressRepository.findById(montage.getAddressId())
                .orElseThrow(() -> new NotFoundException("address_id", montage.getAddressId()));

        List<Schedule> schedules = new ArrayList<>();
        for (LocalDateTime date : montage.schedule()) {
            schedules.add(new Schedule(
                    null,
                    date,
                    date.plusMinutes(30),
                    worker,
                    company,
                    address,
                    null
            ));
        }

        return scheduleRepository.saveAll(schedules);
    }
}
