package localhost.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class CacheApplication {

	private static final Logger log = LoggerFactory.getLogger(CacheApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(CacheApplication.class, args);
	}

}
