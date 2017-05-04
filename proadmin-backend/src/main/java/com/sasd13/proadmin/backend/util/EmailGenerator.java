package com.sasd13.proadmin.backend.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.Map;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class EmailGenerator {
	
	private JavaMailSender mailSender;

	public void sendEmail(String to, String subject, String template, Map model) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom(new InternetAddress("noreply@proadmin.com", "Proadmin"));
		helper.setTo(to);
		helper.setSubject(subject);
		
		final String result = FreeMarkerTemplateUtils.processTemplateIntoString(getConfigObject(this.getClass()).getTemplate(template), model);
		helper.setText(result, true);
		mailSender.send(message);
	}
	
	public void sendEmail(String to, String subject, String template, Map model, String attachmentName, byte[] attachmentData) throws Exception {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
		
		helper.setFrom(new InternetAddress("noreply@proadmin.com", "Proadmin"));
		helper.setTo(to);
		helper.setSubject(subject);
		
		final String result = FreeMarkerTemplateUtils.processTemplateIntoString(getConfigObject(this.getClass()).getTemplate(template), model);
		helper.setText(result, true);
		helper.addAttachment(attachmentName, new ByteArrayResource(attachmentData));
		mailSender.send(message);
	}
	
	private Configuration getConfigObject(Class cls) throws Exception {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_23);
		
		cfg.setClassForTemplateLoading(cls, "/templates");
		cfg.setDefaultEncoding("UTF-8");
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		return cfg;
	}
	
	public static byte[] getByteArray(String filename) throws Exception {
		FileInputStream fis = null;
		ByteArrayOutputStream bos;
		
		try {
			File file = new File(filename);
			fis = new FileInputStream(file);
			bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			
			for (int readNum; (readNum = fis.read(buf)) != -1; ) {
				bos.write(buf, 0, readNum); //no doubt here is 0
				//Writes len byte from the specified byte array starting at offset off to this byte array output stream
				//LOGGER.debug("read " + readNum + " bytes,");
			}
		} finally {
			if (fis != null) {
				fis.close();
			}
		}
		
		return bos.toByteArray();
	}
}
