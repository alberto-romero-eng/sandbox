package localhost.sandbox.jse8.FunctionalInterface;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class FunctionalInterfaceMain {

	public static void main() {


		// Runnable
		String sRunnable = "Hello Runnable";

		Runnable r = () -> {
			System.out.println(sRunnable);
			// sRunnable = sRunnable + ", value has changed"; // not allowed, sRunnable must be final, or effectively final
			return;
		};
		r.run();
		System.out.println(sRunnable);


		// Consumer
		String sConsumer = "Hello Consumer";
		Consumer<String> c = (s) -> {
			System.out.println(s);
			System.out.println(sConsumer);
			s = s + ", value has changed";
			System.out.println(s);
			String oS = "just another local string";
			System.out.println(oS);
			return;
		};
		System.out.println(sConsumer);
		c.accept(sConsumer);
		System.out.println(sConsumer);


		// Supplier
		String sSupplier = "Hello Supplier";
		String supplierRe = null;
		Supplier<String> s = () -> {
			// sSupplier = sSupplier + ", value has changed"; // not allowed, must be final or effectively final
			String out = "result from supplier";
			return out;
		};
		supplierRe = s.get();
		System.out.println(supplierRe);
	}

}
