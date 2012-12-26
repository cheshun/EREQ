package org.upupxjg.ereq.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;

import org.upupxjg.ereq.util.bo.ScanProperties;

public class Properties {
	private static EREQLogger logger = EREQLogger.getLogger(Properties.class);
	private static ScanProperties scanProperties;
	private static boolean loadAllLazy = false;
	public static void init(){
		
		logger.info("::SYSTEM INIT ::");
		logger.info("load properies file");
		URL classpath = Properties.class.getClassLoader().getResource(Constants.PROPERTIES_FILE);
		if(logger.isDebugEnabled()){
			logger.debug("get classpath:"+classpath.getFile());			
		}
		java.util.Properties propertiesUtil = new java.util.Properties();
		scanProperties = new ScanProperties();
		
		try {
			propertiesUtil.load(new FileReader(new File(classpath.getFile())));
		}  catch (IOException e) {
			logger.info("Can not find configure file:ereq.properies in classpath,load defualt configure");
			return;
		}
		
		String packages = propertiesUtil.getProperty("ereq.scan.package");
		String excepts = propertiesUtil.getProperty("ereq.scan.except");
		String recursion = propertiesUtil.getProperty("ereq.scan.recursion");
		String lazy = propertiesUtil.getProperty("ereq.load.lazy");
		
		if (packages != null) {
			scanProperties.setPackages(packages.split(","));
		}
		if (excepts != null) {
			scanProperties.setExceptPackages(excepts.split(","));
		}
		if(recursion != null){
			scanProperties.setRecursion("true".equals(recursion));
		}
		if(lazy != null){
			loadAllLazy = "true".equals(lazy);
		}
		
		if(logger.isDebugEnabled()){
			logger.debug("Load packages:  "+packages);
			logger.debug("load excepts:  "+excepts);
			logger.debug("load recursion:   "+recursion);
			logger.debug("load loadLazy:   "+lazy);
		}
		
		logger.info("load properties completed");
	}
	
	public static ScanProperties scan(){
		return scanProperties;
	}
	
	public static boolean loadLazy(){
		return loadAllLazy;
	}
}
