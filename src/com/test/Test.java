package com.test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;

public class Test {

	/**
	 * @param args
	 * @throws ParseException 
	 */
	public static void main(String[] args) throws ParseException {
		String dep_name = "冀 东 油 田";	
		dep_name = dep_name.trim();//去除空格
		System.out.println(dep_name);
		dep_name = dep_name.replace(" ", "");
		System.out.println(dep_name);
			String [] names = new String[]{"油田","勘探开发","油气田"};
			
			for(String name : names){
				dep_name = 	dep_name.replace(name, "");
			}
			System.out.println(dep_name);
		}

}
