package localhost.sandbox.jse8.A0Helper;

import java.nio.charset.StandardCharsets;

public class ByteHexHelper {

	private static final char[] HEX_ARRAY = "0123456789ABCDEF".toCharArray();



	public static void main() {

		System.out.println("Hello from ByteHexHelper!");
		System.out.println();

		test01();
		System.out.println();

		test02();
		System.out.println();
	}


	private static void test01() {
		System.out.println("Hello from test01!");

		// test
		int readInt1 = 65; 
		byte b1 = Byte.valueOf((new Integer(readInt1)).toString(), 10); // x41, "A"
		byte[] b1a = { b1 };
		System.out.println("test -- " + "readInt: " + readInt1 + ", byte: " + b1 + ", baToHex: " + byteArrayToHexString(b1a));

		// test
		int readInt2 = 66;
		byte b2 = Byte.valueOf((new Integer(readInt2)).toString(), 10); // x42, "B"
		byte[] b2a = { b2 };
		System.out.println("test -- " + "readInt: " + readInt2 + ", byte: " + b2 + ", baToHex: " + byteArrayToHexString(b2a));

		// test
		int readInt3 = 67;
		byte b3 = Byte.valueOf((new Integer(readInt3)).toString(), 10); // x43, "C"
		byte[] b3a = { b3 };
		System.out.println("test -- " + "readInt: " + readInt3 + ", byte: " + b3 + ", baToHex: " + byteArrayToHexString(b3a));

		// test
		int readInt4 = 68;
		byte b4 = Byte.valueOf((new Integer(readInt4)).toString(), 10); // x44, "D"
		byte[] b4a = { b4 };
		System.out.println("test -- " + "readInt: " + readInt4 + ", byte: " + b4 + ", baToHex: " + byteArrayToHexString(b4a));

		byte[] bas = {b1, b2, b3, b4};
		String s = new String(bas, StandardCharsets.UTF_8);
		System.out.println("test -- bas: " + bas + ", s: " + s + ", basToHex: " + byteArrayToHexString(bas));
	}


	private static void test02() {
		System.out.println("Hello from test02!");

		// check string, latin ene with tilde:
		byte[] upperEneByteArray = hexStringToByteArray("41C39141");
		byte[] lowerEneByteArray = hexStringToByteArray("41C3B141");

		String upperEneString = new String(upperEneByteArray, StandardCharsets.UTF_8);
		String lowerEneString = new String(lowerEneByteArray, StandardCharsets.UTF_8);

		System.out.println("upper n-tilde: " + upperEneString);
		System.out.println("lower n-tilde: " + lowerEneString);
	}



	public static String byteArrayToHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = HEX_ARRAY[v >>> 4];
			hexChars[j * 2 + 1] = HEX_ARRAY[v & 0x0F];
		}
		return new String(hexChars);
	}



	/**
	 * <p>Param <i>s</i> must be an even-length string.
	 * 
	 */
	public static byte[] hexStringToByteArray(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character.digit(s.charAt(i+1), 16));
		}
		return data;
	}

}
