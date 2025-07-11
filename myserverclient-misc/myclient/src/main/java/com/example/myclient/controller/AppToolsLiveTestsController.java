package com.example.myclient.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.myclient.service.FeignCircuitBreakerTestManager;
import com.example.myclient.service.FeignCircuitBreakerTestManager.TestRequestWrapperPojo;
import com.example.myclient.service.FeignCircuitBreakerTestManager.TestResponsePojo;
import com.example.myclient.service.FeignCircuitBreakerTestManager.TestResponseSinglePojo;
import com.example.myclient.service.FeignCircuitBreakerTestManager.TestResponseWrapperPojo;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/v1/appTools/liveTests")
@Tag(name = "AppToolsLiveTests", description = "Application tools, live tests")
public class AppToolsLiveTestsController {

	Logger log = LoggerFactory.getLogger(AppToolsLiveTestsController.class);

	@Autowired
	FeignCircuitBreakerTestManager feignCbtm;


	/* @GetMapping("/public/callProductmanagementDoSleep/sleepSecs/{sleepSecs}")
	@Operation(summary = "Endpoint intended only for live manual testing.  Call Productmanagement's doSleep endpoint")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "500", description = "internal server error") })
	public ResponseEntity<TestResponseSinglePojo> callProductmanagementDoSleep(@PathVariable Long sleepSecs) {
		ResponseEntity<TestResponseSinglePojo> re = feignCbtm.callProductmanagementDoSleep(sleepSecs);
		return re;
	} */


	@GetMapping("/public/callProductmanagementDoSleep/parallelThreads/{parallelThreads}/iterations/{iterations}/sleepMilisecs{sleepMillisecs}/reStatusCode/{reStatusCode}")
	@Operation(summary = "Endpoint intended only for live manual testing.  Call Productmanagement's doSleep endpoint")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "400", description = "bad request"), @ApiResponse(responseCode = "500", description = "internal server error") })
	public ResponseEntity<TestResponsePojo> callProductmanagementDoSleep(@PathVariable Integer parallelThreads, @PathVariable Integer iterations, @PathVariable Long sleepMillisecs, @PathVariable Integer reStatusCode) {
		ResponseEntity<TestResponsePojo> re = feignCbtm.callProductmanagementDoSleep(parallelThreads, iterations, sleepMillisecs, reStatusCode);
		return re;
	}


	@PostMapping("/public/callProductmanagementDoSleep/tests")
	@Operation(summary = "Endpoint intended only for live manual testing.  Call Productmanagement's doSleep endpoint")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "ok"), @ApiResponse(responseCode = "400", description = "bad request"), @ApiResponse(responseCode = "500", description = "internal server error") })
	public ResponseEntity<TestResponseWrapperPojo> callProductmanagementDoSleep(@RequestBody TestRequestWrapperPojo testRequestWrapper) {
		ResponseEntity<TestResponseWrapperPojo> re = feignCbtm.callProductmanagementDoSleep(testRequestWrapper);
		return re;
	}

}
