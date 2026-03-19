package localhost.sandbox.jse8.JavaLang.Miscelaneous;

import java.util.ArrayList;
import java.util.function.Consumer;

public class Test02Foreach {

	public static void test01_Foreach() {
		ArrayList<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");

		Consumer<String> c = s -> {
			System.out.println(s); // System.out::println // java 9+, but which one? 
		};

		list.forEach(c);

		list.stream().forEach(c);

		list.parallelStream().forEach(c);


	}

}
