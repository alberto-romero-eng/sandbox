package localhost.sandbox.jse8.A0Helper;

import java.util.concurrent.TimeUnit;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;

/**
 * <p> Sleep helping methods.
 * 
 * <p> Updates:
 * <ul> 
 * <li>2024-10-03: (non-specified)
 * <li>2025-10-18: add/modify methods to implement {@link TimeUnit} use.
 * </ul>
 * 
 * @author Alberto Romero
 * @since 2024-09-26
 * 
 */
public abstract class SleepHelper {

	// private static Logger log = LoggerFactory.getLogger(SleepHelper.class);
	private static LogHelper log = new LogHelper();


	public static void main () {
		System.out.println("Hello from SleepHelper!");
		SleepHelper.sleepPrintInterval(11);
		SleepHelper.sleepPrintInterval(10);
		SleepHelper.sleepPrintInterval(4);
	}


	/**
	 * <p>Non-synchronized sleep.
	 * 
	 * <p>Prefer implementation(s) using parameter {@link TimeUnit}.
	 * 
	 * @author Alberto Romero
	 * @since 2023-10-21
	 */
	/* @Deprecated
	public static void sleep(long timeMillisecs) {
		try {
			Thread.sleep(timeMillisecs);
		} catch (Exception e) {
			log.error("non-synchronized sleep error -- ", e);
		}
	} */


	/**
	 * <p>Implementation allowing {@link TimeUnit} use.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public static void sleep(long amount, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(amount);
		} catch (Exception e) {
			log.error("non-synchronized sleep error -- ", e);
		}
	}


	/**
	 * <p> Synchronized sleep.
	 * 
	 * <p>Updates:
	 * <ul>
	 * <li>2024-03-16: does this synchronized method really makes sense?
	 * <li>2025-10-18: changed to implement parameter {@link TimeUnit}
	 * </ul>
	 * 
	 * @author Alberto Romero
	 * @since 2023-10-21
	 */
	public static synchronized void synchronizedSleep(long amount, TimeUnit timeUnit) {
		try {
			timeUnit.sleep(amount);
		} catch (Exception e) {
			log.error("synchronized sleep error -- ", e);
		}
	}


	/**
	 * <p> Sleep, non synchronized, printing intervals.
	 * 
	 * <p> Use of ExecTimeSecsHelper along with this method, may be useful.  
	 * Mentioned helper has been left outside of this one for flexibility and 
	 * implementation easing.
	 * 
	 * <p> Updates:
	 * <ul> 
	 * <li>2024-10-03: (non-specified)
	 * <li>2025-10-18: change parameter to seconds, change implementation to use {@link TimeUnit}.
	 * </ul>
	 * 
	 * @author Alberto Romero
	 * @since 2024-09-26
	 */
	public static void sleepPrintInterval(long amountSecs) {
		final long intervalSecs = 5L;
		long remainingSecs = amountSecs;
		while (remainingSecs > intervalSecs) {
			log.info("sleeping {} secs -- initialSecs: {}, remainingSecs: {}", intervalSecs, amountSecs, remainingSecs);
			SleepHelper.sleep(intervalSecs, TimeUnit.SECONDS);
			remainingSecs = remainingSecs - intervalSecs;
		}
		if (remainingSecs <= intervalSecs && remainingSecs > 0) {
			log.info("sleeping {} secs (soon to end) -- initialSecs: {}, remainingSecs: {}", remainingSecs, amountSecs, remainingSecs);
			SleepHelper.sleep(remainingSecs, TimeUnit.SECONDS);
		}
		log.info("sleeping done! -- initialSecs: {}", amountSecs);
	}




	/**
	 * <p>Class for aliasing {@link SleepHelper} methods.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-18
	 */
	public abstract class SH extends SleepHelper {
	}

}