package localhost.sandbox.jse8.A0Helper;

import java.net.URI;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;


public class URLHelper {

	public static void main() throws Throwable {
		// params
		String inLink = null;
		inLink = "https://alberto-romero-eng.github.io/img/test/marrón.jpg";
		String expectedOutLink = "https://alberto-romero-eng.github.io/img/test/marr%C3%B3n.jpg"; // corrected
		String outLink1 = null;
		String outLink2 = null;
		String outLink3 = null;
		// transformation
		outLink1 = URLHelper.toASCIIString(inLink);
		outLink2 = URLHelper.toASCIIString(outLink1);
		outLink3 = URLHelper.toASCIIString(outLink2);
		// result
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
	public static String toASCIIString(String inputLink) throws Throwable {
		URL url = new URL(inputLink);
		boolean isPureAscii = isPureAscii(url.getPath());
		if (isPureAscii) {
			return inputLink;
		}
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		String outLink = uri.toASCIIString();
		return outLink;
	}


	private static boolean isPureAscii(String input) {
		return StandardCharsets.US_ASCII.newEncoder().canEncode(input);
	}
}
