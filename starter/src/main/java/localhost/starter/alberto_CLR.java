package localhost.starter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import localhost.starter.Service.DefaultService;

@Component
public class alberto_CLR implements CommandLineRunner{

	private static Logger log = LoggerFactory.getLogger(alberto_CLR.class);

	@Autowired
	DefaultService defaultService;

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

	@Value("${project.nameInnerRef}")
	private String projectNameInnerRef;

	@Override
	public void run(String... args) throws Exception {
		log.info("Hello from alberto_CLR!");
		log.info("spring.application.name: {}", springApplicationName);
		log.info("project.name: {} -- project.version: {} -- project.description: {} -- project.versionDate: {} -- project.versionComment: {}", projectName, projectVersion, projectDescription, projectVersionDate, projectVersionComment);
		log.info("project.nameInnerRef: {}", projectNameInnerRef);

		test01(args);
		test02();

		log.info("Bye from alberto_CLR!");
	}

	public void test01(String[] args) {
		log.info("Start test00()");
		log.info("spring.application.name: {}", springApplicationName);

		String argsStr = "'";
		int i = 0;

		for (i=0 ; i<args.length ; i++) {
			argsStr = argsStr + args[i] + "' ; '";
		}

		argsStr = argsStr.substring(0, argsStr.length() - 1);

		log.info("args: " +  args);
		log.info("argsStr: " +  argsStr);

	}

	public void test02() {
		defaultService.defaultMethod01();
	}

}
