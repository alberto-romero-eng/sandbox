package localhost.cache.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import localhost.cache.service.CacheGatewayService;
import localhost.cache.service.CacheService;


@Order(1)
@Component
public class InitializerCLR implements CommandLineRunner{

	@Autowired
	CacheService cacheService;

	@Autowired
	CacheGatewayService cacheGatewayService;

	/**
	 * Whether {@link CacheGatewayService} uses {@link Cacheable} implementation or not.
	 */
	@Value("${cache.person.enabled}")
	private boolean cachePersonEnabled;

	private static Logger log = LoggerFactory.getLogger(InitializerCLR.class);

	@Override
	public void run(String... args) throws Exception {
		initCacheGatewayService();
		// initCacheServiceForCanada();
		log.info("Finish run()");
	}


	private void initCacheGatewayService() {
		cacheGatewayService.initialize(cachePersonEnabled, true);
		log.info("Finish initCacheGatewayService()");
	}


	private void initCacheServiceForCanada() {
		String continentForCanada = null;
		continentForCanada = cacheService.getContinentByCountryCacheWithUnlessIsNull("Canada");
		log.info("Finish initCacheService() -- results -- continentForCanada: {}", continentForCanada);
	}

}
