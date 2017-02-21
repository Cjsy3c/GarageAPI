package com.cjsy3c.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.auth0.jwt.JWT;
import com.cjsy3c.JwtToken;
import com.cjsy3c.service.Gpio;


@RestController
public class GarageController {
	
	@Autowired
	JwtToken tokenAuth;
	@Autowired
	public static Gpio gpio;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String checkGarage(){
		return "Hello World";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public ResponseEntity<String> toggleGarage(HttpServletRequest request){
		
		try {
			JWT jwt = tokenAuth.verify(request.getHeader("x-auth"));
		} catch (Exception e) {
			// return invalid key
			return new ResponseEntity<String>("Invalid Authentication", HttpStatus.FORBIDDEN);
		}
		
		gpio.toggle();
		
		return new ResponseEntity<String>("Toggled",HttpStatus.OK);
	}
	
	@RequestMapping("/quit")
	public String quit(){
		gpio.clearGpio();
		return "Shut down";
	}
}
