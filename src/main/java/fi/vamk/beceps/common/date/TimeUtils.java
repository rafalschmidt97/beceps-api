package fi.vamk.beceps.common.date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import lombok.experimental.UtilityClass;

@UtilityClass
public class TimeUtils {
  public static Instant getTodayMidnight() {
    return ZonedDateTime.now()
      .withHour(0)
      .withMinute(0)
      .withSecond(0)
      .withNano(0)
      .toInstant();
  }

  public static int getWeekDay() {
    return Instant.now().atZone(ZoneId.systemDefault()).getDayOfWeek().getValue();
  }

  public static Instant getWeekAgo() {
    return TimeUtils.getDateWithDayAddition(Instant.now(), -7);
  }

  public static Instant getWeekLater(Instant date) {
    return TimeUtils.getDateWithDayAddition(date, 7);
  }

  private static Instant getDateWithDayAddition(Instant date, int amount) {
    return ZonedDateTime.from(date).plusDays(amount).toInstant();
  }
}
