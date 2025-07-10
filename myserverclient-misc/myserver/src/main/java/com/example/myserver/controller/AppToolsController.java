package com.example.myserver.controller;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myserver.util.CustomThreadFactory;
import com.example.myserver.util.ExecTimeSecsHelper.ETSH;
import com.example.myserver.util.SleepHelper;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@RestController
@RequestMapping("/v1/appTools")
@Tag(name = "AppTools", description = "Application tools")
public class AppToolsController {

	Logger log = LoggerFactory.getLogger(AppToolsController.class);

	static private final ExecutorService SleepExecutorService = Executors.newFixedThreadPool(1, new CustomThreadFactory("sleepThread"));


	/**
	 * <p>Controller method execution is NOT interrupted if http-client disconnects prematurely.
	 * 
	 * <p>Useful test:
	 * <pre>
	 * time curl -si -X 'GET' 'http://localhost:4000/v1/appTools/public/doSleep/sleepMillisecs/6000/reStatusCode/200' -H 'accept: [star]/[star]' --max-time 3
	 * </pre>
	 * 
	 * @author Alberto Romero
	 * @since 2025-07-10
	 */
	@GetMapping("/public/doSleep/sleepMillisecs/{sleepMillisecs}/reStatusCode/{reStatusCode}")
	@Operation(summary = "Sleep during {sleepMillisecs}, returning {reStatusCode} with zero-length-body. SleepMillisecs examples: 200 (0.2 secs), 1000 (1 sec).  ReStatusCode examples: 200 (ok), 404 (bad request), 500 (internal server error).  Note: Controller method execution is NOT interrupted if http-client disconnects prematurely.")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "400", description = "bad request"), @ApiResponse(responseCode = "500", description = "internal server error") })
	public ResponseEntity<Void> doSleep(@PathVariable Long sleepMillisecs, @PathVariable Integer reStatusCode) throws Throwable {
		log.info("Start doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {}", sleepMillisecs, reStatusCode);
		ETSH etsh = ETSH.init();
		Future<ResponseEntity<Void>> future = SleepExecutorService.submit( () -> { 
			log.info("Start doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {}", sleepMillisecs, reStatusCode);
			HttpStatus httpStatus = HttpStatus.valueOf(reStatusCode);
			ResponseEntity<Void> re = ResponseEntity.status(httpStatus).build();
			SleepHelper.sleepPrintInterval(sleepMillisecs);
			log.info("Finish doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {} -- results -- execTimeSecs: {}", sleepMillisecs, reStatusCode, etsh.get());
			return re;
		});
		ResponseEntity<Void> re = null;
		try {
			re = future.get();
		} catch (ExecutionException ex) {
			log.error("Finish doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {} -- results -- error -- ex: {}, ex.cause: {} -- ", sleepMillisecs, reStatusCode, ex, ex.getCause(), ex);
			throw ex.getCause();
		} catch (InterruptedException ex) {
			log.error("Finish doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {} -- results -- error -- ex: {}, -- ", sleepMillisecs, reStatusCode, ex, ex);
			throw ex;
		}
		log.info("Finish doSleep() -- params -- sleepMillisecs: {} ; reStatusCode: {} -- results -- execTimeSecs: {}", sleepMillisecs, reStatusCode, etsh.get());
		return re;
	}

}
