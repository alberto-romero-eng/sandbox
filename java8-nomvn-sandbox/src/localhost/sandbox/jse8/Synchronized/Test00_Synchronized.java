package localhost.sandbox.jse8.Synchronized;

public class Test00_Synchronized {

	/**
	 * <p>By debugging this test, it is simple to visualize there is a monitor-lock
	 * for each class, and also a monitor-lock for each instance of a class.
	 * 
	 * <p>Token <i>synchronized</i> operates according to monitor-lock type above
	 * mentioned.
	 * 
	 * @author Alberto Romero
	 * @since 2026-10-09
	 */
	public static void test00_Synchronized () {

		MySyncPrinter mspOne = new MySyncPrinter();
		MySyncPrinter mspTwo = new MySyncPrinter();

		Runnable r = () -> {
			System.out.println("start");
			MySyncPrinter.staticPrint();
			System.out.println("just exited MySyncPrinter.staticPrint()");
			mspOne.instancePrint();
			System.out.println("just exited mspOne.instancePrint()");
			mspTwo.instancePrint();
			System.out.println("just exited mspTwo.instancePrint()");
		};

		Thread tA = new Thread(r, "tA");
		Thread tB = new Thread(r, "tB");
		Thread tC = new Thread(r, "tC");

		tA.start();
		tB.start();
		tC.start();

		System.out.println("main, done!");
	}



	public static class MySyncPrinter {

		public static synchronized void staticPrint() {
			System.out.println("static synchronized print!!!");
			return;
		}

		public synchronized void instancePrint() {
			System.out.println("instance synchronized print!!!");
			return;
		}
	}
}
