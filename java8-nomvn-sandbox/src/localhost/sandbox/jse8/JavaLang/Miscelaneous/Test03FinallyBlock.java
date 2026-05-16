package localhost.sandbox.jse8.JavaLang.Miscelaneous;

public class Test03FinallyBlock {

	/**
	 * Tests regarding <i>finally</i> block operation.
	 * 
	 * @author Alberto Romero
	 * @since 2026-05-16
	 */
	public static void main() {

		String result = null;
		// test00
		result = null;
		System.out.println("--- TEST00, begin ---");
		try {
			result = testFinally00();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		System.out.println("result: " + result);
		// test01
		result = null;
		System.out.println("--- TEST01, begin ---");
		try {
			result = testFinally01();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		System.out.println("result: " + result);
		// test02
		result = null;
		System.out.println("--- TEST02, begin ---");
		try {
			result = testFinally02();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		System.out.println("result: " + result);
		// test03
		result = null;
		System.out.println("--- TEST03, begin ---");
		try {
			result = testFinally03();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		System.out.println("result: " + result);
		// test04
		result = null;
		System.out.println("--- TEST04, begin ---");
		try {
			result = testFinally04();
		} catch (Exception e) {
			System.err.println("e: " + e);
		}
		System.out.println("result: " + result);

	}

	/**
	 * test00: try-block returns normal result, finally-block runs before execution of "return" clause.
	 */
	private static String testFinally00() throws Exception {
		String result = null;
		try {
			System.out.println("try block!");
			result = "result";
			return result; // executes after finally-block
		} catch (Exception e) {
			System.err.println("catch-block!");
			throw e;
		} finally {
			System.out.println("finally-block!");
			// return result; // warning: "finally does not complete regularly"
		}
	}

	/**
	 * test01: catch-block returns normal result; finally-block runs before execution of "return" clause.
	 */
	private static String testFinally01() throws Exception {
		String result = null;
		try {
			System.out.println("try-block!");
			result.codePointAt(4); // throws exception
			return result;
		} catch (Exception e) {
			System.err.println("catch-block!");
			result = "error";
			return result; // executes after finally-block
		} finally {
			System.out.println("finally-block!");
			// return result; // warning: "finally does not complete regularly"
		}
	}

	/**
	 * test02: try-block returns normal result, finally-block CANNOT override result; it seems "return" clause operates by value, not by reference.
	 */
	private static String testFinally02() throws Exception {
		String result = null;
		try {
			System.out.println("try block!");
			result = "result";
			return result; // executes after finally-block
		} catch (Exception e) {
			System.err.println("catch-block!");
			throw e;
		} finally {
			System.out.println("finally-block!");
			result = "finally-block-overriden-result";
			// return result; // warning: "finally does not complete regularly"
		}
	}

	/**
	 * test03: catch-block throws exception; finally-block runs before throwing exception.
	 */
	private static String testFinally03() throws Exception {
		String result = null;
		try {
			System.out.println("try-block!");
			result.codePointAt(4); // throws exception
			return result;
		} catch (Exception e) {
			System.err.println("catch-block!");
			throw e; // executes after finally-block
		} finally {
			System.out.println("finally-block!");
			// return result; // warning: "finally does not complete regularly"
		}
	}

	/**
	 * test04: catch-block throws exception; finally-block runs before throwing exception, and overrides it.
	 */
	private static String testFinally04() throws Exception {
		Exception ef = null;
		String result = null;
		try {
			System.out.println("try-block!");
			result.codePointAt(4); // throws exception
			return result;
		} catch (Exception e) {
			System.err.println("catch-block!");
			ef = e;
			throw e; // executes after finally-block
		} finally {
			System.out.println("finally-block!");
			if (ef != null) {
				throw new Exception("FinallyOverridenException");
			}
			// return result; // warning: "finally does not complete regularly"
		}
	}


}
