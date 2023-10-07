package vagnerlg.com.github.medicalservices.schedule.application.http.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import vagnerlg.com.github.medicalservices.schedule.domain.Montage;
import vagnerlg.com.github.medicalservices.schedule.domain.entity.Schedule;
import vagnerlg.com.github.medicalservices.schedule.domain.service.ScheduleService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {

    @Autowired
    private ScheduleService scheduleService;

    @PostMapping("/montage")
    public List<Schedule> montage(@RequestBody @Valid Montage montage) {
        return scheduleService.createByMontage(montage);
    }
}
