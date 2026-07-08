package localhost.sandbox.jse8.CompareSort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

/**
 * <p>There are two strategies for comparison:
 * <ul>
 * <li>Using {@link Comparable}, as {@link String#compareTo(String)}.
 * <li>Using {@link Comparator}, as {@link String#CASE_INSENSITIVE_ORDER}.
 * </ul>
 * 
 * <p>There are also two corresponding strategies for sorting:
 * <ul>
 * <li>Using {@link Collections#sort(java.util.List)}
 * <li>Using {@link Collections#sort(java.util.List, Comparator)}
 * </ul>
 * 
 * <p>Frequently used types contain implementations of both comparison strategies:
 * <ul>
 * <li>{@link Integer}: {@link Integer#compareTo(Integer)}, {@link Integer#compare(int, int)}.
 * <li>{@link Double}: {@link Double#compareTo(Double)}, {@link Double#compare(double, double)}.
 * <li>{@link Boolean}: {@link Boolean#compareTo(Boolean)}, {@link Boolean#compare(boolean, boolean)}.
 * <li>{@link String}: {@link String#compareTo(String)}, {@link String#CASE_INSENSITIVE_ORDER}.
 * </ul>
 * 
 * <p>There are also other built-in comparison implementations:
 * <ul>
 * <li>{@link Integer#compareUnsigned(int, int)}
 * <li>{@link String#compareToIgnoreCase(String)}
 * </ul>
 * 
 * <p>Explore also other frequently used types: {@link Long}, {@link Float}.
 * 
 * <p>For examples of {@link Comparator} using different implementations, see {@link PersonPojo}.
 * 
 * <p>Updates:
 * <ul>
 * <li>2024-04-11: initial release.
 * <li>2026-07-08: enhance javadocs, enhance internal implementatons for comparison in {@link PersonPojo}.
 * </ul>
 * 
 * 
 * @author Alberto Romero
 * @since 2024-04-11
 * @version 2026-07-08
 */
public class Test00_CompareTo_Sort_Compare {

	/**
	 * <p>Types String, Integer, Double, have built-in implementations
	 * of "x.compareTo(y)" method.
	 * 
	 * @since 2024-04-11
	 */
	public static void test00_CompareTo() {
		System.out.println("Hello from test00_CompareTo!");

		// String, compareTo
		System.out.println(""
				+ "String -- " 
				+ "\"B\".compareTo(\"A\"): " + "B".compareTo("A") + " ; " 
				+ "\"B\".compareTo(\"C\"): " + "B".compareTo("C") + " ; " 
				+ "\"B\".compareTo(\"B\"): " + "B".compareTo("B") + " ; "
				);

		// String, compareToIgnoreCase
		System.out.println(""
				+ "String -- " 
				+ "\"B\".compareToIgnoreCase(\"A\"): " + "B".compareToIgnoreCase("A") + " ; " 
				+ "\"B\".compareToIgnoreCase(\"C\"): " + "B".compareToIgnoreCase("C") + " ; " 
				+ "\"B\".compareToIgnoreCase(\"B\"): " + "B".compareToIgnoreCase("B") + " ; "
				);

		// Integer
		System.out.println(""
				+ "Integer -- " 
				+ "((Integer) 2).compareTo(1): " + (((Integer) 2).compareTo(1)) + " ; " 
				+ "((Integer) 2).compareTo(3): " + (((Integer) 2).compareTo(3)) + " ; " 
				+ "((Integer) 2).compareTo(2): " + (((Integer) 2).compareTo(2)) + " ; "
				);

		// Double
		System.out.println(""
				+ "Double -- " 
				+ "((Double) 2.2).compareTo(1.1): " + (((Double) 2.2).compareTo(1.1)) + " ; " 
				+ "((Double) 2.2).compareTo(3.3): " + (((Double) 2.2).compareTo(3.3)) + " ; " 
				+ "((Double) 2.2).compareTo(2.2): " + (((Double) 2.2).compareTo(2.2)) + " ; "
				);
	}



	/**
	 * <p>Built-in implementations of "objX.compareTo(objY)" are used
	 * internally by "Collections.sort(Collection<T>)".
	 * 
	 * @since 2024-04-11
	 */
	public static void test01_Sort() {
		System.out.println("Hello from test01_Sort!");

		// String
		ArrayList<String> als = new ArrayList<>();
		als.add("1B");
		als.add("1C");
		als.add("00");
		als.addAll(Arrays.asList("1A", "000", "0"));
		System.out.println("String -- array, before sort: " + als);
		Collections.sort(als);
		System.out.println("String -- array, after sort: " + als);

		// Integer
		ArrayList<Integer> ali = new ArrayList<>();
		ali.add(6);
		ali.addAll(Arrays.asList(4, 1, 9));
		System.out.println("Integer -- array, before sort: " + ali);
		Collections.sort(ali);
		System.out.println("Integer -- array, after sort: " + ali);

		// Double
		ArrayList<Double> ald = new ArrayList<>();
		ald.add(7.19);
		ald.addAll(Arrays.asList(4.33, 2.45, 9.03));
		System.out.println("Double -- array, before sort: " + ald);
		Collections.sort(ald);
		System.out.println("Double -- array, after sort: " + ald);

		// Float
		ArrayList<Float> alf = new ArrayList<>();
		alf.add(7.19f);
		alf.addAll(Arrays.asList(4.33f, 2.45f, 9.03f));
		System.out.println("Float -- array, before sort: " + alf);
		Collections.sort(alf);
		System.out.println("Float -- array, after sort: " + alf);

	}



	/**
	 * <p>Explicit implementation of "objComparator.compare(objX,objY)" 
	 * method can be done using built-in implementations of 
	 * "objX.compareTo(objY)".
	 * 
	 * <p>This test proposes a way to statically define several 
	 * "compare()" methods, then conveniently using anonymous class 
	 * of {@link Comparator} interface making use of such static definitions.
	 * 
	 * @since 2024-04-11
	 */
	public static void test02_Sort_Compare_CompareTo_OnPojo_Anonymous() {
		System.out.println("Hello from test02_Sort_Compare_CompareTo_OnPojo_Anonymous!");

		// pojos
		PersonPojo pJosefa = new PersonPojo("Josefa", 80, 1.50);
		PersonPojo pNena = new PersonPojo("Nena", 63, 1.61);
		PersonPojo pDavid = new PersonPojo("David", 29, 1.77);
		PersonPojo pDiego = new PersonPojo("Diego", 33, 1.73);

		// String, surname
		ArrayList<PersonPojo> as = new ArrayList<>();
		as.add(pJosefa);
		as.addAll(Arrays.asList(pNena, pDavid, pDiego));
		System.out.println("PersonPojo -- array, before sort by surname: " + as);
		Collections.sort(as, new Comparator<PersonPojo>() {
			@Override
			public int compare(PersonPojo o1, PersonPojo o2) {
				return PersonPojo.compareBySurname(o1, o2);
			}
		});
		System.out.println("PersonPojo -- array, after sort by surname: " + as);

		// Integer, age
		ArrayList<PersonPojo> aa = new ArrayList<>();
		aa.add(pJosefa);
		aa.addAll(Arrays.asList(pNena, pDavid, pDiego));
		System.out.println("PersonPojo -- array, before sort by age: " + aa);
		Collections.sort(aa, new Comparator<PersonPojo>() {
			@Override
			public int compare(PersonPojo o1, PersonPojo o2) {
				return PersonPojo.compareByAge(o1, o2);
			}
		});
		System.out.println("PersonPojo -- array, after sort by age: " + aa);

		// Double, height
		ArrayList<PersonPojo> ah = new ArrayList<>();
		ah.add(pJosefa);
		ah.addAll(Arrays.asList(pNena, pDavid, pDiego));
		System.out.println("PersonPojo -- array, before sort by height: " + ah);
		Collections.sort(aa, new Comparator<PersonPojo>() {
			@Override
			public int compare(PersonPojo o1, PersonPojo o2) {
				return PersonPojo.compareByHeight(o1, o2);
			}
		});
		System.out.println("PersonPojo -- array, after sort by height: " + ah);

	}



	/**
	 * <p>Example for {@link Comparator}, with implementation 
	 * of {@link Comparator#compare(Object, Object)}, so it can be
	 * re-used multiple times.
	 * 
	 * <p>See also {@link String#CASE_INSENSITIVE_ORDER}.
	 * 
	 * @since 2024-12-23
	 */
	public static void test03_Sort_Compare_Separate() {
		System.out.println("Hello from test03_Sort_Compare_Separate!");

		// String, surname
		ArrayList<String> as = new ArrayList<>();
		as.add("abcdefghi");
		as.addAll(Arrays.asList("abc", "abcdef", "abcdefgh"));
		System.out.println("Array, before sort by length: " + as);
		Collections.sort(as, new MyStringComparatorByLength());
		System.out.println("Array, after sort by length: " + as);
	}



	/**
	 * <p>POJO for testing.
	 * 
	 * <p>Contains several comparison implementations which can be used with {@link Comparator}.
	 * 
	 * <p>See:
	 * <ul>
	 * <li>{@link Test00_CompareTo_Sort_Compare#test02_Sort_Compare_CompareTo_OnPojo_Anonymous()}
	 * <li>{@link Test00_CompareTo_Sort_Compare#test03_Sort_Compare_Separate()}
	 * </ul>
	 * 
	 */
	private static class PersonPojo {

		private String surname;
		private Integer age;
		private Double height;

		public PersonPojo(String surname, Integer age, Double height) {
			super();
			this.surname = surname;
			this.age = age;
			this.height = height;
		}

		public String getSurname() {
			return surname;
		}
		public void setSurname(String surname) {
			this.surname = surname;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		public Double getHeight() {
			return height;
		}
		public void setHeight(Double height) {
			this.height = height;
		}

		public String toString() {
			String out = ""
					+ "{ "
					+ "surname:" + this.surname + ", "
					+ "age:" + this.age + ", "
					+ "height:" + this.height + ""
					+ " }"
					;
			return out;
		}

		public static int compareBySurname(PersonPojo o1, PersonPojo o2) {
			// definitions for null values, main objects
			if ((o1 == null) && (o2 == null))
			{
				return 0;
			}
			else if ((o1 == null) && (o2 != null))
			{
				return -1;
			}
			else if ((o1 != null) && (o2 == null))
			{
				return +1;
			}
			// definitions for null values, target attributes, surname
			if ((o1.getSurname() == null) && (o2.getSurname() == null))
			{
				return 0;
			}
			else if ((o1.getSurname() == null) && (o2.getSurname() != null))
			{
				return -1;
			}
			else if ((o1.getSurname() != null) && (o2.getSurname() == null))
			{
				return +1;
			}
			// actual values definition
			else
			{
				return o1.getSurname().compareTo(o2.getSurname());
			}
		}

		public static int compareByAge(PersonPojo o1, PersonPojo o2) {
			// definitions for null values, main objects
			if ((o1 == null) && (o2 == null)) 
			{
				return 0;
			}
			else if ((o1 == null) && (o2 != null)) 
			{
				return -1;
			}
			else if ((o1 != null) && (o2 == null))
			{
				return +1;
			}
			// definitions for null values, target attributes, age
			if ((o1.getAge() == null) && (o2.getAge() == null))
			{
				return 0;
			}
			else if ((o1.getAge() == null) && (o2.getAge() != null))
			{
				return -1;
			}
			else if ((o1.getAge() != null) && (o2.getAge() == null))
			{
				return +1;
			}
			// actual values definition
			else
			{
				// return Integer.valueOf(o1.getAge()).compareTo(Integer.valueOf(o2.getAge())); // if age is primitive 'int'
				return o1.getAge().compareTo(o2.getAge());
			}
		}

		public static int compareByHeight(PersonPojo o1, PersonPojo o2) {
			// definitions for null values, main objects
			if ((o1 == null) && (o2 == null))
			{
				return 0;
			}
			else if ((o1 == null) && (o2 != null))
			{
				return -1;
			}
			else if ((o1 != null) && (o2 == null))
			{
				return +1;
			}
			// definitions for null values, target attributes, height
			if ((o1.getHeight() == null) && (o2.getHeight() == null))
			{
				return 0;
			}
			else if ((o1.getHeight() == null) && (o2.getHeight() != null))
			{
				return -1;
			}
			else if ((o1.getHeight() != null) && (o2.getHeight() == null))
			{
				return +1;
			}
			// actual values definition
			else
			{
				// return Double.valueOf(o1.getHeight()).compareTo(Double.valueOf(o2.getHeight())); // if height is primitive 'double'
				return o1.getHeight().compareTo(o2.getHeight());
			}
		}

	}



	/**
	 * <p> Example of {@link Comparator} implementation.
	 * 
	 * @since 2024-12-23
	 * 
	 */
	public static class MyStringComparatorByLength implements Comparator<String> {

		public MyStringComparatorByLength() {
			super();
		}

		public int compare(String s1, String s2) {
			// definitions for null values, main objects
			if (s1 == null && s2 == null) {
				return 0;
			}
			else if (s1 != null && s2 == null) {
				return 1;
			}
			else if (s1 == null && s2!= null) {
				return -1;
			}
			return s1.length() - s2.length();
		}
	}

}
