package localhost.sandbox.jse8.Process;

import java.io.Console;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

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


		// try to obtain folder, nothing else
		try {
			System.out.println("block 1, folder");
			ProcessBuilder pb = new ProcessBuilder("echo", "Hello world of Java-17+ scripts!"); // echo works equally in Windows-Terminal and Bash
			File folder = pb.directory();
			if (folder != null) {
				System.out.println("folder: " + folder.getAbsolutePath());
			} else {
				System.out.println("folder is null");
			}
			Process p = pb.start();
			int exitCode = -127;
			exitCode = p.waitFor();
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}



		// read output from subprocess, windows, with pb.inheritIO().
		try {
			System.out.println("block 2, print subprocess output using pb.inheritIO()");
			ProcessBuilder pb = new ProcessBuilder("echo", "Hello world of Java-17+ scripts!"); // echo works equally in Windows-Terminal and Bash
			pb.inheritIO();
			Process p = pb.start();
			int exitCode = p.waitFor();
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}

		// read output from subprocess, windows, without pb.inheritIO().
		try {
			System.out.println("block 3, read subprocess output using IOStreams (disable pb.inheritIO())");
			ProcessBuilder pb = new ProcessBuilder("echo", "Hello world of Java-17+ scripts!"); // echo works equally in Windows-Terminal and Bash
			Process p = pb.start();
			int exitCode = p.waitFor();;
			InputStream is = p.getInputStream();
			InputStream eis = p.getErrorStream();
			List<String> outputs = IOStreamHelper.readAllLines(is);
			List<String> errors = IOStreamHelper.readAllLines(eis);
			System.out.println("outputs: " + outputs);
			System.out.println("errors: " + errors);
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}



		// read variable from subprocess, windows
		try {
			System.out.println("block 4, subprocess reads value using pb.inheritIO(); pending: use System.in for capturing input");
			ProcessBuilder pb = new ProcessBuilder("C:\\opt\\git-2.48.1\\bin\\bash.exe", "-c", "echo -n 'Next string is going to System.in! (type 12345679890, see what happens later): ' ; read -N 1 -p input myvar ; echo ${myvar}"); // bash's read, param -p fails, but simple read works fine, along with pb.inheritIO().
			// ProcessBuilder pb = new ProcessBuilder("C:\\opt\\git-2.48.1\\bin\\bash.exe", "-c", "echo hello world of gitbash and java! ; echo 'again hello'! ; echo \"hello once more, double-quoted!\""); // two first echo well, third one fails, but process exits ok
			pb.inheritIO();
			Process p = pb.start();
			// OutputStream os = p.getOutputStream();
			// InputStream is = p.getInputStream();
			boolean waitB1 = p.waitFor(3000L, TimeUnit.MILLISECONDS);
			// p.getOutputStream().write("aaaaa".getBytes(StandardCharsets.UTF_8));
			boolean waitB2 = p.waitFor(3000L, TimeUnit.MILLISECONDS);
			// List<String> results = IOStreamHelper.readAllLines(p.getInputStream());
			// System.out.println("results: " + results);
			int exitCode = p.waitFor();
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}



		// read variable from subprocess, windows
		try {
			System.out.println("block 5, subprocess reads value using IOStreams, (disable pb.inheritIO()) check if java still cannot read it");
			ProcessBuilder pb = new ProcessBuilder("C:\\opt\\git-2.48.1\\bin\\bash.exe", "-c", "echo 'AAAA' ; read -N 1 myvar ; echo ${myvar}"); // bash's read, param -p fails, but simple read works fine, along with pb.inheritIO().
			// ProcessBuilder pb = new ProcessBuilder("C:\\opt\\git-2.48.1\\bin\\bash.exe", "-c", "echo hello world of gitbash and java! ; echo 'again hello'! ; echo \"hello once more, double-quoted!\""); // two first echo well, third one fails, but process exits ok
			Process p = pb.start();
			OutputStream os = p.getOutputStream();
			InputStream is = p.getInputStream();
			int isAvCount = 0;
			String outStr = null;
			InputStream eis = p.getErrorStream();
			int eisAvCount = 0;
			String errStr = null;


			boolean waitB1 = p.waitFor(3000L, TimeUnit.MILLISECONDS);
			os.write("CCC\r\n".getBytes(StandardCharsets.UTF_8));
			os.flush();
			isAvCount = is.available();
			eisAvCount = eis.available();
			outStr = new String(IOStreamHelper.readAvailableBytes(is, isAvCount), StandardCharsets.UTF_8);
			System.out.println("outStr: " + outStr);
			errStr = new String(IOStreamHelper.readAvailableBytes(eis, eisAvCount), StandardCharsets.UTF_8);
			System.err.println("errStr: " + errStr);


			boolean waitB2 = p.waitFor(3000L, TimeUnit.MILLISECONDS);
			// os.write("DDD\n".getBytes(StandardCharsets.UTF_8));
			// os.flush();
			isAvCount = is.available();
			eisAvCount = eis.available();
			outStr = new String(IOStreamHelper.readAvailableBytes(is, isAvCount), StandardCharsets.UTF_8);
			System.out.println("outStr: " + outStr);
			errStr = new String(IOStreamHelper.readAvailableBytes(eis, eisAvCount), StandardCharsets.UTF_8);
			System.err.println("errStr: " + errStr);


			// os.write("EEE\n".getBytes(StandardCharsets.UTF_8));
			// os.flush();
			outStr = new String(IOStreamHelper.readAvailableBytes(is, isAvCount), StandardCharsets.UTF_8);
			System.out.println("outStr: " + outStr);
			errStr = new String(IOStreamHelper.readAvailableBytes(eis, eisAvCount), StandardCharsets.UTF_8);
			System.err.println("errStr: " + errStr);
			int exitCode = p.waitFor();
			System.out.println("exitCode: " + exitCode);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}



		// java, normal read
		try {
			System.out.println("block 6, normal java read using Scanner, see how System.in was affected from previous reading using inheritIO()");
			System.err.println("this is stderr");
			System.out.println("this is stdout");
			InputStream sysIn = System.in;
			int sysInAvCount = sysIn.available();
			byte[] dirtyByteArray = IOStreamHelper.readAvailableBytes(sysIn, sysInAvCount);
			System.out.println("dirtyByteArray: " + new String(dirtyByteArray, StandardCharsets.US_ASCII));
			System.out.print("Now, System.in is clean, type another input for Scanner: ");
			Scanner scanner = new Scanner(sysIn);
			String scanned = scanner.nextLine();
			System.out.println("scanned: " + scanned);
		} catch (Exception ex) {
			System.err.println("ex.class: " + ex.getClass() + ", ex.message: " + ex.getMessage());
		}


	}

	// C:\opt\git-2.48.1\bin\bash.exe -c 'echo hello world'
	// (win-terminal, double-quote mandatory): set /p "myvar=Enter myvar: "
	// (win-terminal, double-quote mandatory): set /p myvar= "please input value: "
	// (win-terminal): echo %myvar%
	// (win-terminal, several commands in one line): set /p myvar= "please input value: " & echo %myvar%
	// (win-terminal, second statement executes only if first is successful): set /p myvar= "please input value: " && echo %myvar%
	// (win-terminal) C:\opt\git-2.48.1\bin\bash.exe -c 'read -p "please write input: " myvar ; echo ${myvar}'

}
