package localhost.cache.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import localhost.cache.service.CacheService;


@Order(1)
@Component
public class InitializerCLR implements CommandLineRunner{

	@Autowired
	CacheService cacheService;


	private static Logger log = LoggerFactory.getLogger(InitializerCLR.class);

	@Override
	public void run(String... args) throws Exception {
		// initCacheServiceForCanada();
		log.info("Finish run()");
	}


	private void initCacheServiceForCanada() {
		String continentForCanada = null;
		continentForCanada = cacheService.getContinentByCountryCacheWithUnlessIsNull("Canada");
		log.info("Finish initCacheService() -- results -- continentForCanada: {}", continentForCanada);
	}

}
