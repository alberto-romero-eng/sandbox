package com.example.myclient.service;

import org.springframework.http.ResponseEntity;

import com.example.myclient.service.PersonService.PersonPojo;

public interface PersonInterface {

	public ResponseEntity<PersonPojo> getById(Integer id) throws Throwable;

}
