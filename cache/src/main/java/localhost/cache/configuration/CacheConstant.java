package localhost.cache.configuration;

import org.springframework.cache.annotation.Cacheable;

public class CacheConstant {


	/**
	 * <p>Use of this enumeration is not mandatory, but it may be useful.
	 * 
	 * <p>Ensure a 1:1 mapping with values in {@link CacheNameStr}, which 
	 * may be better suited for {@link Cacheable} annotations.
	 * 
	 */
	public static enum CacheNameEnum {
		CONTINENT_SYNC_FALSE(CacheNameStr.CONTINENT_SYNC_FALSE),
		BRAND_SYNC_TRUE(CacheNameStr.BRAND_SYNC_TRUE),
		PERSON_SYNC_FALSE(CacheNameStr.PERSON_SYNC_FALSE),
		PERSON_SYNC_TRUE(CacheNameStr.PERSON_SYNC_TRUE),
		SIMPLEST(CacheNameStr.SIMPLEST);

		private CacheNameEnum(String name) {
			if (!name.equals(this.name())) {
				throw new IllegalArgumentException();
			}
		}
	}


	/**
	 * <p>See {@link CacheNameEnum} comment.
	 * 
	 */
	public static class CacheNameStr {
		public static final String CONTINENT_SYNC_FALSE = "CONTINENT_SYNC_FALSE";
		public static final String BRAND_SYNC_TRUE = "BRAND_SYNC_TRUE";
		public static final String PERSON_SYNC_FALSE = "PERSON_SYNC_FALSE";
		public static final String PERSON_SYNC_TRUE = "PERSON_SYNC_TRUE";
		public static final String SIMPLEST = "SIMPLEST";
	}

}
