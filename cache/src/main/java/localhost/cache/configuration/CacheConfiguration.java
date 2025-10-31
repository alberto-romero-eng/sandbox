package localhost.cache.configuration;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
// import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
// import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import localhost.cache.configuration.Constant.CacheName;
import localhost.cache.configuration.Constant.CacheNameEnum;

@Configuration
public class CacheConfiguration implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

	/**
	 * <p>Global CacheConfiguration(s).
	 * 
	 * <p>If <i>allowNullValues</i> is set to <i>false</i>, exception is thrown when method with {@link Cacheable} 
	 * annotation returns null.
	 */
	@Override
	public void customize(ConcurrentMapCacheManager cacheManager) {
		cacheManager.setCacheNames( Arrays.asList( CacheName.CONTINENT, 
				CacheName.BRAND, 
				CacheName.PERSON_SYNC_FALSE, 
				CacheNameEnum.PERSON_SYNC_TRUE.name() ));
		cacheManager.setAllowNullValues(Boolean.FALSE);
	}

	/**
	 * Spring's example
	 */
	/* @Bean
	CacheManager cacheManager() {
		CacheManager cacheManager = null;
		// CaffeineCacheManager cacheManager = null; // this pom.xml does not contain instance's dependency
		// cacheManager = new CaffeineCacheManager(); 
		// cacheManager.setCacheSpecification(.cacheManager(...);
		return cacheManager;
	} */

}
