package localhost.sandbox.jse8.URI;

import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import localhost.sandbox.jse8.A0Helper.IOStreamHelper;


/**
 * 
 * <h1>Overview</h1>
 * <p><b>URI</b> - sequence of characters that allows the complete identification of any abstract or physical resource.</p>
 * <p><b>URL</b> - subset of URI that, in addition to identifying where a resource is available, describes the primary mechanism to access it.</p>
 * 
 * 
 * 
 * 
 * <h2>URI</h2>
 * <p>Classification:<p>
 * <ul>
 * <li>Opaque (Absolute)</li>
 * <li>Hierarchical Absolute</li>
 * <li>Hierarchical Relative</li>
 * </ul>
 * <p>Syntax, higher level:</p>
 * <pre>[scheme:]scheme-specific-part[#fragment]</pre>
 * 
 * 
 * <p><b>URI, Opaque (Absolute)</b>.  Absolute: it defines an <i>scheme</i>.  Opaque: its <i>scheme-specific-part</i> does not begin with '/', so it cannot be further parsed.</p>
 * <p>Syntax:</p>
 * <pre>scheme:scheme-specific-part[#fragment]</pre>
 * <p>Examples:</p>
 * <pre>mailto:java-net@java.sun.com
 *news:comp.lang.java
 *urn:isbn:096139210x</pre>
 * 
 * 
 * <p><b>URI, Hierarchical Absolute</b>.  Absolute: it defines an <i>scheme</i>.  Hierarchical: its <i>scheme-specific-part</i> begins with '/', so it can be further parsed.</p>
 * <p>Syntax:</p>
 * <pre>scheme:[//authority][/path][?query][#fragment]</pre>
 * <p>Examples:</p>
 * <pre>http://java.sun.com/j2se/1.3/
 *file:///~/calendar</pre>
 * 
 * 
 * <p><b>URI, Hierarchical Relative</b>.  Does not define an <i>scheme</i>, depends on a <i>URI, Hierarchical Absolute</i>.</p>
 * <p>Examples:</p>
 * <pre>docs/guide/collections/designfaq.html#28
 *../../../demo/jfc/SwingSet2/src/SwingSet2.java</pre>
 *
 * 
 * 
 * 
 * <h2>Syntax components:</h2>
 * <pre>scheme:[//authority][/path][?query][#fragment]</pre>
 * <ul>
 * <li><b>scheme</b> − for URLs, is the name of the protocol used to access the resource, for other URIs, is a name that refers to a specification for assigning identifiers within that scheme</li>
 * <li><b>authority</b> − an optional part comprised of user authentication information, a host and an optional port</li>
 * <li><b>path</b> − it serves to identify a resource within the scope of its scheme and authority</li>
 * <li><b>query</b> − additional data that, along with the path, serves to identify a resource. For URLs, this is the query string</li>
 * <li><b>fragment</b> − an optional identifier to a specific part of the resource</li>
 * </ul>
 * 
 * 
 * 
 * 
 * <h2>URL</h2>
 * <p>Valid URL protocols (schemes): <i>tp, http, https, gopher, mailto, news, nntp, telnet, wais, file, prospero</i>.</p>
 * <p>URL examples:</p>
 * <pre>mailto:johndoe@mail.com
 *ftp://ftp.is.co.za/rfc/rfc1808.txt
 *https://tools.ietf.org/html/rfc3986</pre>
 * 
 * 
 * <p>URI not URL examples:</p>
 * <pre>tel:+1-816-555-1212
 *urn:oasis:names:docbook:dtd:xml:4.1
 *urn:isbn:1234567890</pre>
 *
 *
 *
 *
 *<h2>Operations</h2>
 *<p>With URI: {@link URI#relativize(URI)}, {@link URI#resolve(URI)}</p>
 *<p>With URL: {@link URL#openStream()}, {@link URL#openConnection()}</p>
 * 
 * 
 * 
 * 
 * @since 2026-03-20
 * 
 */
public class Test00_UriAndUrl {


	public static void test00a_CreateUri () throws Throwable {

		System.out.println("--- test00a ---");

		URI uri01 = new URI("scheme://user:passw@host:80/pathVar01/pathVar02?query#fragment");

		URI uri02 = new URI(
				"scheme",
				"user:passw",
				"host",
				80,
				"/pathVar01/pathVar02",
				"query",
				"fragment");

		URI uri03 = URI.create("scheme://user:passw@host:80/pathVar01/pathVar02?query#fragment"); // if argument is not valid, does not throw checked-exception

		String sch01  = uri01.getScheme();
		String auth01 = uri01.getAuthority();
		String ui01   = uri01.getUserInfo();
		String host01 = uri01.getHost();
		String path01 = uri01.getPath();
		String qry01  = uri01.getQuery();
		String frag01 = uri01.getFragment();
		System.out.println("sch01:  " + sch01);
		System.out.println("auth01: " + auth01);
		System.out.println("ui01:   " + ui01);
		System.out.println("host01: " + host01);
		System.out.println("path01: " + path01);
		System.out.println("qry01:  " + qry01);
		System.out.println("frag01: " + frag01);

		boolean eqU12 = uri01.equals(uri02);
		boolean eqU23 = uri02.equals(uri03);
		System.out.println("uri01:  " + uri01);
		System.out.println("uri02:  " + uri02);
		System.out.println("uri03:  " + uri03);
		System.out.println("eqU12:  " + eqU12);
		System.out.println("eqU23:  " + eqU23);
	}



	public static void test00b_CreateUrl () throws Throwable {

		System.out.println("--- test00b ---");

		URL url01 = new URL("http://host:80/pathVar01/pathVar02?query#reference");

		URL url02 = new URL(
				"http",
				"host",
				80,
				"/pathVar01/pathVar02?query#reference");

		URL url03 = new URL("http://user:passw@host:80/pathVar01/pathVar02?query#reference");

		String prot01  = url01.getProtocol();
		String auth01 = url01.getAuthority();
		String ui01   = url01.getUserInfo();
		String host01 = url01.getHost();
		String path01 = url01.getPath();
		String qry01  = url01.getQuery();
		String ref01  = url01.getRef();
		System.out.println("prot01: " + prot01);
		System.out.println("auth01: " + auth01);
		System.out.println("ui01:   " + ui01);
		System.out.println("host01: " + host01);
		System.out.println("path01: " + path01);
		System.out.println("qry01:  " + qry01);
		System.out.println("ref01:  " + ref01);

		boolean eq12 = url01.equals(url02);
		boolean eq23 = url02.equals(url03);
		System.out.println("url01: " + url01);
		System.out.println("url02: " + url02);
		System.out.println("url03: " + url03);
		System.out.println("eq12:  " + eq12);
		System.out.println("eq23:  " + eq23);
	}


	public static void test01_UrlToUriAndViceversa() throws Throwable {

		System.out.println("--- test01 ---");

		// SUCCESS:

		URL url01 = new URL("http://host:80/pathVar01/pathVar02?query#refFrag");
		URI uri01 = url01.toURI();
		URL url01r = uri01.toURL();
		System.out.println("url01:  " + url01);
		System.out.println("uri01:  " + uri01);
		System.out.println("url01r: " + url01r);

		// FAIL:

		URI uri02 = new URI("scheme://user:passw@authority:80/pathVar01/pathVar02?query#refFrag");
		System.out.println("uri02:  " + uri02);
		try {
			URL url02r = uri02.toURL(); // MalformedUrlException thrown, unknown protocol ('scheme' nor valid URL protocol)
			System.out.println("url02r: " + url02r); // execution does not reach here
		} catch (Throwable ex) {
			System.err.println("ex.clazz: " + ex.getClass() + " ; ex.message: " + ex.getMessage());
		}
	}


	public static void test02_UriResolveRelativize() throws Throwable {

		System.out.println("--- test02 ---");

		// relativize SUCCESS:

		URI uri01 = new URI("http://host:80/pathVar01/pathVar02/");
		URI uri02 = new URI("http://host:80/pathVar01/pathVar02/images/image01.jpg");
		System.out.println("uri01: " + uri01);
		System.out.println("uri02: " + uri02);

		URI uri02relat = uri01.relativize(uri02);
		String uri02relatSch = uri02relat.getScheme();
		System.out.println("uri02relat:    " + uri02relat);
		System.out.println("uri02relatSch: " + uri02relatSch); // scheme is null, relativize successful
		URI uri02resol = uri01.resolve(uri02relat);
		System.out.println("uri02resol:    " + uri02resol);

		// relativize FAIL:

		URI uri03 = new URI("http://host:80/pathVar01/pathVar02/index.html");
		URI uri04 = new URI("http://host:80/pathVar01/pathVar02/images/image01.jpg");
		System.out.println("uri03: " + uri03);
		System.out.println("uri04: " + uri04);

		URI uri04relat = uri03.relativize(uri04);
		String uri04relatSch = uri04relat.getScheme();
		System.out.println("uri04relat:    " + uri04relat);
		System.out.println("uri04relatSch: " + uri04relatSch); // scheme has value, relativize not successful
		URI uri04resol = uri03.resolve(uri04relat); // uri04relat is not relative, since uri03.path is not prefix of uri04.pah
		System.out.println("uri04resol:    " + uri04resol);
	}


	public static void test03_UrlOpenStream() throws Throwable {

		System.out.println("--- test03 ---");

		URL url; // enable one value assignment below

		/*
		 * FAIL:
		 */
		// url = new URL("https://postman-echo.com/get"); // fail: java.io.IOException, server returned HTTP response code 403
		// url = new URL("file://tmp/numbers.txt"); // fail: java.net.UnknownHostException, tmp

		/*
		 * SUCCESS:
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
