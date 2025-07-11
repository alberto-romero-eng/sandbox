package com.example.myclient.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.myclient.feignclient.myserver.MyServerClient;
import com.example.myclient.util.CustomThreadFactory;
import com.example.myclient.util.ExecTimeSecsHelper.ETSH;
import com.example.myclient.util.SleepHelper;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Service
public class FeignCircuitBreakerTestManager {

	Logger log = LoggerFactory.getLogger(FeignCircuitBreakerTestManager.class);

	@Autowired
	private MyServerClient pmClient;


	/* public ResponseEntity<TestResponseSinglePojo> callProductmanagementDoSleep(Long sleepSecs) {
		log.info("Start callProductmanagementDoSleep() -- params -- sleepSecs: {}", sleepSecs);
		ETSH etsh = ETSH.init();
		ResponseEntity<TestResponseSinglePojo> re;
		ExecutorService es = Executors.newFixedThreadPool(1, new CustomThreadFactory("productmanagementClientThread"));
		Future<String> future = es.submit( () -> {
			ResponseEntity<Void> pmRe = null;
			String result = null;
			try {
				pmRe = pmClient.callDoSleep(sleepSecs);
				result = "SUCCESS -- " + pmRe.getStatusCode();
			} catch (Throwable e) {
				result = "ERROR -- " + e.getClass() + " -- " + e.getMessage() + " -- " + e.getCause().getClass();
				log.error("error -- ", e);
			}
			return result;
		});
		// await job termination
		String result = null;
		try {
			result = future.get();
		} catch (Throwable e) {
			log.error("error -- ", e);
		}
		re = ResponseEntity.status(HttpStatus.OK).body(new TestResponseSinglePojo(result));
		log.info("Finish callProductmanagementDoSleep() -- params -- sleepSecs: {} -- results -- re: {} ; execTimeSecs: {}", sleepSecs, re, etsh.get());
		return re;
	} */


	public ResponseEntity<TestResponsePojo> callProductmanagementDoSleep(Integer parallelThreads, Integer iterations, Long sleepMillisecs, Integer reStatusCode) {
		log.info("Start callProductmanagementDoSleep() -- params -- parallelThreads: {} ; iterations: {} ; sleepMillisecs: {} ; reStatusCode: {}", parallelThreads, iterations, sleepMillisecs, reStatusCode);
		ETSH etsh = ETSH.init();
		HttpStatus httpStatus = HttpStatus.valueOf(reStatusCode); // just for validation
		ResponseEntity<TestResponsePojo> re = ResponseEntity.status(HttpStatus.OK).build();
		ExecutorService es = Executors.newFixedThreadPool(parallelThreads, new CustomThreadFactory("productmanagementClientThread"));
		List<String> syncList = Collections.synchronizedList(new ArrayList<String>());
		int i = 0;
		for (i = 0 ; i < iterations ; i++) {
			final int iAux = i;
			es.submit( () -> {
				ResponseEntity<Void> pmRe = null;
				String result;
				try {
					pmRe = pmClient.callDoSleep(sleepMillisecs, reStatusCode);
					log.info("iteration: {} ; pmRe: {}", iAux, pmRe);
					result = "SUCCESS -- " + pmRe.getStatusCode();
				} catch (Throwable e) {
					result = "ERROR -- " + e.getClass() + " -- " + e.getMessage() + " -- " + e.getCause().getClass();
					log.error("iteration: {} -- error -- ", iAux, e);
				}
				syncList.add(result);
			});
		}
		// await jobs termination
		if (!(es instanceof ThreadPoolExecutor)) {
			log.error("error -- executorService not instance of ThreadPoolExecutor");
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
		ThreadPoolExecutor tpe = (ThreadPoolExecutor) es;
		while (tpe.getActiveCount() > 0) {
			SleepHelper.sleep(250);
		}
		re = ResponseEntity.status(HttpStatus.OK).body(new TestResponsePojo(syncList));
		log.info("Finish callProductmanagementDoSleep() -- params -- parallelThreads: {} ; iterations: {} ; sleepMillisecs: {} ; reStatusCode: {} -- results -- re: {} ; execTimeSecs: {}", parallelThreads, iterations, sleepMillisecs, reStatusCode, re, etsh.get());
		return re;
	}


	public ResponseEntity<TestResponseWrapperPojo> callProductmanagementDoSleep(TestRequestWrapperPojo testRequestWrapper) {
		log.info("Start callProductmanagementDoSleep() -- params -- testRequestWrapper: {}", testRequestWrapper);
		ETSH etsh = ETSH.init();
		ResponseEntity<TestResponsePojo> reAux = null;
		ArrayList<TestResponsePojo> list = new ArrayList<>();
		for (TestRequestPojo test : testRequestWrapper.getTests()) {
			SleepHelper.sleep(test.prevSleepMillisecs);
			log.info("sleep executed -- prevSleepMillisecs: {}", test.prevSleepMillisecs);
			reAux = callProductmanagementDoSleep(test.parallelThreads, test.iterations, test.sleepMillisecs, test.reStatusCode);
			list.add(reAux.getBody());
		}
		ResponseEntity<TestResponseWrapperPojo> re = ResponseEntity.status(HttpStatus.OK).body(new TestResponseWrapperPojo(list));
		log.info("Finish callProductmanagementDoSleep() -- params -- testRequestWrapper: {} -- results -- re: {} ; execTimeSecs: {}", testRequestWrapper, re, etsh.get());
		return re;
	}



	/*
	 * POJOS
	 */


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class TestRequestWrapperPojo {

		private ArrayList<TestRequestPojo> tests = new ArrayList<>();

		public String toString() {
			String out = "{ "
					+ "" + "tests: " + tests
					+ " }"
					;
			return out;
		}

	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class TestRequestPojo {

		private long prevSleepMillisecs = 0;

		private int parallelThreads = 1;

		private int  iterations = 1;

		private long sleepMillisecs = 1000L;

		private int reStatusCode = 200;

		public String toString() {
			String out = "{ "
					+ "" + "prevSleepMillisecs: " + prevSleepMillisecs
					+ ", " + "parallelThreads: " + parallelThreads
					+ ", " + "iterations: " + iterations
					+ ", " + "sleepMillisecs: " + sleepMillisecs
					+ ", " + "reStatusCode: " + reStatusCode
					+ " }"
					;
			return out;
		}

	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class TestResponseWrapperPojo {

		private ArrayList<TestResponsePojo> tests = new ArrayList<>();

		public TestResponseWrapperPojo(List<TestResponsePojo> list) {
			super();
			this.tests = new ArrayList<>();
			for (TestResponsePojo trp : list) {
				this.tests.add(trp);
			}
		}

		public String toString() {
			String out = "{ "
					+ "" + "tests: " + tests
					+ " }"
					;
			return out;
		}

	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class TestResponsePojo {

		private ArrayList<String> results;

		public TestResponsePojo(List<String> list) {
			super();
			this.results = new ArrayList<>();
			for (String r : list) {
				this.results.add(r);
			}
		}

		public String toString() {
			String out = "{ "
					+ "" + "results: " + results
					+ " }"
					;
			return out;
		}

	}


	@NoArgsConstructor
	@AllArgsConstructor
	@Getter
	@Setter
	public static class TestResponseSinglePojo {

		private String result;

		public String toString() {
			String out = "{ "
					+ "" + "result: " + result
					+ " }"
					;
			return out;
		}

	}

}