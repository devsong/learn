package com.gzs.learn.dubbo;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Component;

@Component
public class SayHelloProvider implements SayHello {
	public final String PATTERN = "yyyy-MM-dd HH:mm:ss.SSS";

	@Override
	public String sayHello(String reqMsg) {
		SimpleDateFormat sdf = new SimpleDateFormat(PATTERN);
		String dateSuffix = sdf.format(new Date());
		System.out.println("recv client msg->" + reqMsg + dateSuffix);
		return "from provider hello " + dateSuffix;
	}
}
