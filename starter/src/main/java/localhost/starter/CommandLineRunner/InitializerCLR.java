package localhost.starter.CommandLineRunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import localhost.starter.Service.DefaultService;


@Order(1)
@Component
public class InitializerCLR implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(InitializerCLR.class);

	@Autowired
	DefaultService asyncHelper;

	@Value("${spring.application.name}")
	private String springApplicationName;

	@Value("${project.name}")
	private String projectName;

	@Value("${project.version}")
	private String projectVersion;

	@Value("${project.description}")
	private String projectDescription;

	@Value("${project.versionDate}")
	private String projectVersionDate;

	@Value("${project.versionComment}")
	private String projectVersionComment;

	@Override
	public void run(String... args) throws Exception {
		log.info("Start run()");
		log.info("spring.application.name: {} -- project.name: {} -- project.version: {} -- project.description: {} -- project.versionDate: {} -- project.versionComment: {}", springApplicationName, projectName, projectVersion, projectDescription, projectVersionDate, projectVersionComment);
		initSomething();
		log.info("Finish run()");
	}

	public void initSomething() {
		log.info("Start initSomething() -- params -- (none)");
		log.info("spring.application.name: {}", springApplicationName);
		// init something
		log.info("Finish initSomething() -- params -- (none) -- results -- (none)");
	}

}
