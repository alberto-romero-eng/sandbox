package com.example.myclient.feignclient.myserver;

import feign.FeignException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;

import com.example.myclient.service.PersonService.PersonPojo;



public class MyServerClientFallback implements MyServerClient {
	Logger log = LoggerFactory.getLogger(MyServerClientFallback.class);
	private final Throwable cause;

	MyServerClientFallback(Throwable cause) {
		this.cause = cause;
	}


	public ResponseEntity<PersonPojo> getPersonById(@PathVariable Integer id) throws Throwable {
		throw cause;
	}


	/**
	 * <p>This strategy of creating a <i>new errorMessage</i> and throwing a <i>new Exception</i> is specific for this endpoint testing purpose.
	 * 
	 * <p>In general, it is more accurate to re-throw original Throwable.
	 * 
	 * @author Alberto Romero
	 * @since 2024-10-05
	 */
	@Override
	public ResponseEntity<Void> callDoSleep(Long sleepSecs) throws Throwable {
		String errorMessage = null;
		if ((cause instanceof FeignException)) {
			FeignException fe = (FeignException) cause;
			log.error("FALLBACK -- error calling doSleep from productmanagement with sleepSecs {} -- e.getClass(): {} -- e.status(): {} -- e.getMessage() : {} -- e.getCause(): {}", sleepSecs, fe.getClass(), fe.status(), fe.getMessage(), fe.getCause());
			errorMessage = "FALLBACK -- " + fe.status() +  " -- " + fe.getClass() + " -- " + fe.getMessage();
		} else {
			log.error("FALLBACK -- error calling doSleep from productmanagement with sleepSecs {} -- e.getClass(): {} -- e.getMessage() : {} -- e.getCause(): {}", sleepSecs, cause.getClass(), cause.getMessage(), cause.getCause());
			errorMessage = "FALLBACK -- " + cause.getClass() + " -- " + cause.getMessage();
		}
		throw new Exception(errorMessage, null);
	}


	/**
	 * <p>This strategy of creating a <i>new errorMessage</i> and throwing a <i>new Exception</i> is specific for this endpoint testing purpose.
	 * 
	 * <p>In general, it is more accurate to re-throw original Throwable.
	 * 
	 * @author Alberto Romero
	 * @since 2024-10-05
	 */
	@Override
	public ResponseEntity<Void> callDoSleep(Long sleepMillisecs, Integer reStatusCode) throws Throwable {
		String errorMessage = null;
		if ((cause instanceof FeignException)) {
			FeignException fe = (FeignException) cause;
			log.error("FALLBACK -- error calling doSleep from productmanagement with sleepMillisecs {}, reStatusCode: {} -- e.getClass(): {} -- e.status(): {} -- e.getMessage() : {} -- e.getCause(): {}", sleepMillisecs, reStatusCode, fe.getClass(), fe.status(), fe.getMessage(), fe.getCause());
			errorMessage = "FALLBACK -- " + fe.status() +  " -- " + fe.getClass() + " -- " + fe.getMessage();
		} else {
			log.error("FALLBACK -- error calling doSleep from productmanagement with sleepMillisecs {}, reStatusCode: {} -- e.getClass(): {} -- e.getMessage() : {} -- e.getCause(): {}", sleepMillisecs, reStatusCode, cause.getClass(), cause.getMessage(), cause.getCause());
			errorMessage = "FALLBACK -- " + cause.getClass() + " -- " + cause.getMessage();
		}
		throw new Exception(errorMessage, null);
	}

}
