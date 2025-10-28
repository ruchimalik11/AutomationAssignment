package com.utility;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

import com.constants.Env;
import com.google.gson.Gson;
import com.ui.pojos.Config;
import com.ui.pojos.Environment;

public class JSONUtility {
	
	public static Environment readJson(Env env) {
		
		Gson gson= new Gson();
		File jsonFile= new File(System.getProperty("user.dir") + "/config/config.json");
		FileReader fileReader = null;
		try {
			fileReader = new FileReader(jsonFile);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}//pointer telling where the file is present
		Config config= gson.fromJson(fileReader, Config.class);//responsible for reading the file, we are passing the filereader to Gson and 
		//giving info about which class it has to map it to.(corresponding file for that)
		Environment environment=config.getEnvironment().get("UAT");
		return environment;
	}

	
}
