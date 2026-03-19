package localhost.sandbox.jse8.Thread;

public class Test03_Synchronization {

	/**
	 * <p>Use debugger, place breakpoints at beginning of runnable lambda and 
	 * at beggining of every {@link MySynchroClass} method.
	 * 
	 * <p>By stepping, make progressively threads advance.  Pay attention to
	 * how threads acquire monitor locks when entering static and instance 
	 * method blocks of {@link MySychroClass}.
	 * 
	 * <p>By use of debugger, this test is friendlier than 
	 * {@link Test02_StaticSyncMethodRaceCondition#test00_StaticSyncMethodRaceCondition()} 
	 * for grasping monitor-lock and race-condition principles.
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-11
	 * 
	 */
	public static void test01_Synchronization() {

		MySynchroClass msc1 = new MySynchroClass();
		MySynchroClass msc2 = new MySynchroClass();

		Runnable runnable = () -> {
			MySynchroClass.myStaticSynchroMethodOne();
			MySynchroClass.myStaticSynchroMethodTwo();
			msc1.myInstanceSynchroMethodOne();
			msc1.myInstanceSynchroMethodTwo();
			msc2.myInstanceSynchroMethodOne();
			msc2.myInstanceSynchroMethodTwo();
		};

		Thread t1 = new Thread(runnable, "t1");
		Thread t2 = new Thread(runnable, "t2");
		Thread t3 = new Thread(runnable, "t3");
		Thread t4 = new Thread(runnable, "t4");

		t1.start();
		t2.start();
		t3.start();
		t4.start();

		try {
			t1.join();
			t2.join();
			t3.join();
			t4.join();
		} catch (Throwable ex) {
			System.err.println("ex: " + ex.getClass() + ", " + ex.getMessage());
		}
	}

	private static class MySynchroClass {

		public static synchronized void myStaticSynchroMethodOne() {
			String logMsg = "myStaticSynchroMethodOne -- currentThread: " + Thread.currentThread().getName();
			System.out.println(logMsg);
			return;
		}

		public static synchronized void myStaticSynchroMethodTwo() {
			String logMsg = "myStaticSynchroMethodTwo -- currentThread: " + Thread.currentThread().getName();
			System.out.println(logMsg);
			return;
		}

		public synchronized void myInstanceSynchroMethodOne() {
			String logMsg = "myInstanceSynchroMethodOne -- currentThread: " + Thread.currentThread().getName();
			System.out.println(logMsg);
			return;
		}

		public synchronized void myInstanceSynchroMethodTwo() {
			String logMsg = "myInstanceSynchroMethodTwo -- currentThread: " + Thread.currentThread().getName();
			System.out.println(logMsg);
			return;
		}

	}
}
