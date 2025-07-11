package com.example.myclient.feignclient.myserver;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myclient.service.PersonService.PersonPojo;


@FeignClient(name = "myserver", url = "${myserver.url}" /*, url = "http://localhost:4000"*/ )
public interface MyServerClient {

	@GetMapping("/myserver/v1/person/{id}")
	ResponseEntity<PersonPojo> getPersonById(@PathVariable Integer id) throws Throwable;

	@GetMapping("/v1/appTools/public/doSleep/sleepSecs/{sleepSecs}")
	public ResponseEntity<Void> callDoSleep(@PathVariable Long sleepSecs) throws Throwable;

	@GetMapping("/v1/appTools/public/doSleep/sleepMillisecs/{sleepMillisecs}/reStatusCode/{reStatusCode}")
	public ResponseEntity<Void> callDoSleep(@PathVariable Long sleepMillisecs, @PathVariable Integer reStatusCode) throws Throwable;


}
