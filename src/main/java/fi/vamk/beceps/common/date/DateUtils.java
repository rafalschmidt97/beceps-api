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
    val calendar = Calendar.getInstance();
    calendar.setTime(new Date());
    return LocalDate.now().get(ChronoField.DAY_OF_WEEK);
  }
}
