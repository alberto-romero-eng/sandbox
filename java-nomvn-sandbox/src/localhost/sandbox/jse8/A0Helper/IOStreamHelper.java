package localhost.sandbox.jse8.A0Helper;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class IOStreamHelper {

	public static final Charset CHARSET_DEFAULT = StandardCharsets.UTF_8;


	public static void main() {

		System.out.println("Hello from IOStreamHelper!");

		String srcText = ""
				// uppercase
				+ "ABCD" + "\n"
				+ "EFGH" + "\n"
				+ "IJKL" + "\n"
				// lowercase
				+ "abcd" + "\n"
				+ "efgh" + "\n"
				+ "ijkl" + "\n"
				// number
				+ "1234" + "\n"
				+ "5678" + "\n"
				+ "90" + "\n"
				;
		byte[] srcByteArray = srcText.getBytes(StandardCharsets.UTF_8); // new byte[] { 0, 1, 2, 3, 4, 5, 6 }
		InputStream is;
		byte[] targetByteArray = null;
		List<String> targetStrList = null;

		// test 01, readAllBytesBaos
		System.out.println("test01, readAllBytesBaos");
		is = new ByteArrayInputStream(srcByteArray);
		targetByteArray = null;
		try {
			targetByteArray = readAllBytesBaos(is);
		} catch (Exception ex) {
			System.err.println("");
		}
		String targetStringRabBaos = new String(targetByteArray, CHARSET_DEFAULT);
		System.out.println("targetStringRabBaos:");
		System.out.println(targetStringRabBaos);
		System.out.println();

		// test 02, readAllBytesDis
		System.out.println("test02, readAllBytesDis");
		is = new ByteArrayInputStream(srcByteArray);
		targetByteArray = null;
		try {
			targetByteArray = readAllBytesDis(is);
		} catch (Exception ex) {
			System.err.println("");
		}
		String targetStringRabDis = new String(targetByteArray, CHARSET_DEFAULT);
		System.out.println("targetStringRabDis:");
		System.out.println(targetStringRabDis);
		System.out.println();

		// test03, readAllLines
		System.out.println("test03, readAllLines");
		is = new ByteArrayInputStream(srcByteArray);
		String targetStringFromRal = "";
		targetStrList = new ArrayList<>();
		try {
			targetStrList = readAllLines(is);
		} catch (Exception ex) {
			System.err.println("");
		}
		for (String line : targetStrList) {
			targetStringFromRal += line + "\n";
		}
		System.out.println("targetStringFromRal:");
		System.out.println(targetStringFromRal);
		System.out.println();
	}



	public static enum InputStreamProcessingMethod {
		READ_ALL_BYTES_BAOS,
		READ_ALL_BYTES_DIS,
		READ_ALL_LINES
	}



	/**
	 * <p>Read available bytes, using {@link ByteArrayOutputStream} at internal implementation.
	 * 
	 * <p>Java-17 may already have a built-in method of this kind.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-05
	 */
	public static byte[] readAvailableBytes(InputStream is, int available) throws Exception {
		ByteArrayOutputStream buffer = new ByteArrayOutputStream();
		int consumed = 0;
		int nRead;
		while (consumed < available) {
			consumed++;
			nRead = is.read();
			if (nRead == -1) {
				break;
			} else {
				buffer.write(nRead);
			}
		}
		if (available == 0) {
			is.read();
		}
		buffer.flush();
		byte[] targetByteArray = buffer.toByteArray();
		return targetByteArray;
	}



	/**
	 * <p>Read all bytes, using {@link ByteArrayOutputStream} at internal implementation.
	 * 
	 * <p>Java-17 may already have a built-in method of this kind.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-05
	 */
	public static byte[] readAllBytesBaos(InputStream is) throws Exception {
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



	/**
	 * <p>Read all bytes, using {@link DataInputStream} at internal implementation.
	 * 
	 * <p>Java-17 may already have a built-in method of this kind.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-05
	 */
	public static byte[] readAllBytesDis(InputStream is) throws Exception {
		is.reset();
		byte[] targetByteArray = new byte[is.available()];
		DataInputStream dis = new DataInputStream(is);
		dis.readFully(targetByteArray);
		return targetByteArray;
	}



	/**
	 * <p>Read bytes as Strings, line by line.  Uses {@link BufferedReader} at internal implementation.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-05
	 */
	public static List<String> readAllLines(InputStream is) throws Exception {
		ArrayList<String> targetStrList = new ArrayList<>();
		BufferedReader bReader = new BufferedReader(new InputStreamReader(is, CHARSET_DEFAULT));
		String line = bReader.readLine();
		while (line != null) {
			targetStrList.add(line);
			line = bReader.readLine();
		}
		return targetStrList;
	}

}
