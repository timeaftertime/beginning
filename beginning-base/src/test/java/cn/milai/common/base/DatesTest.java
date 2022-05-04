package cn.milai.common.base;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Calendar;

import org.junit.Test;

/**
 * {@link Dates} 测试类
 * @author milai
 * @date 2022.03.27
 */
public class DatesTest {

	@Test
	public void testFormat() {
		Calendar calendar = Calendar.getInstance();
		calendar.set(2022, 3 - 1, 27, 11, 37, 25);
		assertEquals("2022-03-27 11:37:25", Dates.format(calendar.getTime()));
		assertEquals("2022:3-27+25 37 11", Dates.format(calendar.getTime(), "yyyy:M-dd+ss mm HH"));
		calendar.set(2022, 11 - 1, 3, 11, 37, 25);
		assertEquals("2022:11-03+25 37 11", Dates.format(calendar.getTime(), "yyyy:M-dd+ss mm HH"));

		assertNull(Dates.from(null));

		LocalDateTime dateTime = Dates.from("2022-01-21 10:10:10");
		assertEquals(2022, dateTime.getYear());
		assertEquals(Month.JANUARY, dateTime.getMonth());
		assertEquals(21, dateTime.getDayOfMonth());
		assertEquals(DayOfWeek.FRIDAY, dateTime.getDayOfWeek());
		assertEquals(10, dateTime.getHour());
		assertEquals(10, dateTime.getMinute());
		assertEquals(10, dateTime.getSecond());
	}
}
