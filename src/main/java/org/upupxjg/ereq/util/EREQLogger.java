package org.upupxjg.ereq.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.Marker;

public class EREQLogger implements Logger{

	private EREQLogger(){};
	private static EREQLogger ereqLogger;
	private  Logger logger;
	
	public static EREQLogger getLogger(Class<?> clazz){
		if (ereqLogger == null) {
			ereqLogger = new EREQLogger();
			ereqLogger.logger = LoggerFactory.getLogger(clazz);
		}
		return ereqLogger;
	}

	@Override
	public void debug(String msg) {
		logger.debug(msg);
		
	}

	@Override
	public void debug(String msg, Object e) {
		logger.debug(msg, e);
		
	}

	@Override
	public void debug(String arg0, Object[] e) {
		logger.debug(arg0, e);
		
	}

	@Override
	public void debug(String msg, Throwable e) {
		logger.debug(msg, e);
		
	}

	@Override
	public void debug(Marker marker, String msg) {
		logger.debug(marker,msg);
		
	}

	@Override
	public void debug(String arg0, Object arg1, Object arg2) {
		logger.debug(arg0,arg1,arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2) {
		
		logger.debug(arg0,arg1,arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object[] arg2) {
		
		logger.debug(arg0,arg1,arg2);
		
	}

	@Override
	public void debug(Marker arg0, String arg1, Throwable arg2) {
		
		
		logger.debug(arg0,arg1,arg2);
	}

	@Override
	public void debug(Marker arg0, String arg1, Object arg2, Object arg3) {
		
		
		logger.debug(arg0,arg1,arg2,arg3);
	}

	@Override
	public void error(String arg0) {
		
		logger.error(arg0);
	}

	@Override
	public void error(String arg0, Object arg1) {
		
		logger.error(arg0,arg1);
		
	}

	@Override
	public void error(String arg0, Object[] arg1) {
		
		
		logger.error(arg0,arg1);
	}

	@Override
	public void error(String arg0, Throwable arg1) {
		
		
		logger.error(arg0,arg1);
	}

	@Override
	public void error(Marker arg0, String arg1) {
		
		
		logger.error(arg0,arg1);
	}

	@Override
	public void error(String arg0, Object arg1, Object arg2) {
		
		
		logger.error(arg0,arg1,arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2) {
		
		logger.error(arg0,arg1,arg2);
		
	}

	@Override
	public void error(Marker arg0, String arg1, Object[] arg2) {
		
		
		logger.error(arg0,arg1,arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Throwable arg2) {
		
		
		logger.error(arg0,arg1,arg2);
	}

	@Override
	public void error(Marker arg0, String arg1, Object arg2, Object arg3) {
		
		
		logger.error(arg0,arg1,arg2);
	}

	@Override
	public String getName() {
		
		return logger.getName();
	}

	@Override
	public void info(String arg0) {
		
		logger.info(arg0);
	}

	@Override
	public void info(String arg0, Object arg1) {
		
		logger.info(arg0,arg1);
	}

	@Override
	public void info(String arg0, Object[] arg1) {
		
		
		logger.info(arg0,arg1);
	}

	@Override
	public void info(String arg0, Throwable arg1) {
		
		
		logger.info(arg0,arg1);
	}

	@Override
	public void info(Marker arg0, String arg1) {
		
		
		logger.info(arg0,arg1);
	}

	@Override
	public void info(String arg0, Object arg1, Object arg2) {
		
		
		logger.info(arg0,arg1,arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2) {
		
		
		logger.info(arg0,arg1,arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object[] arg2) {
		
		
		logger.info(arg0,arg1,arg2);
	}

	@Override
	public void info(Marker arg0, String arg1, Object arg2, Object arg3) {
		
		
		logger.info(arg0,arg1,arg2,arg3);
	}

	@Override
	public boolean isDebugEnabled() {
		
		return logger.isDebugEnabled();
	}

	@Override
	public boolean isDebugEnabled(Marker arg0) {
		
		return logger.isDebugEnabled(arg0);
	}

	@Override
	public boolean isErrorEnabled() {
		
		return logger.isErrorEnabled();
	}

	@Override
	public boolean isErrorEnabled(Marker arg0) {
		
		return logger.isErrorEnabled(arg0);
	}

	@Override
	public boolean isInfoEnabled() {
		
		return logger.isInfoEnabled();
	}

	@Override
	public boolean isInfoEnabled(Marker arg0) {
		
		return logger.isInfoEnabled(arg0);
	}

	@Override
	public boolean isTraceEnabled() {
		
		return logger.isTraceEnabled();
	}

	@Override
	public boolean isTraceEnabled(Marker arg0) {
		
		return logger.isTraceEnabled(arg0);
	}

	@Override
	public boolean isWarnEnabled() {
		
		return logger.isWarnEnabled();
	}

	@Override
	public boolean isWarnEnabled(Marker arg0) {
		
		return logger.isWarnEnabled(arg0);
	}

	@Override
	public void trace(String arg0) {
		
		logger.trace(arg0);
	}

	@Override
	public void trace(String arg0, Object arg1) {
		
		logger.trace(arg0,arg1);
	}

	@Override
	public void trace(String arg0, Object[] arg1) {
		
		
		logger.trace(arg0,arg1);
	}

	@Override
	public void trace(String arg0, Throwable arg1) {
		
		
		logger.trace(arg0,arg1);
	}

	@Override
	public void trace(Marker arg0, String arg1) {
		
		
		logger.trace(arg0,arg1);
	}

	@Override
	public void trace(String arg0, Object arg1, Object arg2) {
		
		
		logger.trace(arg0,arg1,arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2) {
		
		
		logger.trace(arg0,arg1,arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object[] arg2) {
		
		
		logger.trace(arg0,arg1,arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Throwable arg2) {
		
		
		logger.trace(arg0,arg1,arg2);
	}

	@Override
	public void trace(Marker arg0, String arg1, Object arg2, Object arg3) {
		
		
		logger.trace(arg0,arg1,arg2,arg3);
	}

	@Override
	public void warn(String arg0) {
		
		logger.warn(arg0);
	}

	@Override
	public void warn(String arg0, Object arg1) {
		
		logger.warn(arg0,arg1);
	}

	@Override
	public void warn(String arg0, Object[] arg1) {
		
		
		logger.warn(arg0,arg1);
	}

	@Override
	public void warn(String arg0, Throwable arg1) {
		
		
		logger.warn(arg0,arg1);
	}

	@Override
	public void warn(Marker arg0, String arg1) {
		
		
		logger.warn(arg0,arg1);
	}

	@Override
	public void warn(String arg0, Object arg1, Object arg2) {
		
		
		logger.warn(arg0,arg1,arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2) {
		
		
		logger.warn(arg0,arg1,arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object[] arg2) {
		
		
		logger.warn(arg0,arg1,arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Throwable arg2) {
		
		
		logger.warn(arg0,arg1,arg2);
	}

	@Override
	public void warn(Marker arg0, String arg1, Object arg2, Object arg3) {
		
		
		logger.warn(arg0,arg1,arg2,arg3);
	}

	@Override
	public void info(Marker arg0, String arg1, Throwable arg2) {
		
		logger.info(arg0,arg1,arg2);
	}
	
	
}
