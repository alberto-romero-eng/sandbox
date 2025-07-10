package com.example.myserver.util;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class CustomThreadFactory implements ThreadFactory {

	private AtomicInteger ai;
	private String threadPrefix;

	public CustomThreadFactory (String inThreadPrefix) {
		super();
		this.threadPrefix = inThreadPrefix;
		this.ai = new AtomicInteger(1);
	}

	@Override
	public Thread newThread(Runnable runnable) {
		int i = ai.getAndIncrement();
		// return new Thread(runnable, String.format("newFtpFileThread-%s", i));
		return new Thread(runnable, String.format("%s-%s", threadPrefix, i));
	}

}
