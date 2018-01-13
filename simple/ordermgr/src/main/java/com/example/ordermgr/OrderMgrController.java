package com.example.ordermgr;


import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;



@RestController
public class OrderMgrController {

    @Autowired
    private RestTemplate restTemplate;

    private static String accountMgrUrl = System.getProperty("ACCOUNTMGR_URL", "http://api1-tomcat.default.svc.cluster.local:8080");

	@Autowired
	private HttpServletRequest request;
	

    @RequestMapping("/buy")
    public String buy() throws InterruptedException {
    		
    		Thread.sleep(1 + (long)(Math.random()*500));
	        HttpHeaders requestHeaders = new HttpHeaders();
			requestHeaders.set("myname","jasmi");
			requestHeaders.set("user-agent", request.getHeader("user-agent"));
			requestHeaders.set("x-request-id", request.getHeader("x-request-id"));
			requestHeaders.set("x-b3-traceid", request.getHeader("x-b3-traceid"));
			requestHeaders.set("x-b3-spanid", request.getHeader("x-b3-spanid"));
			requestHeaders.set("x-b3-parentspanid", request.getHeader("x-b3-parentspanid"));
			requestHeaders.set("x-b3-sampled", request.getHeader("x-b3-sampled"));
			requestHeaders.set("x-b3-flags", request.getHeader("x-b3-flags"));
			requestHeaders.set("x-ot-span-context", request.getHeader("x-ot-span-context"));
			
			HttpEntity<String> entity = new HttpEntity<String>(requestHeaders);
        	System.out.println("-----");
        	System.out.println(entity.getHeaders());
            ResponseEntity<String> response = restTemplate.postForEntity(accountMgrUrl + "/account", entity, String.class);
            return "BUY + " + response.getBody();
        
    }

    @RequestMapping("/sell")
    public String sell() throws InterruptedException {
        Thread.sleep(1 + (long)(Math.random()*500));
        ResponseEntity<String> response = restTemplate.getForEntity(accountMgrUrl + "/account", String.class);
        return "SELL + " + response.getBody();
    }

}
