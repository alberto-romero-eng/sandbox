package localhost.cache.configuration;

import java.util.Arrays;

import org.springframework.boot.autoconfigure.cache.CacheManagerCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CacheConfiguration implements CacheManagerCustomizer<ConcurrentMapCacheManager> {

	/**
	 * Actual cache-configuration in wm-pm.
	 */
	@Override
	public void customize(ConcurrentMapCacheManager cacheManager) {
		cacheManager.setCacheNames(Arrays.asList("continent", "brand"));
		cacheManager.setAllowNullValues(Boolean.TRUE);
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
