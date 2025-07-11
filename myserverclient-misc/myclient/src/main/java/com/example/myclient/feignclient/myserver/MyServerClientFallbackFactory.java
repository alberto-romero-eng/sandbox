package com.example.myclient.feignclient.myserver;

import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class MyServerClientFallbackFactory implements FallbackFactory<MyServerClient>{

	@Override
	public MyServerClient create(Throwable cause) {
		return new MyServerClientFallback(cause);
	}

}
