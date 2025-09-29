package localhost.sandbox.jse8.A0Helper;

import java.security.SecureRandom;

/**
 * <p>Updates:
 * <ul> 
 * <li>2025-07-21, Alberto Romero: refactor, added enumeration, class to aid aliasing, tests.
 * </ul>
 * 
 */
public class TokenGeneratorHelper {



	public static void main() {
		System.out.println("Hello from TokenGeneratorHelper!");

		// test 01, alphanumeric token
		String token01 = TGH.gen(Source.ALPHA_NUMERIC, 8);
		System.out.println("token01: " + token01);

		// test 02, numeric token
		String token02 = TGH.gen(Source.NUMERIC, 6);
		System.out.println("token02: " + token02);
	}



	/**
	 * <p>Main function, generate token considering indicated {@link Source} and char-length.
	 */
	public static String generate(Source source, int n) {
		// System.out.println("Executing generate() -- source: " + source + ", n: " + n);
		// chose a Character random from this String
		// create StringBuffer size of AlphaNumericString
		StringBuilder sb = new StringBuilder(n);
		for (int i = 0; i < n; i++) {
			// generate a random number between
			// 0 to AlphaNumericString variable length
			SecureRandom random = new SecureRandom();
			int index = random.nextInt(source.value().length());
			// add Character one by one in end of sb
			sb.append(source.value().charAt(index));
		}
		return sb.toString();
	}



	/**
	 * <p>Char sources for token generation.
	 */
	public static enum Source {
		NUMERIC("0123456789"),
		UPPERCASE("ABCDEFGHIJKLMNOPQRSTUVWXYZ"),
		LOWERCASE("abcdefghijklmnopqrstuvxyz"),
		ALPHA_NUMERIC(NUMERIC.value() + UPPERCASE.value() + LOWERCASE.value());

		private String value;

		private Source(String value) {
			this.value = value;
		}

		private String value() {
			return this.value;
		}
	}



	/**
	 * <p>Class to aid aliasing.
	 */
	public static class TGH {

		/**
		 * <p>Alias for {@link TokenGeneratorHelper#generate(String, int)}.
		 */
		public static String gen(Source source, int n) {
			return TokenGeneratorHelper.generate(source, n);
		}

	}

}
