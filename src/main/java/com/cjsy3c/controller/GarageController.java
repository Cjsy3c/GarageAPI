package com.cjsy3c.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.cjsy3c.service.Gpio;


@RestController
public class GarageController {
	public static Gpio gpio;

	@RequestMapping(value="/", method=RequestMethod.GET)
	public String checkGarage(){
		return "Hello World";
	}
	@RequestMapping(value="/", method=RequestMethod.POST)
	public String toggleGarage(){
		gpio = Gpio.getInstance();
		gpio.toggle();
		
		return "Toggled";
	}
	
	@RequestMapping("/quit")
	public String quit(){
		gpio.clearGpio();
		return "Shut down";
	}
}
