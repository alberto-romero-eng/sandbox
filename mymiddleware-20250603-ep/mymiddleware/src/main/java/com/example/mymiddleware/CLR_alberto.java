package com.example.mymiddleware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.example.mymiddleware.client.myserver.MyServerClient;
import com.example.mymiddleware.service.PersonPojo;
import com.example.mymiddleware.service.PersonService;


@Component
public class CLR_alberto implements CommandLineRunner /**/ {

	private static Logger log = LoggerFactory.getLogger(CLR_alberto.class);

	// @Value("${alberto.check}")
	// private String albertoCheck;

	@Value("${spring.application.name}")
	private String springApplicationName;

	@Value("${server.port}")
	private String serverPort;

	@Autowired
	PersonService personService;

	@Autowired
	MyServerClient myserverClient;

	@Autowired
	MyServerClient_albertoImpl myserverAlbertoClient;



	/**/
	@Override
	public void run(String... args) throws Exception {
		main(args);
	}


	public void main(String[] args) {

		log.info("hello world from CLR_alberto!");
		log.info("spring.application.name: {}", springApplicationName);

		// tests
		// testAlberto01(); // TODO: disable for test 1 (all services) ; enable for test 2 (focus on mymiddleware).
	}

	private void testAlberto01() {

		personService.initialize(myserverAlbertoClient, true);

		Integer id = 25;
		ResponseEntity<PersonPojo> re = null;

		try {
			re = personService.getById(id);
		} catch (Exception ex) {
			log.error("exception: {}", ex);
		}

		log.info("Finish -- results -- re: {}", re);
	}

}
