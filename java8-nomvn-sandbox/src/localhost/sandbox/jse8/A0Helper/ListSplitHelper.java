package localhost.sandbox.jse8.A0Helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * <p>Class containing helping functions regarding {@link List} splitting.
 * 
 * <p>Updates:
 * <ul>
 * <li>2026-07-08: initial release.
 * </ul>
 * 
 * 
 * @author Alberto Romero
 * @since 2026-07-08
 * @version 2026-07-08
 * 
 */
public class ListSplitHelper {


	public static void main() {
		test01();
		test02();
		test03();
		test04();
	}


	public static <T> void testGeneral(String testName, List<T> inputList, int chunkSize) {
		System.out.println("--- " + testName + " ---");
		List<List<T>> outerList = LSH.split(inputList, chunkSize);
		String inputCsv = LSH.inputListToCsv(inputList);
		System.out.println("inputList: " + inputList);
		System.out.println("chunkSize: " + chunkSize);
		System.out.println("outerList: " + outerList);
		String outerCsv = LSH.outerListToCsvPlain(outerList);
		String outerCsvChunked = LSH.outerListToCsvChunked(outerList);
		System.out.println("inputCsv       : " + inputCsv);
		System.out.println("outerCsvPlain  : " + outerCsv);
		System.out.println("outerCsvChunked: " + outerCsvChunked);
		System.out.println("");
	}


	public static void test01() {
		String testName = "test01";
		List<Integer> inputList = new ArrayList<>();
		inputList.addAll(Arrays.asList(1,2,3,4,5,6,7,8,9,10,11,12,13,14,15));
		int chunkSize = 4;
		testGeneral(testName, inputList, chunkSize);
	}


	public static void test02() {
		String testName = "test02";
		List<Integer> inputList = new ArrayList<>();
		inputList.addAll(Arrays.asList());
		int chunkSize = 4;
		testGeneral(testName, inputList, chunkSize);
	}


	public static void test03() {
		String testName = "test03";
		List<Integer> inputList = new ArrayList<>();
		inputList.addAll(Arrays.asList(1));
		int chunkSize = 4;
		testGeneral(testName, inputList, chunkSize);
	}



	public static void test04() {
		String testName = "test04";
		List<Integer> inputList = null;
		int chunkSize = 4;
		testGeneral(testName, inputList, chunkSize);
	}




	/**
	 * <p>Main method of interest.
	 * 
	 * <p>Example:
	 * <ul>
	 * <li>inputList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
	 * <li>chunkSize: 4
	 * <li>returns: [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15]]
	 * </ul>
	 * 
	 */
	public static <T> List<List<T>> split(List<T> inputList, int chunkSize) {
		List<List<T>> outerList = new ArrayList<>();
		if (inputList == null || inputList.size() == 0) {
			return outerList;
		}
		outerList.add(new ArrayList<>());
		List<T> currentInnerList = outerList.get(outerList.size() - 1);
		for (T e : inputList) {
			currentInnerList.add(e);
			if (currentInnerList.size() >= chunkSize) {
				outerList.add(new ArrayList<>());
				currentInnerList = outerList.get(outerList.size() - 1);
			} else {
				// do nothing
			}
		}
		return outerList;
	}


	/**
	 * <p>Example:
	 * <ul>
	 * <li>inputList: [1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15]
	 * <li>returns: "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15"
	 * </ul>
	 * 
	 */
	public static <T> String inputListToCsv(List<T> inputList) {
		if (inputList == null) {
			return null;
		}
		String out = "";
		for (T e : inputList) {
			out += e + ",";
		}
		out = removeLastCharacter(out);
		return out;
	}


	/**
	 * <p>Example:
	 * <ul>
	 * <li>outerList: [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15]]
	 * <li>returns: "1,2,3,4,5,6,7,8,9,10,11,12,13,14,15"
	 * </ul>
	 * 
	 */
	public static <T> String outerListToCsvPlain(List<List<T>> outerList) {
		if (outerList == null) {
			return null;
		}
		String out = "";
		for (List<T> innerList : outerList) {
			for (T e : innerList) {
				out += e + ",";
			}
		}
		out = removeLastCharacter(out);
		return out;
	}


	/**
	 * <p>Example:
	 * <ul>
	 * <li>outerList: [[1, 2, 3, 4], [5, 6, 7, 8], [9, 10, 11, 12], [13, 14, 15]]
	 * <li>returns: "'1,2,3,4','5,6,7,8','9,10,11,12','13,14,15'"
	 * </ul>
	 * 
	 */
	public static <T> String outerListToCsvChunked(List<List<T>> outerList) {
		if (outerList == null) {
			return null;
		}
		String out = "";
		List<String> chunkList = new ArrayList<>();
		for (List<T> innerList : outerList) {
			String chunk = "";
			for (T e : innerList) {
				chunk += e + ",";
			}
			chunk = removeLastCharacter(chunk);
			chunk = "'" + chunk + "'";
			chunkList.add(chunk);
		}
		out = inputListToCsv(chunkList);
		return out;
	}


	private static String removeLastCharacter(String input) {
		if (input == null) {
			return null;
		}
		if (input.length() == 0) {
			return "";
		}
		return input.substring(0, input.length() - 1);
	}


	/**
	 * <p>Class to aid {@link ListSplitHelper} aliasing.
	 */
	public static class LSH extends ListSplitHelper {
	}

}
