package com.example.myclient.client.mymiddleware;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myclient.service.PersonService.PersonPojo;


@FeignClient(name = "mymiddleware")
public interface MyMiddlewareClient {

	@GetMapping("/mymiddleware/v1/person/{id}")
	ResponseEntity<PersonPojo> getPersonById(@PathVariable Integer id) throws Exception;

}
