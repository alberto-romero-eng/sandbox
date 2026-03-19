package localhost.sandbox.jse8.A0Helper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>For two collections, <i>left</i> and <i>right</i>,
 * this class provides a function to obtain <i>leftOuterJoin</i>,
 * <i>innerJoin</i> and <i>rightOuterJoin</i> collections.
 * 
 * <p>If <i>E</i> extends auto-boxing types (String, Boolean, Integer, 
 * Long, Float, Double), no extra definitions are required.  
 * For other types, it is necessary to define overriding 
 * functions {@link Object#hashCode()} and {@link Object#equals(Object)}  
 * for <i>E</i> objects.
 * 
 * <p>It is also very useful for troubleshooting if type <i>E</i> and 
 * all of its attributes types have a convenient definition of 
 * {@link Object#toString()}.
 * 
 * <p>Different implementations of {@link Collection} may be used as 
 * parameters (e.g.: {@link List}, {@link Set}).
 * 
 * <p>Updated: 2025-05-09.
 * 
 * @author Alberto Romero
 * @since 2024-05-30
 * 
 */
public class JoinHelper {

	public static void main() throws Throwable {
		System.out.println("Hello from JoinHelper!");
		testForList();
		testForSet();
		testForListAndSet();
	}

	private static void testForList() throws Throwable {
		System.out.println("Hello from testForList!");
		// left
		List<String> left = new ArrayList<>();
		left.add("left00");
		left.add("left01");
		left.add("inner00");
		left.add("inner01");
		// right
		List<String> right = new ArrayList<>();
		right.add("inner00");
		right.add("inner01");
		right.add("right00");
		right.add("right01");
		// result
		JoinResultPojo<String> jr = null;
		jr = getJoinResult(left, right);
		System.out.println("JoinResult.toString(): " + jr);
		System.out.println("JoinResult.toStringLarge(): " + jr.toStringLarge());
	}

	private static void testForSet() throws Throwable {
		System.out.println("Hello from testForList!");
		// left
		Set<String> left = new HashSet<>();
		left.add("left00");
		left.add("left01");
		left.add("inner00");
		left.add("inner01");
		// right
		Set<String> right = new HashSet<>();
		right.add("inner00");
		right.add("inner01");
		right.add("right00");
		right.add("right01");
		// result
		JoinResultPojo<String> jr = null;
		jr = getJoinResult(left, right);
		System.out.println("JoinResult.toString(): " + jr);
		System.out.println("JoinResult.toStringLarge(): " + jr.toStringLarge());
	}

	private static void testForListAndSet() throws Throwable {
		System.out.println("Hello from testForLisAndSett!");
		// left
		List<String> left = new ArrayList<>();
		left.add("left00");
		left.add("left01");
		left.add("inner00");
		left.add("inner01");
		// right
		Set<String> right = new LinkedHashSet<>();
		right.add("inner00");
		right.add("inner01");
		right.add("right00");
		right.add("right01");
		// result
		JoinResultPojo<String> jr = null;
		jr = getJoinResult(left, right);
		System.out.println("JoinResult.toString(): " + jr);
		System.out.println("JoinResult.toStringLarge(): " + jr.toStringLarge());
	}




	/**
	 * <p>See main comment on this class.
	 * 
	 * <p>Updated: 2025-05-09.
	 * 
	 * @author Alberto Romero
	 * @since 2024-05-30
	 * 
	 */
	public static <E> JoinResultPojo<E> getJoinResult(Collection<E> left, Collection<E> right) throws Throwable {
		// vars
		Collection<E> leftOuterJoin = new ArrayList<>();
		Collection<E> innerJoin = new ArrayList<>();
		Collection<E> rightOuterJoin = new ArrayList<>();
		JoinResultPojo<E> joinResult = new JoinResultPojo<E>();
		// innerJoin
		innerJoin.addAll(left); 
		innerJoin.retainAll(right);
		// leftOuterJoin
		for (E e : left) {
			if (!innerJoin.contains(e)) leftOuterJoin.add(e);
		}
		// rightOuterJoin
		for (E e : right) {
			if (!innerJoin.contains(e)) rightOuterJoin.add(e);
		}
		// joinResult
		joinResult.setLeft(left);
		joinResult.setRight(right);
		joinResult.setLeftOuterJoin(leftOuterJoin);
		joinResult.setInnerJoin(innerJoin);
		joinResult.setRightOuterJoin(rightOuterJoin);
		return joinResult;
	}


	public static class JoinResultPojo<E> {

		Collection<E> left;
		Collection<E> right;
		Collection<E> leftOuterJoin;
		Collection<E> innerJoin;
		Collection<E> rightOuterJoin;

		public JoinResultPojo () {
			super();
		}

		public Collection<E> getLeft() {
			return left;
		}

		public void setLeft(Collection<E> left) {
			this.left = left;
		}

		public Collection<E> getRight() {
			return right;
		}

		public void setRight(Collection<E> right) {
			this.right = right;
		}

		public Collection<E> getLeftOuterJoin() {
			return leftOuterJoin;
		}

		public void setLeftOuterJoin(Collection<E> leftOuterJoin) {
			this.leftOuterJoin = leftOuterJoin;
		}

		public Collection<E> getRightOuterJoin() {
			return rightOuterJoin;
		}

		public void setRightOuterJoin(Collection<E> rightOuterJoin) {
			this.rightOuterJoin = rightOuterJoin;
		}

		public Collection<E> getInnerJoin() {
			return innerJoin;
		}

		public void setInnerJoin(Collection<E> innerJoin) {
			this.innerJoin = innerJoin;
		}

		public String toString() {
			String out = "{ "
					+ "" + "left: " + this.left.size()
					+ ", " + "right: " + this.right.size()
					+ ", " + "leftOuterJoin: " + this.leftOuterJoin.size()
					+ ", " + "innerJoin: " + this.innerJoin.size()
					+ ", " + "rightOuterJoin: " + this.rightOuterJoin.size()
					+ " }"
					;
			return out;
		}

		public String toStringLarge() {
			String out = "{ "
					+ "" + "left: " + this.left
					+ ", " + "right: " + this.right
					+ ", " + "leftOuterJoin: " + this.leftOuterJoin
					+ ", " + "innerJoin: " + this.innerJoin
					+ ", " + "rightOuterJoin: " + this.rightOuterJoin
					+ " }"
					;
			return out;
		}
	}
}
