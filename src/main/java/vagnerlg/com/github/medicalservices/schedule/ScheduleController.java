package vagnerlg.com.github.medicalservices.schedule;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import vagnerlg.com.github.medicalservices.schedule.montage.Montage;

import java.util.List;

@Tag(name = "Schedule", description = "Entidade referente a agendamento e marcações de data/horários")
@RestController
@RequestMapping("/schedule")
class ScheduleController {
    private ScheduleService scheduleService;

    @Autowired
    ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @PostMapping("/montage")
    List<Schedule> montage(@RequestBody @Valid Montage montage) {
        return scheduleService.createByMontage(montage);
    }
}
