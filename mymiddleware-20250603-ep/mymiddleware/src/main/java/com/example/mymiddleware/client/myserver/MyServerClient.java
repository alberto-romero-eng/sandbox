package com.example.mymiddleware.client.myserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.mymiddleware.service.PersonPojo;

@FeignClient(name = "myserver")
public interface MyServerClient {

	@GetMapping("/myserver/v1/person/{id}")
	public ResponseEntity<PersonPojo> getPersonById(@PathVariable Integer id) throws Exception;

}
