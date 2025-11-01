package localhost.cache.configuration;

import org.springframework.cache.annotation.Cacheable;

public class CacheNameDefn {


	/**
	 * <p>Use of this enumeration is not mandatory, but it may be useful.
	 * 
	 * <p>Ensure a 1:1 mapping with values in {@link CacheName}, which 
	 * may be better suited for {@link Cacheable} annotations.
	 * 
	 */
	public static enum CacheNameE {
		CONTINENT_SYNC_FALSE(CacheName.CONTINENT_SYNC_FALSE, CacheImpl.SPRINGBOOT_CACHE),
		BRAND_SYNC_TRUE(CacheName.BRAND_SYNC_TRUE, CacheImpl.SPRINGBOOT_CACHE),
		PERSON_SYNC_FALSE(CacheName.PERSON_SYNC_FALSE, CacheImpl.SPRINGBOOT_CACHE),
		PERSON_SYNC_TRUE(CacheName.PERSON_SYNC_TRUE, CacheImpl.SPRINGBOOT_CACHE),
		SIMPLEST(CacheName.SIMPLEST, CacheImpl.SPRINGBOOT_CACHE);


		private CacheImpl cacheImpl;

		private CacheNameE(String name, CacheImpl cacheImpl) {
			if (!name.equals(this.name())) {
				throw new IllegalArgumentException();
			}
			this.cacheImpl = cacheImpl;
		}

		public CacheImpl cacheImpl() {
			return this.cacheImpl;
		}

	}


	/**
	 * <p>See {@link CacheNameE} comment.
	 * 
	 */
	public static class CacheName {
		public static final String CONTINENT_SYNC_FALSE = "CONTINENT_SYNC_FALSE";
		public static final String BRAND_SYNC_TRUE = "BRAND_SYNC_TRUE";
		public static final String PERSON_SYNC_FALSE = "PERSON_SYNC_FALSE";
		public static final String PERSON_SYNC_TRUE = "PERSON_SYNC_TRUE";
		public static final String SIMPLEST = "SIMPLEST";
	}



	public static enum CacheImpl {
		SYNCHRONIZED_MAP,
		CONCURRENT_MAP,
		SPRINGBOOT_CACHE
	}

}
