package localhost.cache.configuration;

public class Constant {

	public static class CacheName {

		public static final String CONTINENT = "continentSyncFalse";

		public static final String BRAND = "brandSyncTrue";

		public static final String PERSON_SYNC_FALSE = "personSyncFalse";

		public static final String PERSON_SYNC_TRUE = "PERSON_SYNC_TRUE";

	}

	public static enum CacheNameEnum {

		PERSON_SYNC_TRUE("personSyncTrue");

		private String cacheName;

		CacheNameEnum(String cacheName) {
			this.cacheName = cacheName;
		}

		public String cacheName() {
			return this.cacheName;
		}

	}

}
