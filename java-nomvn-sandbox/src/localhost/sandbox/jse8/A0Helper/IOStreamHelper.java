package localhost.sandbox.jse8.A0Helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;

public class IOStreamHelper {


	public static void main() {

		System.out.println("Hello from IOStreamHelper!");

		String srcText = ""
				// uppercase
				+ "AAAA" + "\n"
				+ "BBBB" + "\n"
				+ "CCCC" + "\n"
				+ "DDDD" + "\n"
				// lowercase
				+ "aaaa" + "\n"
				+ "bbbb" + "\n"
				+ "cccc" + "\n"
				+ "dddd" + "\n"
				// number
				+ "1111" + "\n"
				+ "2222" + "\n"
				+ "3333" + "\n"
				+ "4444" + "\n"
				;
		byte[] srcByteArray = srcText.getBytes(StandardCharsets.UTF_8); // new byte[] { 0, 1, 2, 3, 4, 5, 6 }
		InputStream is;

		// test 01, getByteArray
		System.out.println("test01, getByteArray");
		is = new ByteArrayInputStream(srcByteArray);
		byte[] targetByteArray = null;
		try {
			targetByteArray = getByteArray(is);
		} catch (Exception ex) {
			System.err.println("");
		}
		String targetStringFromBA = new String(targetByteArray, StandardCharsets.UTF_8);
		System.out.println("targetStringFromBA:");
		System.out.println(targetStringFromBA);
		System.out.println();

		// test02, readLineByLine
		System.out.println("test02, readLineByLine");
		is = new ByteArrayInputStream(srcByteArray);
		String targetStringFromRLBL = null;
		try {
			targetStringFromRLBL = readLineByLine(is);
		} catch (Exception ex) {
			System.err.println("");
		}
		System.out.println("targetStringFromRLBL:");
		System.out.println(targetStringFromRLBL);
		System.out.println();
	}



	public static enum InputStreamProcessingMethod {
		GET_BYTE_ARRAY,
		READ_LINE_BY_LINE
	}



	private static byte[] getByteArray(InputStream is) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int nRead;
		byte[] data = new byte[4];
		while ((nRead = is.read(data, 0, data.length)) != -1) { // ((nRead = is.read()) != -1)
			buffer.write(data, 0, nRead); // buffer.write(nRead);
		}
		buffer.flush();
		byte[] targetByteArray = buffer.toByteArray();
		return targetByteArray;
	}



	private static String readLineByLine(InputStream is) throws Exception {
		String targetString = "";
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
		String line = bReader.readLine();
		while (line != null) {
			targetString += line + "\n";
			line = bReader.readLine();
		}
		if (targetString.isEmpty()) {
			return null;
		} else {
			return targetString;
		}
	}

}
