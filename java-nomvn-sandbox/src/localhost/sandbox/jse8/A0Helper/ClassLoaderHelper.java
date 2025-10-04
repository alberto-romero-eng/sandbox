package localhost.sandbox.jse8.A0Helper;

import java.net.URL;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;


/**
 * <p>Class to aid {@link ClassLoader} operations.
 * 
 * @author Alberto Romero
 * @since 2025-10-04
 * 
 */
public class ClassLoaderHelper {

	public static void main() {

		test00ClassLoaderTypes();
		System.out.println("");

		test01GetResourceUrl();
		System.out.println("");

		test02LoadClass();
		System.out.println("");
	}



	/**
	 * <h1>ClassLoader types:</h1>
	 * 
	 * <ul>
	 * 
	 * <li><i>Bootstrap ClassLoader:</i> JVM built-in; is <i>null</i> Java object,
	 * since it's native code, and is parent to all ClassLoader instances.
	 * 
	 * <li><i>Platform ClassLoader:</i> Java SE classes, platform classes.
	 * 
	 * <li><i>System ClassLoader:</i> or application class loader, loads classes
	 * on application class path; its direct parent is <i>Platform Class Loader</i>. 
	 * 
	 * </ul>
	 * 
	 * <p>Since Java-17, class <i>ClassLoaders</i> provides easy access to the mentioned 
	 * {@link ClassLoader}s.
	 * 
	 * <p>A parent ClassLoader has visibility over its children's loaded classes,
	 * but not vice versa (check this, pending).
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-04
	 * 
	 */
	private static void test00ClassLoaderTypes() {
		System.out.println("Hello from test00ClassLoaderTypes!");

		// Bootstrap ClassLoader
		System.out.println("Bootstrap ClassLoader example -- Classloader of ArrayList.class: "
				+ ArrayList.class.getClassLoader());

		// Platform ClassLoader
		/* System.out.println("Platform Classloader example -- direct method: "
				+ ClassLoader.getPlatformClassLoader()); */ // probably Java-17
		System.out.println("Platform ClassLoader example -- Classloader of DriverManager.class: "
				+ DriverManager.class.getClassLoader());

		// System (Application) ClassLoader
		System.out.println("System (Application) Classloader example -- direct method: "
				+ ClassLoader.getSystemClassLoader());
		System.out.println("System (Application) Classloader example -- ClassLoader of this class: "
				+ ClassLoaderHelper.class.getClassLoader());
		System.out.println("System (Application) Classloader example -- ClassLoader of current thread: "
				+ Thread.currentThread().getContextClassLoader());
	}



	private static void test01GetResourceUrl() {
		System.out.println("Hello from test01GetResourceUrl!");
		// case 01
		String resourceToSearch01 = "localhost/sandbox/jse8/A0Helper/LargeStringHelper.class";
		ArrayList<URL> urlList01 = getResourceUrlList(resourceToSearch01);
		if (urlList01.size() > 0) {
			urlList01.forEach(System.out::println);
		} else {
			System.out.println("resource '" + resourceToSearch01 + "' not found");
		}

		// case 02
		String resourceToSearch02 = "localhost/sandbox/jse8/A0Helper/LargeStringHelper.java";
		ArrayList<URL> urlList02 = getResourceUrlList(resourceToSearch02);
		if (urlList02.size() > 0) {
			urlList02.forEach(System.out::println);
		} else {
			System.out.println("resource '" + resourceToSearch02 + "' not found");
		}
	}



	public static void test02LoadClass() {

		System.out.println("Hello from test02LoadClass!");

		// normal class, class in package
		String clazzToLoad = "localhost.sandbox.jse8.A0Helper.ByteHexHelper";
		Class<?> clazz = null;
		try {
			clazz = loadClass(clazzToLoad);
		} catch (Throwable ex) {
			System.out.println("clazz is null (no class loaded)");
		}
		if (clazz != null) {
			System.out.println("clazz.getName(): " + clazz.getName());
			System.out.println("clazz.getCanonicalName(): " + clazz.getCanonicalName());
		}

		// inner class, class into another class
		String innerClazzToLoad = "localhost.sandbox.jse8.A0Helper.LargeStringHelper$LSH";
		Class<?> innerClazz = null;;
		try {
			innerClazz = loadClass(innerClazzToLoad);
		} catch (Throwable ex) {
			System.err.println("innerClazz is null (no inner class loaded)");
		}
		if (innerClazz != null) {
			System.out.println("innerClazz.getName(): " + innerClazz.getName());
			System.out.println("innerClazz.getCanonicalName(): " + innerClazz.getCanonicalName());
		}
	}




	/**
	 * <p>Get URL of any file reachable from class path.
	 * 
	 * <p>Example of <i>resourceToSearch</i> (any file type is allowed):
	 * <ul>
	 * <li>"localhost/sandbox/jse8/A0Helper/LargeStringHelper.class"
	 * <li>"localhost/sandbox/jse8/A0Helper/LargeStringHelper.java"
	 * <li>"org/w3c/dom/Element.class"
	 * </ul>
	 * 
	 * <p>Note that different URLs may refer to resources having the same name, but not necessarily the same content.  If such 
	 * resources are <i>.class</i> files (Java byte code), compiler will complain accordingly.
	 * 
	 * <p>Example of URLs containing duplicated class (Java-17):
	 * <ul>
	 * <li>jrt:/java.xml/org/w3c/dom/Element.class
	 * <li>jar:file:/C:/Users/my-home-folder/.m2/repository/xml-apis/xml-apis/1.3.04/xml-apis-1.3.04.jar!/org/w3c/dom/Element.class
	 * </ul>
	 * 
	 * <p>Then, to locate dependency including jar with duplicated resource:
	 * <br>
	 * <code>mvn dependency:tree</code>
	 * 
	 * <p>Example of URL, simple file:
	 * <ul>
	 * <li>file:/C:/Users/my-home-folder/Desktop/my-java-root-folder/bin/localhost/helper/LargeStringHelper.class
	 * </ul>
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-04
	 * 
	 */
	public static Enumeration<URL> getResourceUrlEnumeration(String resourceToSearch) {

		if (resourceToSearch == null || resourceToSearch.isEmpty()) {
			System.err.println("resourceToSearch cannot be null nor empty -- returning null");
			return null;
		}

		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader(); // application's ClassLoader

		Enumeration<URL> urlEnum = null;

		try {
			urlEnum = sysClassLoader.getResources(resourceToSearch);
		} catch (Throwable ex) {
			System.err.println("exception -- " + ex);
		}

		return urlEnum;
	}




	/**
	 * See {@link #getResourceUrlEnumeration(String)}.
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-04
	 * 
	 */
	public static ArrayList<URL> getResourceUrlList(String resourceToSearch) {
		Enumeration<URL> urlEnum = getResourceUrlEnumeration(resourceToSearch);
		ArrayList<URL> urlList = Collections.list(urlEnum);
		return urlList;
	}




	/**
	 * <p>Import class dynamically, using System (Application) ClassLoader. 
	 * 
	 * <p>Argument must be a <i> binary class name</i>.  Examples:
	 * <ul>
	 * <li>"localhost.helper.LargeStringHelper" (class in package)
	 * <li>"localhost.helper.LargeStringHelper$LSH" (inner class - class in another class)
	 * </ul>
	 * 
	 * @author Alberto Romero
	 * @since 2025-10-04
	 */
	public static Class<?> loadClass(String clazzToLoad) throws Exception {
		ClassLoader sysClassLoader = ClassLoader.getSystemClassLoader(); // application's ClassLoader
		Class<?> clazz = null;;
		try {
			clazz = sysClassLoader.loadClass(clazzToLoad);
		} catch (ClassNotFoundException ex) {
			System.err.println("exception -- " + ex);
			throw ex;
		}
		return clazz;
	}


}
