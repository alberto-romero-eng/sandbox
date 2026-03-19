package localhost.sandbox.jse8.A0Helper;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class TimeUnixFormatHelper {

	private static final String DEFAULT_PATTERN_ALT1 = "yyyy-MM-dd'T'HH:mm:ssXXX";

	private static final String DEFAULT_PATTERN_ALT2 = "yyyy-MM-dd'T'HH:mm:ss.SSSXXX";

	private static final String DEFAULT_PATTERN = DEFAULT_PATTERN_ALT1;

	private static final String DEFAULT_TIMEZONE = "UTC";

	private static final SimpleDateFormat SDF;

	static {
		SDF = new SimpleDateFormat(DEFAULT_PATTERN, Locale.US);
		SDF.setTimeZone(TimeZone.getTimeZone(DEFAULT_TIMEZONE));
	}

	public static void main () {
		System.out.println("Hello from TimeUnixToFormatHelper!");

		long unixTimeSecsInit = 0;
		String formattedC;
		long unixTimeSecsC = 0;

		// test 01
		unixTimeSecsInit = System.currentTimeMillis()/1000L;
		formattedC = TUFH.toFormat(unixTimeSecsInit);
		unixTimeSecsC = TUFH.toUnix(formattedC);
		System.out.println("test01:");
		System.out.println("unixTimeSecsInit: " + unixTimeSecsInit);
		System.out.println("formattedC: " + formattedC);
		System.out.println("unixTimeSecsC: " + unixTimeSecsC);

		// test 02
		unixTimeSecsInit = (System.currentTimeMillis()/1000L) - (3600L * 12L); // 12 hours prior
		formattedC = TUFH.toFormat(unixTimeSecsInit);
		unixTimeSecsC = TUFH.toUnix(formattedC);
		System.out.println("test02:");
		System.out.println("unixTimeSecsInit: " + unixTimeSecsInit);
		System.out.println("formattedC: " + formattedC);
		System.out.println("unixTimeSecsC: " + unixTimeSecsC);
	}

	public static String toFormat(Long unixTimeSecs) {
		if (unixTimeSecs == null) {
			return null;
		}
		long unixTimeMillisecs = Long.valueOf(unixTimeSecs) * 1000L;
		Date date = new Date(unixTimeMillisecs);
		String formatted = SDF.format(date);
		return formatted;
	}

	public static Long toUnix(String formatted) {
		if (formatted == null) {
			return null;
		}
		Date date = null;
		try {
			date = SDF.parse(formatted);
		} catch (Throwable ex) {
			return null;
		}
		long unixTimeSecs = date.getTime() / 1000L;
		return unixTimeSecs;
	}


	public static class TUFH extends TimeUnixFormatHelper {
	}

}
