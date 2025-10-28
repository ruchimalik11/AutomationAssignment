package com.utility;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;


public class LoggerUtility {
	
	//create logger for us
	
	//Global configuration/ set-up for logger
	//design pattern- ensures that you were using oops
	//1. POJO
	//2. Page object
	//3.singleton design pattern--class can only have one object creation
	//database connectivity- singleton, logger-ensures that you'll have only 1 object created for this particular class.
	
	private LoggerUtility() {
		
		
		
	}
	public static Logger getLogger(Class<?> clazz) {
		Logger logger= null;
		if (logger==null) {

		logger= LogManager.getLogger(clazz);
		
		}
		return logger;}
	

}
