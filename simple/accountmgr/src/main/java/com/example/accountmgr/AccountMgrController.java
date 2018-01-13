package com.example.accountmgr;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountMgrController {

	@Autowired
	private HttpServletRequest request;
	
    @RequestMapping("/account")
    public String getAccount() throws InterruptedException {
    
    	
    	List<String> list=new ArrayList<String>();
    	
    	list.add(request.getHeader("myname"));  
    	list.add(request.getHeader("user-agent"));
    	list.add(request.getHeader("x-request-id"));
    	list.add(request.getHeader("x-b3-traceid"));
    	list.add(request.getHeader("x-b3-spanid"));
    	list.add(request.getHeader("x-b3-traceid"));
    	list.add(request.getHeader("x-b3-parentspanid"));
    	list.add(request.getHeader("x-b3-sampled"));
    	list.add(request.getHeader("x-request-id"));
    	list.add(request.getHeader("x-b3-flags"));
    	
    	System.out.println(list);

    	
        Thread.sleep(1 + (long)(Math.random()*500));
        if (Math.random() > 0.8) {
            throw new RuntimeException("Failed to find account");
        }
        return "Account details";
    }

}
