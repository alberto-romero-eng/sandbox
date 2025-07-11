package com.example.myclient.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SleepHelper {

	private static Logger log = LoggerFactory.getLogger(SleepHelper.class);


	public static void sleep(long timeMillisecs) {
		try {
			Thread.sleep(timeMillisecs);
		} catch (Exception e) {
			log.error("sleep error -- " + e);
		}
	}


	public static void sleepPrintInterval(long timeMillisecs) {
		// constants
		final float initialSecs = timeMillisecs / 1000.0f;
		final long intervalMillisecs = 5 * 1000L;
		final float intervalSecs = intervalMillisecs / 1000.0f;
		// vars
		long remainingMillisecs = timeMillisecs;
		float remainingSecs = remainingMillisecs / 1000.0f;
		// sleep loop
		while (remainingMillisecs > intervalMillisecs) {
			log.info("sleeping {} secs -- initialSecs: {} ; remainingSecs: {}", intervalSecs, initialSecs, remainingSecs);
			SleepHelper.sleep(intervalMillisecs);
			remainingMillisecs = remainingMillisecs - intervalMillisecs;
			remainingSecs = remainingMillisecs / 1000.0f;
		}
		if (remainingMillisecs <= intervalMillisecs && remainingMillisecs > 0) {
			log.info("sleeping {} secs (soon to end) -- initialSecs: {} ; remainingSecs: {}", remainingSecs, initialSecs, remainingSecs);
			SleepHelper.sleep(remainingMillisecs);
		}
	}

}
