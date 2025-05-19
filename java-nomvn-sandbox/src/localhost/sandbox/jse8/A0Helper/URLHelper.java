package localhost.sandbox.jse8.A0Helper;

import java.net.URI;
import java.net.URL;


public class URLHelper {

	public static void main() throws Throwable {
		// params
		String inLink = null;
		inLink = "https://alberto-romero-eng.github.io/img/test/marrón.jpg";
		String expectedOutLink = "https://alberto-romero-eng.github.io/img/test/marr%C3%B3n.jpg"; // corrected
		String outLink = null;
		// transformation
		outLink = URLHelper.toASCIIString(inLink);
		// result
		System.out.println("outLink: " + outLink + ", ok: " + expectedOutLink.equals(outLink));
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
	 * @author Alberto Romero
	 * @since 2025-05-18
	 */
	public static String toASCIIString(String inputLink) throws Throwable {
		URL url = new URL(inputLink);
		URI uri = new URI(url.getProtocol(), url.getUserInfo(), url.getHost(), url.getPort(), url.getPath(), url.getQuery(), url.getRef());
		String outLink = uri.toASCIIString();
		return outLink;
	}

}
