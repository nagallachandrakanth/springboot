package com.te.sendotp_tomobile.entity;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@Component
@ConfigurationProperties(value = "twilio")
public class TwilioConfiguration {

	private String accountSid;
	private String authToken;
	private String fromNumber;

}
