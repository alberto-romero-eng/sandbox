package localhost.sandbox.jse8.Process;

import java.io.Console;
import java.io.File;
import java.io.OutputStream;
import java.util.List;

import localhost.sandbox.jse8.A0Helper.IOStreamHelper;

public class Test01ProcessBuilder {


	public static void test00_Simple() {
		System.out.println("Hello from test00_Simple!");
		ProcessBuilder pb = new ProcessBuilder("echo", "Hello world of Java-17+ scripts!"); // echo works equally in Windows-Terminal and Bash
		pb.inheritIO(); // output as System.out.println()
		File folder = pb.directory();
		if (folder != null) {
			System.out.println("folder: " + folder.getAbsolutePath());
		} else {
			System.out.println("folder is null");
		}
		Process p = null;
		try {
			p = pb.start();
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}
		int exitCode = -127;
		try {
			exitCode = p.waitFor();
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}
		System.out.println("exitCode: " + exitCode);
	}


	public static void test01_Console() {
		System.out.println("Hello from test01_Console!");
		Console cons = System.console();
		cons.printf("%s", "input a line:");
		String input = cons.readLine();
		cons.printf("your input was: '%s'", input);
	}


	public static void test02_IOStream() {
		System.out.println("Hello from test02_IOStream!");
		try {
			ProcessBuilder pb = new ProcessBuilder("echo", "Hello world of Java-17+ scripts!"); // echo works equally in Windows-Terminal and Bash
			// pb.inheritIO(); // output as System.out.println() // disabled in order to use process IO-Streams
			File folder = pb.directory();
			if (folder != null) {
				System.out.println("folder: " + folder.getAbsolutePath());
			} else {
				System.out.println("folder is null");
			}
			Process p = null;
			p = pb.start();
			List<String> results = IOStreamHelper.readAllLines(p.getInputStream());
			System.out.println("results: " + results);
			int exitCode = -127;
			exitCode = p.waitFor();
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}

	}

}
