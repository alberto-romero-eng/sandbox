package localhost.starter.Service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class DefaultService {

	private static Logger log = LoggerFactory.getLogger(DefaultService.class);

	// @Async
	public void defaultMethod01 () {
		log.info("Start default Method01 -- params -- (none)");
		// do something
		log.info("Finish default Method01 -- params -- (none) -- results -- (none)");
	}

}
