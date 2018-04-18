package br.com.keyrus.java8.samples;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.junit.Before;
import org.junit.Test;

import br.com.keyrus.samples.java8.date.BusinessHourCalculator;
import br.com.keyrus.samples.java8.date.BusinessHourCalculator.DayOfWeekLocal;

/*
 * Java 8 version
 * https://github.com/adrianoreis/misc
 */
public class BusinessHourCalculatorTest {

    private BusinessHourCalculator  businessHourCalculator;
    private final DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

    @Before
    public void classSetup() throws ParseException {
        businessHourCalculator = new BusinessHourCalculator("09:00", "15:00");
        businessHourCalculator.setOpeningHours(DayOfWeekLocal.FRIDAY, "10:00", "17:00");
        businessHourCalculator.setClosed("2010-12-25");
    }

    @Test
    public void testDeliverySameDay() throws ParseException {
        LocalDateTime expectedVal = LocalDateTime.parse("07/06/2010 11:10", sdf);
        LocalDateTime retVal = businessHourCalculator.calculateDeadline(2 * 60 * 60, "2010-06-07 09:10");
        assertEquals(expectedVal, retVal);
    }

    @Test
    public void testDeliverySameDayDropOffAfterClosing() throws ParseException {
        LocalDateTime expectedVal = LocalDateTime.parse("08/06/2010 11:00", sdf);
        LocalDateTime retVal = businessHourCalculator.calculateDeadline(2 * 60 * 60, "2010-06-07 18:10");
        assertEquals(expectedVal, retVal);
    }

    @Test
    public void testDeliverySameDayDropOffBeforeOpening() throws ParseException {
        LocalDateTime expectedVal = LocalDateTime.parse("07/06/2010 11:00", sdf);
        LocalDateTime retVal = businessHourCalculator.calculateDeadline(2 * 60 * 60, "2010-06-07 08:40");
        assertEquals(expectedVal, retVal);
    }

    @Test
    public void testDeliveryNextDay() throws ParseException {
        businessHourCalculator.setClosed(DayOfWeekLocal.SUNDAY, DayOfWeekLocal.WEDNESDAY);
        LocalDateTime expectedVal = LocalDateTime.parse("10/06/2010 09:03", sdf);
        LocalDateTime retVal = businessHourCalculator.calculateDeadline(15 * 60, "2010-06-08 14:48");
        assertEquals(expectedVal, retVal);
    }

    @Test
    public void testLongerDeliveryTimeBecauseOfHoliday() throws ParseException {
        businessHourCalculator.setOpeningHours("2010-12-24", "8:00", "13:00");
        businessHourCalculator.setClosed(DayOfWeekLocal.SUNDAY, DayOfWeekLocal.WEDNESDAY, DayOfWeekLocal.MONDAY);
        businessHourCalculator.setClosed("2010-12-25");
        LocalDateTime expectedVal = LocalDateTime.parse("28/12/2010 11:00", sdf);
        LocalDateTime retVal = businessHourCalculator.calculateDeadline(7 * 60 * 60, "2010-12-24 6:45");
        assertEquals(expectedVal, retVal);
    }

}
