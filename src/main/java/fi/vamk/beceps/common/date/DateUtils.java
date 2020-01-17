package fi.vamk.beceps.common.date;

import java.time.LocalDate;
import java.time.temporal.ChronoField;
import java.util.Calendar;
import java.util.Date;
import lombok.experimental.UtilityClass;
import lombok.val;

@UtilityClass
public class DateUtils {
  public static Date getTodayMidnight() {
    val calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 0);
    calendar.set(Calendar.MINUTE, 0);
    calendar.set(Calendar.SECOND, 0);
    calendar.set(Calendar.MILLISECOND, 0);
    return calendar.getTime();
  }

  public static int getWeekDay() {
    return LocalDate.now().get(ChronoField.DAY_OF_WEEK);
  }

  public static Date getWeekAgo() {
    return DateUtils.getDateWithDayAddition(new Date(), -7);
  }

  public static Date getWeekLater(Date date) {
    return DateUtils.getDateWithDayAddition(date, 7);
  }

  private static Date getDateWithDayAddition(Date date, int amount) {
    val calendar = Calendar.getInstance();
    calendar.setTime(date);
    calendar.add(Calendar.DATE, amount);
    return calendar.getTime();
  }
}
