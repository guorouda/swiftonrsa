package com.defonds;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.defonds.service.LinkPayAntharService;

public class RsaEncryptor {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		LinkPayAntharService linkPayAntharService = (LinkPayAntharService) context.getBean("linkPayAntharService");
		linkPayAntharService.dealWithYearData();
	}

}
