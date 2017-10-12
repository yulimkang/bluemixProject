package com.ibmMeeting;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

@SpringBootApplication
public class IbmMeetingApplication extends SpringBootServletInitializer{

	public static void main(String[] args) {
		SpringApplication.run(IbmMeetingApplication.class, args);
	}

//	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
//		return builder.sources(IbmMeetingApplication.class);
//	}
}
