package localhost.sandbox.jse8.Enum;

public class Test00Enum {

	static public void test00_SimplestEnum() {
		System.out.println("Start test00_SimplestEnum");

		Animal cat = Animal.valueOf("CAT");
		System.out.println("cat: " + cat + " ; cat.name(): " + cat.name() + " ; cat.ordinal(): " + cat.ordinal());

		Animal dog = Enum.valueOf(Animal.class, "DOG");
		System.out.println("dog: " + dog + " ; dog.name(): " + dog.name() + " ; dog.ordinal(): " + dog.ordinal());

		try {
			Animal fish = Enum.valueOf(Animal.class, "_fish_");
			System.out.println("fish: " + fish + " ; fish.name(): " + fish.name() + " ; fish.ordinal(): " + fish.ordinal());

		} catch (Throwable ex) {
			System.err.println("error -- ex.getClass(): " + ex.getClass() + " -- ex.getMessage(): " + ex.getMessage());
		}

		Animal bird = Animal.valueOf(Animal.class, "BIRD");
		System.out.println("bird: " + bird + " ; bird.name(): " + bird.name() + " ; bird.ordinal(): " + bird.ordinal());

		System.out.println("Animal.values(): " + Animal.values());
		for (Animal a : Animal.values()) {
			System.out.println("animal: " + a);
		}
	}



	static public void test01_SimpleEnumCaseInsensitive() {
		System.out.println("Start test01_SimpleEnumCaseInsensitive");

		Animal cat = Animal.from("CAT");
		System.out.println("cat: " + cat + " ; cat.name(): " + cat.name() + " ; cat.ordinal(): " + cat.ordinal());

		Animal dog = Animal.from("dog");
		System.out.println("dog: " + dog + " ; dog.name(): " + dog.name() + " ; dog.ordinal(): " + dog.ordinal());

		try {
			Animal fish = Animal.from("_fish_");
			System.out.println("fish: " + fish + " ; fish.name(): " + fish.name() + " ; fish.ordinal(): " + fish.ordinal());
		} catch (Throwable ex) {
			System.err.println("error -- ex.getClass(): " + ex.getClass() + " -- ex.getMessage(): " + ex.getMessage());
		}

		Animal bird = Animal.from("Ird");
		System.out.println("bird: " + bird + " ; bird.name(): " + bird.name() + " ; bird.ordinal(): " + bird.ordinal());

		System.out.println("AnimalIns.values(): " + Animal.values());
		for (Animal ai : Animal.values()) {
			System.out.println("animalIns: " + ai);
		}
	}



	/**
	 * <p>Of interest: read java-source of {@link String#equals(Object)} and {@link Enum#equals(Object)}.
	 */
	static public void test02_EnumEquals() {
		System.out.println("Start test02_EnumEquals");

		String catStr = "CAT";
		Animal catE = Animal.valueOf(catStr);
		System.out.println("catE: " + catE + " ; cat.name(): " + catE.name() + " ; cat.ordinal(): " + catE.ordinal());

		Animal dogE = Enum.valueOf(Animal.class, "DOG");
		System.out.println("dog: " + dogE + " ; dog.name(): " + dogE.name() + " ; dog.ordinal(): " + dogE.ordinal());


		// equals
		boolean stringToStringEqualsEquals = catStr == "CAT";
		System.out.println("stringToStringEqualsEquals: " + stringToStringEqualsEquals);
		boolean stringToStringEqualsMethod = "CAT".equals(catStr);
		System.out.println("stringToStringEqualsMethod: " + stringToStringEqualsMethod);

		boolean enumToEnumEqualsEquals = Animal.CAT == catE;
		System.out.println("enumToEnumEqualsEquals: " + enumToEnumEqualsEquals);
		boolean enumToEnumEqualsMethod = Animal.CAT.equals(catE);
		System.out.println("enumToEnumEqualsMethod: " + enumToEnumEqualsMethod);
		boolean enumToStringEqualsMethod = Animal.CAT.equals(catStr);
		System.out.println("enumToStringEqualsMethod: " + enumToStringEqualsMethod);
	}



	static public void test03_EnumWithSeveralAttributes() {
		System.out.println("Start test02_EnumWithSeveralAttributes");

		CarBrand ford = CarBrand.from("ford");
		System.out.println("ford: " + ford);

		CarBrand chevy = CarBrand.valueOf("CHEVY");
		System.out.println("chevy" + chevy);
	}



	/*
	 * Enums
	 */

	private enum Animal {
		CAT,
		DOG,
		FISH,
		BIRD;


		/**
		 * <p> Method {@link java.lang.Enum#valueOf(String)} cannot be overridden.
		 */
		public static Animal from(String input) {
			for (Animal ai : Animal.values()) {
				if (ai.name().matches("(?i)^.*" + input.toLowerCase() + ".*$")) {
					return ai;
				}
			}
			return null;
		}
	}



	/**
	 * <p> Attributes 'name' and 'ordinal' are incorporated, 
	 * even if not declared explicitly.
	 */
	private enum CarBrand {

		FORD("FORD", 2001, false),
		CHEVY("CHEVY", 2009, false),
		AUDI("AUDI", 2016, true);

		private String brand;

		private Integer year;

		private Boolean superCar;

		CarBrand(String brand, Integer year, Boolean superCar) {
			this.brand = brand;
			this.year = year;
			this.superCar = superCar;
		}

		public String brand() {
			return this.brand;
		}

		public Integer year() {
			return this.year;
		}

		public Boolean superCar() {
			return this.superCar;
		}

		@Override
		public String toString() {
			String out = "{ "
					+ "" + "brand: " + this.brand
					+ ", " + "year: " + this.year
					+ ", " + "superCar: " + this.superCar
					+ " }" ;
			return out;

		}

		/**
		 * <p> Method {@link java.lang.Enum#valueOf(String)} cannot be overridden.
		 */
		public static CarBrand from(String input) {
			for (CarBrand cb : CarBrand.values()) {
				if ( cb.brand.matches("(?i)^.*" + input + ".*$") || cb.year.toString().matches("(?i)^.*" + input + ".*$") ) {
					return cb;
				}
			}
			return null;
		}
	}

}
