package com.example.mymiddleware.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.mymiddleware.client.myserver.MyServerClient;

@Service
public class PersonService {

	private static Logger log = LoggerFactory.getLogger(PersonService.class);

	private static boolean initialized = false;

	@Autowired
	private MyServerClient myserverClient;

	public ResponseEntity<PersonPojo> getById(Integer id) {
		waitForInitialization();
		ResponseEntity<PersonPojo> re = null;
		try {
			re = myserverClient.getPersonById(id);
		} catch (Exception ex) {
			re = ResponseEntity.of(Optional.empty());
		}
		log.info("Finish getPerson() -- params -- id: {} -- results -- re: {}", id, re);
		return re;
	}


	public void initialize(MyServerClient myserverClient, boolean initialized) {
		this.myserverClient = myserverClient;
		PersonService.initialized = initialized;
	}



	/**
	 * This method should NOT be syhchronized!
	 */
	public static void waitForInitialization() {
		while (!initialized) {
			try {
				Thread.sleep(5L * 1000L);
			} catch (Exception e) {
				log.error("waitForInitialization() -- Exception ", e);
			}
		}
	}

}
