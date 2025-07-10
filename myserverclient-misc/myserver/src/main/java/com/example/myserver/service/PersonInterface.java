package com.example.myserver.service;

import java.util.Optional;

import com.example.myserver.service.PersonService.PersonPojo;

public interface PersonInterface {

	public Optional<PersonPojo> getById(Integer id);

}
