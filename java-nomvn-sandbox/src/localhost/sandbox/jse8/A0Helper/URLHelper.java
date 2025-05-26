package localhost.sandbox.jse8.A0Helper;

import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.text.Normalizer;


public class URLHelper {

	public static void main() throws Throwable {

		String s1 = "ó";
		String s2 = null;
		s2 = normalizeNonAsciiChars(s1);

		System.out.println("s1: " + s1 + ", s2: " + s2);
	}

	public static void testUrlEncodes() throws Throwable {

		// params
		String inLink = null;
		String outLink1 = null;
		String outLink2 = null;
		String outLink3 = null;
		String expectedOutLink = null;

		// test 1, url-encode
		System.out.println("test1");
		inLink = "https://alberto-romero-eng.github.io/img/test/marrón.jpg";
		expectedOutLink = "https://alberto-romero-eng.github.io/img/test/marr%C3%B3n.jpg"; // corrected
		// transformation
		outLink1 = URLHelper.toAsciiByUrlEncode(inLink);
		outLink2 = URLHelper.toAsciiByUrlEncode(outLink1);
		outLink3 = URLHelper.toAsciiByUrlEncode(outLink2);
		// result
		System.out.println("inLink1: " + inLink);
		System.out.println("outLink1: " + outLink1 + ", ok1: " + expectedOutLink.equals(outLink1));
		System.out.println("outLink2: " + outLink2 + ", ok2: " + expectedOutLink.equals(outLink2));
		System.out.println("outLink3: " + outLink3 + ", ok3: " + expectedOutLink.equals(outLink3));

		// test 2, normalization
		System.out.println("test2");
		inLink = "https://alberto-romero-eng.github.io/img/test/marrón.jpg";
		expectedOutLink = "https://alberto-romero-eng.github.io/img/test/marron.jpg"; // corrected
		// transformation
		outLink1 = URLHelper.toAsciiByNormalization(inLink);
		outLink2 = URLHelper.toAsciiByNormalization(outLink1);
		outLink3 = URLHelper.toAsciiByNormalization(outLink2);
		// result
		System.out.println("inLink1: " + inLink);
		System.out.println("outLink1: " + outLink1 + ", ok1: " + expectedOutLink.equals(outLink1));
		System.out.println("outLink2: " + outLink2 + ", ok2: " + expectedOutLink.equals(outLink2));
		System.out.println("outLink3: " + outLink3 + ", ok3: " + expectedOutLink.equals(outLink3));

		// test 3, normalization
		System.out.println("test3");
		inLink = "https://alberto-romero-eng.github.io/img/test/__ãéí–áá__.jpg";
		expectedOutLink = "https://alberto-romero-eng.github.io/img/test/__aeiaa__.jpg"; // corrected
		// transformation
		outLink1 = URLHelper.toAsciiByNormalization(inLink);
		outLink2 = URLHelper.toAsciiByNormalization(outLink1);
		outLink3 = URLHelper.toAsciiByNormalization(outLink2);
		// result
		System.out.println("inLink1: " + inLink);
		System.out.println("outLink1: " + outLink1 + ", ok1: " + expectedOutLink.equals(outLink1));
		System.out.println("outLink2: " + outLink2 + ", ok2: " + expectedOutLink.equals(outLink2));
		System.out.println("outLink3: " + outLink3 + ", ok3: " + expectedOutLink.equals(outLink3));
	}


	/**
	 * <p>Converts UTF8 characters in <i>inputLink</i> to ASCII URL-encoded characters.
	 * 
	 * <p>Note, for this url example: 'https://example.com/img/test/marrón.jpg':
	 * <ul>
	 * <li><code>curl -si -I HEAD 'https://example.com/img/test/marrón.jpg'</code>
	 * <br>
	 * Returns <i>404 NOT FOUND</i>
	 * <li><code>curl -si -I HEAD 'https://example.com/img/test/marr%C3%B3n.jpg'</code>
	 * <br>
	 * Returns <i>200 OK</i>
	 * </ul>
	 * 
	 * <p>ASCII verification (<i>is inputLink to be URL-encoded, or not?</i>), requires further 
	 * analysis (initially, only <i>path</i> is being considered).
	 * 
	 * @author Alberto Romero
	 * @since 2025-05-18
	 */
	public static String toAsciiByUrlEncode(String inputLink) throws Throwable {
		URL url = new URL(inputLink);
		boolean isPureAscii = isPureAscii(url.getPath());
		if (isPureAscii) {
			return inputLink;
		}
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		String outLink = uri.toASCIIString();
		return outLink;
	}


	/**
	 * <p>Supress non-ASCII characters in <i>inputLink</i>'s path.
	 * 
	 * <p>ASCII verification, requires further analysis (initially, only <i>path</i> is being considered).
	 * 
	 * @author Alberto Romero
	 * @since 2025-05-18
	 */
	public static String toAsciiByNormalization(String inputLink) throws Throwable {
		URL url = new URL(inputLink);
		boolean isPureAscii = isPureAscii(url.getPath());
		if (isPureAscii) {
			return inputLink;
		}
		String modifiedPath = normalizeNonAsciiChars(url.getPath());
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), modifiedPath, url.getQuery(), url.getRef());
		String outLink = uri.toASCIIString();
		return outLink;
	}


	private static boolean isPureAscii(String input) {
		return StandardCharsets.US_ASCII.newEncoder().canEncode(input);
	}


	public static String normalizeNonAsciiChars(String input) {
		String output = null;
		output = Normalizer.normalize(input, Normalizer.Form.NFD);
		output = output.replaceAll("[^\\x00-\\x7F]", "");
		return output;
	}

}
