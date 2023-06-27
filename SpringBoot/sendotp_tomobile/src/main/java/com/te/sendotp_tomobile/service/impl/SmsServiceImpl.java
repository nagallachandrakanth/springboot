package com.te.sendotp_tomobile.service.impl;

import org.springframework.stereotype.Service;

import com.te.sendotp_tomobile.entity.Sms;
import com.te.sendotp_tomobile.entity.TwilioConfiguration;
import com.te.sendotp_tomobile.service.SmsService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class SmsServiceImpl implements SmsService {

	private final TwilioConfiguration twilioConfiguration;

	@Override
	public void sendSms(Sms sms) {
		Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
		Message message = Message.creator(new PhoneNumber(sms.getPhoneNumber()),
				new PhoneNumber(twilioConfiguration.getFromNumber()), sms.getMessage()).create();

	}

}
