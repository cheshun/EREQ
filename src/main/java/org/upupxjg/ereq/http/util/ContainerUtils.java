package org.upupxjg.ereq.http.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;

public class ContainerUtils {
	public static <T> T getSessionObj(HttpServletRequest request,String key,Class<T> clazz){
		Object obj = request.getSession().getAttribute(key);
		return  obj == null?null:clazz.cast(obj);
	}
	
	public static void setSessionObj(HttpServletRequest request,String key,Object obj){
		request.getSession().setAttribute(key, obj);
	}
	
	public static void setApplicationObj(HttpServlet servlet,String key,Object obj){
		servlet.getServletContext().setAttribute(key, obj);
	}
	
	public static <T> T getApplicationObj(HttpServlet servlet,String key,Class<T> clazz){
		Object obj = servlet.getServletContext().getAttribute(key);
		return  obj == null?null:clazz.cast(obj);
	}
	
	public static String  getCookieObj(HttpServletRequest request,String key){
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if(cookie.getName().equals(key)){
				return cookie.getValue();
			}
		}
		
		return null;
	}
	
	public static <T> T getObj(HttpServlet servlet,HttpServletRequest request,String key,Class<T> clazz){
		Object obj = getSessionObj(request, key, clazz);
		if (null == obj) {
			obj = getApplicationObj(servlet, key, clazz);
		}
		
		return  obj == null?null:clazz.cast(obj);
	}
}
