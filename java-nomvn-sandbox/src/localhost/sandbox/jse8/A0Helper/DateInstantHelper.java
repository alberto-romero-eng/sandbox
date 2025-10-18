package localhost.sandbox.jse8.A0Helper;

import java.time.Duration;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAmount;
import java.time.temporal.TemporalUnit;
import java.util.Date;


/**
 * <p>Wrappers to assist {@link Instant} operations, using {@link Date} parameters:
 * <ul>
 * <li>{@link Instant#plus(long, TemporalUnit)}
 * <li>{@link Instant#minus(long, TemporalUnit)}
 * <li>{@link Instant#isSupported(TemporalUnit)}
 * <li>{@link Instant#isBefore(Instant)}
 * <li>{@link Instant#isAfter(Instant)}
 * </ul>
 * 
 * <p>Important: high {@link ChronoUnit} operations are not supported for {@link Instant} 
 * (see {@link Instant#isSupported(TemporalUnit)}). 
 * <br>For such cases, use {@link ZonedDateTime} or {@link LocalDateTime}.
 * 
 * <p>Using {@link Duration#of(long, TemporalUnit)}, there are also wrappers for:
 * <ul>
 * <li>{@link Instant#plus(TemporalAmount)}
 * <li>{@link Instant#minus(TemporalAmount)}
 * </ul> 
 * 
 * @author Alberto Romero
 * @since 2025-10-18
 * 
 */
public abstract class DateInstantHelper {


	public static void main() {
		System.out.println("Hello from DateInstantHelper!");

		// now
		System.out.println("--> now:");
		Date now = new Date();
		System.out.println("now: " + now);

		// plus, before
		System.out.println("--> plus, before:");
		Date nowPlusOneHour = DIH.plus(now, 1L, ChronoUnit.HOURS);
		boolean is_Now_Before_NowPlusOneHour = DIH.isBaseBeforeOther(now, nowPlusOneHour);
		System.out.println("nowPlusOneHour: " + nowPlusOneHour + ", is_Now_Before_NowPlusOneHour: " + is_Now_Before_NowPlusOneHour);
		Date nowPlusTwoHours = DIH.plus(now, Duration.of(2L, ChronoUnit.HOURS));
		boolean is_Now_Before_NowPlusTwoHours = DIH.isBaseBeforeOther(now, nowPlusTwoHours);
		System.out.println("nowPlusTwoHours: " + nowPlusTwoHours + ", is_Now_Before_NowPlusTwoHours: " + is_Now_Before_NowPlusTwoHours);

		// minus, after
		System.out.println("--> minus, after:");
		Date nowMinusTenMinutes = DIH.minus(now, 10L, ChronoUnit.MINUTES);
		boolean is_Now_After_NowMinusTenMinutes = DIH.isBaseAfterOther(now, nowMinusTenMinutes);
		System.out.println("nowMinusTenMinutes: " + nowMinusTenMinutes + ", is_Now_After_NowMinusTenMinutes: " + is_Now_After_NowMinusTenMinutes);
		Date nowMinusTwentyMinutes = DIH.minus(now, Duration.of(20L, ChronoUnit.MINUTES));
		boolean is_Now_After_NowMinusTwentyMinutes = DIH.isBaseAfterOther(now, nowMinusTwentyMinutes);
		System.out.println("nowMinusTwentyMinutes: " + nowMinusTwentyMinutes + ", is_Now_After_NowMinusTwentyMinutes: " + is_Now_After_NowMinusTwentyMinutes);

		// supported
		System.out.println("--> supported:");
		boolean is_MinutesSupported = DIH.isSupported(ChronoUnit.MINUTES);
		System.out.println("is_MinutesSupported: " + is_MinutesSupported);
		boolean is_YearsSupported = DIH.isSupported(ChronoUnit.YEARS);
		System.out.println("is_YearsSupported: " + is_YearsSupported);
	}


	/**
	 * <p>Wrapper for {@link Instant#plus(long, TemporalUnit)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static Date plus(Date baseDate, long amountToAdd, ChronoUnit chronoUnit) {
		Instant baseInstant = baseDate.toInstant();
		Instant plusInstant = baseInstant.plus(amountToAdd, chronoUnit);
		Date plusDate = Date.from(plusInstant);
		return plusDate;
	}


	/**
	 * <p>Wrapper for {@link Instant#plus(TemporalAmount)}.
	 * 
	 * <p>Use {@link Duration#of(long, TemporalUnit)} for second argument.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static Date plus(Date baseDate, Duration duration) {
		Instant baseInstant = baseDate.toInstant();
		Instant plusInstant = baseInstant.plus(duration);
		Date plusDate = Date.from(plusInstant);
		return plusDate;
	}


	/**
	 * <p>Wrapper for {@link Instant#minus(long, TemporalUnit)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static Date minus(Date baseDate, long amountToSubtract, ChronoUnit chronoUnit) {
		Instant baseInstant = baseDate.toInstant();
		Instant minusInstant = baseInstant.minus(amountToSubtract, chronoUnit);
		Date minusDate = Date.from(minusInstant);
		return minusDate;
	}


	/**
	 * <p>Wrapper for {@link Instant#minus(TemporalAmount)}.
	 * 
	 * <p>Use {@link Duration#of(long, TemporalUnit)} for second argument.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static Date minus(Date baseDate, Duration duration) {
		Instant baseInstant = baseDate.toInstant();
		Instant minusInstant = baseInstant.minus(duration);
		Date minusDate = Date.from(minusInstant);
		return minusDate;
	}


	/**
	 * <p>Wrapper for {@link Instant#isSupported(TemporalUnit)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static boolean isSupported(ChronoUnit chronoUnit) {
		boolean supported = Instant.now().isSupported(chronoUnit);
		return supported;
	}


	/**
	 * <p>Wrapper for {@link Instant#isBefore(Instant)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static boolean isBaseBeforeOther(Date baseDate, Date otherDate) {
		Instant baseInstant = baseDate.toInstant();
		Instant otherInstant = otherDate.toInstant();
		boolean baseBeforeOther = baseInstant.isBefore(otherInstant);
		return baseBeforeOther;
	}


	/**
	 * <p>Wrapper for {@link Instant#isAfter(Instant)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static boolean isBaseAfterOther(Date baseDate, Date otherDate) {
		Instant baseInstant = baseDate.toInstant();
		Instant otherInstant = otherDate.toInstant();
		boolean baseAfterOther = baseInstant.isAfter(otherInstant);
		return baseAfterOther;
	}




	/**
	 * <p>Class for aliasing {@link DateInstantHelper} methods.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public abstract class DIH extends DateInstantHelper {
	}

}
