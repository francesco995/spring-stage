package com.objectway.stage.service.client;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.core.Response;

import org.apache.cxf.helpers.IOUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.objectway.stage.services.DomainProvider;

public class StageClient {

	public static void main(String[] args) {
		ApplicationContext ac = new ClassPathXmlApplicationContext("classpath:/spring-config/services-client-spring-beans.xml");
		DomainProvider domainClient = ac.getBean(DomainProvider.class);
		Response response = domainClient.comuni();
		try {
			String json = new String(IOUtils.readBytesFromStream((InputStream)response.getEntity()));
			ObjectMapper mapper = new ObjectMapper();
			JsonNode node = mapper.readTree(json);
			System.out.println(node.size());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
