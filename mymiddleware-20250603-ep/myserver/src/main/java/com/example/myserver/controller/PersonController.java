package com.example.myserver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.myserver.service.PersonService;
import com.example.myserver.service.PersonService.PersonPojo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/myserver/v1/person")
public class PersonController {

	@Autowired
	private PersonService personService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	@Operation(summary = "Get person by id")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "404", description = "not found") } )
	public ResponseEntity<PersonPojo> get(@PathVariable Integer id){
		return ResponseEntity.of(personService.getById(id));
	}

}
