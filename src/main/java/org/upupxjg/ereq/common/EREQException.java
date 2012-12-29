package org.upupxjg.ereq.common;

import org.upupxjg.ereq.util.EREQLogger;

public class EREQException extends Exception {

	private static final String EREQ_SIGN = "EREQ EXCEPTION::";
	
	private static EREQLogger logger = EREQLogger.getLogger(EREQException.class);
	
	public EREQException(String msg){
		super(EREQ_SIGN+msg);
		log(msg);
	}
	
	public EREQException(){
		super(EREQ_SIGN);
		log(null);
	}
	
	public EREQException(String msg,Throwable e){
		super(EREQ_SIGN+msg, e);
		log(msg);
	}
	
	public EREQException(Throwable e){
		super(EREQ_SIGN,e);
		log(null);
	}
	
	private void log(String msg){
		if (logger.isDebugEnabled()) {
			logger.debug(EREQ_SIGN+msg);
		}
	}
}
