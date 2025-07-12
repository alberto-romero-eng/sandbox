package localhost.cache.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


import localhost.cache.service.PersonCacheGatewayService;
import localhost.cache.service.InfoCacheService;


@Order(1)
@Component
public class InitializerCLR implements CommandLineRunner{

	@Autowired
	InfoCacheService infoCacheService;

	@Autowired
	PersonCacheGatewayService personCacheGatewayService;

	/**
	 * Whether {@link PersonCacheGatewayService} uses {@link Cacheable} implementation or not.
	 */
	@Value("${cache.person.enabled}")
	private boolean cachePersonEnabled;

	private static Logger log = LoggerFactory.getLogger(InitializerCLR.class);

	@Override
	public void run(String... args) throws Exception {
		initPersonCacheGatewayService();
		initInfoCacheServiceForCanada();
		log.info("Finish run()");
	}


	private void initPersonCacheGatewayService() {
		personCacheGatewayService.initialize(cachePersonEnabled, true);
		log.info("Finish initPersonCacheGatewayService()");
	}


	private void initInfoCacheServiceForCanada() {
		String continentForCanada = null;
		continentForCanada = infoCacheService.getContinentByCountryCacheSyncFalseUnlessResultNull("Canada");
		log.info("Finish initInfoCacheServiceForCanada() -- results -- continentForCanada: {}", continentForCanada);
	}

}
