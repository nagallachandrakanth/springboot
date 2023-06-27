package com.te.sendotp_tomobile.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.te.sendotp_tomobile.entity.Sms;
import com.te.sendotp_tomobile.service.SmsService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class SmsController {

	private final SmsService smsService;

	@PostMapping(path = "/sendOtp")
	public ResponseEntity<String> sendOtp(@RequestBody Sms sms) {
		smsService.sendSms(sms);
		return ResponseEntity.ok().body("Otp sent successfull");
	}

}
