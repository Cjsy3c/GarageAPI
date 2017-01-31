package com.cjsy3c.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

	@RequestMapping(value= "/")
	public String sayHello(){
		return "Hello World";
	}
}
