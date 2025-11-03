package localhost.sandbox.jse8.ConcurrentMap;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * <p>A raw cache implementation example using {@link ConcurrentHashMap} at 
 * its core.
 * 
 * <p>{@link PersonRepository} represents some kind of data source (database, 
 * http client, other) which may be queried repeatedly, or which processing 
 * time / effort is significant.
 * 
 * <p>{@link PersonCacheService} contains cache implementation, to avoid repeated 
 * calls of {@link PersonRepository}.
 * 
 * <p>{@link PersonCacheService#doCopy(PersonModel)} may have to be adapted, in 
 * order to protect {@link ConcurrentHashMap} stored objects from undesired 
 * modifications at service layers different from {@link PersonRepository}.
 * 
 * <p>{@link PersonCacheService#clearPersonModelCache()} is currently the only 
 * existing method for clearing cache.  Other additional method, which allows 
 * a selection of keys to clear/remove, is also convenient (pending development).
 * 
 * 
 * @author Alberto Romero
 * @since 2025-11-03
 * 
 */
public class Test00_Cache {

	/**
	 * <p>See {@link Test00_Cache} comment.
	 */
	public static void test00_cache() {
		System.out.println("Hello from Test00_Cache.test00_cache()!");

		PersonCacheService personCacheService = new PersonCacheService();

		PersonModel pm00 = null;
		PersonModel pm01 = null;
		PersonModel pm02 = null;
		PersonModel pm03 = null;

		pm00 = personCacheService.getByName("Jose");
		pm01 = personCacheService.getByName("Jose");
		personCacheService.clearPersonModelCache();

		pm02 = personCacheService.getByName("Jose");
		pm03 = personCacheService.getByName("Jose");
		personCacheService.clearPersonModelCache();

		System.out.println("pm00: " + pm00);
		System.out.println("pm01: " + pm01);
		System.out.println("pm02: " + pm02);
		System.out.println("pm03: " + pm03);
	}



	private static class PersonModel {
		public String name;
		public Integer age;
		public Float height;
		public Boolean militaryActive;

		public PersonModel(String name, Integer age, Float height, Boolean militaryActive) {
			this.name = name;
			this.age = age;
			this.height = height;
			this.militaryActive = militaryActive;
		}

		public PersonModel(PersonModel p) {
			this.name = p.name;
			this.age = p.age;
			this.height = p.height;
			this.militaryActive = p.militaryActive;
		}

		@Override
		public String toString() {
			String out = "{ "
					+ "" + "name: " + this.name
					+ ", " + "age: " + this.age
					+ ", " + "height: " + this.height
					+ ", " + "militaryActive: " + this.militaryActive
					+ " }"
					;
			return out;
		}
	}



	private static class PersonRepository {

		private PersonModel p = new PersonModel("Jose", 31, 1.92f, false);

		public PersonModel getByName(String name) {
			return p;
		}

	}



	private static class PersonCacheService {

		private final ConcurrentMap<String,PersonModel> personModelCache = new ConcurrentHashMap<>();

		private PersonRepository personRepository = new PersonRepository();

		public PersonModel getByName(String name) {
			if (name == null || name.isEmpty()) {
				return null;
			}
			PersonModel result = null;
			PersonModel safeResult = null;
			String key = getKeyForPersonModelCache(name);
			result = personModelCache.get(key);
			if (result != null) {
				safeResult = doCopy(result);
				return safeResult;
			}
			result = personRepository.getByName(name);
			if (result != null) {
				personModelCache.put(name, result);
				safeResult = doCopy(result);
				return safeResult;
			}
			return null;
		}

		private String getKeyForPersonModelCache(String name) {
			if (name == null || name.isEmpty()) {
				return null;
			}
			return name;
		}

		private PersonModel doCopy(PersonModel p) {
			if (p == null) {
				return null;
			}
			PersonModel pCopy = new PersonModel(p);
			return pCopy;
		}

		public void clearPersonModelCache() {
			Set<String> keySet = personModelCache.keySet();
			for (String key : keySet) {
				personModelCache.remove(key);
			}
		}
	}

}
