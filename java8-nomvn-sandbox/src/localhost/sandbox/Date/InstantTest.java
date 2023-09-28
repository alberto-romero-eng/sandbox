package localhost.sandbox.Date;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class InstantTest {

	/**
	 * 
	 * Instant is similar to Date in nature.
	 * 
	 * Date measures milliseconds from unix epoch, using type "long".
	 * 
	 * Instant measures nanoseconds from unix epoch, using "long" for seconds and "int" 
	 * for fractional seconds part, down to nanoseconds precision.
	 * 
	 */
	public static void instantTest() {
		
		System.out.println("Hello from instantTest()! \n");
		
		
		// Date to Instant
		Date currentDate = new Date();
		Instant currentInstant = currentDate.toInstant();

		
		// time unit support
		// (See ChronoUnitSupportTest)
		
		
		// instant plus/minus tima unit
		// (throws exception if time unit is not supported)
		Instant currentMinusTwoDaysInstant = currentInstant.minus(2, ChronoUnit.DAYS);
		Instant currentPlusTwoHoursInstant = currentInstant.plus(2, ChronoUnit.HOURS);
		System.out.println( ""
				+ "currentInstant: " + currentInstant + "\n"
				+ "currentMinusTwoDaysInstant: " + currentMinusTwoDaysInstant + "\n"
				+ "currentPlusTwoHoursInstant: " + currentPlusTwoHoursInstant + "\n"
				);
		
		
		// Instant to Date
		// (See SimpleDateFormat for human-readable string format)
		Date currentMinusTwoDaysDate = Date.from(currentMinusTwoDaysInstant);
		
		
		// Instant to ZonedDateTime
		// (See DateTimeFormatter for human-readable string format)
		ZonedDateTime zonedDateTimeUTC = currentInstant.atZone(ZoneId.of("UTC"));
		ZonedDateTime zonedDateTimeCcs = currentInstant.atZone(ZoneId.of("America/Caracas"));
		ZonedDateTime zonedDateTimeMad = currentInstant.atZone(ZoneId.of("Europe/Madrid"));
		System.out.println(""
				+ "zonedDateTimeUTC: " + zonedDateTimeUTC + "\n"
				+ "zonedDateTimeCcs: " + zonedDateTimeCcs + "\n"
				+ "zonedDateTimeMad: " + zonedDateTimeMad + "\n"
				);
		
	}

}