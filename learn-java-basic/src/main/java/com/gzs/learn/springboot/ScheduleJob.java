package com.gzs.learn.springboot;

import java.util.Date;

import org.apache.commons.lang.time.DateFormatUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ScheduleJob {

	@Scheduled(cron = "0/5 * * * * ?")
	public void testJob() {
		log.info("current time:{}", DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:ss"));
	}
}
