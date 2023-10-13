package vagnerlg.com.github.medicalservices.schedule.domain;

import org.junit.jupiter.api.Test;
import vagnerlg.com.github.medicalservices.schedule.montage.*;

import java.time.DayOfWeek;
import java.util.List;
import java.util.Set;
import java.util.UUID;

class MontageTest {

    @Test
    public void testShouldCreateListDay() {
        Week week = new Week(Set.of(DayOfWeek.FRIDAY));
        Day day = new Day(
                List.of(new TimeGroup(new Time(8,0),new Time(9,0))),
                30
        );
        Montage montage = new Montage(
                Set.of(
                        new Month(
                                java.time.Month.SEPTEMBER,
                                week,
                                day,
                                null,
                                null
                        )
//                        ,
//                        new Month(
//                                java.time.Month.DECEMBER,
//                                week,
//                                day,
//                                Set.of(3),
//                                Set.of(1)
//                        )
                ),
                UUID.randomUUID(),
                UUID.randomUUID()
        );
        System.out.println(montage.schedule());
    }
}