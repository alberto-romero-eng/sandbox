package localhost.sandbox.jse8.URI;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import localhost.sandbox.jse8.A0Helper.IOStreamHelper;


/**
 * <p>
 * <b>URI</b> - sequence of characters that allows the complete identification of any abstract or physical resource.
 * <br>
 * <b>URL</b> - subset of URI that, in addition to identifying where a resource is available, describes the primary mechanism to access it.
 * 
 * <p>
 * URI (and URL) syntax:
 * <pre>
 * scheme:[//authority][/path][?query][#fragment]</pre>
 * <ul>
 * <li><b>scheme</b> − for URLs, is the name of the protocol used to access the resource, for other URIs, is a name that refers to a specification for assigning identifiers within that scheme</li>
 * <li><b>authority</b> − an optional part comprised of user authentication information, a host and an optional port</li>
 * <li><b>path</b> − it serves to identify a resource within the scope of its scheme and authority</li>
 * <li><b>query</b> − additional data that, along with the path, serves to identify a resource. For URLs, this is the query string</li>
 * <li><b>fragment</b> − an optional identifier to a specific part of the resource</li>
 * </ul>
 * 
 * <p>
 * Valid URL protocols: <i>tp, http, https, gopher, mailto, news, nntp, telnet, wais, file, prospero</i>.
 * 
 * <p>
 * URL examples:
 * <pre>
 * ftp://ftp.is.co.za/rfc/rfc1808.txt
 * https://tools.ietf.org/html/rfc3986
 * mailto:john@doe.com</pre>
 * 
 * <p>
 * URI not URL examples:
 * <pre>
 * tel:+1-816-555-1212
 * urn:oasis:names:docbook:dtd:xml:4.1
 * urn:isbn:1234567890</pre>
 * 
 * 
 * @since 2026-03-20
 * 
 */
public class Test00_UriAndUrl {


	public static void test00a_CreateUri () throws Throwable {

		URI uri01 = new URI("scheme://user:passw@authority:80/pathVar01/pathVar02?query#fragment");

		URI uri02 = new URI(
				"scheme",
				"user:passw",
				"authority",
				80,
				"/pathVar01/pathVar02",
				"query",
				"fragment");

		URI uri03 = URI.create("scheme://user:passw@authority:80/pathVar01/pathVar02?query#fragment"); // if argument is not valid, does not throw checked-exception

		boolean eqU12 = uri01.equals(uri02);
		boolean eqU23 = uri02.equals(uri03);
		System.out.println("uri01: " + uri01);
		System.out.println("uri02: " + uri02);
		System.out.println("uri03: " + uri03);
		System.out.println("eqU12: " + eqU12);
		System.out.println("eqU23: " + eqU23);

		String sch01 = uri01.getScheme();
		String sch02 = uri02.getScheme();
		boolean eqS12 = sch01.equals(sch02);
		System.out.println("sch01: " + sch01);
		System.out.println("sch02: " + sch02);
		System.out.println("eqS12: " + eqS12);

		String host01 = uri01.getHost();
		String host02 = uri02.getHost();
		boolean eqH12 = host01.equals(host02);
		System.out.println("host01: " + host01);
		System.out.println("host02: " + host02);
		System.out.println("eqH12:  " + eqH12);
	}



	public static void test00b_CreateUrl () throws Throwable {

		URL url01 = new URL("http://host:80/pathVar01/pathVar02?query#fragment");

		URL url02 = new URL(
				"http",
				"host",
				80,
				"/pathVar01/pathVar02?query#fragment");

		URL url03 = new URL("http://user:passw@host:80/pathVar01/pathVar02?query#fragment");


		boolean eq12 = url01.equals(url02);
		boolean eq23 = url02.equals(url03);
		System.out.println("url01: " + url01);
		System.out.println("url02: " + url02);
		System.out.println("url03: " + url03);
		System.out.println("eq12:  " + eq12);
		System.out.println("eq23:  " + eq23);

		String pro01 = url01.getProtocol();
		String pro02 = url02.getProtocol();
		boolean eqP12 = pro01.equals(pro02);
		System.out.println("pro01: " + pro01);
		System.out.println("pro02: " + pro02);
		System.out.println("eqP12: " + eqP12);

		String host01 = url01.getHost();
		String host02 = url02.getHost();
		boolean eqH12 = host01.equals(host02);
		System.out.println("host01: " + host01);
		System.out.println("host02: " + host02);
		System.out.println("eqH12:  " + eqH12);
	}


	public static void test01_UrlToUriAndViceversa() throws Throwable {

		URL url01 = new URL("http://host:80/pathVar01/pathVar02?query#fragment");
		URI uri01 = url01.toURI();
		URL url01r = uri01.toURL();
		System.out.println("url01:  " + url01);
		System.out.println("uri01:  " + uri01);
		System.out.println("url01r: " + url01r);

		URI uri02 = new URI("scheme://user:passw@authority:80/pathVar01/pathVar02?query#fragment");
		System.out.println("uri02:  " + uri02);
		try {
			URL url02r = uri02.toURL(); // MalformedUrlException thrown, unknown protocol
			System.out.println("url02r: " + url02r); // execution does not reach here
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}
	}


	public static void test02_UrlStream() throws Throwable {

		URL url; // enable one value assignment below

		/*
		 * fail:
		 */
		// url = new URL("https://postman-echo.com/get"); // fail: java.io.IOException, server returned HTTP response code 403
		// url = new URL("file://tmp/numbers.txt"); // fail: java.net.UnknownHostException, tmp

		/*
		 * success:
		 */
		// url = new URL("http://courses.baeldung.com"); // ok, but InputStream empty
		// url = new URL("file:///tmp/numbers.txt"); // ok, using C:\tmp\numbers.txt
		url = Test00_UriAndUrl.class.getClassLoader().getResource("tmp/numbers.txt"); // ok, "tmp" folder must be copied into project's "bin" folder, (see "classpath" in "Show Command Line", at debugger configuration)

		try {
			InputStream inputStream = url.openStream(); // exception thrown here, if InputStream is null
			List<String> targetStrList = new ArrayList<>(); // if exception thrown, execution does no reach here
			targetStrList = IOStreamHelper.readAllLines(inputStream);
			for (String s : targetStrList) {
				System.out.println(s);
			}
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}
	}

}
