package com.te.practice.service.impl;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.BeanUtils;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.te.practice.dto.CustomerDto;
import com.te.practice.entity.Customer;
import com.te.practice.entity.TwilioConfiguration;
import com.te.practice.repository.CustomerRepository;
import com.te.practice.service.CustomerService;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
	private final CustomerRepository customerRepository;
	private final JavaMailSender javaMailSender;
	private final TwilioConfiguration twilioConfiguration;

	@Override
	public Optional<Boolean> custRegistration(CustomerDto customerDto) {
		if (customerDto != null) {
			Customer customer = new Customer();
			BeanUtils.copyProperties(customerDto, customer);
			customerRepository.save(customer);
			Twilio.init(twilioConfiguration.getAccountSid(), twilioConfiguration.getAuthToken());
			Message message = Message.creator(new PhoneNumber(customerDto.getPhoneNumber()),
					new PhoneNumber(twilioConfiguration.getFromNumber()), customerDto.getMessage()).create();

			try {
				mailSender(customerDto.getEmailId(), "Registration Successfull", "Your attachment will be in below",
						"C:\\Users\\CHANDRAKANTH\\Downloads\\Core Java Interview Questions & Answers.Pdf.pdf");
			} catch (MessagingException e) {
				e.printStackTrace();
			}
			return Optional.ofNullable(true);
		}
		return Optional.ofNullable(true);

	}

	public void mailSender(String to, String subject, String text) {
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("fg");
		mailMessage.setTo(to);
		mailMessage.setSubject(subject);
		mailMessage.setText(text);
		javaMailSender.send(mailMessage);

	}

	public void mailSender(String to, String subject, String text, String attachment) throws MessagingException {
		MimeMessage mimeMessage = javaMailSender.createMimeMessage();

		MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage, true);
		messageHelper.setFrom("gn");
		messageHelper.setTo(to);
		messageHelper.setSubject(subject);
		messageHelper.setText(text);
		FileSystemResource fileSystemResource = new FileSystemResource(new File(attachment));
		messageHelper.addAttachment(fileSystemResource.getFilename(), fileSystemResource);

		javaMailSender.send(mimeMessage);
	}

	@Override
	public void generateExcelSheet(HttpServletResponse response) {
		List<Customer> customer = customerRepository.findAll();
		XSSFWorkbook workbook = new XSSFWorkbook();
		XSSFSheet sheet = workbook.createSheet("customer info");
		XSSFRow row = sheet.createRow(0);
		row.createCell(0).setCellValue("customerID");
		row.createCell(1).setCellValue("customerName");
		row.createCell(2).setCellValue("products");
		row.createCell(3).setCellValue("emailId");
		row.createCell(4).setCellValue("phoneNumber");
		row.createCell(5).setCellValue("price");
		row.createCell(6).setCellValue("message");
		
		int dataRowValue=1;
		for (Customer customerData : customer) {
			XSSFRow rowData = sheet.createRow(dataRowValue);
			rowData.createCell(0).setCellValue(customerData.getCustomerID());
			rowData.createCell(1).setCellValue(customerData.getCustomerName());
			rowData.createCell(2).setCellValue(customerData.getProducts());
			rowData.createCell(3).setCellValue(customerData.getEmailId());
			rowData.createCell(4).setCellValue(customerData.getPhoneNumber());
			rowData.createCell(5).setCellValue(customerData.getPrice());
			rowData.createCell(6).setCellValue(customerData.getMessage());
			
		}
		
		try {
			ServletOutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			workbook.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

	@Override
	public void saveData(MultipartFile file) {
		
   		
	}

}
