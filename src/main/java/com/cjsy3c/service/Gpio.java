package com.cjsy3c.service;

import org.springframework.stereotype.Service;

import com.pi4j.io.gpio.GpioController;
import com.pi4j.io.gpio.GpioFactory;
import com.pi4j.io.gpio.GpioPinDigitalOutput;
import com.pi4j.io.gpio.PinPullResistance;
import com.pi4j.io.gpio.PinState;
import com.pi4j.io.gpio.RaspiPin;

public class Gpio {
	private static Gpio gpioInstance;
	final private static GpioController gpio = GpioFactory.getInstance();
	private static GpioPinDigitalOutput garage;
	
	private Gpio(){
		garage = gpio.provisionDigitalOutputPin(
				RaspiPin.GPIO_07,// PIN NUMBER
                "Garage",           // PIN FRIENDLY NAME (optional)
                PinState.LOW);      // PIN STARTUP STATE (optional)
		
		garage.setShutdownOptions(true, PinState.LOW, PinPullResistance.OFF);
	}
	
	public static Gpio getInstance(){
		if(gpioInstance == null)
			gpioInstance = new Gpio();
		return gpioInstance;
	}
	
	public void toggle(){
		garage.high();
		try {
			
			Thread.sleep(250);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("thread wait failed");
		}
		garage.low();
	}
	
	public void clearGpio(){
		gpio.shutdown();
	}

}