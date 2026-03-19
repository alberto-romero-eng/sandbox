package localhost.sandbox.jse8.ThreadExecutors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.stream.Stream;

public class Test02_ParallelStreamThreadStarvation {


	public static void test00_ThreadCompetitionA() {

		Thread ct = Thread.currentThread();
		System.out.println("thread: " + ct.getName() + " -- begining");


		ArrayList<Integer> numbers = new ArrayList<>();
		numbers.add(0);
		numbers.add(1);
		numbers.add(2);
		numbers.add(3);
		numbers.add(4);
		numbers.add(5);
		numbers.add(6);
		numbers.add(7);
		numbers.add(8);
		numbers.add(9);

		/*
        ArrayList<String> letters = new ArrayList<>();
        letters.add("A");
        letters.add("B");
        letters.add("C");
        letters.add("D");
        letters.add("E");
        letters.add("F");
        letters.add("G");
        letters.add("H");
        letters.add("I");
        letters.add("J");
		 */

		Thread t00 = new Thread( 
				() -> {
					Stream<Integer> parallelStreamInteger = numbers.parallelStream();

					parallelStreamInteger.forEach(n -> {

						Thread ctn = Thread.currentThread();
						System.out.println("number loop -- thread: " + ctn.getName() + " -- number: " + n);

						/*
                Stream<String> parallelStreamString = letters.parallelStream();

                parallelStreamString.forEach(l -> {

                    Thread ctl = Thread.currentThread();
                    System.out.println("letter loop -- thread: " + ctl.getName() + " -- number: " + n + " , letter: " + l);

                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.err.println("error -- message: " + e.getMessage() + " -- cause: " + e.getCause());
                    }

                });
						 */

					});

				},
				"t00");



		Thread t01 = new Thread( 
				() -> {
					Stream<Integer> parallelStreamInteger = numbers.parallelStream();

					parallelStreamInteger.forEach(n -> {

						Thread ctn = Thread.currentThread();
						System.out.println("number loop -- thread: " + ctn.getName() + " -- number: " + n);

					});

				},
				"t01");



		Thread t02 = new Thread( 
				() -> {
					Stream<Integer> parallelStreamInteger = numbers.parallelStream();

					parallelStreamInteger.forEach(n -> {

						Thread ctn = Thread.currentThread();
						System.out.println("number loop -- thread: " + ctn.getName() + " -- number: " + n);

					});

				},
				"t02");



		Thread t03 = new Thread( 
				() -> {
					Stream<Integer> parallelStreamInteger = numbers.parallelStream();

					parallelStreamInteger.forEach(n -> {

						Thread ctn = Thread.currentThread();
						System.out.println("number loop -- thread: " + ctn.getName() + " -- number: " + n);

					});

				},
				"t03");


		Thread t04 = new Thread( 
				() -> {
					Stream<Integer> parallelStreamInteger = numbers.parallelStream();

					parallelStreamInteger.forEach(n -> {

						Thread ctn = Thread.currentThread();
						System.out.println("number loop -- thread: " + ctn.getName() + " -- number: " + n);

					});

				},
				"t04");


		t00.start();
		t01.start();
		t02.start();
		t03.start();
		t04.start();

		try {
			t00.join();
			t01.join();
			t02.join();
			t03.join();
			t04.join();
		} catch (Throwable ex) {
			System.err.println(ex);
		}


		return;
	}

}
