package br.com.keyrus.samples.java8.date;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.OffsetDateTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Examples with Java 8
 *
 */
public class RunDatesWithJava8 {

    public static void main(String[] args) {

        caption("Java 8 !");
        caption("LocalDate");
        LocalDate hoje = LocalDate.now();
        System.out.println(hoje); // 2018-04-12(formato ISO-8601 yyyy-MM-dd)
        LocalDate natal = LocalDate.of(2018, Month.DECEMBER, 25);
        System.out.println(natal); // 2018-12-25
        System.out.println(natal.getDayOfWeek().toString()); // THURSDAY (imprime por extenso)
        System.out.println(natal.plusDays(7)); // 2019-01-01

        caption("Time");
        LocalTime time = LocalTime.now();
        System.out.println(time); // 12:01:45.382
        System.out.println(time.getHour()); // 12
        System.out.println(time.getSecond()); // 45
        System.out.println(time.plusHours(1)); // 13:01:45.382

        caption("DateTime");
        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println(dateTime); // 2018-04-12T08:10:06.687

        caption("Timezone");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(ZoneId.of("America/Sao_Paulo"));
        System.out.println(zonedDateTime); // 2018-04-12T08:10:55.136-03:00[America/Sao_Paulo]
        OffsetDateTime offsetDateTime = OffsetDateTime.now(ZoneId.of("-03:00"));
        System.out.println(offsetDateTime); // 2018-04-12T08:10:55.137-03:00

        caption("Instant");
        Instant instant = Instant.now();
        System.out.println(instant);
        // 2018-04-12T11:37:16.209Z
        System.out.println(instant.plusMillis(100)); // 2018-04-12T11:37:16.309Z
        System.out.println(instant.plusNanos(1));
        // 2018-04-12T11:37:16.209000001Z

        caption("Duration");
        LocalTime inicioApresentacao = LocalTime.of(11, 00);
        LocalTime fimApresentacao = LocalTime.of(12, 30);
        Duration duration = Duration.between(inicioApresentacao, fimApresentacao);
        System.out.println(duration); // PT1H30M
        System.out.println(duration.toMillis()); // 5400000
        System.out.println(duration.toNanos()); // 5400000000000

        caption("Period");
        LocalDate hojePeriod = LocalDate.now();
        LocalDate natalPeriod = LocalDate.of(2020, Month.DECEMBER, 25);
        Period period = Period.between(hojePeriod, natalPeriod);
        System.out.println(period); // P2Y8M13D

        caption("Formatando (adeus SimpleDateFormat)");
        LocalDateTime dateTimeF = LocalDateTime.now();
        System.out.println(dateTimeF); // 2018-04-12T08:19:07.531
        System.out.println(dateTimeF.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm")));
        // 12/04/18 08:19

        caption("Integração com legado");
        Date date = Date.from(Instant.now());
        Calendar calendar = new GregorianCalendar();
        Instant instantL = calendar.toInstant();
        System.out.println(date);
        System.out.println(instantL);
    }

    private static void caption(String message) {
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
        System.out.println(message);
        System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
    }
}
