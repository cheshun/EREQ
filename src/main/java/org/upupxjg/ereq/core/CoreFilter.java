package org.upupxjg.ereq.core;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.upupxjg.ereq.common.EREQException;
import org.upupxjg.ereq.core.pool.ServletPool;
import org.upupxjg.ereq.http.register.Regeister;
import org.upupxjg.ereq.http.register.impl.FilterRegeister;
import org.upupxjg.ereq.http.register.impl.ServletRegeister;
import org.upupxjg.ereq.scanner.Scanner;
import org.upupxjg.ereq.util.EREQLogger;
import org.upupxjg.ereq.util.Properties;

public class CoreFilter implements Filter {

	private static EREQLogger logger = EREQLogger.getLogger(CoreFilter.class);

	private static String GET = "doGet";
	private static String POST = "doPost";
	private static String DELETE = "doDelete";
	private static String HEAD = "doHead";
	private static String OPTIONS = "doOptions";
	private static String PUT = "doPut";
	private static String TRACE = "doTrace";
	private static String SERVICE = "service";

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		if (request instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = HttpServletRequest.class.cast(request);
			String path = httpRequest.getServletPath();
			try {
				HttpServlet servlet = ServletPool.find(path);
				String methodBy = httpRequest.getMethod();
				Method method = null;
				Class clazz = servlet.getClass();
				String methodName = null;
				if (methodBy.equalsIgnoreCase("get")) {
					methodName = GET;
				} else if (methodBy.equalsIgnoreCase("post")) {
					methodName = POST;
				} else if (methodBy.equalsIgnoreCase("delete")) {
					methodName = DELETE;
				} else if (methodBy.equalsIgnoreCase("options")) {
					methodName = OPTIONS;
				} else if (methodBy.equalsIgnoreCase("put")) {
					methodName = PUT;
				} else if (methodBy.equalsIgnoreCase("trace")) {
					methodName = TRACE;
				} else if (methodBy.equalsIgnoreCase("head")) {
					methodName = HEAD;
				} else {
					logger.error("get unkown http method: " + methodBy);
					chain.doFilter(request, response);
					return;
				}
				try {
					method = servlet.getClass().getDeclaredMethod(methodName, HttpServletRequest.class,
							HttpServletResponse.class);
					method.setAccessible(true);
					method.invoke(servlet, request, response);
					logger.info("invoke " + methodName + " success at path: " + path + " call servlet: "
							+ servlet.getClass().getName());

				} catch (Exception e) {
					logger.error("can not call method " + methodName + "with uri: " + path+" use service dispatch");
					servlet.service(request, response);
					return;
				}
			} catch (EREQException e) {
				logger.warn("Can not find the request uri: " + path);
				chain.doFilter(request, response);
			}

		} else {
			logger.warn("received a no http request" + request.getProtocol());
			chain.doFilter(request, response);
		}

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

		Properties.init();
		Scanner scanner = new Scanner();
		scanner.init();
		try {
			scanner.scan(new Regeister[] { new FilterRegeister(), new ServletRegeister() });
		} catch (EREQException e) {
			logger.error("EREQ INIT ERRER!", e);
			System.exit(1);
		}

	}

}
