package com.example.mymiddleware.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.example.mymiddleware.client.myserver.MyServerClient;
import com.example.mymiddleware.service.PersonService;



@Order(1)
@Component
public class InitializerCLR implements CommandLineRunner{

	@Autowired
	MyServerClient myserverClient;

	@Autowired
	PersonService personService;

	private static Logger log = LoggerFactory.getLogger(InitializerCLR.class);

	@Override
	public void run(String... args) throws Exception {
		initPersonService();
		log.info("Finish run()");
	}


	private void initPersonService() {
		personService.initialize(myserverClient, true);
		log.info("Finish initPersonService()");
	}

}
