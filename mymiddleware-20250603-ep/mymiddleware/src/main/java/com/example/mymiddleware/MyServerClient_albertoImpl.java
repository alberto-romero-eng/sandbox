package com.example.mymiddleware;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mymiddleware.client.myserver.MyServerClient;
import com.example.mymiddleware.service.PersonPojo;

@Component
public class MyServerClient_albertoImpl implements MyServerClient {

	private static Logger log = LoggerFactory.getLogger(MyServerClient_albertoImpl.class);

	public ResponseEntity<PersonPojo> getPersonById(@PathVariable Integer id) throws Exception {
		log.info("Start albertoImpl -- params -- id: {}", id);
		PersonPojo pp = new PersonPojo(id, "DummyPerson", 2.0f, true);
		ResponseEntity<PersonPojo> re = ResponseEntity.of(Optional.of(pp));
		log.info("Finish albertoImpl -- params -- id: {} -- results -- re: {}", id, re);
		return re;
	}
}
