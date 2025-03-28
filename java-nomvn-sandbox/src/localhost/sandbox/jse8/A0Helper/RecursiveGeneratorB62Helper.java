package localhost.sandbox.jse8.A0Helper;

import java.util.ArrayList;
import java.util.List;

import localhost.sandbox.jse8.A0Helper.ExecTimeSecsHelper.ETSH;

/**
 * <p>Simple <i>ArrayList<String></i> recursive generator 
 * using <i>Base62</i> source.
 * 
 * <p>Functions {@link #generate(int,int)}, {@link #generate(int)} are 
 * the real ones of interest.
 * 
 * <p>Function {@link #testPrint()} is mainly to observe lag between 
 * end of processing and printing.
 * 
 * @since 2025-03-28
 * @author Alberto Romero
 */
public class RecursiveGeneratorB62Helper {


	private static final String[] SRC = { 
			"0", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"A", "B", "C", "D", "E", "F", "G", "H", "I", "J", 
			"K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", 
			"U", "V", "W", "X", "Y", "Z", 
			"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", 
			"k", "l", "m", "n", "o", "p", "q", "r", "s", "t", 
			"u", "v", "w", "x", "y", "z"
	};


	/**
	 * <p>Suggested value: <i>1_000</i>.
	 * 
	 * <p>Use <i>1</i> to print all lines. 
	 * 
	 * @since 2025-03-28
	 * @author Alberto Romero
	 */
	private static final int LINE_INTERVAL = 1_000;



	/**
	 * <p>Interesting note regarding test: with argument <i>nLoops => 3</i> and
	 * using <i>LINE_INTERVAL == 1</i>, <i>execTimeSecs</i> is significantly lower 
	 * than actual time of printing of corresponding last line.
	 * 
	 * @since 2025-03-28
	 * @author Alberto Romero
	 */
	public static void main() {
		System.out.println("RecursiveB62Helper -- main()");
		testPrint();
		return;
	}



	/**
	 * <p>Interesting note regarding test: with argument <i>nLoops => 3</i> and
	 * using <i>LINE_INTERVAL == 1</i>, <i>execTimeSecs</i> is significantly lower 
	 * than actual time of printing of corresponding last line.
	 * 
	 * @since 2025-03-28
	 * @author Alberto Romero
	 */
	public static void testPrint() {
		System.out.println("RecursiveB62Helper -- testPrint()");
		ETSH etsh = ETSH.init();
		int i;
		int nLoops = 3;
		int maxSize = 1_100;

		/**
		 * using maxSize
		 */
		System.out.println("using maxSize:");
		List<String> resultList1 = generate(maxSize, nLoops);
		String[] resultArray1 = resultList1.toArray(new String[] {});
		i = 0;
		for (String s : resultArray1) {
			if ((i % LINE_INTERVAL) == 0) {
				System.out.println(i + ": " + s);
			}
			i++;
		}
		System.out.println("n: " + resultArray1.length + " ; execTimeSecs: " + etsh.get());

		/**
		 * not using maxSize
		 */
		System.out.println("not using maxSize:");
		List<String> resultList2 = generate(nLoops);
		String[] resultArray2 = resultList2.toArray(new String[] {});
		i = 0;
		for (String s : resultArray2) {
			if ((i % LINE_INTERVAL) == 0) {
				System.out.println(i + ": " + s);
			}
			i++;
		}
		System.out.println("n: " + resultArray2.length + " ; execTimeSecs: " + etsh.get());

		/**
		 * return
		 */
		return;
	}



	/**
	 * <p>Result size reference, for several <i>nLoops</i> values:
	 * <ul>
	 * <li>1 : 62
	 * <li>2 : 3_844
	 * <li>3 : 238_328
	 * <li>4 : 14_776_336
	 * <li>5 : 916_132_832
	 * <li>6 : 56_800_235_584
	 * <li>7 : 3_521_614_606_208
	 * <li>8 : 218_340_105_584_896
	 * </ul>
	 * 
	 * 
	 * @since 2025-03-28
	 * @author Alberto Romero
	 */
	public static List<String> generate(int maxSize, int nLoops) {
		List<String> target = new ArrayList<String>();
		loopManage(maxSize, nLoops, nLoops, target, null);
		return target;
	}



	/**
	 * <p>See {@link #generate(int, int)}.
	 * 
	 * @since 2025-03-28
	 * @author Alberto Romero
	 */
	public static List<String> generate(int nLoops) {
		List<String> target = new ArrayList<String>();
		loopManage(Integer.MAX_VALUE, nLoops, nLoops, target, null);
		return target;
	}



	private static void loopManage(int maxSize, int maxIxLoop, int currIxLoop, List<String> target, String prevContrib) {
		if (currIxLoop == maxIxLoop) {
			for (String s : SRC) {
				loopManage(maxSize, maxIxLoop, currIxLoop - 1, target, s);
				if (target.size() > maxSize) return;
			}
		}
		else if (currIxLoop < maxIxLoop && currIxLoop > 1) {
			for (String s : SRC) {
				loopManage(maxSize, maxIxLoop, currIxLoop - 1, target, prevContrib + s);
				if (target.size() > maxSize) return;
			}
		}
		else if (currIxLoop == 1) {
			for (String s : SRC) {
				target.add(prevContrib + s);
				if (target.size() > maxSize) return;
			}
		}
	}

}
