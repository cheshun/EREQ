package org.upupxjg.ereq.core.pool;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServlet;

import org.upupxjg.ereq.common.EREQException;

public class ServletPool {


	private static ServletPool pool = new ServletPool();
	
	public static void regeist(String path,HttpServlet servlet){
		pool.innerRegeist(path, servlet);
	}
	
	public static HttpServlet find(String path) throws EREQException{
		return pool.innerFind(path);
	}
	
	private Map<String, HttpServlet> servletMap;
	

	private void innerRegeist(String path, HttpServlet servlet) {
		servletMap.put(path, servlet);
	}
	private HttpServlet innerFind(String path) throws EREQException{
		HttpServlet servlet =  servletMap.get(path);
		if (null == servlet) {
			throw new EREQException("Can not find servlet with path: "+path);
		}
		return servlet;
	}
	private ServletPool(){
		servletMap = new HashMap<String, HttpServlet>();
	}
}
