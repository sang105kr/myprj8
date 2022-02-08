package com.kh.myrpj.test;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.time.chrono.ChronoLocalDate;
import java.time.chrono.Chronology;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
public class StreamTest {
    @Test
    void test4(){
        Set<String> zoneIds = ZoneId.getAvailableZoneIds();
        for(String zoneId: zoneIds){
            log.info("zoneId={}",zoneId);
        }
        LocalDate now = LocalDate.now();
        ZonedDateTime zonedDateTime = now.atStartOfDay(ZoneId.of("Europe/Rome"));
        log.info("localDateTime={}", zonedDateTime);

        ZonedDateTime zonedDateTime1 = now.atTime(LocalTime.now()).atZone(ZoneId.of("Asia/Seoul"));
        log.info("zonedDateTime1={}", zonedDateTime1);

        ChronoLocalDate chronoLocalDate = Chronology.ofLocale(Locale.KOREA).dateNow();
        log.info(chronoLocalDate.format(DateTimeFormatter.ofPattern("yyyy-mm-dd")));
    }
    @Test
    void test3(){
        LocalDate localDate = LocalDate.now();
        LocalDate localDate2 = localDate.with(TemporalAdjusters.next(DayOfWeek.SATURDAY));
        log.info("다음주토요일={}",localDate2);
        localDate2 = LocalDate.now().with(TemporalAdjusters.nextOrSame(DayOfWeek.SATURDAY));
        log.info("다음주토요일={}",localDate2);
        localDate2 = localDate.with(TemporalAdjusters.dayOfWeekInMonth(2, DayOfWeek.SATURDAY));
        log.info("dd={}", localDate2);
        localDate2 = localDate.with(TemporalAdjusters.lastDayOfMonth());
        log.info("lastDayofMohth={}", localDate2);
        localDate2 = localDate.with(TemporalAdjusters.lastInMonth(DayOfWeek.SATURDAY));
        log.info("lastInMonth={}", localDate2);
        localDate2 = localDate.with(TemporalAdjusters.firstDayOfNextMonth()).with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.SATURDAY));
        log.info("lastInMonth={}", localDate2);
        localDate2 = localDate.plus(Period.ofMonths(1)).with(TemporalAdjusters.dayOfWeekInMonth(2,DayOfWeek.SATURDAY));
        log.info("lastInMonth={}", localDate2);
    }
    @Test
    void test2(){
        String name = "홍길동";
        String name2 = null;
        Optional<String> op = Optional.ofNullable(name);
        Optional<String> op2 = Optional.of(null);
        Stream<String> stream = op.stream();

        System.out.println("stream.count() = " + stream.count());
    }

    @Test
    @Disabled
    void test1() {
        ArrayList<Person> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new Person());
        }
        Stream<Person> stream = list.stream();
        stream.map(Person::getCar)
                .map(optCar -> optCar.flatMap(Car::getInsurance))
                .map(optIns -> optIns.map(Insurance::getName))
                .flatMap(Optional::stream)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toSet());

    }


    static class Person {
        Optional<Car> car;

        Optional<Car> getCar() {
            return car;
        }
    }

    static class Car {
        Optional<Insurance> insurance;

        Optional<Insurance> getInsurance() {
            return insurance;
        }
    }

    static class Insurance {
        Optional<String> name;

        Optional<String> getName() {
            return name;
        }
    }
}


