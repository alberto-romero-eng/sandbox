package localhost.sandbox.jse8.ThreadRaceConditionSynchroWaitNotifyAtomic;

import localhost.sandbox.jse8.Synchronized.SynchronizedMain;

/**
 * <p>IMPORTANT, 2026-10-09: see {@link SynchronizedMain}.
 */
public class ThreadRaceConditionSynchroWaitNotifyAtomicMain {

	/**
	 * <p>IMPORTANT, 2026-10-09: see {@link SynchronizedMain}.
	 */
	public static void main() {
		System.out.println("Hello from ThreadRaceCondition!");
		Test01_SynchroWaitNotify.test00_SynchroWaitNotify();
		// Test02_AtomicReference.test00_AtomicReference();
	}
}
