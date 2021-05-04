package com.iu.s1.home;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class IndexController {
	
	@GetMapping("/accessError")
	public String error()throws Exception{
		System.out.println("error 발생");
		return "error/error";
	}
	
	@GetMapping
	public String index()throws Exception{

		return "index";
	}
	
	@GetMapping("/sample")
	public String sample()throws Exception{
		return "sample";
	}


}
